package com.vityarthi.library.model;
import java.math.BigDecimal; import java.time.LocalDate;
public class Loan {
 private long id; private final long bookId,memberId; private final LocalDate issueDate,dueDate; private LocalDate returnDate; private BigDecimal fine;
 public Loan(long id,long bookId,long memberId,LocalDate issueDate,LocalDate dueDate,LocalDate returnDate,BigDecimal fine){this.id=id;this.bookId=bookId;this.memberId=memberId;this.issueDate=issueDate;this.dueDate=dueDate;this.returnDate=returnDate;this.fine=fine==null?BigDecimal.ZERO:fine;}
 public long getId(){return id;} public long getBookId(){return bookId;} public long getMemberId(){return memberId;} public LocalDate getIssueDate(){return issueDate;} public LocalDate getDueDate(){return dueDate;} public LocalDate getReturnDate(){return returnDate;} public BigDecimal getFine(){return fine;}
 public String toString(){return String.format("%d | book=%d | member=%d | %s -> %s | returned=%s | fine=₹%s",id,bookId,memberId,issueDate,dueDate,returnDate==null?"-":returnDate,fine);}
}