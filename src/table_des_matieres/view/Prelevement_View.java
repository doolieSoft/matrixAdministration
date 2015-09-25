/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JDialogAjouterElement.java
 *
 * Created on 08-avr.-2011, 19:02:05
 */

package table_des_matieres.view;


import java.util.ResourceBundle;
import table_des_matieres.lib.autocompletepack.AutoCompleteDecorator;
import table_des_matieres.lib.panel.RoundedPanel;
import table_des_matieres.lib.MyUtil;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import java.util.Date;
import java.util.Calendar;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
/**
 *
 * @author fabrice
 */
public class Prelevement_View extends javax.swing.JDialog {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("table_des_matieres/Bundle");

    /** Creates new form JDialogAjouterElement */
    TableDesMatieres_View parentFrame;
    int idSelected;
    public enum TypeDialog { AJOUT, MODIF};
    TypeDialog typeDialog;
    public Prelevement_View(TableDesMatieres_View parent, TypeDialog td) {
        super(parent, true);
        initComponents();
        initAutoComplete();
        parentFrame=parent;
        setLocationRelativeTo(null);
        typeDialog=td;
        switch(typeDialog) {
            case AJOUT:
                    jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), bundle.getString("AJOUTER UN PRÉLÈVEMENT")));
                break;
            case MODIF:
                    jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), bundle.getString("MODIFIER LE PRÉLÈVEMENT")));
                    jButtonAjouter.setText(bundle.getString("MODIFIER"));

                    idSelected = parentFrame.getJTable2().convertRowIndexToModel(parentFrame.getJTable2().getSelectedRow());
                    
                    String date = parentFrame.getJTable2().getModel().getValueAt(idSelected, 1).toString();
                    if(date.equals("")) {
                        jTextFieldJour.setText("");
                        jTextFieldMois.setText("");
                        jTextFieldAnnee.setText("");
                    }
                    else {
                        String[] dateSplit = date.split("/");
                        jTextFieldJour.setText(dateSplit[2]);
                        jTextFieldMois.setText(dateSplit[1]);
                        jTextFieldAnnee.setText(dateSplit[0]);
                    }
                    String nom = parentFrame.getJTable2().getModel().getValueAt(idSelected, 2).toString();
                    String type = parentFrame.getJTable2().getModel().getValueAt(idSelected, 3).toString();
                    String casier = parentFrame.getJTable2().getModel().getValueAt(idSelected, 4).toString();
                    
                    jTextField2.setText(nom);
                    jTextField3.setText(type);
                    jTextField4.setText(casier);
                
                try {
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:table_des_matieres.db");

                    Statement statement = connection.createStatement();
                    ResultSet ret = statement.executeQuery("SELECT ELEMENT_ID, DATE, NOM, TYPE, CASIER, CHEMIN "+
                                                    "FROM ELEMENT "+
                                                    "WHERE ELEMENT_ID = '"+parentFrame.getJTable2().getModel().getValueAt(parentFrame.getJTable2().getSelectedRow(), 0).toString()+"' ");
                    ret.next();

                    jTextField5.setText(ret.getString("chemin"));
                    statement.close();
                    connection.close();
                }
                catch(SQLException ex) {
                    ex.printStackTrace();
                }

                break;
        }
    }

    private void initAutoComplete() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:table_des_matieres.db");

            Statement statement = connection.createStatement();
            ResultSet ret = statement.executeQuery("SELECT DISTINCT NOM, TYPE, CASIER FROM ELEMENT");
             
            ArrayList listeNom= new ArrayList();
            ArrayList listeType= new ArrayList();
            ArrayList listeCasier= new ArrayList();

            while(ret.next()) {
                 listeNom.add(ret.getString("nom"));
                 listeType.add(ret.getString("type"));
                 listeCasier.add(ret.getString("casier"));
            }
            AutoCompleteDecorator.decorate(jTextField2,listeNom, false);
            AutoCompleteDecorator.decorate(jTextField3,listeType, false);
            AutoCompleteDecorator.decorate(jTextField4,listeCasier, false);

            ret.close();

        }
        catch(SQLException e) {

        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldJour = new javax.swing.JTextField();
        jTextFieldMois = new javax.swing.JTextField();
        jTextFieldAnnee = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButtonAjouter = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(97, 210, 110));

        jLabel1.setText(bundle.getString("DATE (JJ MM AAAA)")); // NOI18N

        jTextFieldJour.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldJourKeyReleased(evt);
            }
        });

        jTextFieldMois.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldMoisKeyReleased(evt);
            }
        });

        jTextFieldAnnee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAnneeKeyReleased(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel2.setText(bundle.getString("NOM")); // NOI18N

        jLabel3.setText(bundle.getString("TYPE")); // NOI18N

        jLabel4.setText(bundle.getString("CASIER")); // NOI18N

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jButtonAjouter.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.foreground"));
        jButtonAjouter.setText(bundle.getString("AJOUTER")); // NOI18N
        jButtonAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterActionPerformed(evt);
            }
        });

        jLabel5.setText(bundle.getString("CHEMIN")); // NOI18N

        jButton1.setText(bundle.getString("CHEMIN D'ACCÈS AU FICHIER")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(bundle.getString("ANNULER")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/accept.png"))); // NOI18N
        jLabel7.setFocusable(false);
        jLabel7.setVisible(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldJour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMois, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(11, 11, 11))
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButtonAjouter)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2))
                        .addComponent(jButton1)))
                .addGap(63, 63, 63))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldJour, jTextFieldMois});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButtonAjouter});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMois, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldJour, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButtonAjouter))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButtonAjouter, jTextField2, jTextField3, jTextField4, jTextField5, jTextFieldAnnee, jTextFieldJour, jTextFieldMois});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldJourKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldJourKeyReleased
        // TODO add your handling code here:
        int keyCode=evt.getKeyCode();
        String text = jTextFieldJour.getText();
        int size = text.length();
        try {
            if (size<2) {
            }
            else {
                if(jTextFieldJour.getText().length()==2) {
                    Integer.parseInt(jTextFieldJour.getText());
                    jTextFieldJour.transferFocus();
                }
                else {
                    jTextFieldJour.setText(text.substring(0,2));
                    Integer.parseInt(jTextFieldJour.getText());
                }
            }
        }
        catch (NumberFormatException ex) {
                jTextFieldJour.setText("");
        }
}//GEN-LAST:event_jTextFieldJourKeyReleased

    private void jTextFieldMoisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMoisKeyReleased
        // TODO add your handling code here:
        String text = jTextFieldMois.getText();
        int size = text.length();
        try {
            if (size<2) {
            }
            else {
                if(jTextFieldMois.getText().length()==2) {
                    Integer.parseInt(jTextFieldMois.getText());
                    jTextFieldMois.transferFocus();
                }
                else {
                    jTextFieldMois.setText(text.substring(0,2));
                    Integer.parseInt(jTextFieldMois.getText());
                }
                if(evt.getKeyCode()==10) {
                    jTextFieldMois.transferFocus();
                }

            }
        }
        catch (NumberFormatException ex) {
                    jTextFieldMois.setText("");
        }
}//GEN-LAST:event_jTextFieldMoisKeyReleased

    private void jTextFieldAnneeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAnneeKeyReleased
        // TODO add your handling code here:

        String text = jTextFieldAnnee.getText();
        int size = text.length();
        try {
            if (size<4) {
            }
            else {
                if(jTextFieldAnnee.getText().length()==4) {
                    Integer.parseInt(jTextFieldAnnee.getText());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    formatter.setLenient(false);
                    formatter.parse(jTextFieldJour.getText()+"/"+jTextFieldMois.getText()+ "/" +jTextFieldAnnee.getText());

                    jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/accept.png"))); // NOI18N
                    jLabel7.setVisible(true);
                    jTextFieldAnnee.transferFocus();
                }
                else {
                    jTextFieldAnnee.setText(text.substring(0,4));
                    Integer.parseInt(jTextFieldAnnee.getText());
                }
                int keyCode=evt.getKeyCode();
                switch(keyCode) {
                    case 10:
                    case 40:jTextField2.requestFocusInWindow();
                    break;
                    
                }
            }
        }
        catch (ParseException e) {
            jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/cross.png"))); // NOI18N
            jLabel7.setVisible(true);
        }
        catch (NumberFormatException ex) {
            jTextFieldAnnee.setText("");
        }
}//GEN-LAST:event_jTextFieldAnneeKeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        int keyCode=evt.getKeyCode();
        switch(keyCode) {
            case 10:
            case 40:jTextField2.transferFocus();
            break;
            case 38:jTextFieldJour.requestFocusInWindow();
        }
}//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:
        int keyCode=evt.getKeyCode();
        switch(keyCode) {
            case 10:
            case 40:jButton1.requestFocusInWindow();
            break;
            case 38:jTextField3.requestFocusInWindow();;
            break;
        }
}//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
        int keyCode=evt.getKeyCode();
        switch(keyCode) {
            case 10:
            case 40:jTextField4.requestFocusInWindow();
            break;
            case 38:jTextField2.requestFocusInWindow();;
            break;
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jButtonAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterActionPerformed
        // TODO add your handling code here:

        String datePrelevement ;
        String datePrelevementFranco ;
        try {
           if(jTextFieldJour.getText().equals("") && jTextFieldMois.getText().equals("") && jTextFieldAnnee.getText().equals("")) {
               datePrelevement="";
           }
           else {
               SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               formatter.setLenient(false);
               datePrelevementFranco=jTextFieldJour.getText()+"/"+jTextFieldMois.getText()+"/"+jTextFieldAnnee.getText();
               datePrelevement=jTextFieldAnnee.getText()+"/"+jTextFieldMois.getText()+"/"+jTextFieldJour.getText();
               formatter.parse(datePrelevementFranco);

               jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/accept.png"))); // NOI18N
               jLabel7.setVisible(true);
            }

            if (evt.getActionCommand().equals(bundle.getString("AJOUTER"))) {

                    Connection connection = DriverManager.getConnection("jdbc:sqlite:table_des_matieres.db");

                    
                    
                    jTextField2.setText(MyUtil.toUpperCaseExceptµ(jTextField2.getText()));
                    jTextField3.setText(MyUtil.toUpperCaseExceptµ(jTextField3.getText()));
                    jTextField4.setText(MyUtil.toUpperCaseExceptµ(jTextField4.getText()));

                    Statement statement = connection.createStatement();
                    int ret = statement.executeUpdate("INSERT INTO ELEMENT(DATE, NOM, TYPE, CASIER, CHEMIN) VALUES(" +
                            "'" + datePrelevement+"', " +
                           "'" + this.jTextField2.getText() + "', " +
                           "'" + this.jTextField3.getText() + "', " +
                           "'" + this.jTextField4.getText() + "', " +
                           "'" + this.jTextField5.getText().trim() + "' )");
                    System.out.println("INSERT INTO ELEMENT(DATE, NOM, TYPE, CASIER, CHEMIN) VALUES(" +
                            "'" + datePrelevement+"', " +
                           "'" + this.jTextField2.getText()+ "', " +
                           "'" + this.jTextField3.getText() + "', " +
                           "'" + this.jTextField4.getText() + "', " +
                           "'" + this.jTextField5.getText().trim() + "'");
                    connection.close();
                    parentFrame.LoadData();
                    this.dispose();
            }
            else {
                if (evt.getActionCommand().equals(bundle.getString("MODIFIER"))) {
                        Connection connection = DriverManager.getConnection("jdbc:sqlite:table_des_matieres.db");

                        Statement statement = connection.createStatement();
                        String query = "UPDATE ELEMENT SET DATE='"+datePrelevement+"'"+
                                ", NOM='" + MyUtil.toUpperCaseExceptµ(jTextField2.getText()) + "'"+
                                ", TYPE='" + MyUtil.toUpperCaseExceptµ(jTextField3.getText()) + "'"+
                                ", CASIER='" + MyUtil.toUpperCaseExceptµ(jTextField4.getText()) + "' "+
                                ", CHEMIN='" + MyUtil.toUpperCaseExceptµ(jTextField5.getText()).trim() + "' "+
                                " WHERE ELEMENT_ID="+parentFrame.getJTable2().getModel().getValueAt(idSelected, 0);

                        int ret = statement.executeUpdate(query);
                        while(parentFrame.getTableModel().getRowCount()>0) {
                            parentFrame.getTableModel().removeRow(0);
                        }
                        connection.close();
                        parentFrame.LoadData();
                        this.dispose();
                }
            }
            }
             catch (ParseException e) {
                jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/cross.png"))); // NOI18N
                jLabel7.setVisible(true);
            }
             catch(SQLException insertException) {
                insertException.printStackTrace();
            }

}//GEN-LAST:event_jButtonAjouterActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(evt.getActionCommand().equals(bundle.getString("CHEMIN D'ACCÈS AU FICHIER"))) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(this);
            try {
                String path = chooser.getSelectedFile().getAbsolutePath();
                jTextField5.setText(path);
            }
            catch(NullPointerException e) {
                jTextField5.setText("");
            }
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
    * @param args the command line arguments
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextFieldAnnee;
    private javax.swing.JTextField jTextFieldJour;
    private javax.swing.JTextField jTextFieldMois;
    // End of variables declaration//GEN-END:variables

}
