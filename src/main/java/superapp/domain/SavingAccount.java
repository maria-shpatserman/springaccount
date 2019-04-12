package superapp.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
public class SavingAccount extends Account implements Transferable {

public  SavingAccount(){

}
    public SavingAccount( double amount,String  email,String accountNumber) {
        super(amount, email, accountNumber);
    }



    @Override //Template method
    public void withdraw(double amount) {
        if (validate(amount)) //Guard clauses
            throw new IllegalStateException("Not enough funds to withdraw");

        this.amount -= amount;
    }

    @Override
    public void deposit(double amount) {
        this.amount += amount;
    }

    //step
    protected boolean validate(double amount) {
        return amount > this.amount;
    }
}
