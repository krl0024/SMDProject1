import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
//import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;
import java.util.zip.DataFormatException;

public class AddPurchaseUI {
    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtPurchaseID = new JTextField(10);
    public JTextField txtCustomerID = new JTextField(10);
    public JTextField txtProductID = new JTextField(10);
    public JTextField txtQuantity = new JTextField(10);

    public JLabel labPrice = new JLabel("Product Price: ");
    public JLabel labDate = new JLabel("Date of Purchase: ");

    public JLabel labCustomerName = new JLabel("Customer Name: ");
    public JLabel labProductName = new JLabel("Product Name: ");

    public JLabel labCost = new JLabel("Cost: $0.00 ");
    public JLabel labTax = new JLabel("Tax: $0.00");
    public JLabel labTotalCost = new JLabel("Total Cost: $0.00");

    ProductModel product;
    CustomerModel customer;
    PurchaseModel purchase;
    double price, cost, tax, totalCost;

    String name, date;


    public AddPurchaseUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add Purchase");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("PurchaseID "));
        line.add(txtPurchaseID);
        line.add(labDate);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("CustomerID "));
        line.add(txtCustomerID);
        line.add(labCustomerName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("ProductID "));
        line.add(txtProductID);
        line.add(labProductName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Quantity "));
        line.add(txtQuantity);
        line.add(labPrice);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(labCost);
        line.add(labTax);
        line.add(labTotalCost);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        view.getContentPane().add(line);

        btnAdd.addActionListener(new AddButtonListener());

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.dispose();
                //JOptionPane.showMessageDialog(null, "You click on Cancel button!!!");
            }
        });

        txtProductID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                process();
            }

            private void process() {
                String s = txtProductID.getText();

                if (s.length() == 0) {
                    labProductName.setText("Product Name: [not specified!]");
                    return;
                }

                System.out.println("ProductID = " + s);

                int id;

                try {
                    id = Integer.parseInt(s);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Invalid ProductID", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                product = StoreManager.getInstance().getDataAdapter().loadProduct(id);

                if (product == null) {
                    JOptionPane.showMessageDialog(null,
                            "Error: No product with id = " + id + " in store!", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    labProductName.setText("Product Name: ");

                    return;
                }
                name = product.mName;
                labProductName.setText("Product Name: " + product.mName);
                price = product.mPrice;
                labPrice.setText("Product Price: " + product.mPrice);
                System.out.println("Product quantity available: " + product.mQuantity);


            }

        });
        txtCustomerID.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                process();
            }

            private void process() {

                String cn = txtCustomerID.getText();

                if (cn.length() == 0) {
                    labCustomerName.setText("Customer Name: [not specified!]");
                    return;
                }

                System.out.println("CustomerID = " + cn);

                int id2;

                try {
                    id2 = Integer.parseInt(cn);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Invalid CustomerID", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                customer = StoreManager.getInstance().getDataAdapter().loadCustomer(id2);

                if (customer == null) {
                    JOptionPane.showMessageDialog(null,
                            "Error: No customer with id = " + id2 + " in store!", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    labCustomerName.setText("Customer Name: ");

                    return;
                }

                labCustomerName.setText("Customer Name: " + customer.mName);
                //labPrice.setText("Product Price: " + product.mPrice);
            }
        });


        txtQuantity.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                process();
            }

            private void process() {

                String q = txtQuantity.getText();

                if (q.length() == 0) {
                    // labCustomerName.setText("Customer Name: [not specified!]");
                    return;
                }
                System.out.println(q);
                System.out.println(product.mQuantity);
                if (Double.parseDouble(q) > product.mQuantity) {
                    JOptionPane.showMessageDialog(null, "Not enough available product!");

                    return;
                }
                else {
                    //purchase.mQuantity = Integer.parseInt(q);
                    System.out.println("Quantity = " + q);
                }
                int id3;

                try {
                    id3 = Integer.parseInt(q);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Invalid CustomerID", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // customer = StoreManager.getInstance().getDataAdapter().loadCustomer(id3);

                // if (customer == null) {
                //   JOptionPane.showMessageDialog(null,
                //         "Error: No customer with id = " + id2 + " in store!", "Error Message",
                //       JOptionPane.ERROR_MESSAGE);
                // labCustomerName.setText("Customer Name: ");

                //return;
                //}
                // int m = q.;
                // BigDecimal c = new BigDecimal(product.mPrice);
                //BigDecimal qu = new BigDecimal(product.mQuantity);

                //purchase.mPrice = product.mPrice*product.mQuantity;
                //purchase.mTax =  product.mTax*product.mQuantity;
                //purchase.mTotal = purchase.mPrice + purchase.mTax;
                cost = product.mPrice * Integer.parseInt(q);
                tax = product.mTax * Integer.parseInt(q);
                totalCost = cost + tax;


                labTax.setText("Tax: $" + tax);
                labCost.setText("Cost: $" + cost);
                labTotalCost.setText("Total Cost: $" + totalCost);
//                purchase.mQuantity = Integer.parseInt(q);
                //labPrice.setText("Product Price: " + product.mPrice);
            }
        });


//        txtCustomerID.getDocument().addDocumentListener(new DocumentListener() {
//            public void changedUpdate(DocumentEvent e) {
//                process();
//            }
//
//            public void removeUpdate(DocumentEvent e) {
//                process();
//            }
//            public void insertUpdate(DocumentEvent e) {
//                process();
//            }
//
//            private void process() {
//                String s = txtCustomerID.getText();
//
//                if (s.length() == 0) {
//                    labCustomerName.setText("Customer Name: [not specified!]");
//                    return;
//                }
//
//                System.out.println("CustomerID = " + s);
//
//                try {
//                    int id = Integer.parseInt(s);
//
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(null,
//                            "Error: Please enter number bigger than 0", "Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//
//            }
//        });
        // purchase.mPurchaseID = txtPurchaseID.getText();
        /**   String id = txtPurchaseID.getText();

         if (id.length() == 0) {
         JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
         return;
         }

         try {
         purchase.mPurchaseID = Integer.parseInt(id);
         } catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
         return;
         } **/

    }

    public void run() {
        date = Calendar.getInstance().getTime().toString();
        labDate.setText("Date of purchase: " + date);
        view.setVisible(true);


    }

    class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PurchaseModel p = new PurchaseModel();

            String id = txtPurchaseID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                return;
            }

            try {
                p.mPurchaseID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                System.out.print(id);
                System.out.print(p.mPurchaseID);
                JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                return;
            }

            String id2 = txtCustomerID.getText();

            if (id2.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            try {
                p.mCustomerID = Integer.parseInt(id2);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }

            String id3 = txtProductID.getText();

            if (id3.length() == 0) {
                JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
                return;
            }

            try {
                p.mProductID = Integer.parseInt(id3);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ProductID is invalid!");
                return;
            }

            String d = date;

            if (d.length() == 0) {
                JOptionPane.showMessageDialog(null, "Date cannot be null!");
                return;
            }

            p.mDate = d;


            String n = name;
            if (n.length() == 0 || n == null) {
                JOptionPane.showMessageDialog(null, "No product with this ID!");
                return;
            }

            p.mPName = n;

            //  double pr = price;
            // try {
            //   p.mPrice = pr;
            //} catch (NumberFormatException e) {
            //  System.out.print("The price is: " + price);
            //System.out.print("The pr is: " + pr);
            //JOptionPane.showMessageDialog(null, "Price is invalid!");
            // return;
            //}

            String quant = txtQuantity.getText();
            //  if(Double.parseDouble(quant) < product.mQuantity) {
            //    JOptionPane.showMessageDialog(null, "Not enough available product!");

            //}
            try {
                p.mQuantity = Double.parseDouble(quant);
                //product.mQuantity -= p.mQuantity;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantity is invalid!");
                return;
            }


            Double pr = price;
            try {
                p.mPrice = pr;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Price is invalid!");
                return;
            }

            // String tax = labTax.getText();
            double t = tax;
            try {
                p.mTax = tax;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tax is invalid!");
                return;
            }

            Double tot = totalCost;
            try {
                p.mTotal = tot;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Total is invalid!");
                return;
            }
            //double available = product.mQuantity - p.mQuantity;
            //System.out.println(available);
            if(p.mQuantity > product.mQuantity){
                JOptionPane.showMessageDialog(null, "Not Enough Product! Can only purchase up to: " + product.mQuantity);
                return;
            }
            else {
                product.mQuantity = product.mQuantity - p.mQuantity;
            }
            int newQ = (int)product.mQuantity;
            switch (StoreManager.getInstance().getDataAdapter().updateProduct(product, newQ)) {
                    case SQLiteDataAdapter.PRODUCT_QUANTITY_ERROR:
                        JOptionPane.showMessageDialog(null, "Purchase NOT added successfully! Not Enough Product!");
                default:
                switch (StoreManager.getInstance().getDataAdapter().savePurchase(p)) {
                    case SQLiteDataAdapter.PRODUCT_DUPLICATE_ERROR:
                        JOptionPane.showMessageDialog(null, "Purchase NOT added successfully! Duplicate purchase ID!");
                    default:
                        JOptionPane.showMessageDialog(null, "Purchase added successfully!" + p);
                        try {
                            // String data = " Tutorials Point is a best website in the world";
                            TXTReceiptBuilder txt = new TXTReceiptBuilder();
                            //   txt.appendHeader("Purchase receipt \n");
                            String te = txt.appendHeader("Purchase receipt \r\n") + txt.appendPurchase(p) + txt.appendFooter("\r\n Thank you!\r\n");
                            // txt.appendFooter("\n Thank you!\n");

                            File f1 = new File("C:\\Users\\Katie\\Desktop\\Fall 2019\\SM&D\\act12\\purchases\\" + p.mPurchaseID + ".txt");
                            if (!f1.exists()) {
                                f1.createNewFile();
                            }
                            // FileWriter fileWritter = new FileWriter(f1.getName(),true);
                            BufferedWriter bw = new BufferedWriter(new FileWriter(f1));
                            bw.newLine();
                            bw.write(te);
                            //bw.wr
                            bw.close();
                            //fileWritter.write(f1, te);
                            // Files.write
                            //fileWritter.close();
                            System.out.println(te);
                            // product.mQuantity = product.mQuantity - p.mQuantity;
                            //StoreManager.getInstance().getDataAdapter().updateProduct(product);
                            //product.mQuantity = product.mQuantity - p.mQuantity;
                            System.out.println(product.mQuantity);
                            view.dispose();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //  txt.appendHeader("Purchase receipt \n");
                        // System.out.print(txt);

                        /**+"\nCustomer: " + customer.mName
                         + " \n " + p.mQuantity + " " + p.mPName + "'s \t $" + price + "each \n\tCost: "+ cost + "\n\t Tax: " + tax + "\t\n Total: " + totalCost );**/
                }
            }
        }
    }
}
