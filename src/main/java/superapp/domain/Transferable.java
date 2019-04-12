package superapp.domain;

public interface Transferable {
    void withdraw(double amount);

    void deposit(double amount);
}
