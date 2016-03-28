package com.geewaza.study.test.web.main;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by WangHeng on 2016/3/28.
 */
public class RangeDownloadClient {
    private static final String DOWNLOAD_FILE = "/tmp/client.dat";
    private static final String SRC_FILE = "/tmp/down.dat";

    public static void main(String[] args) throws IOException {
        test01();
    }

    private static void test02() throws IOException {
        //创造一个传了一半的文件
        File srcFile = new File(SRC_FILE);
        long length = srcFile.length() / 2;
        File dirFile = new File(DOWNLOAD_FILE);
        dirFile.createNewFile();
        FileInputStream input = new FileInputStream(srcFile);
        RandomAccessFile oSavedFile = new RandomAccessFile(DOWNLOAD_FILE, "rw");
        long count = 0;
        try {
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = input.read(b, 0, 1024)) > 0 && count < length) {
                if (count + nRead < length) {
                    oSavedFile.write(b, 0, nRead);
                    count += nRead;
                } else{
                    oSavedFile.write(b, 0, (int)(length - count));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oSavedFile.close();
            input.close();
        }


    }

    public static void test01() throws IOException {
        String url = "localhost:8080/geewaza/rangeDownloadService.do";
        File file = new File(DOWNLOAD_FILE);

        if (file.exists()) {
            // 先看看是否是完整的，完整，换名字，跳出循环，不完整，继续下载
            long localFileSize = file.length();
            System.out.println("已有文件大小为:" + localFileSize);

            if (localFileSize >0) {
                System.out.println("文件续传");
                down(url, localFileSize, DOWNLOAD_FILE);
            } else {
                System.out.println("文件存在，重新下载");
                down(url, 0, DOWNLOAD_FILE);
            }
        } else {
            try {
                file.createNewFile();
                System.out.println("下载中");
                down(url, 0, DOWNLOAD_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void down(String url, long nPos, String savePathAndFile) throws IOException {
        HttpURLConnection conn = null;
        RandomAccessFile oSavedFile = new RandomAccessFile(savePathAndFile, "rw");
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            InputStream input = conn.getInputStream();
            oSavedFile.seek(nPos);
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = input.read(b, 0, 1024)) > 0) {
                oSavedFile.write(b, 0, nRead);
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oSavedFile.close();
        }
    }

}
