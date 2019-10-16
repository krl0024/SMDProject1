import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {

    public JFrame view;

    public JButton btnAddProduct = new JButton("Add Product");
    public JButton btnAddCustomer = new JButton("Add Customer");
    public JButton btnAddPurchase = new JButton("Add Purchase");
    // public JButton btnCancel = new JButton("Cancel");

    public MainUI() {
        this.view = new JFrame();
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Store Management System");

        title.setFont(title.getFont().deriveFont(24.0f));
        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAddProduct);
        panelButtons.add(btnAddCustomer);
        panelButtons.add(btnAddPurchase);
        view.getContentPane().add(panelButtons);

        btnAddProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddProductUI ap = new AddProductUI();
                ap.run();

            }
        });
        btnAddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCustomerUI ac = new AddCustomerUI();
                ac.run();
            }
        });
        btnAddPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPurchaseUI ax = new AddPurchaseUI();
                ax.run();
            }
        });

    }
}
