package pl.lstypka.springSecurityDistributedSystem.config.dto;

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