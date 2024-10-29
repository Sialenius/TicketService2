package config;

public class ConfigurationObject {
    private String url ;
    private String login;
    private String password;

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return url + '\n' + login + '\n' + password;
    }
}
