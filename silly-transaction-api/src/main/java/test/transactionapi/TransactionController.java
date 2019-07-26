package test.transactionapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TransactionController {
    @Autowired
    private TransactionRepository repo;

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        log.info("returning 5 transactions...");
        return repo.retrieveTransactionsSince(0L);
    }
}
