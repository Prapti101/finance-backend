package com.example.demo.service;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.TransactionType;
import com.example.demo.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final FinancialRecordRepository repo;

    public DashboardService(FinancialRecordRepository repo) {
        this.repo = repo;
    }

    public double totalIncome() {
        return repo.findAll().stream()
                .filter(r -> r.getType() == TransactionType.INCOME)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    public double totalExpense() {
        return repo.findAll().stream()
                .filter(r -> r.getType() == TransactionType.EXPENSE)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    public double balance() {
        return totalIncome() - totalExpense();
    }

    public Map<String, Double> categorySummary() {
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(
                        FinancialRecord::getCategory,
                        Collectors.summingDouble(FinancialRecord::getAmount)
                ));
    }

    public Map<String, Double> monthlyTrends() {
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(
                        r -> r.getDate().getMonth().toString(),
                        Collectors.summingDouble(FinancialRecord::getAmount)
                ));
    }

    public List<FinancialRecord> recent() {
        return repo.findAll().stream()
                .sorted((a,b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .limit(5)
                .toList();
    }
}