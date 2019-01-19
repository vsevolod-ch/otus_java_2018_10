import ATM.ATM;
import ATM.Moneys.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Bond5000> pack5000 = Bond.issueBanknotes(Bond5000.class,100);
            ArrayList<Bond2000> pack2000 = Bond.issueBanknotes(Bond2000.class,100);
            ArrayList<Bond1000> pack1000 = Bond.issueBanknotes(Bond1000.class,100);
            ArrayList<Bond500> pack500 = Bond500.issueBanknotes(500);
            ArrayList<Bond200> pack200 = Bond200.issueBanknotes(500);
            ArrayList<Bond100> pack100 = Bond100.issueBanknotes(1000);
            ATM atm = new ATM();
            atm.put(pack5000);
            atm.put(pack2000);
            atm.put(pack1000);
            atm.put(pack500);
            atm.put(pack200);
            atm.put(pack100);

            System.out.println("Balance:");
            System.out.println(atm.balance());
            ArrayList<? extends Bond> m = atm.get(10300);
            System.out.println("My Money:");
            for (Bond b: m)
                System.out.println(b.getDignity());
            System.out.println("Balance:");
            System.out.println(atm.balance());
            atm.put(m);
            System.out.println("Balance:");
            System.out.println(atm.balance());
            m = atm.get(3000);
            System.out.println("My Money:");
            for (Bond b: m)
                System.out.println(b.getDignity());
            System.out.println("Balance:");
            System.out.println(atm.balance());
            atm.get(510);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
