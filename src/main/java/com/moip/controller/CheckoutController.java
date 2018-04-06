package com.moip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}
