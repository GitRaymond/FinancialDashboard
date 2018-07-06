package com.raymond.financialdashboard.cucumber.stepdefs;

import com.raymond.financialdashboard.FinancialdashboardApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = FinancialdashboardApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
