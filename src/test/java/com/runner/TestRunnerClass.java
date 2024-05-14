package com.runner;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClass;
import com.report.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(tags = ("@LOGIN or @GETSTATEID or @GETCITYID or @ADDRESS or @SEARCHPRODUCT or @CHANGEPROFILEPIC"), 
				snippets = SnippetType.CAMELCASE, dryRun = false, monochrome = true, publish = true, 
				plugin = { "pretty", "json:target\\report.json" }, stepNotifications = true,
				features = "src\\test\\resources", glue = "com.stepdefinition")

public class TestRunnerClass extends BaseClass {

	@AfterClass
	public static void afterClass() throws FileNotFoundException, IOException {
		Reporting.generateJVMReport(getProjectPath() + getPropertyFileValue("jsonPath")); 
	}
}

