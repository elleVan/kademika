package shop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class TransactionsView extends JPanel {

    private TransactionsModel model;

    private JTable table;

    public TransactionsView(TransactionsModel m) {
        setLayout(new GridLayout(1, 0));
        model = m;

        Vector<String> columnNames = new Vector<>(Arrays.asList("#", "Name", "Sweets", "Total count", "Sum", "Date"));

        table = new JTable(new DefaultTableModel(model.getData(), columnNames));

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public JTable getTable() {
        return table;
    }
}
