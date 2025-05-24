package com.ashish.cachingApp.services.impl;

import com.ashish.cachingApp.entities.Employee;
import com.ashish.cachingApp.entities.SalaryAccount;
import com.ashish.cachingApp.repositories.SalaryAccountRepository;
import com.ashish.cachingApp.services.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SalaryAccountServiceImpl implements SalaryAccountService {

    private final SalaryAccountRepository salaryAccountRepository;

    public SalaryAccountServiceImpl(SalaryAccountRepository salaryAccountRepository) {
        this.salaryAccountRepository = salaryAccountRepository;
    }

    @Override
    public void createAccount(Employee employee) {

//        if(employee.getName().equals("Anuj")) throw new RuntimeException("Anuj is not allowed");

        SalaryAccount salaryAccount = SalaryAccount.builder()
//                .employee(employee)
                .balance(BigDecimal.ZERO)
                .build();

        salaryAccountRepository.save(salaryAccount);
    }

    @Override
    @Transactional
    public SalaryAccount incrementBalance(Long accountId) {

        SalaryAccount salaryAccount = salaryAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        BigDecimal prevBalance = salaryAccount.getBalance();
        BigDecimal newBalance = prevBalance.add(BigDecimal.valueOf(1L));

        salaryAccount.setBalance(newBalance);

        return salaryAccountRepository.save(salaryAccount);
    }
}
