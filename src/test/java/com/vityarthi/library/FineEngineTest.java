package com.vityarthi.library;
import com.vityarthi.library.service.FineEngine;import org.junit.jupiter.api.Test;import java.math.*;import java.time.*;import static org.junit.jupiter.api.Assertions.*;
class FineEngineTest{
 FineEngine f=new FineEngine(0,new BigDecimal("5.00"));
 @Test void earlyIsZero(){assertEquals(new BigDecimal("0.00"),f.calculateFine(LocalDate.of(2026,9,15),LocalDate.of(2026,9,14)));}
 @Test void threeDaysIsFifteen(){assertEquals(new BigDecimal("15.00"),f.calculateFine(LocalDate.of(2026,9,15),LocalDate.of(2026,9,18)));}
 @Test void overdueDays(){assertEquals(4,f.overdueDays(LocalDate.of(2026,9,15),LocalDate.of(2026,9,19)));}
}