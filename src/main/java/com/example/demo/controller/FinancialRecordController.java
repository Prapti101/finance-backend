package com.example.demo.controller;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.TransactionType;
import com.example.demo.service.FinancialRecordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    private final FinancialRecordService service;

    public FinancialRecordController(FinancialRecordService service) {
        this.service = service;
    }

    // 🔹 CREATE → ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public FinancialRecord createRecord(@RequestBody FinancialRecord record) {
        return service.addRecord(record);
    }

    // 🔹 GET ALL → ALL ROLES
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    @GetMapping
    public List<FinancialRecord> getAllRecords() {
        return service.getAll();
    }

    // 🔹 UPDATE → ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public FinancialRecord updateRecord(@PathVariable Long id, @RequestBody FinancialRecord record) {
        return service.update(id, record);
    }

    // 🔹 DELETE → ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        service.delete(id);
    }

    // 🔹 FILTER BY TYPE → ALL ROLES
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    @GetMapping("/type")
    public List<FinancialRecord> getByType(@RequestParam TransactionType type) {
        return service.getByType(type);
    }

    // 🔹 FILTER BY CATEGORY → ALL ROLES
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    @GetMapping("/category")
    public List<FinancialRecord> getByCategory(@RequestParam String category) {
        return service.getByCategory(category);
    }

    // 🔹 FILTER BY DATE RANGE → ALL ROLES
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    @GetMapping("/date")
    public List<FinancialRecord> getByDateRange(@RequestParam LocalDate start,
                                               @RequestParam LocalDate end) {
        return service.getByDateRange(start, end);
    }
}