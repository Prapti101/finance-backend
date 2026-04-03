package com.example.demo.service;

import com.example.demo.dto.DashboardResponse;
import com.example.demo.model.FinancialRecord;
import com.example.demo.model.TransactionType;
import com.example.demo.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final FinancialRecordRepository repo;

    public DashboardService(FinancialRecordRepository repo) {
        this.repo = repo;
    }
public DashboardResponse getSummary() {

    List<FinancialRecord> incomeList = repo.findByType(TransactionType.INCOME);
    List<FinancialRecord> expenseList = repo.findByType(TransactionType.EXPENSE);

    double totalIncome = incomeList.stream()
            .mapToDouble(FinancialRecord::getAmount)
            .sum();

    double totalExpense = expenseList.stream()
            .mapToDouble(FinancialRecord::getAmount)
            .sum();

    double netBalance = totalIncome - totalExpense;

    // empty maps & list for now (to match constructor)
    return new DashboardResponse(
            totalIncome,
            totalExpense,
            netBalance,
            java.util.Collections.emptyMap(),
            java.util.Collections.emptyMap(),
            java.util.Collections.emptyList()
    );
}
}