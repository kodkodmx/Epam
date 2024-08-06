public class Shop {
    // Assuming other necessary fields and methods are present

    // Method to process a tick of time
    public void tact() {
        for (int i = 0; i < cashBoxes.length; i++) {
            CashBox box = cashBoxes[i];
            switch (box.getState()) {
                case ENABLED:
                    // Process buyers in the box
                    box.processBuyers();
                    break;
                case IS_CLOSING:
                    // Handle closing state: finish processing buyers, then close the box
                    box.processBuyers();
                    box.setState(State.CLOSED);
                    break;
                case CLOSED:
                    // Closed boxes should not process buyers
                    break;
            }
        }
    }

    // Example method to get the state string representation
    public String getState() {
        StringBuilder sb = new StringBuilder();
        for (CashBox box : cashBoxes) {
            switch (box.getState()) {
                case ENABLED:
                    sb.append("+");
                    break;
                case IS_CLOSING:
                    sb.append("|");
                    break;
                case CLOSED:
                    sb.append("-");
                    break;
            }
            sb.append(box.getContent()); // Add content representation of each box
        }
        return sb.toString();
    }

    // Method to add a buyer to the next available cash box
    public void addBuyer(Buyer buyer) {
        for (CashBox box : cashBoxes) {
            if (box.getState() == State.ENABLED) {
                box.addBuyer(buyer);
                return;
            }
        }
        // Handle case where no enabled boxes are available
    }

    // Method to set the state of a cash box
    public void setCashBoxState(int index, State state) {
        cashBoxes[index].setState(state);
    }
}
