package com.jinwoo.android.databasebasic.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by pc on 2/14/2017.
 */

@DatabaseTable(tableName = "memo")
public class Memo {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String content;

    @DatabaseField
    private Date currentDate;

    public Memo() {
        // 이게 없으면 ormlite가 동작하지 않는다
    }


    public Memo(String content){
        this.content = content;
        this.currentDate = new Date(System.currentTimeMillis()); // 날짜를 기본으로 생성
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }




}
