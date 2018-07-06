package com.raymond.financialdashboard.domain.seeder;

import com.raymond.financialdashboard.domain.seeder.partial.BankAccountSeeder;
import com.raymond.financialdashboard.domain.seeder.partial.IngSeeder;
import com.raymond.financialdashboard.domain.seeder.partial.VendorSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Seeders {

    @Autowired
    private IngSeeder ingSeeder;

    @Autowired
    private BankAccountSeeder bankAccountSeeder;

    @Autowired
    private VendorSeeder vendorSeeder;


    public void initAll() {
        ingSeeder.init();
        bankAccountSeeder.init();
        vendorSeeder.init();
    }

    public void initIngSeeder() {
        ingSeeder.init();
    }

    public void initBankAccountSeeder() {
        bankAccountSeeder.init();
    }

    public void initVendorSeeder() {
        vendorSeeder.init();
    }


}
