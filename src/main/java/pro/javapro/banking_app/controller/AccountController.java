package pro.javapro.banking_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.javapro.banking_app.dto.AccountDTO;
import pro.javapro.banking_app.model.Deposit;
import pro.javapro.banking_app.service.AccountService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO){
        return  new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id){
        AccountDTO accountDTO=accountService.getAccountById(id);
        return ResponseEntity.ok(accountDTO);
    }
    @PutMapping("/deposit")
    public ResponseEntity<AccountDTO> deposit(@RequestBody Deposit deposit){
        AccountDTO accountDTO=accountService.deposit(deposit);
        return ResponseEntity.ok(accountDTO);
    }

    @PutMapping("/withDraw")
    public ResponseEntity<AccountDTO>withDraw(@RequestBody Deposit deposit){
        AccountDTO accountDTO=accountService.withDraw(deposit);
        return ResponseEntity.ok(accountDTO);
    }
    @GetMapping
    public  ResponseEntity<List<AccountDTO>> getAllAccounts(){
        List<AccountDTO> accounts=accountService.getAllAcount();
      return  ResponseEntity.ok(accounts);
    }

    @PostMapping("/delete")
    public  ResponseEntity<String> deleteAccount(@RequestBody Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
