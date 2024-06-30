package com.BidWheels.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "Welcome To Shipping Bids project ";
    }

    @GetMapping("/auth/code")
    public String token(@RequestParam("code") String code,@RequestParam("flag") String flag) {
        GithubTokenAuthentication githubTokenAuthentication = new GithubTokenAuthentication();
        System.out.println("code " + code);
        System.out.println("flag " + flag);
        return githubTokenAuthentication.generateToken(code);
    }
}
