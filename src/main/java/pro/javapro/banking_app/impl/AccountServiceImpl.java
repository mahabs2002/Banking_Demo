package pro.javapro.banking_app.impl;

import org.springframework.stereotype.Service;
import pro.javapro.banking_app.Exception.AccountNotFoundException;
import pro.javapro.banking_app.dto.AccountDTO;
import pro.javapro.banking_app.entity.Account;
import pro.javapro.banking_app.mapper.AccountMapper;
import pro.javapro.banking_app.model.Deposit;
import pro.javapro.banking_app.repository.AccountRepository;
import pro.javapro.banking_app.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account= AccountMapper.mapToAccount(accountDTO);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.maptoAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account=accountRepository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("Account id not Found"));
        return AccountMapper.maptoAccountDTO(account);
    }

    @Override
    public AccountDTO deposit(Deposit deposit) {
        Account account=accountRepository.findById(deposit.getId())
                .orElseThrow(()->new AccountNotFoundException("Account id not Found"));
        double total=account.getBalance()+deposit.getAmount();
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.maptoAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO withDraw(Deposit deposit) {
      Account account=accountRepository.findById(deposit.getId())
              .orElseThrow(()->new AccountNotFoundException("Account id not found"));

      if(account.getBalance()<deposit.getAmount()){
          throw new RuntimeException("Insufficient Balance");
      }else{
          double total= account.getBalance()-deposit.getAmount();
          account.setBalance(total);
          Account savAccount=accountRepository.save(account);
          return AccountMapper.maptoAccountDTO(savAccount);
      }
    }

    @Override
    public List<AccountDTO> getAllAcount() {
        List<Account> account = accountRepository.findAll();
        return account.stream().map((accounts) -> AccountMapper.maptoAccountDTO(accounts))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account ac = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Id not found"));

        accountRepository.deleteById(id);
    }


}
