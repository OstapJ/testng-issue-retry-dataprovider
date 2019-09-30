import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryImpl implements IRetryAnalyzer {
    private static final Logger LOG = LoggerFactory.getLogger(RetryImpl.class);
    private final Map<Integer, AtomicInteger> counts = new ConcurrentHashMap<>();

    private AtomicInteger getCount(ITestResult result) {
        int id = Arrays.hashCode((result.getTestClass().getName() + result.getName() + result.getMethod().getParameterInvocationCount()).getBytes());

        AtomicInteger count = counts.get(id);
        if (count == null) {
            count = new AtomicInteger(3);
            counts.put(id, count);
        }
        return count;
    }

    @Override
    public boolean retry(ITestResult result) {
        int retriesRemaining = getCount(result).getAndDecrement();
        String params = Arrays.toString(result.getParameters()).isEmpty() ? "No parameters" : Arrays.toString(result.getParameters());

        LOG.debug("The test '{}' with params '{}'. Retries left is {}. Status is '{}'",
                result.getMethod().getMethodName(),
                params, retriesRemaining, result.getStatus());

        return retriesRemaining > 0;

    }
}
