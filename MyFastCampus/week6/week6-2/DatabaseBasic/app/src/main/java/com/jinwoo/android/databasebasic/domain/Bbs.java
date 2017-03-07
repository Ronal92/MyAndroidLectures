package com.jinwoo.android.databasebasic.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

// 어노테이션이 테이블을 생성해준다.
@DatabaseTable(tableName = "bbs")
public class Bbs {

    // 어노테이션이 컬럼을 생성해준다.
    @DatabaseField(generatedId = true) // auto_increment
    private int id;

    @DatabaseField
    private String title;

    @DatabaseField
    private String content;

    @DatabaseField
    private Date currentDate;

    Bbs(){
        // 이게 없으면 ormlite가 동작하지 않는다.
    }

    public Bbs(String title, String content, Date currentDate){
        this.title = title;
        this.content = content;
        this.currentDate = currentDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
