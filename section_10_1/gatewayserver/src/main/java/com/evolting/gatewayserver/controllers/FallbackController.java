package com.evolting.gatewayserver.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @RequestMapping("/contact-support")
    public String contactSupport(){
        return "Contact support";
    }
}
