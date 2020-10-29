package com.mz363.FinanceManager.dao;

import com.mz363.FinanceManager.model.Expense;
import com.mz363.FinanceManager.model.Income;

import java.util.List;
import java.util.Optional;

public interface IncomeDao {
    void insertIncome(Income income);

    List<Income> selectAllIncome();

    List<Income> selectIncomeByType(String type);

    Income selectIncomeById(String id, String type);

    List<Income> deleteIncomeById(Income income);
}
