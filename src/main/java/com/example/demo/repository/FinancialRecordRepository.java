package com.example.demo.repository;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByType(TransactionType type);

    List<FinancialRecord> findByCategory(String category);

    List<FinancialRecord> findByDateBetween(LocalDate start, LocalDate end);

    List<FinancialRecord> findByTypeAndCategory(TransactionType type, String category);
}