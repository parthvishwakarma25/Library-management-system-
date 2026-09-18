package com.vityarthi.library.model;
import com.vityarthi.library.security.Role;
public final class Member extends User { private final int priority; public Member(long id,String u,String p,String n,int priority){super(id,u,p,n,Role.MEMBER);this.priority=priority;} public int getPriority(){return priority;} }