package org.siu.myboot.server.controller;

import org.siu.myboot.componnent.oss.minio.MinioTemplate;
import org.siu.myboot.core.annotation.RestfulApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;


/**
 * @Author Siu
 * @Date 2020/2/15 23:18
 * @Version 0.0.1
 */
@RequestMapping("/minio")
@RestController
public class MinIOController {

    @Autowired
    MinioTemplate minioTemplate;

    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file", required = false) MultipartFile file, HttpServletRequest request) {
        if (file == null || file.getSize() == 0) {
            return "fail";
        }
        String filename = file.getOriginalFilename();
        try {
            InputStream in = file.getInputStream();
            String contentType = file.getContentType();
            minioTemplate.putObject("mybucket", filename, in, contentType);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }


    @GetMapping("/test")
    @RestfulApi
    public String test(@RequestParam List<String> name) {
        return "Hello " + name.get(0);
    }


}
