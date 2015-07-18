package presentation;

import business.*;
import domain.Task;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import services.*;

public class MainWindow extends javax.swing.JFrame {
    Task task = new Task();
    private DefaultTableModel model;
    public Object[] columnNames = {"id", "Task Name", "Due Date"};
    private Object[][] data;
    private int result;
    
    public MainWindow() {
        initComponents();
        TaskMgr mgr = new TaskMgr();
        int result = 0;
        try {
            result = mgr.getSize();
        } catch (TaskSvcException e) {
            result = 0;
        } try {
            //model.addRow(dataInfo(result));
            model = new DefaultTableModel(dataInfo(result), columnNames);
            assignmentTable.setModel(model);
        } catch (TaskSvcException e) {
            JOptionPane.showMessageDialog(this, "Cannot display table");
            /*model = new DefaultTableModel(null, columnNames);
            assignmentTable.setModel(model);*/
        }
    }
    
    public void setTableModel(DefaultTableModel tm){
        assignmentTable.setModel(tm);
    }
    
    public int getRow() throws TaskSvcException{
        try {
            TaskMgr mgr = new TaskMgr();
            result = mgr.getSize();
        } catch(Exception e) {
            System.out.println("Error" + e);
        }
        for (int i=0;i<result;i++){
            if(assignmentTable.isRowSelected(i)==true){
                return (Integer) assignmentTable.getValueAt(i, 0);
            }
        }
        return -1;
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        assignmentTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        createButton.setText("+");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        updateButton.setText("⟲");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("-");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        assignmentTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(assignmentTable);

        fileMenu.setText("File");
        jMenuBar1.add(fileMenu);

        editMenu.setText("Edit");
        jMenuBar1.add(editMenu);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(49, 49, 49)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 537, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(createButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(updateButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .add(deleteButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(120, 120, 120)
                        .add(createButton)
                        .add(32, 32, 32)
                        .add(deleteButton)
                        .add(32, 32, 32)
                        .add(updateButton)))
                .add(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        CreateTask createTask = new CreateTask(this);
        createTask.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTask.setVisible(true);
    }//GEN-LAST:event_createButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int id = 0;
        try {
            id = getRow();
        } catch (TaskSvcException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            TaskMgr mgr = new TaskMgr();
            System.out.println(id);
            task = mgr.retrieve(id);
            int reply = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete task# " + id);
            if (reply == JOptionPane.YES_OPTION) {
                    mgr.delete(task);
            } else {
                this.dispose();
            }
            int result = 0;
            try {
                result = mgr.getSize();
            } catch (TaskSvcException e) {
                result = 0;
            }
            model = new DefaultTableModel(dataInfo(result), columnNames);
            assignmentTable.setModel(model);
            
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        /*UpdateTask updateTask = new UpdateTask(this);
        updateTask.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateTask.setVisible(true);*/
    }//GEN-LAST:event_updateButtonActionPerformed
    
    public Object[][] dataInfo(int result) throws TaskSvcException{
        TaskMgr mgr = new TaskMgr();
        //System.out.println(result);
        if (result <= 0){
            System.out.println("No data in Matrix");
            return null;
        } else {
            int[] ID = mgr.getIdTable();
            String [] taskName = mgr.getTaskName();
            Date [] dueDate = mgr.getDueDate();
            data = new Object [result][3];
            for(int i=0;i<result;i++){
                data [i][0] = ID[i];
                data [i][1] = taskName[i];
                data [i][2] = dueDate[i];
            }
            return data;
        }
    }
    
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            /*for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }*/
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } catch (InstantiationException e) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } catch (IllegalAccessException e) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable assignmentTable;
    private javax.swing.JButton createButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}