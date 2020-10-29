package com.mz363.FinanceManager.api;

import com.mz363.FinanceManager.model.Income;
import com.mz363.FinanceManager.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/income")
@RestController
@CrossOrigin
public class IncomeController {
    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    public String addIncome(@RequestBody Income income) {
        incomeService.addIncome(income);
        return "Successfully inserted into DynamoDB table";
    }

    @GetMapping
    public List<Income> getAllIncome() {
        return incomeService.getAllIncome();
    }

    @GetMapping(path = "/type/{type}")
    public List<Income> getIncomeByType(@PathVariable("type") String type) {
        return incomeService.getIncomeByType(type);
    }

    @GetMapping(path = "/{id}/{type}")
    public ResponseEntity<Income> getIncomeById(@PathVariable("id") String id, @PathVariable("type") String type) {
        Income income = incomeService.getIncomeById(id, type);
        return new ResponseEntity<Income>(income, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}/{type}")
    public List<Income> deleteIncomeById(@PathVariable("id") String id, @PathVariable("type") String type) {
        Income income = new Income();
        income.setId(id);
        income.setType(type);
        return incomeService.deleteIncomeById(income);
    }
}
