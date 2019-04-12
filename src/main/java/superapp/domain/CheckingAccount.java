package superapp.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Immutable
 */
@Entity
@DiscriminatorValue("C")
public class CheckingAccount extends SavingAccount { //IS-A
    private double overdraft;
    public CheckingAccount(){}

    public CheckingAccount( double amount,String email,String accountnumber, double overdraft) {
        super( amount,email,accountnumber);
        this.overdraft = overdraft;
    }


    public double getOverdraft() {
        return overdraft;
    }

    @Override
    protected boolean validate(double amount) {
        return super.validate(amount - overdraft);
    }
}
