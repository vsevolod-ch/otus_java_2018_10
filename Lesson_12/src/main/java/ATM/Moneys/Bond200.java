package ATM.Moneys;

import java.util.ArrayList;

public class Bond200 extends Bond {
    public Bond200() {
        super.dignity = 200;
    }
    public static ArrayList<Bond200> issueBanknotes(int count) throws ReflectiveOperationException {
        return Bond.issueBanknotes(Bond200.class, count);
    }
}
