import org.testng.annotations.DataProvider;

import java.util.concurrent.atomic.AtomicInteger;

public class DP {
    private static AtomicInteger counter = new AtomicInteger();

    @DataProvider
    public static Object[][] dpWithException() {
        return new Object[][]{
            {foo()},
        };
    }

    private static String foo(){
        counter.getAndIncrement();

        if(counter.get() == 1){
            return "First";
        }
        if(counter.get() == 2){
            return "Second";
        }
        throw new RuntimeException("TestNG doesn't handle an exception");
    }
}
