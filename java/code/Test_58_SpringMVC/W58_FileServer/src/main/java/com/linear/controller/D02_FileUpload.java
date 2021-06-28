package com.linear.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * 58.5、上传文件
 *      支持：commons-fileupload
 *          commons-io
 *
 */
@Controller
@RequestMapping("/upload")
public class D02_FileUpload {

    /**
     * 58.5.2、跨服务器保存文件
     */
    public String test(){
        return "success";
    }
}
