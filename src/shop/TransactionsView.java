package shop;

import javax.swing.*;
import java.awt.*;

public class TransactionsView extends JPanel {

    private TransactionsModel model;

    public TransactionsView(TransactionsModel m) {
        setLayout(new GridLayout(1, 0));
        model = m;

        String[] columnNames = {"#", "Name", "Sweets", "Total count", "Sum"};

        JTable table = new JTable(model.getData(), columnNames);

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
