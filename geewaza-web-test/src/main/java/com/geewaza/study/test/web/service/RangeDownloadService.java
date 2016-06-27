package com.geewaza.study.test.web.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by WangHeng on 2016/3/28.
 */
@Component("rangeDownLoadService")
public class RangeDownloadService extends AbstractService {

    private static final String DOWNLOAD_FILE = "/tmp/down.dat";
    @Override
    public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {


        File file = new File(DOWNLOAD_FILE);
        FileInputStream fis = new FileInputStream(file);
        response.reset();

        response.setHeader("Server", "Geewaza");
        response.setHeader("Accept-Ranges", "bytes");

        long point = 0;
        long length = 0;
        length = file.length();

        if (request.getHeader("Range") != null) {
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);//206
            //从RANGE: bytes=2000070- 获取开始的地址
            point = Long.parseLong(request.getHeader("Range").replaceAll("bytes=", "").replaceAll("-", ""));
        }

        response.setHeader("Content-Length", new Long(length - point).toString());
        if (point != 0) {
            response.setHeader("Content-Range", "bytes"
                    + new Long(point).toString() + "-" + new Long(length - 1).toString()
                    + "/" +  new Long(length).toString());
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
        fis.skip(point);
        byte[] b = new byte[1024];
        int i;
        while ( (i = fis.read(b)) != -1) {
            response.getOutputStream().write(b, 0, i);
        }
        fis.close();
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
