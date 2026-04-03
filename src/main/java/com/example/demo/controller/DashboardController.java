package com.example.demo.controller;

import com.example.demo.dto.DashboardResponse;
import com.example.demo.service.DashboardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    // 🔹 DASHBOARD → ADMIN + ANALYST ONLY
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping
    public DashboardResponse getDashboard() {
        return service.getSummary();
    }
}