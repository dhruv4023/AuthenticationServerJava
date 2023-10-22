package com.sunbase.sserver.controller;

import com.sunbase.sserver.models.UserInfo;
import com.sunbase.sserver.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final UserInfoService userInfoService;

    @Autowired
    public MainController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    @GetMapping("/home")
    public String home() {
        UserInfo u=new UserInfo("test@sunbasedata.com","test@sunbasedata.com","Test@123","admin");
        userInfoService.createUserInfo(u);
        return "hello world";
    }
}
