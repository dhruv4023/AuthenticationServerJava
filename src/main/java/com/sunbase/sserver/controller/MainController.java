package com.sunbase.sserver.controller;

import com.sunbase.sserver.models.UserInfo;
import com.sunbase.sserver.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class MainController {
    private final UserInfoService userInfoService;

    @Autowired
    public MainController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    @GetMapping("/createAdmin")
    public String createAdmin() {
        UserInfo u = new UserInfo("test@sunbasedata.com", "test@sunbasedata.com", "Test@123", "admin");
        userInfoService.createUserInfo(u);
        return "Admin Created";
    }

    @GetMapping("/")
    public String index() {
        try {
            ClassPathResource resource = new ClassPathResource("static/index.html");
            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "index.html file not found!";
        }

    }
}
