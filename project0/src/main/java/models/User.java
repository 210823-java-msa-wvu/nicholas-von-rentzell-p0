package models;

public class User {

    private Integer id;
    private String username;
    private String password;
    private Boolean stdSub;
    private Boolean prmSub;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password, Boolean stdSub, Boolean prmSub) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.stdSub = stdSub;
        this.prmSub = prmSub;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStdSub() {
        return stdSub;
    }

    public void setStdSub(Boolean stdSub) {
        this.stdSub = stdSub;
    }

    public Boolean getPrmSub() {
        return prmSub;
    }

    public void setPrmSub(Boolean prmSub) {
        this.prmSub = prmSub;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", stdSub=" + stdSub +
                ", prmSub=" + prmSub +
                '}';
    }
}
