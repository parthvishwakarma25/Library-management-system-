package com.vityarthi.library.model;
import com.vityarthi.library.security.Role;
public final class Librarian extends User { public Librarian(long id,String u,String p,String n){super(id,u,p,n,Role.LIBRARIAN);} }