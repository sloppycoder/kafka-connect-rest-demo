package test.transactionapi;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Transaction {
    @NotNull int id;
    @NotNull double amount;
    @NotNull String memo;
}
