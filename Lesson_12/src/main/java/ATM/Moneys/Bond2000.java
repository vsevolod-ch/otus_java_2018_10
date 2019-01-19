package ATM.Moneys;

import java.util.ArrayList;

public class Bond2000 extends Bond {
    public Bond2000() {
        super.dignity = 2000;
    }
    public static ArrayList<Bond2000> issueBanknotes(int count) throws ReflectiveOperationException {
        return Bond.issueBanknotes(Bond2000.class, count);
    }
}
