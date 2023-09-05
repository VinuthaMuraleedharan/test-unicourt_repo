package com.unicourt.api.utils;



import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;

import cucumber.api.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import static org.testng.AssertJUnit.assertTrue;
@KarateOptions(features= {
		  "classpath:com/unicourt/api/search/caseSearch.feature"
		})

public class Runner {
	@Test
	public void testOAuth() {
		System.setProperty("karate.env","qa");
		String karateOutputPath = "target/cucumber-html-reports";
		KarateStats stats = CucumberRunner.parallel(getClass(), 10, karateOutputPath);
		generateReport(karateOutputPath);
		assertTrue("there are scenario failures ", stats.getFailCount() > 0);
	}

	private static void generateReport(String karateOutputPath) {
		Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] { "json" }, true);
		List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
		for (File file : jsonFiles) {
			jsonPaths.add(file.getAbsolutePath());
		}
		Configuration config = new Configuration(new File("target"), "Unicourt");
		config.addClassifications("Environment", System.getProperty("karate.env", "qa"));
		ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
		reportBuilder.generateReports();
	}

}


