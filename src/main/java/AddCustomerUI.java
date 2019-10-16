//package edu.auburn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddCustomerUI {

    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtName = new JTextField(20);
    public JTextField txtPhone = new JTextField(20);
    public JTextField txtAddress = new JTextField(20);


    public AddCustomerUI() {
        this.view = new JFrame();
        view.setTitle("Add Customer");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        String[] labels = {"CustomerID ", "Name ", "Phone ", "Address "};

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("CustomerID "));
        line1.add(txtCustomerID);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Name "));
        line2.add(txtName);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Phone "));
        line3.add(txtPhone);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Address "));
        line4.add(txtAddress);
        view.getContentPane().add(line4);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAdd);
        panelButtons.add(btnCancel);
        view.getContentPane().add(panelButtons);

        btnAdd.addActionListener(new AddButtonListerner());

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               view.dispose();
                // JOptionPane.showMessageDialog(null, "You click on Cancel button!!!");
            }
        });

    }

    public void run() {
        view.setVisible(true);
    }

    class AddButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            CustomerModel customer = new CustomerModel();

            String id = txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            try {
                customer.mCustomerID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }

            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Customer name cannot be empty!");
                return;
            }

            customer.mName = name;

            String phone = txtPhone.getText();
            if (phone.length() == 0) {
                JOptionPane.showMessageDialog(null, "Address cannot be null!");
                return;
            }
            customer.mPhone = phone;
          //  try {
            //    customer.mPhone = phone;
            //} catch (NumberFormatException e) {
              //  JOptionPane.showMessageDialog(null, "Phone number is invalid!");
                //return;
            //}

            String address = txtAddress.getText();
            /**  try {
             customer.mAddress = address;
             } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(null, "Address is invalid!");
             return;
             } **/
            if (address.length() == 0) {
                JOptionPane.showMessageDialog(null, "Address cannot be null!");
                return;
            }
            customer.mAddress = address;
            //adapter.saveCustomer(customer);
            //System.out.print(product);
            switch (StoreManager.getInstance().getDataAdapter().saveCustomer(customer)) {

                case SQLiteDataAdapter.CUSTOMER_DUPLICATE_ERROR:
                    //System.out.print(SQLiteDataAdapter.PRODUCT_DUPLICATE_ERROR);
                    JOptionPane.showMessageDialog(null, "Customer NOT added successfully! Duplicate customer ID!");
                default:
                    JOptionPane.showMessageDialog(null, "Customer added successfully!" + customer);
                   try{
                    TXTReceiptBuilder txt = new TXTReceiptBuilder();

                    String te = txt.appendHeader("Customer Added \r\n") + txt.appendCustomer(customer) + txt.appendFooter("\r\n Thank you!\r\n");


                    File f1 = new File("C:\\Users\\Katie\\Desktop\\Fall 2019\\SM&D\\act12\\customers\\" + customer.mCustomerID + ".txt");
                    if(!f1.exists()) {
                        f1.createNewFile();
                    }
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f1));
                    bw.newLine();
                    bw.write(te);
                    bw.close();
                    System.out.println(te);
                    view.dispose();
            } catch(IOException e){
                e.printStackTrace();
            }

        }
        }
    }

}

      /**  class CancelButtonListerner implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // JOptionPane.showMessageDialog(null, "You click on Cancel button!!!");
                view.setVisible(false);
            }
        }**/
//Lay out the panel.
//        SpringUtilities.makeCompactGrid(p,
//                numPairs, 2, //rows, cols
//                6, 6,        //initX, initY
//                6, 6);       //xPad, yPad

  //  }

//}
