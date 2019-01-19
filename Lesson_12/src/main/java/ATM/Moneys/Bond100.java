package ATM.Moneys;

import java.util.ArrayList;

public class Bond100 extends Bond {
    public Bond100() {
        super.dignity = 100;
    }
    public static ArrayList<Bond100> issueBanknotes(int count) throws ReflectiveOperationException {
        return Bond.issueBanknotes(Bond100.class, count);
    }
}
