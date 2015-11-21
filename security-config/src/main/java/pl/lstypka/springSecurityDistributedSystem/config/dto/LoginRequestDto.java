package pl.lstypka.springSecurityDistributedSystem.config.dto;

/**
 * Created by Lukasz Stypka on 2015-11-20.
 */
public class LoginRequestDto {

    private String user;
    private String password;

    public LoginRequestDto() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}