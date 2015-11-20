package pl.lstypka.springSecurityDistributedSystem.client2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lstypka.springSecurityDistributedSystem.config.dto.UserDto;
import pl.lstypka.springSecurityDistributedSystem.config.service.AuthService;

/**
 * Created by Łukasz on 2015-11-20.
 */
@RestController
public class UserController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/user")
    public UserDto getUser() {
        return authService.getLoggedUser();
    }

}
