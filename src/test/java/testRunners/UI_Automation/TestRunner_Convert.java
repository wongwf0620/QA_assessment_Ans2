package testRunners.UI_Automation;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/UI_Automation.feature"},
    glue = "stepDefinitions",
    tags = "@RunAll",
    plugin = {"pretty", "json:target/cucumber-report.json", "html:target/Login-Function_Report/Login Test.html"}
)
public class TestRunner_Convert {

    // Your TestRunner code here

    public static void generateCucumberReport() {
        File reportOutputDirectory = new File("target/cucumber-reports/html");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-report.json");

        // Set the report title to "Add To Cart"
        Configuration configuration = new Configuration(reportOutputDirectory, "Login Test");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    public static void main(String[] args) {
        io.cucumber.core.cli.Main.main(args); // Run Cucumber tests
        generateCucumberReport(); // Generate Cucumber report after test execution
    }
}
