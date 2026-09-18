package com.vityarthi.library.model;
import com.vityarthi.library.security.*;
public abstract class User implements PermissionAware {
 private long id; private final String username,password,name; private final Role role;
 protected User(long id,String username,String password,String name,Role role){this.id=id;this.username=username;this.password=password;this.name=name;this.role=role;}
 public long getId(){return id;} public void setId(long id){this.id=id;} public String getUsername(){return username;} public String getPassword(){return password;} public String getName(){return name;} public Role getRole(){return role;}
 public boolean hasPermission(Permission p){return role.allows(p);}
 public String toString(){return id+" | "+name+" | "+username+" | "+role;}
}