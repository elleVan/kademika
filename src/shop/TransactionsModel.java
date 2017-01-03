package shop;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class TransactionsModel {

    private Vector<Vector<String>> data;
    private List<Transaction> transactions;

    public TransactionsModel(List<Transaction> trans) {
        transactions = trans;
        data = new Vector<>();
        initData();
    }

    public void initData() {
        int i = 0;
        for (Transaction t : transactions) {
            data.add(prepareRow(t, i));
            i++;
        }
    }

    private Vector<String> prepareRow(Transaction t, int i) {
        Vector<String> result = new Vector<>();
        result.add(Integer.toString(i + 1));

        if (t != null) {
            if (t.getCustomer() != null) {
                result.add(t.getCustomer().getName());
            } else {
                result.add("");
            }

            String sweetsStr = "";
            int totalCount = 0;

            List<Sweet> sweets = t.getSweets();
            for (int j = 0; j < sweets.size(); j++) {
                Sweet sweet = t.getSweets().get(j);

                if (j < sweets.size() - 1) {
                    sweetsStr += sweet.getName() + ", ";
                } else {
                    sweetsStr += sweet.getName();
                }

                totalCount += sweet.getQuantity();
            }

            result.add(sweetsStr);
            result.add(Integer.toString(totalCount));
            result.add(Integer.toString(t.getSum()));

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            result.add(sdf.format(t.getDate()));
        }

        return result;
    }

    public Vector<String> addRow(Transaction transaction) {
        Vector<String> result = prepareRow(transaction, data.size());
        data.add(result);
        return result;
    }

    public Vector<Vector<String>> getData() {
        return new Vector<>(data);
    }
}
