package com.geewaza.study.test.web.solr;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by WangHeng on 2016/3/26.
 */
public class Suggest {

    private static final String WORD_FILE = "/tmp/headwords.dat";


    public static void insertData() throws IOException, BadHanyuPinyinOutputFormatCombination, SolrServerException {
        List<String> lines = FileUtils.readLines(new File(WORD_FILE));
        HttpSolrServer server = new HttpSolrServer("http://localhost:8080/solr/suggest");
        for (String line : lines) {

            String[] lineItems = line.split("\\t");
            String queryWord = lineItems[0];
            int queryCount = Integer.parseInt(lineItems[1]);
//            System.out.println(queryWord + "---" + queryCount);
//            System.out.println(queryWord + "---" +  converterToSpell(queryWord) + "---" +converterToFirstSpell(queryWord));
            String[] fullPinyin = converterToSpell(queryWord).split(",");

            String[] firstSpell = converterToFirstSpell(queryWord).split(",");
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("kw", queryWord);
            for (String pinyin : fullPinyin) {
                doc.addField("pinyin", pinyin);
            }
            for (String abbre : firstSpell) {
                doc.addField("abbre", abbre);
            }
            doc.addField("kwfreq", queryCount);
            server.add(doc);
        }
        server.commit();
    }


    /**
     * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（长沙市长:cssc,zssz,zssc,cssz）
     *
     * @param chines
     *            汉字
     * @return 拼音
     */
    public static String converterToFirstSpell(String chines) throws BadHanyuPinyinOutputFormatCombination {
        StringBuffer pinyinName = new StringBuffer();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    // 取得当前汉字的所有全拼
                    String[] strs = PinyinHelper.toHanyuPinyinStringArray(
                            nameChar[i], defaultFormat);
                    if (strs != null) {
                        for (int j = 0; j < strs.length; j++) {
                            // 取首字母
                            pinyinName.append(strs[j].charAt(0));
                            if (j != strs.length - 1) {
                                pinyinName.append(",");
                            }
                        }
                    }
                    // else {
                    // pinyinName.append(nameChar[i]);
                    // }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(nameChar[i]);
            }
            pinyinName.append(" ");
        }
        // return pinyinName.toString();
        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
    }

    /**
     * 汉字转换位汉语全拼，英文字符不变，特殊字符丢失
     * 支持多音字，生成方式如（重当参:zhongdangcen,zhongdangcan,chongdangcen
     * ,chongdangshen,zhongdangshen,chongdangcan）
     *
     * @param chines
     *            汉字
     * @return 拼音
     */
    public static String converterToSpell(String chines) {
        StringBuffer pinyinName = new StringBuffer();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    // 取得当前汉字的所有全拼
                    String[] strs = PinyinHelper.toHanyuPinyinStringArray(
                            nameChar[i], defaultFormat);
                    if (strs != null) {
                        for (int j = 0; j < strs.length; j++) {
                            pinyinName.append(strs[j]);
                            if (j != strs.length - 1) {
                                pinyinName.append(",");
                            }
                        }
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(nameChar[i]);
            }
            pinyinName.append(" ");
        }
        // return pinyinName.toString();
        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
    }

    /**
     * 去除多音字重复数据
     *
     * @param theStr
     * @return
     */
    private static List<Map<String, Integer>> discountTheChinese(String theStr) {
        // 去除重复拼音后的拼音列表
        List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>();
        // 用于处理每个字的多音字，去掉重复
        Map<String, Integer> onlyOne = null;
        String[] firsts = theStr.split(" ");
        // 读出每个汉字的拼音
        for (String str : firsts) {
            onlyOne = new Hashtable<String, Integer>();
            String[] china = str.split(",");
            // 多音字处理
            for (String s : china) {
                Integer count = onlyOne.get(s);
                if (count == null) {
                    onlyOne.put(s, new Integer(1));
                } else {
                    onlyOne.remove(s);
                    count++;
                    onlyOne.put(s, count);
                }
            }
            mapList.add(onlyOne);
        }
        return mapList;
    }


    /**
     * 解析并组合拼音，对象合并方案(推荐使用)
     *
     * @return
     */
    private static String parseTheChineseByObject(
            List<Map<String, Integer>> list) {
        Map<String, Integer> first = null; // 用于统计每一次,集合组合数据
        // 遍历每一组集合
        for (int i = 0; i < list.size(); i++) {
            // 每一组集合与上一次组合的Map
            Map<String, Integer> temp = new HashMap<String, Integer>();
            // 第一次循环，first为空
            if (first != null) {
                // 取出上次组合与此次集合的字符，并保存
                for (String s : first.keySet()) {
                    for (String s1 : list.get(i).keySet()) {
                        String str = s + s1;
                        temp.put(str, 1);
                    }
                }
                // 清理上一次组合数据
                if (temp != null && temp.size() > 0) {
                    first.clear();
                }
            } else {
                for (String s : list.get(i).keySet()) {
                    String str = s;
                    temp.put(str, 1);
                }
            }
            // 保存组合数据以便下次循环使用
            if (temp != null && temp.size() > 0) {
                first = temp;
            }
        }
        String returnStr = "";
        if (first != null) {
            // 遍历取出组合字符串
            for (String str : first.keySet()) {
                returnStr += (str + ",");
            }
        }
        if (returnStr.length() > 0) {
            returnStr = returnStr.substring(0, returnStr.length() - 1);
        }
        return returnStr;
    }



    /**
     * 多音字拼音全排列
     * @param termArrays 重庆 {{"chong", "zhong"},{"qing"},};
     * @param start 从哪一位开始全排列
     * @return
     */
    public static List<String> getPermutationSentence(List<List<String>> termArrays, int start) {

        if (CollectionUtils.isEmpty(termArrays))
            return Collections.emptyList();

        int size = termArrays.size();
        if (start < 0 || start >= size) {
            return Collections.emptyList();
        }

        if (start == size-1) {
            return termArrays.get(start);
        }

        List<String> strings = termArrays.get(start);

        List<String> permutationSentences = getPermutationSentence(termArrays, start + 1);

        if (CollectionUtils.isEmpty(strings)) {
            return permutationSentences;
        }

        if (CollectionUtils.isEmpty(permutationSentences)) {
            return strings;
        }

        List<String> result = new ArrayList<String>();
        for (String pre : strings) {
            for (String suffix : permutationSentences) {
                result.add(pre+suffix);
            }
        }
        return result;
    }

    private static SolrQuery getSuggestQuery(String prefix, Integer limit) {
        SolrQuery solrQuery = new SolrQuery();
        StringBuilder sb = new StringBuilder();
        sb.append("suggest:").append(prefix).append("*");
        solrQuery.setQuery(sb.toString());
        solrQuery.addField("kw");
        solrQuery.addField("kwfreq");
        solrQuery.addSort("kwfreq", SolrQuery.ORDER.desc);
        solrQuery.setStart(0);
        solrQuery.setRows(limit);
        return solrQuery;
    }


    public static void main(String[] args) throws Exception{
        test03();
        System.out.println("OK!");
        System.exit(0);
    }

    private static void test03() throws SolrServerException {
        String[] prefixs = {
                "wp",
                "tai",
                "lv",
                "你",
                "王牌对",
                "火影",
                "hy",
        };
        HttpSolrServer solr = new HttpSolrServer("http://localhost:8080/solr/suggest");
        for (String prefix : prefixs) {
            SolrQuery solrQuery = getSuggestQuery(prefix, 10);
            QueryResponse response = solr.query(solrQuery);
            SolrDocumentList results = response.getResults();
            for (int i = 0; i < results.size(); ++i) {
                System.out.println(results.get(i));
            }
            System.out.println("---");
        }
    }

    private static void test02() throws IOException, BadHanyuPinyinOutputFormatCombination, SolrServerException {
        insertData();
    }

    private static void test01() {
        String[][] testStrings = {
                {"chong", "zhong"},
                {"qing"},
        };

        List<List<String>> list = new ArrayList<List<String>>();
        for (String[] test : testStrings) {
            List<String> termArray = new ArrayList<String>();
            Collections.addAll(termArray, test);
            list.add(termArray);
        }

        System.out.println(getPermutationSentence(list, 0));

    }

}
