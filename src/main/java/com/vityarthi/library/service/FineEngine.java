package com.vityarthi.library.service;
import java.math.*; import java.time.*; import java.time.temporal.ChronoUnit;
public class FineEngine {
 private final int graceDays; private final BigDecimal dailyFine;
 public FineEngine(int graceDays,BigDecimal dailyFine){this.graceDays=graceDays;this.dailyFine=dailyFine;}
 public BigDecimal calculateFine(LocalDate due,LocalDate returned){long overdue=ChronoUnit.DAYS.between(due,returned)-graceDays;if(overdue<=0)return new BigDecimal("0.00");return dailyFine.multiply(BigDecimal.valueOf(overdue)).setScale(2);}
 public long overdueDays(LocalDate due,LocalDate date){return Math.max(0,ChronoUnit.DAYS.between(due,date)-graceDays);}
}