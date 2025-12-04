public class DiscountBill2 {

    private final GroceryBill bill;
    private final boolean regularCustomer;

    private int discountCount;
    private double discountAmount;
    private double totalWithoutDiscount;

    /**
     * @param clerk           продавец
     * @param regularCustomer является ли покупатель постоянным
     */
    public DiscountBill2(Employee clerk, boolean regularCustomer) {
        this.bill = new GroceryBill(clerk);
        this.regularCustomer = regularCustomer;
        this.discountCount = 0;
        this.discountAmount = 0.0;
        this.totalWithoutDiscount = 0.0;
    }

    /**
     * Доступ к продавцу — делегируем внутреннему чеку.
     */
    public Employee getClerk() {
        return bill.getClerk();
    }

    /**
     * Добавляет товар во внутренний GroceryBill и считает скидки
     * по той же логике, что и в DiscountBill.
     */
    public void add(Item i) {
        if (i == null) {
            return;
        }
        bill.add(i);

        totalWithoutDiscount += i.getPrice();

        if (regularCustomer && i.getDiscount() > 0.0) {
            discountCount++;
            discountAmount += i.getDiscount();
        }
    }

    /**
     * Итоговая сумма к оплате:
     * - обычный клиент: как у GroceryBill (полная цена),
     * - постоянный: с учетом скидок.
     */
    public double getTotal() {
        if (!regularCustomer) {
            return bill.getTotal();
        }
        return totalWithoutDiscount - discountAmount;
    }

    public int getDiscountCount() {
        if (!regularCustomer) {
            return 0;
        }
        return discountCount;
    }

    public double getDiscountAmount() {
        if (!regularCustomer) {
            return 0.0;
        }
        return discountAmount;
    }

    public double getDiscountPercent() {
        if (!regularCustomer || totalWithoutDiscount == 0.0) {
            return 0.0;
        }
        return discountAmount * 100.0 / totalWithoutDiscount;
    }
}
