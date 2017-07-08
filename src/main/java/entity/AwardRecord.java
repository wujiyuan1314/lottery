package main.java.entity;

import java.util.Date;

public class AwardRecord {
    private Integer id;

    private String mobile;

    private Integer awareid;

    private String state;

    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getAwareid() {
        return awareid;
    }

    public void setAwareid(Integer awareid) {
        this.awareid = awareid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}