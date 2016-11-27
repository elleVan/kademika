package shop;

import javax.swing.*;
import java.awt.*;

public class ShopUI {

    private Shop shop;

    public ShopUI(Shop shop) {
        this.shop = shop;

        JFrame f = new JFrame();
        f.setLocation(300, 100);
        f.setMinimumSize(new Dimension(800, 600));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        f.getContentPane().add(createPanel());

        f.pack();
        f.setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();

        JLabel lName = new JLabel("Your name:");
        JTextField tfName = new JTextField(10);
        panel.add(lName);
        panel.add(tfName);

        JPanel buttons = new JPanel(new GridLayout(0, 1));
        ButtonGroup group = new ButtonGroup();
        for (String[] el : shop.getSweets()) {
            JRadioButton button = new JRadioButton(el[Shop.NAME]);
            buttons.add(button);
            group.add(button);
        }

        panel.add(buttons);

        JLabel lQuantity = new JLabel("Quantity:");
        JTextField tfQuantity = new JTextField(3);

        panel.add(lQuantity);
        panel.add(tfQuantity);

        JButton button = new JButton("Buy");
        panel.add(button);

        return panel;
    }

}
