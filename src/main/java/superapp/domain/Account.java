package superapp.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Inheritance
@DiscriminatorColumn(name="ACCOUNT_TYPE")
public abstract class Account implements Transferable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;
    protected double amount;
    @Email @Size(max = 50) private String email;

    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public Account() {
    }

    public Account(double amount, String email,String accountNumber) {
        this.amount = amount;
        this.email = email;
        this.accountNumber = accountNumber;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }
}
