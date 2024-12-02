package com.ll.simpleDb;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sql {

    String sql = "";

    Statement stat;

    public Sql(Statement stat){
        this.stat = stat;
    }

    public Sql append(Object... objects){
        sql += objects[0].toString() + " ";
        if(objects.length > 1){
            for (int i = 1; i < objects.length; i++){
                sql = sql.replaceFirst("\\?", objects[i] instanceof String ? "'" + objects[i] + "'" : objects[i].toString()) + " ";
            }
        }

        return this;
    }

    public Sql appendIn(Object... objects){
        sql += objects[0].toString() + " ";

        if(objects.length > 1){
            String s;
            if(objects[1] instanceof String){
                s = "'" + objects[1].toString() + "'";
            }
            else {
                s = objects[1].toString();
            }
            for(int i = 2; i < objects.length; i++){
                if(objects[i] instanceof String){
                    s += ", '" + objects[i].toString() + "' ";
                }else {
                    s += ", " + objects[i].toString() + " ";
                }

            }

            sql = sql.replace("?", s);
        }

        return this;
    }

    public long insert(){
        try {
            return (long) this.stat.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long update(){
        try {
            return (long) this.stat.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long delete(){
        try {
            return (long) this.stat.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, Object>> selectRows(){
        try {
            ResultSet rs = this.stat.executeQuery(sql);

            List<Map<String, Object>> list = new ArrayList<>();

            while(rs.next()){
                Map<String, Object> map = new HashMap<>();
                map.put("id",  rs.getLong(1));
                map.put("createdDate", rs.getTimestamp(2).toLocalDateTime());
                map.put("modifiedDate", rs.getTimestamp(3).toLocalDateTime());
                map.put("title", rs.getString(4));
                map.put("body", rs.getString(5));
                map.put("isBlind", rs.getBoolean(6));
                list.add(map);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> selectRows(Class<T> ignoredTClass){

        return null;
    }

    public Map<String, Object> selectRow(){
        try {
            ResultSet rs = this.stat.executeQuery(sql);

            Map<String, Object> map = new HashMap<>();
            while(rs.next()){

                map.put("id",  rs.getLong(1));
                map.put("createdDate", rs.getTimestamp(2).toLocalDateTime());
                map.put("modifiedDate", rs.getTimestamp(3).toLocalDateTime());
                map.put("title", rs.getString(4));
                map.put("body", rs.getString(5));
                map.put("isBlind", rs.getBoolean(6));

            }

            return map;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
