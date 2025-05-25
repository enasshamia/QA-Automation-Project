public class RetryLogic {
    public void validateWithRetry(Runnable validation, int maxRetries) {
        int retries = 0;
        while (retries < maxRetries) {
            try {
                validation.run();  // Execute the test
                return;            // If successful, exit
            } catch (AssertionError e) {
                retries++;
                if (retries == maxRetries) throw e;  // Final failure
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                }
                // Wait before retrying
            }
        }
    }
}
//validateWithRetry(() -> productPage.validateProductsDetails(), 3);