package com.ll.simpleDb;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Sql {

    String sql;



    public Sql append(Object... objects){
        return this;
    }

    public Sql appendIn(Object... objects){
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
