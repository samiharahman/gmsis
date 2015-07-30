package gmsis.customer;

import gmsis.connection.DBConnection;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.proteanit.sql.DbUtils;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaffer
 */
public class CustomersPanel extends javax.swing.JPanel {
        DBConnection connector1E = new DBConnection();
        ResultSet rs = null;
        Object val;
        Object val2;
        Object tc;
        Object caID;
        Object vID;
        CardLayout card;
        JPanel cardPanel;
        DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        String [] typeOfCustomer = {"Business","Individual"};
        String [] reminderStatus = {"Reminded","Not Reminded"};
    /**
     * Creates new form CustomersPanel
     */
    public CustomersPanel(JPanel cards, CardLayout c) {
        cardPanel = cards;
        card = c;
        initComponents();
        setTables();
    }
    public void setTables(){
        
        setCustomerTable();
        setCustomerAccountsTable();
    
    }
    
    public void setCustomerTable(){ 
        
        connector1E.openConn();
        connector1E.prepStmt("SELECT customer_id Customer,type_of_customer Type,first_name CustomerFirstName,last_name CustomerLastName, mobile MobileNumber,address CustomerAddress, reminded ReminderStatus FROM customer");
        rs = connector1E.exQuery();
        customerTable.setModel(DbUtils.resultSetToTableModel(rs));
        connector1E.closeConn();
        
        System.out.println("Customer Table initiated");
    }
    
    public void setCustomerAccountsTable(){ 
        
        connector1E.openConn();
        connector1E.prepStmt("SELECT customer_account_id customerAccountID, Customercustomer_id customerID, total_cost total_cost, pay_status PaymentStatus FROM Customer_Account");
        rs = connector1E.exQuery();
        customerAccountsTable.setModel(DbUtils.resultSetToTableModel(rs));
        connector1E.closeConn();
       
        System.out.println("Customer Account Table initiated");
    }
    
    
    public void addCustomer() {
        
        
        
        JTextField field1 = new JTextField(5);
        JComboBox field2 = new JComboBox(typeOfCustomer);
        JTextField field3 = new JTextField(5);
        JTextField field4 = new JTextField(5);
        JTextField field5 = new JTextField(5);
        JTextField field6 = new JTextField(5);
        JTextField field7 = new JTextField(5);
        
        
        
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(new JLabel("Customer ID"));
        myPanel.add(field1);
        myPanel.add(Box.createHorizontalStrut(5));
        myPanel.add(new JLabel("Type Of Customer"));
        myPanel.add(field2);
        myPanel.add(Box.createHorizontalStrut(5));
        myPanel.add(new JLabel("First Name"));
        myPanel.add(field3);
        myPanel.add(Box.createHorizontalStrut(5));
        myPanel.add(new JLabel("Last Name"));
        myPanel.add(field4);
        myPanel.add(Box.createHorizontalStrut(5));
        myPanel.add(new JLabel("Contact Number"));
        myPanel.add(field5);
        myPanel.add(Box.createHorizontalStrut(5));
        myPanel.add(new JLabel("Address"));
        myPanel.add(field6);
        myPanel.add(Box.createHorizontalStrut(5));


        int result = JOptionPane.showConfirmDialog(null, myPanel, "Add Customer", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            
            System.out.println(field1.getText() + field2.getSelectedItem() + field3.getText() + field4.getText() + field5.getText());
            
            connector1E.openConn();
            connector1E.prepStmt("INSERT INTO customer (customer_id,type_of_customer,first_name,last_name,mobile,address) " +
                    "VALUES ('"+ field1.getText() +"','"+ field2.getSelectedItem() +"','"+ field3.getText() +"','"+ field4.getText() +"','"+ field5.getText() +"','"+ field6.getText() +"');");
            connector1E.exUpdate();
            connector1E.closeConn();
            setCustomerTable();
            JOptionPane.showMessageDialog(null,"Customer ADDED successfully");
        }
    }
    
    public void removeCustomer() {
        
        JTextField field1 = new JTextField(5);
        
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(new JLabel("Customer ID to be removed:"));
        myPanel.add(field1);
        myPanel.add(Box.createHorizontalStrut(5));
        
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Remove Customer", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            connector1E.openConn();
            connector1E.prepStmt("DELETE from customer where customer_id='"+field1.getText()+"'");
            connector1E.exUpdate();
            connector1E.closeConn();
            setCustomerTable();
            JOptionPane.showMessageDialog(null,"Customer REMOVED successfully");
        
        }
    }
        
    public void editCustomer() {
        
        try {
        
            connector1E.openConn();
            connector1E.prepStmt("SELECT * FROM customer WHERE customer_id ='"+val+"'");
            rs = connector1E.exQuery();

            JComboBox field2 = new JComboBox(typeOfCustomer);
            JTextField field3 = new JTextField(5);
            JTextField field4 = new JTextField(5);
            JTextField field5 = new JTextField(5);
            JComboBox field6 = new JComboBox(reminderStatus);


            JPanel myPanel = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
            //myPanel.add(new JLabel("Customer ID"));
           // myPanel.add(field1);
           // myPanel.add(Box.createHorizontalStrut(5));
            myPanel.add(new JLabel("Type Of Customer"));
            myPanel.add(field2);
            myPanel.add(Box.createHorizontalStrut(5));
            myPanel.add(new JLabel("Full Name"));
            myPanel.add(field3);
            myPanel.add(Box.createHorizontalStrut(5));
            myPanel.add(new JLabel("Last Name"));
            myPanel.add(field4);
            myPanel.add(Box.createHorizontalStrut(5));
            myPanel.add(new JLabel("Contact Number"));
            myPanel.add(field5);
            myPanel.add(Box.createHorizontalStrut(5));
            myPanel.add(new JLabel("Reminder Status"));
            myPanel.add(field6);
            myPanel.add(Box.createHorizontalStrut(5));
            
            
            
            String cType = null;
            String cFirstName = null;
            String cLastName = null;
            String cMobile = null;
            String cAddress = null;
            
            while ( rs.next() ) {
                /*int cID = rs.getInt("customer_id");*/
                cType = rs.getString("type_of_customer");
                cFirstName  = rs.getString("first_name");
                cLastName  = rs.getString("last_name");
                cMobile = rs.getString("mobile");
                cAddress = rs.getString("address");
               
            }
                field2.setSelectedItem(cType);
                field3.setText(cFirstName);
                field4.setText(cLastName);
                field5.setText(cMobile);
                field6.setSelectedItem(cAddress); 
            connector1E.closeConn();
            
            int result = JOptionPane.showConfirmDialog(null, myPanel, "Edit Customer", JOptionPane.OK_CANCEL_OPTION);


            if (result == JOptionPane.OK_OPTION) {
                connector1E.openConn();
                connector1E.prepStmt("UPDATE customer "
                + "SET type_of_customer = '"+field2.getSelectedItem()+"',"
                + "first_name = '"+field3.getText()+"',"
                + "last_name = '"+field4.getText()+"',"
                + "address = '"+field5.getText()+"',"
                + "reminded = '"+field6.getSelectedItem()+"'"
                + "WHERE customer_id = '"+val+"';");        
                connector1E.exUpdate();
                connector1E.closeConn();
                setCustomerTable();        
                JOptionPane.showMessageDialog(null,"Customer EDITED successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addCustomerAccount() {
        
        JTextField field1 = new JTextField(5);
        JComboBox field2 = new JComboBox();
        JTextField field3 = new JTextField(5);
        
        
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(new JLabel("Customer Account ID"));
        myPanel.add(field1);
        myPanel.add(Box.createHorizontalStrut(5));
        myPanel.add(new JLabel("Associated Customer ID"));
        myPanel.add(field2);
        myPanel.add(Box.createHorizontalStrut(5));
        myPanel.add(new JLabel("total_cost"));
        myPanel.add(field3);
        myPanel.add(Box.createHorizontalStrut(5));
        
        connector1E.openConn();
        connector1E.prepStmt("Select * From Customer");
        rs = connector1E.exQuery();
        try{
            while(rs.next()){
                field2.addItem(rs.getString("Customer_ID"));
            }
                
        } 
        catch(SQLException ex) {
            Logger.getLogger(CustomersPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Add Customer Account", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            
            System.out.println(field1.getText() + field2.getSelectedItem() + field3.getText());
            
            connector1E.openConn();
            connector1E.prepStmt("INSERT INTO Customer_Account (Customer_account_id,customercustomer_id,total_cost,pay_status) " +
                    "VALUES ('"+ field1.getText() +"','"+ field2.getSelectedItem() +"','"+ field3.getText() +"','----');");
            connector1E.exUpdate();
            connector1E.closeConn();
            setCustomerAccountsTable();
            JOptionPane.showMessageDialog(null,"Customer Account ADDED successfully");
        }
        
    }
    
    public void removeCustomerAccount() {
        
        JTextField field1 = new JTextField(5);
        
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(new JLabel("Customer Account ID to be removed:"));
        myPanel.add(field1);
        myPanel.add(Box.createHorizontalStrut(5));
        
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Remove Customer Account", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            connector1E.openConn();
            connector1E.prepStmt("DELETE from Customer_Account where Customer_Account_id='"+field1.getText()+"'");
            connector1E.exUpdate();
            connector1E.closeConn();
            setCustomerAccountsTable();
            JOptionPane.showMessageDialog(null,"Customer Account REMOVED successfully");
        
        }
    }
    
    public void setPaymentStatus() {
        
            connector1E.openConn();
            connector1E.prepStmt("SELECT * FROM Customer_account WHERE customer_account_id ='"+val2+"'");
            
            
            String[] PaymentCB = { "Settled", "Outstanding","Warranty" };
            JComboBox cb1 = new JComboBox(PaymentCB);

            JPanel myPanel = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
            myPanel.add(new JLabel("Select Account Payment Status:"));
            myPanel.add(cb1);
            
            int result = JOptionPane.showConfirmDialog(null, myPanel, "Set Payment Status", JOptionPane.OK_CANCEL_OPTION);
            String selectedItemS = "Settled";
            String selectedItemW = "Warranty";
            
            if(result == JOptionPane.OK_OPTION) {
                connector1E.openConn();
                connector1E.prepStmt("UPDATE customer_account "
                + "SET pay_status = '"+cb1.getSelectedItem().toString()+"'"
                + "WHERE customer_account_id = '"+val2+"';");        
                connector1E.exUpdate();
                if(selectedItemS == cb1.getSelectedItem() || selectedItemW == cb1.getSelectedItem()) {
                    connector1E.prepStmt("UPDATE customer_account "
                    + "SET total_cost = '"+0.0+"' WHERE customer_account_id = '"+val2+"';");
                }
                connector1E.exUpdate();
                connector1E.closeConn();
                setCustomerAccountsTable();
                JOptionPane.showMessageDialog(null,"Payment Status CHANGED successfully");
                
            }
    }
    
    public void Reminders(){
        Calendar currentDate = Calendar.getInstance();    
        currentDate.setTime(new java.util.Date());
        
        Calendar Yesterday = Calendar.getInstance();    
        Yesterday.setTime(new java.util.Date());
        Yesterday.add(Calendar.DATE,+1); 
        
        Calendar beforeOneMonth = Calendar.getInstance();    
        beforeOneMonth.setTime(new java.util.Date());
        beforeOneMonth.add(Calendar.DATE,+30); 
        
        String date1 = dateFormat.format(Yesterday.getTime());
        String date2 = dateFormat.format(beforeOneMonth.getTime());
        
        connector1E.openConn();
        connector1E.prepStmt("SELECT * FROM Booking "
        + " WHERE booking_type = 'diagnosis and repair' AND booking_date = '"+date1+"'");
      
        rs = connector1E.exQuery();
        try{
            while (rs.next()) {
                int caID = rs.getInt("Customer_Accountcustomer_account_id");
                String bDate = rs.getString("booking_date");
                JOptionPane.showMessageDialog(null,"Diagnosis & Repair Reminders to be made for customer: "+caID+" on the: "+bDate);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(CustomersPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector1E.closeConn();
        
        connector1E.openConn();
        connector1E.prepStmt("SELECT * FROM Booking "
        + " WHERE booking_type = 'Scheduled Maintenance' AND booking_date = '"+date2+"'");
        rs = connector1E.exQuery();
        try{
            while (rs.next()) {
                int caID = rs.getInt("Customer_Accountcustomer_account_id");
                String bDate = rs.getString("booking_date");
                JOptionPane.showMessageDialog(null,"Scheduled maintenance Reminders to be made for customer: "+caID+" on the: "+bDate);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(CustomersPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector1E.closeConn();
        JOptionPane.showMessageDialog(null,"No reminder calls to be made.");
    }
    
    
    public void updateTotalCost() {
        
        connector1E.openConn();
        connector1E.prepStmt("SELECT * FROM Customer_account WHERE customer_account_id ='"+val2+"'");

        JTextField field1 = new JTextField(5);
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(new JLabel("Select Amount to be added to total cost:"));
        myPanel.add(field1);
        myPanel.add(Box.createHorizontalStrut(5));
        
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Update Total Cost", JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
            connector1E.openConn();
            connector1E.prepStmt("UPDATE customer_account "
            + "SET total_cost = '"+field1.getText()+"'"
            + "WHERE customer_account_id = '"+val2+"';");        
            connector1E.exUpdate();
            connector1E.closeConn();
            connector1E.openConn();
            connector1E.prepStmt("UPDATE customer_account "
            + "SET pay_status = '----'"
            + "WHERE customer_account_id = '"+val2+"';");        
            connector1E.exUpdate();
            connector1E.closeConn();
            setCustomerAccountsTable();
            JOptionPane.showMessageDialog(null,"Total cost UPDATED successfully");
        }
    }//METHOD NOT USED
    
    public void updateTotalCost2() {
        
        connector1E.openConn();
        connector1E.prepStmt("SELECT customer_account_id, SUM(bill) AS totalCost FROM Customer_Account LEFT OUTER JOIN Booking ON customer_account_id=Customer_Accountcustomer_account_id"
        + " GROUP BY customer_account_id AND customer_account_id = '"+val2+"'");
        
        rs = connector1E.exQuery();
        
        JTextField field1 = new JTextField(5);
        
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(new JLabel("Press OK to UPDATE total cost"));
        myPanel.add(field1);
        myPanel.add(Box.createHorizontalStrut(5));
        
        try{
            while(rs.next()){
                caID = rs.getInt("customer_account_id");
                tc = rs.getFloat("totalCost");
               
                System.out.println("Customer Account: "+caID+" has a totalCost of : "+tc);
              
            }
            field1.setText(caID.toString());
        } catch (SQLException ex) {
            Logger.getLogger(CustomersPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connector1E.closeConn();
        
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Update Total Cost", JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
            connector1E.openConn();
            connector1E.prepStmt("UPDATE customer_account "
            + "SET total_cost = '"+tc+"' WHERE customer_account_id = '"+val2+"'");
            connector1E.exUpdate();
            connector1E.closeConn();
            setCustomerAccountsTable();
            JOptionPane.showMessageDialog(null,"Accounts Have Been UPDATED successfully");
               
        }
        
        
    }
    
    private void showAccountDetails(){
        
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(new JLabel("Customer Account Details:"));
        myPanel.add(Box.createHorizontalStrut(5));
        
        
        connector1E.openConn();
        connector1E.prepStmt("SELECT * FROM Booking WHERE Customer_Accountcustomer_account_id = '"+val2+"'");
        rs = connector1E.exQuery();
        try{
            while(rs.next()){
                caID = rs.getInt("Customer_Accountcustomer_account_id");
                String bd = rs.getString("booking_date");
                
                System.out.println("Customer Account: "+caID+" Has the following booking date: "+bd);
                myPanel.add(new JLabel("Customer Account: "+caID+" Has the following booking date: "+bd));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomersPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector1E.closeConn();
        
        connector1E.openConn();
        connector1E.prepStmt("SELECT * FROM Vehicle WHERE Customer_Accountcustomer_account_id = '"+val2+"'");
        rs = connector1E.exQuery();

        try{
            while(rs.next()){
                caID = rs.getInt("Customer_Accountcustomer_account_id");
                vID = rs.getString("vehicle_id");
                System.out.println("Customer Account: "+caID+" Has the following vehicle: "+vID);
                myPanel.add(new JLabel("Customer Account: "+caID+" Has the following vehicle: "+vID));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomersPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector1E.closeConn();
        

        connector1E.openConn();
        connector1E.prepStmt("SELECT * FROM Vehicle_Parts WHERE Vehiclevehicle_id = '"+vID+"'");
        rs = connector1E.exQuery();
        try{
            while(rs.next()){
                String pID = rs.getString("Partpart_id");
               
                
                System.out.println("Customer Account: "+caID+" AND Associated Vehicle: "+vID+" has used the following part: "+pID);
                myPanel.add(new JLabel("Customer Account: "+caID+" AND Associated Vehicle: "+vID+" has used the following part: "+pID));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomersPanel.class.getName()).log(Level.SEVERE, null, ex);
        
        connector1E.closeConn();
        }
        vID = null;
        
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Update Total Cost", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            //EXIT JOPTION PANE 
        }
    
    }
    
    
    

            
            
            
            
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        customerAccountsTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        customerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(customerTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Customers", jPanel1);

        customerAccountsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        customerAccountsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomerAccountTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(customerAccountsTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Customer Accounts", jPanel2);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 6, 790, 440));

        jButton1.setText("Add Customer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 26, 230, 30));

        jButton4.setText("Edit Customer");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 98, 230, 30));

        jButton5.setText("Remove Customer");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 62, 230, 30));

        jLabel1.setText("Customer Actions:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 6, -1, -1));

        jLabel2.setText("Customer Account Actions:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 154, -1, -1));

        jButton9.setText("Update Total Cost");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 282, 230, 30));

        jButton2.setText("Add Customer Account");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 174, 230, 30));

        jButton7.setText("Remove Customer Account");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 210, 230, 30));

        jButton3.setText("Back To Menu");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 160, -1));

        jButton8.setText("Set Payment Status");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 246, 230, 30));

        jButton10.setText("Show Account Details");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 318, 230, 30));

        jButton6.setText("Reminders");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 354, 230, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void customerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTableMouseClicked
        // TODO add your handling code here:
        int rowIndex = customerTable.getSelectedRow();
        val = customerTable.getValueAt(rowIndex, 0);
        
    }//GEN-LAST:event_customerTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        addCustomer();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        editCustomer();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        removeCustomer();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        addCustomerAccount();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        removeCustomerAccount();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        card.show(cardPanel, "Card_menu");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void CustomerAccountTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerAccountTableMouseClicked
        // TODO add your handling code here:
        int rowIndex = customerAccountsTable.getSelectedRow();
        val2 = customerTable.getValueAt(rowIndex, 0);
    }//GEN-LAST:event_CustomerAccountTableMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Reminders();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        updateTotalCost2();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        showAccountDetails();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        setPaymentStatus();
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable customerAccountsTable;
    private javax.swing.JTable customerTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
    

