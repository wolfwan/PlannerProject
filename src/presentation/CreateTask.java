package presentation;

import business.TaskMgr;
import domain.Task;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import services.TaskSvcException;

public class CreateTask extends javax.swing.JFrame {
    private MainWindow parentWindow;
    private final String filename = "Id";
    
    public CreateTask(MainWindow parentWindow){
        initComponents();
        this.parentWindow = parentWindow;
    }
    
    private ObjectInputStream getInputStream() throws Exception{
        return new ObjectInputStream(new FileInputStream (filename));
    }
    private ObjectOutputStream getOutputStream() throws Exception{
        return new ObjectOutputStream(new FileOutputStream (filename));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        assignmentTextField = new javax.swing.JTextField();
        assignmentLabel = new javax.swing.JLabel();
        dueDateTextField = new com.toedter.calendar.JDateChooser();
        dueDateLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        assignmentTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignmentTextFieldActionPerformed(evt);
            }
        });

        assignmentLabel.setText("Assignment Name:");

        dueDateLabel.setText("Due Date:");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(48, 48, 48)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(assignmentLabel)
                    .add(dueDateLabel)
                    .add(assignmentTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .add(dueDateTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(okButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(cancelButton)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(36, 36, 36)
                .add(assignmentLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(assignmentTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(37, 37, 37)
                .add(dueDateLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dueDateTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 50, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cancelButton)
                    .add(okButton))
                .add(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public int getId()throws TaskSvcException{
        int id;
        try{
            try{
                ObjectInputStream ois = getInputStream();
                //List<Task> tasks = (List<Task>) ois.readObject();
                //id = tasks.size();
                id = (Integer) ois.readObject();
                ois.close();
                ObjectOutputStream oos = getOutputStream();
                oos.writeObject(++id);
                oos.flush();
                oos.close();
                return id;
            }catch(Exception e){
                //System.out.println("Error");
                id = 1;
            }
            ObjectOutputStream oos = getOutputStream();
            oos.writeObject(id);
            oos.flush();
            oos.close();
        }catch(Exception e){
            throw new TaskSvcException("Error in Services: TaskSvcSioImpl: getId: Cannot Retrieve ID", e);
        }
        return id;
    } 
    
    private void assignmentTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignmentTextFieldActionPerformed

    }//GEN-LAST:event_assignmentTextFieldActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        Task task = new Task();
        TaskMgr mgr = new TaskMgr();
        int id = 0;
        try {
            id = getId();
        } catch (Exception e) {
            System.out.println("Caught Exception: 142");
            id = 1;
        }
        String taskName = assignmentTextField.getText();
        Date d1 = dueDateTextField.getDate();
        //String dueDate = d1.toString();
        task.setTaskName(taskName);
        task.setDueDate(d1);
        if (id == 0){
            task.setId(1);
        } else {
            //if (id already exists)
            task.setId(id);
        }
        if(task.validate()==true){
            try { 
                mgr.create(task);
                int result;
                try {
                    result = mgr.getSize();
                } catch(TaskSvcException e) {
                    result = 0;
                    System.out.println("Error in create task");
                }
                try {
                    DefaultTableModel model = new DefaultTableModel(parentWindow.dataInfo(result), parentWindow.columnNames);
                    parentWindow.setTableModel(model);
                    JOptionPane.showMessageDialog(this, "Your assignment has been saved");
                    this.dispose();
                } catch(TaskSvcException e){
                    System.out.println("Error in create task");
                }
            } catch (TaskSvcException e) {
                System.out.println("caught exception: 159");;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid assigment");
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
        /*JFrame mainWindow = new MainWindow();
        //updateTask.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWindow.setVisible(true);*/
    }//GEN-LAST:event_cancelButtonActionPerformed


    public void main(String args[]) {
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
                new CreateTask(parentWindow).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel assignmentLabel;
    private javax.swing.JTextField assignmentTextField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel dueDateLabel;
    private com.toedter.calendar.JDateChooser dueDateTextField;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
