package ATM.Moneys;

import java.util.ArrayList;

public class Bond5000 extends Bond {
    public Bond5000() {
        super.dignity = 5000;
    }
    public static ArrayList<Bond5000> issueBanknotes(int count) throws ReflectiveOperationException {
        return Bond.issueBanknotes(Bond5000.class, count);
    }
}
