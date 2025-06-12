package pro.javapro.banking_app.mapper;

import pro.javapro.banking_app.dto.AccountDTO;
import pro.javapro.banking_app.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDTO accountDTO){
        Account account=new Account(
                accountDTO.getId(),
                accountDTO.getAccountHolderName(),
                accountDTO.getBalance()
        );
        return  account;
    }


    public static AccountDTO maptoAccountDTO(Account account){
        AccountDTO accountDTO=new AccountDTO(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDTO;
    }
}
