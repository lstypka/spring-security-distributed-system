package pl.lstypka.springSecurityDistributedSystem.config.dto;

/**
 * Created by Lukasz Stypka on 2015-11-20.
 */
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserDto {

    private final Long userNo;
    private final String username;
    private final List<String> roles;

    @JsonCreator
    public UserDto(@JsonProperty("userNo") Long userNo, @JsonProperty("username") String username, @JsonProperty("roles") List<String> roles) {
        this.userNo = userNo;
        this.username = username;
        this.roles = roles;
    }

    public Long getUserNo() {
        return userNo;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }
}