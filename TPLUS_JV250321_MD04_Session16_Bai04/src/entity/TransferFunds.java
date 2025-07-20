package entity;

public class TransferFunds {
    private int id;
    private float balance;

    public TransferFunds() {
    }

    public TransferFunds(int id, float balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "TransferFunds{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
