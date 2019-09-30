import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryTest {
    private AtomicInteger counter = new AtomicInteger();

    @Test(description = "Nullpointer occurs when Retried test has exception is DataProvider",
            dataProviderClass = DP.class, dataProvider = "dpWithException",
            groups = {"issue"}, retryAnalyzer = RetryImpl.class
    )
    void testExample(String value) {
        counter.getAndIncrement();

        if(counter.get() == 1){
            assert value.isEmpty();
        }
        if(counter.get() == 2){
            assert value.isEmpty();
        }
    }
}
