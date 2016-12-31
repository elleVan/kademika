package shop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;

public class ShopUI {

    private Shop shop;
    private String sweetName;

    private JFrame f;

    private TransactionsModel transactionsModel;
    private TransactionsView transactionsView;

    private JMenuItem buyItem;
    private JMenuItem transactionsMenuItem;

    public ShopUI(Shop shop) {
        this.shop = shop;
        sweetName = shop.getSweets().get(0).get(Shop.NAME);

        f = new JFrame();
        f.setLocation(300, 100);
        f.setMinimumSize(new Dimension(800, 600));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        transactionsModel = new TransactionsModel(shop.getTransactions());
        transactionsView = new TransactionsView(transactionsModel);

        f.setJMenuBar(createMenuBar());
        f.getContentPane().add(transactionsView);

        f.pack();
        f.setVisible(true);
    }

    private JPanel createBuyingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel lName = new JLabel("Your name:");
        JTextField tfName = new JTextField(10);
        panel.add(lName, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 5), 0, 0));
        panel.add(tfName, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JLabel lButtons = new JLabel("Sweets:");
        JPanel buttons = new JPanel(new GridLayout(0, 1));
        ButtonGroup group = new ButtonGroup();
        ActionListener rbListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sweetName = e.getActionCommand();
            }
        };

        int i = 0;
        for (List<String> el : shop.getSweets()) {
            JRadioButton button = new JRadioButton(el.get(Shop.NAME));
            button.addActionListener(rbListener);

            if (i == 0) {
                button.setSelected(true);
                i++;
            }

            buttons.add(button);
            group.add(button);
        }
        panel.add(lButtons, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(5, 0, 5, 0), 0, 0));
        panel.add(buttons, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(5, 0, 5, 0), 0, 0));

        JLabel lQuantity = new JLabel("Quantity:");
        NumberFormat nf = NumberFormat.getNumberInstance();
        JFormattedTextField tfQuantity = new JFormattedTextField(nf);
        tfQuantity.setColumns(3);
        tfQuantity.setValue(1);

        panel.add(lQuantity, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(tfQuantity, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JButton button = new JButton("Buy");
        panel.add(button, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(10, 0, 0, 0), 0, 0));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transaction transaction = shop.newTransaction(shop.newCustomer(tfName.getText()), new Sweet[] {
                        shop.newSweet(sweetName, Integer.parseInt(tfQuantity.getText())),
                });
                shop.printBase();
                DefaultTableModel tableModel = (DefaultTableModel) transactionsView.getTable().getModel();
                tableModel.addRow(transactionsModel.addRow(transaction));

                showTransactions();
            }
        });

        return panel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        menuBar = new JMenuBar();

        menu = new JMenu("File");

        buyItem = new JMenuItem("Buy Sweets");
        buyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.getContentPane().removeAll();
                f.getContentPane().add(createBuyingPanel());

                transactionsMenuItem.setVisible(true);
                buyItem.setVisible(false);

                f.pack();
                f.repaint();
            }
        });
        menu.add(buyItem);

        transactionsMenuItem = new JMenuItem("View Transactions");
        transactionsMenuItem.setVisible(false);
        transactionsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactions();
            }
        });
        menu.add(transactionsMenuItem);

        menuBar.add(menu);

        return menuBar;
    }

    private void showTransactions() {
        f.getContentPane().removeAll();
        f.getContentPane().add(transactionsView);

        buyItem.setVisible(true);
        transactionsMenuItem.setVisible(false);

        f.pack();
        f.repaint();
    }
}
