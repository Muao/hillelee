package enteties;

public class UserDataFromLoginJSP {

    private String login;
    private String pwd;
    private String submit;

    public UserDataFromLoginJSP() {
    }

    public String getLogin() {
        return login;
    }

    public String getPwd() {
        return pwd;
    }

    public String getSubmit() {
        return submit;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }
}
