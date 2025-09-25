package com.example.apiauto.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-report.html");
        sparkReporter.config().setReportName("API Automation Test Report");
        sparkReporter.config().setDocumentTitle("Test Report");
        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public static void flushInstance() {
        if (extent != null) {
            extent.flush();
        }
    }
}
