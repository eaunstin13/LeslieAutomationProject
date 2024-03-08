package runner;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import utils.ReadWriteExcelProperties;
import wdMethods.ProjMethods;

//@RunWith(Cucumber.class)

@CucumberOptions(features = {"src/main/resources/features"}
				,plugin={"html:target/cucumber","pretty:target/pretty"}
				,monochrome=true
				,glue 	  = {"stepDefinitions"}
				,dryRun = false
				,tags = {"@apitest1"}
				,snippets=SnippetType.CAMELCASE
				)

public class TestNgRunner extends ProjMethods{

	private TestNGCucumberRunner testNGCucumberRunner;
    private  ReadWriteExcelProperties readWriteExcelProperties;
    @BeforeClass(alwaysRun = true)
    public void setUpClass(ITestContext context) throws Exception {
    	context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(1);
        context.getCurrentXmlTest().getSuite().setPreserveOrder(false);
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        readWriteExcelProperties =new ReadWriteExcelProperties();

        readWriteExcelProperties.readWriteProperties();
    }

    @Test(dataProvider = "scenarios")
    public void runFeature(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
    	testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
	}

    @DataProvider(name = "scenarios",parallel=true)
    public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }

}
