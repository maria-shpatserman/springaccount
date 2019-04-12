package superapp.dao;


import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import superapp.domain.Account;
import superapp.domain.Transferable;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public interface AccountRepository extends JpaRepository<Account, Long> {
//    void saveAccount(Transferable from);

    Account getAccountByAccountNumber(String accountNumber);
}
