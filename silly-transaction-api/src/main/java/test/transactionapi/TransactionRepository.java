package test.transactionapi;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TransactionRepository {
    private AtomicInteger counter = new AtomicInteger(0);

    public List<Transaction> retrieveTransactionsSince(long millis) {
        Transaction[] randomFive = {
                generateRandomTransaction(),
                generateRandomTransaction(),
                generateRandomTransaction(),
                generateRandomTransaction(),
                generateRandomTransaction()
        };

        return Arrays.asList(randomFive);
    }

    private Transaction generateRandomTransaction() {
        return new Transaction(counter.incrementAndGet(), Math.random() * 100, "purchase");
    }
}
