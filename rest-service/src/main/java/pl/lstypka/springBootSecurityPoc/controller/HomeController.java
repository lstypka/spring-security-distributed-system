package pl.lstypka.springBootSecurityPoc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Łukasz on 2015-11-20.
 */
@RestController
public class HomeController {

    @RequestMapping("/home")
    public String hello() {
        return "HELLLLOOOOOOOOOOOOOO";
    }

}
