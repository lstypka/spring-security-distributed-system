package pl.lstypka.springSecurityDistributedSystem.client1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Lukasz Stypka on 2015-11-20.
 */
@RestController
public class HomeController {

    @RequestMapping("/time")
    public String hello() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

}
