package com.vityarthi.library.model;
import java.time.LocalDateTime;
public class Reservation {
 private long id; private final long bookId,memberId; private final int priority; private final LocalDateTime reservedAt; private String status;
 public Reservation(long id,long bookId,long memberId,int priority,LocalDateTime at,String status){this.id=id;this.bookId=bookId;this.memberId=memberId;this.priority=priority;this.reservedAt=at;this.status=status;}
 public long getId(){return id;} public long getBookId(){return bookId;} public long getMemberId(){return memberId;} public int getPriority(){return priority;} public LocalDateTime getReservedAt(){return reservedAt;} public String getStatus(){return status;}
 public String toString(){return id+" | book="+bookId+" | member="+memberId+" | priority="+priority+" | "+reservedAt+" | "+status;}
}