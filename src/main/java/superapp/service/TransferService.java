package superapp.service;

import superapp.dao.AccountRepository;
import superapp.domain.Account;
import superapp.domain.Transferable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TransferService {
    @Autowired
    private ConvertingService convertingService; //Creator [GRASP]
    @Resource
    private AccountRepository accountRepository;


    public TransferService(ConvertingService convertingService, AccountRepository stubRepository) {
        this.convertingService = convertingService;
        this.accountRepository = stubRepository;
    }

    public double withdraw(String accountNumber, double amount) {
        Account from = accountRepository.getAccountByAccountNumber(accountNumber);
        from.withdraw(amount);
        accountRepository.saveAndFlush(from);

        return from.getAmount();
    }
    public double deposit(String accountNumber, double amount) {
        Account to = accountRepository.getAccountByAccountNumber(accountNumber);
        to.deposit(amount);
        accountRepository.saveAndFlush(to);

        return  to.getAmount();
    }

    public void exchange(String accountNumberFrom, String accountNumberTo, double amountInUSD) {
        withdraw(accountNumberFrom, amountInUSD);
        double amountInRUB = convertingService.convert(amountInUSD);
        deposit(accountNumberTo, amountInRUB);
    }
}
