package com.ashish.cachingApp.services;

import com.ashish.cachingApp.entities.Employee;
import com.ashish.cachingApp.entities.SalaryAccount;

public interface SalaryAccountService {
    void createAccount(Employee employee);

    SalaryAccount incrementBalance(Long accountId);
}
