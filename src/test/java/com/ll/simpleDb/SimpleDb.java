package com.ll.simpleDb;

import java.sql.*;

public class SimpleDb {

    private String dbDriver = "com.mysql.jdbc.Driver";

    private String ip;
    private String id;
    private String pw;
    private String dbName;

    private boolean devMode;

    private String url;

    private Sql sql;

    private Connection conn;

    private Statement stmt;

    public SimpleDb(String ip, String id, String pw, String dbName) {
        this.ip = ip;
        this.id = id;
        this.pw = pw;
        this.dbName = dbName;

        this.url = "jdbc:mysql://" + ip + ":3306/" + dbName;

        try{
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(url, id, pw);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }

    public void run(Object... sql){

    }

    public Sql genSql(){
        this.sql = new Sql(stmt);

        return this.sql;
    }

    public void closeConnection(){
        try {
            this.stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            this.conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void startTransaction(){

    }

    public void commit(){

    }

    public void rollback(){

    }
}
