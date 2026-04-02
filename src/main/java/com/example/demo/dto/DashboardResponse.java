package com.example.demo.dto;

import com.example.demo.model.FinancialRecord;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class DashboardResponse {

    private double totalIncome;
    private double totalExpense;
    private double balance;
    private Map<String, Double> categorySummary;
    private Map<String, Double> monthlyTrends;
    private List<FinancialRecord> recent;
}