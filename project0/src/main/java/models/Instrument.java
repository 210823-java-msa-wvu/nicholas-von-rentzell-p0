package models;

public class Instrument {

    private Integer id;
    private String instName;
    private Boolean stdSub;
    private Boolean prmSub;

    public Instrument() {
    }

    public Instrument(Integer id, String instName, Boolean stdSub, Boolean prmSub) {
        this.id = id;
        this.instName = instName;
        this.stdSub = stdSub;
        this.prmSub = prmSub;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
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
        return "Instrument{" +
                "id=" + id +
                ", instName='" + instName + '\'' +
                ", stdSub=" + stdSub +
                ", prmSub=" + prmSub +
                '}';
    }
}
