package models;

public class UPJoin {

    private String username;
    private String projName;
    private Integer projNum;

    public UPJoin() {
    }

    public UPJoin(String username, String projName, Integer projNum) {
        this.username = username;
        this.projName = projName;
        this.projNum = projNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public Integer getProjNum() {
        return projNum;
    }

    public void setProjNum(Integer projNum) {
        this.projNum = projNum;
    }

    @Override
    public String toString() {
        return "UPJoin{" +
                "username='" + username + '\'' +
                ", projName='" + projName + '\'' +
                ", projNum=" + projNum +
                '}';
    }
}
