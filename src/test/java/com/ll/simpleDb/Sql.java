package com.ll.simpleDb;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ll.Article;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;

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


    public Sql appendIn(String str, Object[] objects){
        this.sql += str + " ";

        if(objects.length > 0){
            String s = objects[0] instanceof String ? "'" + objects[0] + "' " : objects[0].toString();
            for(int i = 1; i < objects.length; i++){
                if(objects[i] instanceof String){
                    s += ", '" + objects[i] + "'";
                }
                else{
                    s += ", " + objects[i].toString();
                }

            }
            this.sql = sql.replace("?", s);
        }
        return this;
    }

    public Sql appendIn(Object... objects){
        sql += objects[0].toString() + " ";

        if(objects.length > 1){
            String s;
            if(objects[1] instanceof String){
                s = "'" + objects[1] + "'";
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
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Local Date Time 설정
            objectMapper.registerModule(new JavaTimeModule());
            // boolean 값을 필드의 이름에 맞춰서 변경하기 위한 설정
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ResultSet rs = this.stat.executeQuery(sql);

            List<T> list = new ArrayList<>();

            while(rs.next()){
                Map<String, Object> map = new HashMap<>();
                map.put("id",  rs.getLong(1));
                map.put("createdDate", rs.getTimestamp(2).toLocalDateTime());
                map.put("modifiedDate", rs.getTimestamp(3).toLocalDateTime());
                map.put("title", rs.getString(4));
                map.put("body", rs.getString(5));
                map.put("isBlind", rs.getBoolean(6));
                T t = objectMapper.convertValue(map, ignoredTClass);
                list.add(t);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        ObjectMapper objectMapper = new ObjectMapper();
        // Local Date Time 설정
        objectMapper.registerModule(new JavaTimeModule());
        // boolean 값을 필드의 이름에 맞춰서 변경하기 위한 설정
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
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

            return objectMapper.convertValue(map, ignoredTClass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LocalDateTime selectDatetime(){
        try {
            ResultSet rs = this.stat.executeQuery(sql);
            return rs.next() ? rs.getTimestamp(1).toLocalDateTime() : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long selectLong(){
        try {
            ResultSet rs = this.stat.executeQuery(sql);
            return rs.next() ? rs.getLong(1) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String selectString(){
        try {
            ResultSet rs = this.stat.executeQuery(sql);
            return rs.next() ? rs.getString(1) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean selectBoolean(){
        try {
            ResultSet rs = this.stat.executeQuery(sql);
            return rs.next() ? rs.getBoolean(1) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Long> selectLongs(){
        try {
            ResultSet rs = this.stat.executeQuery(sql);

            List<Long> list = new ArrayList<>();

            while(rs.next()){
                long l = rs.getLong(1);
                list.add(l);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
