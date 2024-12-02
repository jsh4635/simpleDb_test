package com.ll.simpleDb;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Sql {

    String sql = "";

    Statement stat;

    public Sql(String sql){
        this.sql = sql;
    }

    public Sql(Statement stat){
        this.stat = stat;
    }

    public Sql append(Object... objects){
        sql += objects[0].toString();
        if(objects.length > 1){
            for (int i = 1; i < objects.length; i++){
                sql = sql.replaceFirst("\\?", objects[i].toString());
            }
        }

        return this;
    }

    public Sql appendIn(Object... objects){
        sql += objects[0].toString();

        if(objects.length > 1){
            String s = "";
            if(objects[1] instanceof String){
                s = "'" + objects[1].toString() + "'";
            }
            else {
                s = objects[1].toString();
            }
            for(int i = 2; i < objects.length; i++){
                if(objects[i] instanceof String){
                    s += ", '" + objects[i].toString() + "'";
                }else {
                    s += ", " + objects[i].toString();
                }

            }

            sql = sql.replace("?", s);
        }

        return this;
    }

    public long insert(){

        return 0L;
    }

    public long update(){
        return 0L;
    }

    public long delete(){
        return 0L;
    }

    public List<Map<String, Object>> selectRows(){
        return null;
    }

    public <T> List<T> selectRows(Class<T> ignoredTClass){

        return null;
    }

    public Map<String, Object> selectRow(){
        ObjectMapper objectMapper = new ObjectMapper();
        return null;
    }

    public <T> T selectRow(Class<T> ignoredTClass){
        return ignoredTClass.cast(selectRow());
    }

    public LocalDateTime selectDatetime(){
        return null;
    }

    public Long selectLong(){
        return 0L;
    }

    public String selectString(){
        return null;
    }

    public Boolean selectBoolean(){
        return false;
    }

    public List<Long> selectLongs(){
        return null;
    }
}
