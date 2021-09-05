package com.springsecurity.spsecuriy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginTemplateController {
@GetMapping("login")
    public String getLoginView() {
        return "login";
    }
}
