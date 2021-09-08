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


}
