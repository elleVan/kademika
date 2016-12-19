package shop;

public class TransactionsModel {

    private Object[][] data;
    private Transaction[][] transactions;

    public TransactionsModel(Transaction[][] trans) {
        transactions = trans;
        data = new Object[getTransactionsQuantity()][];
        initData();
    }

    public void initData() {
        int i = 0;
        for (Transaction[] el : transactions) {
            if (el != null) {
                for (Transaction t : el) {
                    if (t != null && t.getCustomer() != null) {
                        data[i] = new Object[5];
                        data[i][0] = i + 1;
                        data[i][1] = t.getCustomer().getName();

                        String sweets = "";
                        int totalCount = 0;
                        int sum = 0;
                        for (Sweet sweet : t.getSweets()) {
                            sweets += sweet.getName() + " ";
                            totalCount += sweet.getQuantity();
                            sum += sweet.getPrice() * sweet.getQuantity();
                        }

                        data[i][2] = sweets;
                        data[i][3] = totalCount;
                        data[i][4] = sum;
                        i++;
                    }
                }
            }
        }
    }

    private int getTransactionsQuantity() {
        int result = 0;
        for (Transaction[] el : transactions) {
            if (el != null) {
                for (Transaction t : el) {
                    if (t != null && t.getCustomer() != null) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public Object[][] getData() {
        return data;
    }
}
