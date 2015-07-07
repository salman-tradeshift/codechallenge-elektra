import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestMessageService.class,
        TestRestAPI.class
})
public class JunitTestSuite {
}
