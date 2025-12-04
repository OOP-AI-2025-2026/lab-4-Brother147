import ua.opnu.java.inheritance.bill.Employee;
import ua.opnu.java.inheritance.bill.GroceryBill;
import ua.opnu.java.inheritance.bill.Item;

/**
 * Чек с учетом скидок для постоянного покупателя.
 * Наследуется от GroceryBill.
 */
public class DiscountBill extends GroceryBill {

    private final boolean regularCustomer; // true — постоянный клиент
    private int discountCount;             // количество товаров со скидкой
    private double discountAmount;         // суммарная скидка в деньгах
    private double totalWithoutDiscount;   // сумма без учета скидок

    /**
     * @param clerk           продавец
     * @param regularCustomer является ли покупатель постоянным
     */
    public DiscountBill(Employee clerk, boolean regularCustomer) {
        super(clerk);
        this.regularCustomer = regularCustomer;
        this.discountCount = 0;
        this.discountAmount = 0.0;
        this.totalWithoutDiscount = 0.0;
    }

    /**
     * Добавляет товар в чек.
     * Для постоянного клиента дополнительно учитывает скидки.
     */
    @Override
    public void add(Item i) {
        if (i == null) {
            return;
        }
        // базовое поведение (сохранить товар в чек)
        super.add(i);

        // полная стоимость без учета скидки
        totalWithoutDiscount += i.getPrice();

        // если клиент постоянный и на товар есть скидка
        if (regularCustomer && i.getDiscount() > 0.0) {
            discountCount++;
            discountAmount += i.getDiscount();
        }
    }

    /**
     * Итоговая сумма к оплате.
     * Обычный клиент — платит полную стоимость (как GroceryBill).
     * Постоянный клиент — платит с учетом скидок.
     */
    @Override
    public double getTotal() {
        if (!regularCustomer) {
            // поведение как у исходного GroceryBill
            return super.getTotal();
        }
        return totalWithoutDiscount - discountAmount;
    }

    /**
     * @return количество товаров со скидкой (для обычного клиента — 0)
     */
    public int getDiscountCount() {
        if (!regularCustomer) {
            return 0;
        }
        return discountCount;
    }

    /**
     * @return суммарный размер скидки (для обычного клиента — 0.0)
     */
    public double getDiscountAmount() {
        if (!regularCustomer) {
            return 0.0;
        }
        return discountAmount;
    }

    /**
     * @return процент скидки относительно полной стоимости чека.
     * Формула: discountAmount / totalWithoutDiscount * 100.
     * Для обычного клиента или пустого чека — 0.0.
     */
    public double getDiscountPercent() {
        if (!regularCustomer || totalWithoutDiscount == 0.0) {
            return 0.0;
        }
        return discountAmount * 100.0 / totalWithoutDiscount;
    }
}
