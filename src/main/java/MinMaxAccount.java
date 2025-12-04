public class MinMaxAccount extends BankingAccount {

    private int minBalance;
    private int maxBalance;

    /**
     * Конструктор: стартовый баланс считается и минимумом, и максимумом.
     */
    public MinMaxAccount(Startup s) {
        super(s);
        int balance = getBalance();
        this.minBalance = balance;
        this.maxBalance = balance;
    }

    /**
     * Операция пополнения (debit) с обновлением min/max.
     */
    @Override
    public void debit(Debit d) {
        super.debit(d);
        updateMinMax();
    }

    /**
     * Операция списания (credit) с обновлением min/max.
     */
    @Override
    public void credit(Credit c) {
        super.credit(c);
        updateMinMax();
    }

    private void updateMinMax() {
        int balance = getBalance();
        if (balance < minBalance) {
            minBalance = balance;
        }
        if (balance > maxBalance) {
            maxBalance = balance;
        }
    }

    /**
     * @return минимальный баланс за все время.
     */
    public int getMin() {
        return minBalance;
    }

    /**
     * @return максимальный баланс за все время.
     */
    public int getMax() {
        return maxBalance;
    }
}
