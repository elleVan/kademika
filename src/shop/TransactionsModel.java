package shop;

import java.text.SimpleDateFormat;
import java.util.List;

public class TransactionsModel {

    private Object[][] data;
    private List<Transaction> transactions;

    public TransactionsModel(List<Transaction> trans) {
        transactions = trans;
        data = new Object[transactions.size()][];
        initData();
    }

    public void initData() {
        int i = 0;
        for (Transaction t : transactions) {
            if (t != null && t.getCustomer() != null) {
                data[i] = new Object[6];
                data[i][0] = i + 1;
                data[i][1] = t.getCustomer().getName();

                String sweetsStr = "";
                int totalCount = 0;
                int sum = 0;

                Sweet[] sweets = t.getSweets();
                for (int j = 0; j < sweets.length; j++) {
                    Sweet sweet = t.getSweets()[j];

                    if (j < sweets.length - 1) {
                        sweetsStr += sweet.getName() + ", ";
                    } else {
                        sweetsStr += sweet.getName();
                    }

                    totalCount += sweet.getQuantity();
                    sum += sweet.getPrice() * sweet.getQuantity();
                }

                data[i][2] = sweetsStr;
                data[i][3] = totalCount;
                data[i][4] = sum;

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                data[i][5] = sdf.format(t.getDate());
                i++;
            }
        }
    }

    public Object[][] getData() {
        return data;
    }
}
