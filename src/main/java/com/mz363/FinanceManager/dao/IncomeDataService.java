package com.mz363.FinanceManager.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.mz363.FinanceManager.model.Expense;
import com.mz363.FinanceManager.model.Income;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import java.util.stream.Collectors;

@Repository("dynamoDbIncomeDao")
public class IncomeDataService implements IncomeDao {

    private int add_id = 0;
    @Autowired
    private DynamoDBMapper mapper;
//    Debug Logs
//    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbExpenseDataService.class);

    public void insertIncome(Income income) {
        List<Income> allIncome = selectAllIncome();
        add_id = 1;
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < allIncome.size(); i++) {
            ids.add(allIncome.get(i).getId());
        }

        while(true) {
            if(ids.indexOf(Integer.toString(add_id)) == -1) {
                income.setId(Integer.toString(add_id));
                mapper.save(income);
                break;
            }
            add_id += 1;
        }
//        Degug Logs
//        LOGGER.error("invalid data - " + add_id);
//        LOGGER.error("invalid data - " + ids);

    }

    public List<Income> selectAllIncome() {
        List<Income> allIncome = mapper.scan(Income.class, new DynamoDBScanExpression());
        allIncome = allIncome.stream()
                .sorted(Comparator.comparing(Income::getDate))
                .collect(Collectors.toList());
        return allIncome;
    }

    public List<Income> selectIncomeByType(String type) {
        List<Income> DB_Income = selectAllIncome();
        List<Income> DB_Income_Type = new ArrayList<>();
        for (Income income : DB_Income) {
            if (income.getType().equals(type)) {
                DB_Income_Type.add(income);
            }
        }
        return DB_Income_Type;
    }

    public Income selectIncomeById(String id, String type) {
        return mapper.load(Income.class, id, type);
    }

    public List<Income> deleteIncomeById(Income income) {
        mapper.delete(income);
        return selectAllIncome();
    }

}
