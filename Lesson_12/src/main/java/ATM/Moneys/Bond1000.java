package ATM.Moneys;

import java.util.ArrayList;

public class Bond1000 extends Bond {
    public Bond1000() {
        super.dignity = 1000;
    }
    public static ArrayList<Bond1000> issueBanknotes(int count) throws ReflectiveOperationException {
        return Bond.issueBanknotes(Bond1000.class, count);
    }
}
