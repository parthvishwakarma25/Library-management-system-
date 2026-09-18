package com.vityarthi.library.ui;
import com.vityarthi.library.model.*;import com.vityarthi.library.security.*;import com.vityarthi.library.service.*;import java.time.*;import java.util.*;
public class ConsoleUI {
 private final Scanner in=new Scanner(System.in);private final AuthenticationService auth;private final LibraryService lib;private final ReportService reports;
 public ConsoleUI(AuthenticationService a,LibraryService l,ReportService r){auth=a;lib=l;reports=r;}
 public void start(){System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===");while(true){String u=ask("Username (blank exits): ");if(u.isBlank())return;String p=ask("Password: ");try{User user=auth.login(u,p);menu(user);}catch(Exception e){System.out.println("ERROR: "+e.getMessage());}}}
 private void menu(User u){while(true){System.out.println("\n["+u.getRole()+"] 1 Catalogue 5 Issue 6 Return 7 Reserve 8 Queue 9 Reports 0 Logout");if(u.hasPermission(Permission.MANAGE_CATALOGUE))System.out.println("2 Add Book");String c=ask("Choose: ");try{switch(c){case"1"->lib.catalogue().forEach(System.out::println);case"2"->add(u);case"5"->issue(u);case"6"->ret(u);case"7"->reserve(u);case"8"->queue();case"9"->{if(!u.hasPermission(Permission.VIEW_REPORTS))throw new SecurityException("Reports restricted.");reports.printAll(LocalDate.now());}case"0"-> {return;}default->System.out.println("Invalid choice.");}}catch(Exception e){System.out.println("ERROR: "+e.getMessage());}}}
 private void add(User u){String isbn=ask("ISBN: "),title=ask("Title: "),author=ask("Author: ");int y=Integer.parseInt(ask("Year: ")),n=Integer.parseInt(ask("Copies: "));System.out.println("Added book ID "+lib.addBook(u,new Book(0,isbn,title,author,y,n,n)));}
 private void issue(User u){long m=u instanceof Member?u.getId():Long.parseLong(ask("Member ID: "));long b=Long.parseLong(ask("Book ID: "));LocalDate d=date();System.out.println("Issued; due date "+lib.issue(u,m,b,d).getDueDate());}
 private void ret(User u){long m=u instanceof Member?u.getId():Long.parseLong(ask("Member ID: "));long b=Long.parseLong(ask("Book ID: "));System.out.println("Returned; fine ₹"+lib.returnBook(u,m,b,date()));}
 private void reserve(User u){if(!(u instanceof Member))throw new IllegalArgumentException("Only members can reserve.");long b=Long.parseLong(ask("Book ID: "));System.out.println("Reserved: "+lib.reserve(u,u.getId(),b));}
 private void queue(){long b=Long.parseLong(ask("Book ID: "));lib.queue(b).forEach(System.out::println);}
 private LocalDate date(){String s=ask("Date YYYY-MM-DD (blank=today): ");return s.isBlank()?LocalDate.now():LocalDate.parse(s);}
 private String ask(String s){System.out.print(s);return in.nextLine().trim();}
}