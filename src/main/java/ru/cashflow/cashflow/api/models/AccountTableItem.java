package ru.cashflow.cashflow.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.cashflow.cashflow.domain.models.Account;

@Data
@AllArgsConstructor
public class AccountTableItem {

    final private Long id;
    final private String name;
    final private String user;
    final private int balance;

    public static AccountTableItem fromAccount(Account account, int balance){
        return new AccountTableItem(
                        account.getId(),
                        account.getName(),
                        account.getUser().getName(),
                        balance);
    }
}
