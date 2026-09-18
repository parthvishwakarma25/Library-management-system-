package com.vityarthi.library.model;
public class Book {
 private long id; private String isbn,title,author; private int publicationYear,totalCopies,availableCopies;
 public Book(long id,String isbn,String title,String author,int year,int total,int available){this.id=id;this.isbn=isbn;this.title=title;this.author=author;publicationYear=year;totalCopies=total;availableCopies=available;}
 public long getId(){return id;} public String getIsbn(){return isbn;} public String getTitle(){return title;} public String getAuthor(){return author;} public int getPublicationYear(){return publicationYear;} public int getTotalCopies(){return totalCopies;} public int getAvailableCopies(){return availableCopies;}
 public String toString(){return String.format("%d | %s | %s | %s | %d | %d/%d",id,isbn,title,author,publicationYear,availableCopies,totalCopies);}
}