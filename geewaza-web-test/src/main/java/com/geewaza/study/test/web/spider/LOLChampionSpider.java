package com.geewaza.study.test.web.spider;

import com.geewaza.study.commons.spider.AbstractSpider;
import com.geewaza.study.commons.spider.SpiderUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangHeng on 2016/2/2.
 */
@Component("lolChampionSpider")
public class LOLChampionSpider extends AbstractSpider {

	private static final String SRC_DATA_URL = "http://lol.qq.com/biz/hero/champion.js";
	private static final String HERO_VIDEO_URL = "http://lol.qq.com/web201310/js/herovideo.js";
	private static final String IMG_PRIFIX = "http://ossweb-img.qq.com/images/lol/img/champion/";
	private static final String SKIN_PRIFIX = "http://ossweb-img.qq.com/images/lol/web201310/skin/big";
	private static final String CHAMPION_JS_PRIFIX = "http://lol.qq.com/biz/hero/";

	public List<LOLChampion> pullOfficialChampionData() throws Exception {
		List<LOLChampion> result = new ArrayList<LOLChampion>();
		JSONObject championInfoJSON = getJSJsonData(SRC_DATA_URL, "champion");
		JSONObject championExtInfoJSON = getJSJsonData(HERO_VIDEO_URL, "otherthings");
		JSONObject championKeys = championInfoJSON.getJSONObject("keys");
		JSONObject championDataJSON = championInfoJSON.getJSONObject("data");
		JSONObject championExtJSON = championExtInfoJSON.getJSONObject("data");
		for (Object championKey : championKeys.keySet()) {
			LOLChampion lolChampion = new LOLChampion();
			String championId = championKeys.getString((String)championKey);
			JSONObject championExt = championExtJSON.optJSONObject(championId);
			JSONObject championData = championDataJSON.getJSONObject(championId);
			String championName = championData.getString("name");
			String championTitle = championData.getString("title");
			JSONArray tagsArray = championData.getJSONArray("tags");
			String championVideo = null;
			if (championExt != null) {
				championVideo = championExt.getString("vodlink");
			}
			if (tagsArray != null && tagsArray.length() > 0) {
				List<String> tagsList = new ArrayList<String>();
				for (int i = 0; i < tagsArray.length(); i++) {
					String tag = tagsArray.getString(i);
					String tagName = parseChampionTag(tag);
					tagsList.add(tagName);
				}
				lolChampion.setTags(tagsList);
			} else {
				lolChampion.setTags(new ArrayList<String>());
			}
			JSONObject imageJSON = championData.getJSONObject("image");
			String image = IMG_PRIFIX + imageJSON.getString("full");

			JSONObject chiampionJSJSON = getJSJsonData(CHAMPION_JS_PRIFIX + championId + ".js", "LOLherojs.champion."+ championId);
			System.out.println(chiampionJSJSON);
			JSONObject chiampionData = chiampionJSJSON.optJSONObject("data");
			JSONArray skins = chiampionData.optJSONArray("skins");
			JSONObject skinItem = skins.getJSONObject(0);
			String defaltSkinId = skinItem.getString("id");

			lolChampion.setId(championId);
			lolChampion.setKey((String)championKey);
			lolChampion.setName(championName);
			lolChampion.setTitle(championTitle);
			lolChampion.setVideo(championVideo);
			lolChampion.setImage(image);

			lolChampion.setSkin(SKIN_PRIFIX + defaltSkinId + ".jpg");

			result.add(lolChampion);
		}
		return result;
	}

	private JSONObject getJSJsonData(String url, String paramPrefix) throws Exception {
		System.out.println(paramPrefix + "---" + url);
		SpiderUtil.HttpRes httpRes = spiderUtil.getRes(url, false);
		String responseBody = httpRes.getSource();
		if (StringUtils.isBlank(responseBody)) {
			return null;
		}
		String[] responseDataArray = responseBody.split("};");
		for (String item : responseDataArray) {
			item += "}";
			System.out.println(item);
			System.out.println(item.contains(paramPrefix));
			if (item.contains(paramPrefix)) {
				String jsonStr = item.substring(item.indexOf("=") + 1);
				System.out.println(jsonStr);
				JSONObject responseDataJSON = new JSONObject(jsonStr);
				return responseDataJSON;
			}
			System.out.println("-----------");
		}
		return null;
	}


	public static String parseChampionTag(String tag) {
		if ("Fighter".equalsIgnoreCase(tag)) {
			return "战士";
		} else if ("Mage".equalsIgnoreCase(tag)){
			return "法师";
		} else if ("Assassin".equalsIgnoreCase(tag)) {
			return "刺客";
		} else if ("Tank".equalsIgnoreCase(tag)) {
			return "坦克";
		} else if ("Marksman".equalsIgnoreCase(tag)) {
			return "射手";
		} else if ("Support".equalsIgnoreCase(tag)) {
			return "辅助";
		} else {
			return null;
		}
	}

	public static class LOLChampion {
		private String id;
		private String key;
		private String name;
		private String title;
		private List<String> tags;
		private String video;
		private String image;
		private String skin;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public List<String> getTags() {
			return tags;
		}

		public void setTags(List<String> tags) {
			this.tags = tags;
		}

		public String getVideo() {
			return video;
		}

		public void setVideo(String video) {
			this.video = video;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getSkin() {
			return skin;
		}

		public void setSkin(String skin) {
			this.skin = skin;
		}

		@Override
		public String toString() {
			return "{" +
					"id=" + id +
					", key=" + key +
					", name=" + name +
					", title=" + title +
					", tags=" + tags +
					", video=" + video +
					", image=" + image +
					", skin=" + skin +
					"}";
		}

	}

}
