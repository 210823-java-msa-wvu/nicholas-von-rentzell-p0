package models;

public class Project {

    private Integer id;
    private String projName;
    private Integer projNum;
    private String owner;

    public Project() {}

    public Project(String projName, Integer projNum, String owner) {
        this.projName = projName;
        this.projNum = projNum;
        this.owner = owner;
    }

    public Project(Integer id, String projName, Integer projNum, String owner) {
        this.id = id;
        this.projName = projName;
        this.projNum = projNum;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projName='" + projName + '\'' +
                ", projNum=" + projNum +
                ", owner='" + owner + '\'' +
                '}';
    }
}
