package com.mz363.FinanceManager.service;

import com.mz363.FinanceManager.dao.IncomeDao;
import com.mz363.FinanceManager.model.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {
    private final IncomeDao incomeDao;

    @Autowired
    public IncomeService(@Qualifier("dynamoDbIncomeDao") IncomeDao incomeDao) {
        this.incomeDao = incomeDao;
    }

    public void addIncome(Income income) {
        incomeDao.insertIncome(income);
    }

    public List<Income> getAllIncome() {
        return incomeDao.selectAllIncome();
    }

    public List<Income> getIncomeByType(String type) {
        return incomeDao.selectIncomeByType(type);
    }

    public Income getIncomeById(String id, String type) {
        return incomeDao.selectIncomeById(id, type);
    }

    public List<Income> deleteIncomeById(Income income) {
        return incomeDao.deleteIncomeById(income);
    }

}
