package pro.javapro.banking_app.service;

import pro.javapro.banking_app.dto.AccountDTO;
import pro.javapro.banking_app.model.Deposit;

import java.util.List;


public interface AccountService {

    AccountDTO createAccount (AccountDTO account);
    AccountDTO getAccountById(Long id);
    AccountDTO deposit(Deposit deposit);
    AccountDTO withDraw(Deposit deposit);
    List<AccountDTO> getAllAcount();
    void deleteAccount(Long id);
}
