//package edu.auburn;

import javax.swing.*;

public class StoreManager {
   public static final String DBMS_SQ_LITE ="SQLite";
    public static final String DB_FILE ="C:/Users/Katie/SQL/act10.db";

    IDataAdapter adapter = null;
    private static StoreManager instance = null;

    public static StoreManager getInstance() {
        if (instance == null) {
            String dbfile = DB_FILE;
            if (dbfile.length() == 0) {
                JFileChooser fc = new JFileChooser();
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                    dbfile = fc.getSelectedFile().getAbsolutePath();
            }
            instance = new StoreManager(DBMS_SQ_LITE, dbfile);
        }

           // instance = new StoreManager(DBMS_SQ_LITE, DB_FILE);
        return instance;
    }

    private StoreManager(String dbms, String dbfile ) {
        if (dbms.equals("Oracle"))
            adapter = new OracleDataAdapter();
        else
        if (dbms.equals("SQLite"))
            adapter = new SQLiteDataAdapter();

        adapter.connect(dbfile);
        ProductModel product = adapter.loadProduct(3);

        //System.out.println("Loaded product: " + product);

    }

    public IDataAdapter getDataAdapter() {
        return adapter;
    }

    public void setDataAdapter(IDataAdapter a) {
        adapter = a;
    }

    public void run() {
        MainUI ui = new MainUI();
        ui.view.setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Hello class!");
        //AddProductUI ap = new AddProductUI();
        //ap.run();

        //AddCustomerUI ac = new AddCustomerUI();
        //ac.run();
      // MainUI ui = new MainUI();

     StoreManager.getInstance().run();
        //ui.run;
    }

}
