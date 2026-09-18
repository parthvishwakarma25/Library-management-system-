package com.vityarthi.library.security;
import java.util.*;
public enum Role {
 ADMIN(EnumSet.allOf(Permission.class)),
 LIBRARIAN(EnumSet.of(Permission.MANAGE_CATALOGUE,Permission.ISSUE_BOOK,Permission.RETURN_BOOK,Permission.VIEW_REPORTS,Permission.MANAGE_RESERVATIONS)),
 MEMBER(EnumSet.of(Permission.ISSUE_BOOK,Permission.RETURN_BOOK,Permission.RESERVE_BOOK));
 private final Set<Permission> permissions;
 Role(Set<Permission> p){permissions=p;}
 public boolean allows(Permission p){return permissions.contains(p);}
}