package com.example.demo.service;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.TransactionType;
import com.example.demo.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialRecordService {

    private final FinancialRecordRepository repo;

    public FinancialRecordService(FinancialRecordRepository repo) {
        this.repo = repo;
    }

    // 🔹 CREATE
    public FinancialRecord addRecord(FinancialRecord record) {
        return repo.save(record);
    }

    // 🔹 GET ALL
    public List<FinancialRecord> getAll() {
        return repo.findAll();
    }

    // 🔹 UPDATE
    public FinancialRecord update(Long id, FinancialRecord updated) {
        FinancialRecord r = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        r.setAmount(updated.getAmount());
        r.setType(updated.getType());
        r.setCategory(updated.getCategory());
        r.setDate(updated.getDate());
        r.setNotes(updated.getNotes());

        return repo.save(r);
    }

    // 🔹 DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // 🔹 FILTER BY TYPE
    public List<FinancialRecord> getByType(TransactionType type) {
        return repo.findByType(type);
    }

    // 🔹 FILTER BY CATEGORY
    public List<FinancialRecord> getByCategory(String category) {
        return repo.findByCategory(category);
    }

    // 🔹 FILTER BY DATE RANGE
    public List<FinancialRecord> getByDateRange(LocalDate start, LocalDate end) {
        return repo.findByDateBetween(start, end);
    }
}