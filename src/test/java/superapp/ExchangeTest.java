package superapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import superapp.dao.AccountRepository;
import superapp.domain.Account;
import superapp.domain.Transferable;
import superapp.service.TransferService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:base-spring-context.xml"})
@ActiveProfiles("test")
public class ExchangeTest {
    @MockBean
    AccountRepository accountRepository;

    @Autowired
    TransferService transferService;

    @Test
    public void testExchangeSmallerThenAmount() {
        Account usdAccountStub = mock(Account.class);
        Account rubAccountStub = mock(Account.class);
        when(usdAccountStub.getAmount()).thenReturn(100d);

        when(accountRepository.getAccountByAccountNumber("USD")).thenReturn(usdAccountStub);
        when(accountRepository.getAccountByAccountNumber("RUB")).thenReturn(rubAccountStub);

        transferService.exchange("USD", "RUB", 99d);

        verify(usdAccountStub, times(1)).withdraw(99d);
        verify(rubAccountStub, times(1)).deposit(2970d);
    }

    @Test
    public void testAccountPersistenceToDatabase() {

    }

}
