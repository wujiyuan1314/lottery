package main.java.entity;

import java.util.Date;

public class QuestionAnswer {
    private Integer id;

    private Integer questionid;

    private String answerdetail;

    private String isright;

    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getAnswerdetail() {
        return answerdetail;
    }

    public void setAnswerdetail(String answerdetail) {
        this.answerdetail = answerdetail == null ? null : answerdetail.trim();
    }

    public String getIsright() {
        return isright;
    }

    public void setIsright(String isright) {
        this.isright = isright == null ? null : isright.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}