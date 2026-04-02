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

    public FinancialRecord create(FinancialRecord record) {
        return repo.save(record);
    }

    public List<FinancialRecord> getAll() {
        return repo.findAll();
    }

    public FinancialRecord update(Long id, FinancialRecord updated) {
        FinancialRecord r = repo.findById(id).orElseThrow();

        r.setAmount(updated.getAmount());
        r.setType(updated.getType());
        r.setCategory(updated.getCategory());
        r.setDate(updated.getDate());
        r.setNotes(updated.getNotes());

        return repo.save(r);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<FinancialRecord> filter(TransactionType type, String category,
                                        LocalDate start, LocalDate end) {

        if (start != null && end != null) {
            return repo.findByDateBetween(start, end);
        }

        if (type != null && category != null) {
            return repo.findByTypeAndCategory(type, category);
        }

        if (type != null) return repo.findByType(type);
        if (category != null) return repo.findByCategory(category);

        return repo.findAll();
    }
}