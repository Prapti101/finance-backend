package com.example.demo.controller;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.TransactionType;
import com.example.demo.service.FinancialRecordService;
import jakarta.validation.Valid;
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

    // CREATE
    @PostMapping
    public FinancialRecord create(@Valid @RequestBody FinancialRecord record) {
        return service.create(record);
    }

    // GET ALL
    @GetMapping
    public List<FinancialRecord> getAll() {
        return service.getAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public FinancialRecord update(@PathVariable Long id,
                                 @RequestBody FinancialRecord record) {
        return service.update(id, record);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }

    // FILTER (FINAL CLEAN API)
    @GetMapping("/filter")
    public List<FinancialRecord> filter(
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        return service.filter(
                type,
                category,
                startDate != null ? LocalDate.parse(startDate) : null,
                endDate != null ? LocalDate.parse(endDate) : null
        );
    }
}