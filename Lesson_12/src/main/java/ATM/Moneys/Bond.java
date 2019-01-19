package ATM.Moneys;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Bond {

    /**
     * Потокобезопасный объект числа Long.
     */
    protected static final AtomicLong AL = new AtomicLong(1);

    /**
     * "Государственный" номер купюры,
     * просто сквозное порядковое числительное для всех купюр,
     * наследников Bond.
     */
    protected final long ID = AL.getAndIncrement();

    /**
     * Номинал банкноты.
     */
    protected int dignity;

    /**
     * Получение порядкового номера купюры.
     * @return String - номер купюры в формате строки
     */
    public String getNumber() {
        return String.format("%010d", this.ID);
    }

    /**
     * Получение номинала банкноты.
     * @return int
     */
    public int getDignity() {
        return this.dignity;
    }

    /**
     * Завпрос на выпуск банкнот - это плохой метод :)
     * @param dignity - номинал.
     * @param count - количество.
     * @return - пакет с деньгами.
     * @throws ReflectiveOperationException - если номинал недопустимый.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Bond> ArrayList<T> issueBanknotes(int dignity, int count) throws ReflectiveOperationException {
        String packageName = Bond.class.getPackage().getName();
        String bondName = String.format("Bond%d", dignity);
        String className = packageName + '.' + bondName;
        Class<T> clazz = (Class<T>) Class.forName(className);
        return Bond.issueBanknotes(clazz, count);
    }

    /**
     * Запрос на выпуск банкнот.
     * @param dignity - номинал
     * @param count - количество
     * @return - пакет с деньгами
     * @throws ReflectiveOperationException
     */
    public static <T extends Bond> ArrayList<T> issueBanknotes(Class<T> dignity, int count) throws ReflectiveOperationException {
        ArrayList<T> moneysPack = new ArrayList<>(count);
        for (int i = 0; i < count; i++)
            moneysPack.add(dignity.getDeclaredConstructor().newInstance());
        return moneysPack;
    }

}
