
import org.testng.TestNG;

public class Runner {

    public static void main(String[] args) {
        TestNG testSuite = new TestNG();
        testSuite.setTestClasses(new Class[] { RetryTest.class });
        testSuite.addListener(RetryImpl.class);
        testSuite.setDefaultSuiteName("Retry Suite");
        testSuite.setDefaultTestName("Retry Test");
        testSuite.setOutputDirectory("/testngOutput");
        testSuite.run();
    }
}
