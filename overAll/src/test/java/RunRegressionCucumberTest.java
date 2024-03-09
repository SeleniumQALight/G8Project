import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "bdd/stepDefinitions",
        tags = "@Regression",
        plugin = {"pretty", "html:target/cucumber.html"}
)
public class RunRegressionCucumberTest {
}
