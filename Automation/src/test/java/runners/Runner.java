package runners;

//@RunWith(Cucumber.class)
@io.cucumber.testng.CucumberOptions(  
	    features = "src/test/java/features/hotelSearch.feature",
	    glue="stepdefinition",
	    monochrome = true,
		plugin = { "pretty",
				"html:reports/test-report.html",
				"json:target/cucumber-Runner12.json",
				"timeline:target" }
		
		
		)

public class Runner extends io.cucumber.testng.AbstractTestNGCucumberTests  {

}
