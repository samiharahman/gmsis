package gmsis.main;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Prashant
 */
public class menu extends JPanel {

    public menu(final JPanel cards, final CardLayout c) {
        setLayout(new GridLayout(0, 3));

        JButton Customer = new JButton("Customer");
        //Customer.setPreferredSize(new Dimension(10, 10));
        add(Customer);
        Customer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("customer button clicked");
                
                c.show(cards,  "Card_Customer");
            }
        });
    }

}
