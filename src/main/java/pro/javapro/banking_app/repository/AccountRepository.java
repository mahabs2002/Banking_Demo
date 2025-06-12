package pro.javapro.banking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.javapro.banking_app.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
