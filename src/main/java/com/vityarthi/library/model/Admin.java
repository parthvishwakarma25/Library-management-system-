package com.vityarthi.library.model;
import com.vityarthi.library.security.Role;
public final class Admin extends User { public Admin(long id,String u,String p,String n){super(id,u,p,n,Role.ADMIN);} }