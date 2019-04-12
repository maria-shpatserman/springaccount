import org.springframework.context.support.ClassPathXmlApplicationContext;
import superapp.dao.AccountRepository;
import superapp.domain.CheckingAccount;
import superapp.domain.SavingAccount;
import superapp.service.TransferService;

public class TestApp {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "test,prod"); //-Dspring.profiles.active="test,prod"
        /** ApplicationContext vs BeanFactory: https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#context-introduction-ctx-vs-beanfactory */
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( "spring-context.xml", "test-spring-context.xml")) {
            System.out.println("Context initialized successfully");


            TransferService transferService = (TransferService) context.getBean("transferService");
            transferService.exchange("RUB1234356","USD1234",100);

            AccountRepository accountRepository = context.getBean("accountRepository", AccountRepository.class);
            accountRepository.saveAndFlush(new SavingAccount(0, "correct@mail.ru","JPY1234"));
            accountRepository.saveAndFlush(new CheckingAccount(50,"correct.db@mail.ru","EUR1234",4000));

            //Move to spring-context and Show Diagram...
        }
    }
}
