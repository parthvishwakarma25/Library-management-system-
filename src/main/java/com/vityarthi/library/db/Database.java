package com.vityarthi.library.db;
import java.nio.file.*; import java.sql.*;
public final class Database {
 private static final String URL="jdbc:sqlite:data/library.db"; private Database(){}
 public static Connection connect() throws SQLException { try{Files.createDirectories(Path.of("data"));}catch(Exception e){throw new SQLException(e);} Connection c=DriverManager.getConnection(URL); c.createStatement().execute("PRAGMA foreign_keys=ON"); return c; }
 public static void initialize(){
  try(Connection c=connect(); Statement s=c.createStatement()){
   s.executeUpdate("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT UNIQUE NOT NULL,password TEXT NOT NULL,name TEXT NOT NULL,role TEXT NOT NULL,priority INTEGER NOT NULL DEFAULT 0)");
   s.executeUpdate("CREATE TABLE IF NOT EXISTS books(id INTEGER PRIMARY KEY AUTOINCREMENT,isbn TEXT UNIQUE NOT NULL,title TEXT NOT NULL,author TEXT NOT NULL,publication_year INTEGER NOT NULL,total_copies INTEGER NOT NULL,available_copies INTEGER NOT NULL)");
   s.executeUpdate("CREATE TABLE IF NOT EXISTS loans(id INTEGER PRIMARY KEY AUTOINCREMENT,book_id INTEGER NOT NULL,member_id INTEGER NOT NULL,issue_date TEXT NOT NULL,due_date TEXT NOT NULL,return_date TEXT,fine NUMERIC NOT NULL DEFAULT 0,FOREIGN KEY(book_id) REFERENCES books(id),FOREIGN KEY(member_id) REFERENCES users(id))");
   s.executeUpdate("CREATE TABLE IF NOT EXISTS reservations(id INTEGER PRIMARY KEY AUTOINCREMENT,book_id INTEGER NOT NULL,member_id INTEGER NOT NULL,priority INTEGER NOT NULL,reserved_at TEXT NOT NULL,status TEXT NOT NULL DEFAULT 'WAITING',FOREIGN KEY(book_id) REFERENCES books(id),FOREIGN KEY(member_id) REFERENCES users(id))");
  }catch(SQLException e){throw new IllegalStateException("Database initialization failed: "+e.getMessage(),e);}
 }
}