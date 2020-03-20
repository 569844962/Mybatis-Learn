package com.chen.mybatis.demo3.pojo;

import java.util.Date;
import java.util.StringJoiner;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2020.2.23
 * @company
 */
public class BlogDb {

    private Integer ID;

    private String NAME;

    private Date CREATE_TIME;

    private String BOOK;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getBOOK() {
        return BOOK;
    }

    public void setBOOK(String BOOK) {
        this.BOOK = BOOK;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BlogDb.class.getSimpleName() + "[", "]")
                .add("ID=" + ID)
                .add("NAME='" + NAME + "'")
                .add("CREATE_TIME=" + CREATE_TIME)
                .add("BOOK='" + BOOK + "'")
                .toString();
    }
}
