package com.example.demo.controller;

import com.example.demo.dto.DashboardResponse;
import com.example.demo.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping
    public DashboardResponse getDashboard() {
        return new DashboardResponse(
                service.totalIncome(),
                service.totalExpense(),
                service.balance(),
                service.categorySummary(),
                service.monthlyTrends(),
                service.recent()
        );
    }
}