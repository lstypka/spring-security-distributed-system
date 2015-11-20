package pl.lstypka.springBootSecurityPoc.dto;

public class BoUserAuthDto {

    private String user;
    private String password;

    public BoUserAuthDto() {
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