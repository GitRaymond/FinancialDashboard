package com.raymond.financialdashboard.web.rest;

import com.raymond.financialdashboard.domain.seeder.Seeders;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seeder")
public class SeederResource {

    @Autowired
    private Seeders seeders;

    @GetMapping
    public void seedAll() {
        seeders.initAll();
    }

    @GetMapping("/ing")
    public void seedIng() {
        seeders.initIngSeeder();
    }

    @GetMapping("/vendor")
    public void seedVendor() {
        seeders.initVendorSeeder();
    }

    @GetMapping("/bank-account")
    public void seedBankAccount() {
        seeders.initBankAccountSeeder();
    }
}
