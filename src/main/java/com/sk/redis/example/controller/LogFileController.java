package com.sk.redis.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
@RequestMapping("/logFile")
public class LogFileController {
    @GetMapping("/getLog")
    public String getLog(String fileName,HttpServletResponse response){
        String resultMsg = "";
        if(fileName == null && fileName.length() == 0){
           return "文件名为空";
        }
        String filePath = System.getProperty("user.dir") + File.separator + "logs" + File.separator + fileName;
        File file = new File(filePath);
        if(file.exists()){
            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                resultMsg = "文件[" + fileName + "]下载成功";
            }catch(Exception e){
                resultMsg = "文件[" + fileName + "]下载失败，" + e.getMessage();
            }finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) { }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) { }
                }
            }
        }else{
            resultMsg = "文件[" + fileName + "]文件不存在";
        }
        return resultMsg;
    }
}
