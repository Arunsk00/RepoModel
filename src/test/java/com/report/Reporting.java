package com.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.base.BaseClass;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting extends BaseClass {
	public static void generateJVMReport(String jsonFile) throws FileNotFoundException, IOException {

		// 1. Mention the path of JVM report where to store?
		File file = new File(getProjectPath() + getPropertyFileValue("jvmPath"));

		// 2. Create the object for Configuration class
		Configuration configuration = new Configuration(file, "API Project");

		// 3. Key,Value ---> Browser details, OS details, etc
		configuration.addClassifications("FirstName", "Saral");
		configuration.addClassifications("LastName", "Kumar");
		configuration.addClassifications("Batch", "API 3:30PM");
		configuration.addClassifications("TrainerName", "Sabapathy");

		// 4. Create the object for ReportBuilder class ---> pass the json file to fetch
		// results
		List<String> jsonReport = new ArrayList<String>();
		jsonReport.add(jsonFile);

		ReportBuilder builder = new ReportBuilder(jsonReport, configuration);

		// 5. Generate JVM Report
		builder.generateReports();

	}
}
