package com.raymond.financialdashboard.domain.seeder.partial;


import com.raymond.financialdashboard.domain.seeder.SeederInterface;
import com.raymond.financialdashboard.service.BankAccountService;
import com.raymond.financialdashboard.service.dto.BankAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankAccountSeeder implements SeederInterface {

    @Autowired
    private BankAccountService bankAccountService;

    @Override
    public void init() {
        BankAccountDTO item = new BankAccountDTO();
        item.setBank("ING");
        item.setGoal("Betalen");
        item.setIban("NL70INGB0003665457");
        item.setName("ING Betalen");

        bankAccountService.save(item);
    }
}
