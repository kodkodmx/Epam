package com.epam.rd.autocode.queue;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<CashBox> cashBoxes;

    public Shop(int count) {
        cashBoxes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cashBoxes.add(new CashBox(i));
        }
    }

    public int getCashBoxCount() {
        return cashBoxes.size();
    }

    public void addBuyer(Buyer buyer) {
        CashBox targetCashBox = null;
        int minQueueSize = Integer.MAX_VALUE;
    
        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED) && cashBox.getQueue().size() < minQueueSize) {
                minQueueSize = cashBox.getQueue().size();
                targetCashBox = cashBox;
            }
        }
    
        if (targetCashBox != null) {
            targetCashBox.addLast(buyer);
        }
    }    

    public void tact() {
        for (CashBox cashBox : cashBoxes) {
            if (!cashBox.getQueue().isEmpty()) {
                cashBox.serveBuyer();
            }
        }
        balance();
    }
    
    private void balance() {
        List<Buyer> defectorBuyers = new ArrayList<>();
        int[] minMaxSize = getMinMaxSize(cashBoxes);
        int minSize = minMaxSize[0];
        int maxSize = minMaxSize[1];
    
        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED)) {
                while (cashBox.getQueue().size() > maxSize) {
                    defectorBuyers.add(cashBox.removeLast());
                }
            }
        }
    
        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED)) {
                while (cashBox.getQueue().size() < minSize && !defectorBuyers.isEmpty()) {
                    cashBox.addLast(defectorBuyers.remove(0));
                }
            }
        }
    }

    public static int[] getMinMaxSize(List<CashBox> cashBoxes) {
        int enabledCount = 0;
        int totalBuyers = 0;

        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED)) {
                enabledCount++;
                totalBuyers += cashBox.getQueue().size();
            }
        }

        int minSize = enabledCount > 0 ? totalBuyers / enabledCount : 0;
        int maxSize = minSize + (totalBuyers % enabledCount > 0 ? 1 : 0);
        return new int[] {minSize, maxSize};
    }

    public void setCashBoxState(int cashBoxNumber, CashBox.State state) {
        CashBox cashBox = getCashBox(cashBoxNumber);
        if (cashBox != null) {
            cashBox.setState(state);
        }
    }

    public CashBox getCashBox(int cashBoxNumber) {
        return cashBoxNumber >= 0 && cashBoxNumber < cashBoxes.size() ? cashBoxes.get(cashBoxNumber) : null;
    }

    public void print() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < cashBoxes.size(); i++) {
            output.append(cashBoxes.get(i).toString());
            if (i < cashBoxes.size() - 1) {
                output.append(System.lineSeparator());
            }
        }
        System.out.println(output.toString());
    }
}
