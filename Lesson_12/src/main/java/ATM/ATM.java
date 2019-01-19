package ATM;

import ATM.Moneys.Bond;

import java.util.*;

public class ATM {

    /**
     * Ячейки для хранения купюр внутри банкомата.
     */
    private TreeMap<Integer, Stack<Bond>> moneyCells = new TreeMap<>(Collections.reverseOrder());

    /**
     * Перемещение денег из переданной пачки в ячейки банкомата.
     * @param moneys - пачка денег
     * @return int
     */
    public int put(ArrayList<? extends Bond> moneys) {
        Iterator<? extends Bond> i = moneys.iterator();
        while (i.hasNext()) {
            Bond m = i.next();
            if (!this.moneyCells.containsKey(m.getDignity()))
                this.moneyCells.put(m.getDignity(), new Stack<>());
            this.moneyCells.get(m.getDignity()).push(m);
            i.remove();
        }
        return this.balance();
    }

    /**
     * Выдача денег населению.
     * @param sum - требуемая сумма.
     * @return - пачка денег
     * @throws Exception - когда не получается:(
     */
    public ArrayList<? extends Bond> get(int sum) throws Exception {
        Set<Integer> dignities = this.moneyCells.keySet();
        if (dignities.size() == 0)
            throw new Exception ("Денег нет... но вы держитесь там!");
        Map<Integer, Integer> plan = new HashMap<>();
        for (int d: dignities) {
            if (!this.moneyCells.containsKey(d))
                continue;
            if (sum < d)
                continue;
            int count = sum / d; // дробная часть отбрасывается автоматом, без округления.
            if (this.moneyCells.get(d).size() < count)
                continue;
            plan.put(d, count);
            sum %= d;
            if (sum == 0)
                break;
        }
        if (sum != 0 || plan.size() == 0)
            throw new Exception ("Выдача невозможна... но вы держитесь там!");
        ArrayList<Bond> moneys = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e: plan.entrySet()) {
            Stack<Bond> cell = this.moneyCells.get(e.getKey());
            for (int i = 0; i < e.getValue(); i++) {
                moneys.add(cell.pop());
            }
        }
        return moneys;
    }

    /**
     * Возвращает текущий денежный баланс на устройстве.
     * @return int
     */
    public int balance() {
        Set<Integer> dignities = this.moneyCells.keySet();
        int balance = 0;
        for (int dignity: dignities)
            balance += dignity * this.moneyCells.get(dignity).size();
        return balance;
    }

}
