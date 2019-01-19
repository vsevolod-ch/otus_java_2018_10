package ATM.Moneys;

import java.util.ArrayList;

public class Bond500 extends Bond {
    public Bond500() {
        super.dignity = 500;
    }
    public static ArrayList<Bond500> issueBanknotes(int count) throws ReflectiveOperationException {
        return Bond.issueBanknotes(Bond500.class, count);
    }
}
