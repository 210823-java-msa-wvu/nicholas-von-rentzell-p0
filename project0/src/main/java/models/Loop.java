package models;

public class Loop {

    private Integer id;
    private String loopName;
    private Boolean stdSub;
    private Boolean prmSub;

    public Loop() {
    }

    public Loop(String loopName, Boolean stdSub, Boolean prmSub) {
        this.loopName = loopName;
        this.stdSub = stdSub;
        this.prmSub = prmSub;
    }

    public Loop(Integer id, String loopName, Boolean stdSub, Boolean prmSub) {
        this.id = id;
        this.loopName = loopName;
        this.stdSub = stdSub;
        this.prmSub = prmSub;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoopName() {
        return loopName;
    }

    public void setLoopName(String loopName) {
        this.loopName = loopName;
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
        return "Loop{" +
                "id=" + id +
                ", loopName='" + loopName + '\'' +
                ", stdSub=" + stdSub +
                ", prmSub=" + prmSub +
                '}';
    }
}
