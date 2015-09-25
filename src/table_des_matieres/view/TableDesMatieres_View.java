/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TableDesMatieres_View.java
 *
 * Created on 24-mars-2011, 18:50:40
 */
package table_des_matieres.view;

import table_des_matieres.lib.panel.RoundedPanel;
import table_des_matieres.lib.MyUtil;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.table.TableRowSorter;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.RowFilter;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import javax.swing.table.TableColumn;
import javax.swing.text.*;
import java.awt.Desktop;
/**
 *
 * @author fabrice
 */
public class TableDesMatieres_View extends JFrame implements ListSelectionListener {

    private Connection connection;
    private CustomModel tablemodel;
    private TableRowSorter<TableModel> sorterJTable;
    public int selectedRow;

    private static class CustomModel extends DefaultTableModel {

        private final String[] columnNames = {java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("INDEX"), java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("DATE"), java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("NOM"), java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("TYPE"), java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("CASIER")};

        @Override
        public Class<?> getColumnClass(int col) {
            return super.getColumnClass(col);
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }
    }

    /** Creates new form TableDesMatieres_View */
    public TableDesMatieres_View() {
        initComponents();

        this.setLocationRelativeTo(null);
        sorterJTable = new TableRowSorter<TableModel>(jTable2.getModel());
        jTable2.setRowSorter(sorterJTable);

        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumn col = jTable2.getColumnModel().getColumn(0);
        jTable2.getColumnModel().removeColumn(col);
        TableColumn colDate = jTable2.getColumnModel().getColumn(0);
        int width = 100;
        colDate.setPreferredWidth(width);
        TableColumn colNom = jTable2.getColumnModel().getColumn(1);
        width = 240;
        colNom.setPreferredWidth(width);
        TableColumn colType = jTable2.getColumnModel().getColumn(2);
        width = 440;
        colType.setPreferredWidth(width);

        ListSelectionModel listSelectionModel = jTable2.getSelectionModel();
        listSelectionModel.addListSelectionListener(this);

        try {
            Class.forName("org.sqlite.JDBC");
            LoadData();
        } catch (Exception E) {
            E.printStackTrace();
        }

    }

    private Object[] newRow(String id, String date, String Nom, String Type, String Casier) {

        return new Object[]{id, date, Nom, Type, Casier};
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public JTable getJTable2() {
        return jTable2;
    }

    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent.getValueIsAdjusting()) {
            return;
        }
        ListSelectionModel lsm = (ListSelectionModel) listSelectionEvent.getSource();
        if (lsm.isSelectionEmpty()) {
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
        } else {
            selectedRow = lsm.getMinSelectionIndex();
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public DefaultTableModel getTableModel() {
        return tablemodel;
    }

    public void LoadData() {
        ResultSet rs = null;
        Statement statement = null;
        try {
            while (tablemodel.getRowCount() > 0) {
                tablemodel.removeRow(0);
            }

            connection = DriverManager.getConnection("jdbc:sqlite:table_des_matieres.db");

            statement = connection.createStatement();
            try {
                rs = statement.executeQuery(
                        "SELECT ELEMENT_ID, DATE, TYPE, NOM, CASIER FROM ELEMENT ORDER BY ELEMENT_ID");
                int i = 0;
                while (rs.next()) {
                    tablemodel.addRow(newRow(rs.getString("element_id"), rs.getString("date"), rs.getString("nom"), rs.getString("type"), rs.getString("casier")));
                    i++;
                }
                tablemodel.fireTableDataChanged();
                rs.close();
            } catch (SQLException e) {
                statement.executeUpdate("CREATE TABLE ELEMENT (CHEMIN TEXT, ELEMENT_ID INTEGER PRIMARY KEY, NOM TEXT, DATE TEXT, TYPE TEXT, CASIER TEXT);");
                connection.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new RoundedPanel();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        Object[][] dataTable = new Object [][]{};
        //String[] columnsName = new String [] {
            //                "Id", "Date", "Nom", "Type", "Casier"
            //            };
        tablemodel = new CustomModel (){
            public boolean isCellEditable(int iRowIndex, int iColumnIndex)
            {
                return false;
            }
        };
        jTable2 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("table_des_matieres/Bundle"); // NOI18N
        setTitle(bundle.getString("TableDesMatieres_View.title")); // NOI18N
        setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(208, 141, 97));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(109, 40, 40)));
        jPanel3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel3ComponentResized(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(135, 86, 128));
        jPanel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5,5,5,5), "Rechercher"));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(253, 250, 239));
        jLabel1.setText(bundle.getString("TableDesMatieres_View.jLabel1.text")); // NOI18N

        jTextField1.setColumns(12);
        jTextField1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jTextField1.setMargin(null);
        jTextField1.setMaximumSize(null);
        jTextField1.setMinimumSize(new java.awt.Dimension(10, 50));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(253, 250, 239));
        jLabel2.setText(bundle.getString("TableDesMatieres_View.jLabel2.text")); // NOI18N
        jLabel2.setMaximumSize(null);

        jTextField2.setDocument(new PlainDocument() {
            public void insertString(int offset, String str, AttributeSet attr) throws
            BadLocationException {
                if (str != null) {
                    super.insertString(offset, str, attr);
                }
            }
        });
        jTextField2.setColumns(40);
        jTextField2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jTextField2.setMaximumSize(null);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(253, 250, 239));
        jLabel3.setText(bundle.getString("TableDesMatieres_View.jLabel3.text")); // NOI18N
        jLabel3.setMaximumSize(null);

        jTextField3.setDocument(new PlainDocument() {
            public void insertString(int offset, String str, AttributeSet attr) throws
            BadLocationException {
                if (str != null) {
                    super.insertString(offset, str, attr);
                }
            }
        });
        jTextField3.setColumns(15);
        jTextField3.setFont(new java.awt.Font("VERDANA", 0, 14));
        jTextField3.setMaximumSize(null);
        jTextField3.setMinimumSize(new java.awt.Dimension(50, 10));
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField1, jTextField2, jTextField3});

        jPanel1.setBackground(new java.awt.Color(97, 210, 110));
        jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5,5,5,5), java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("LISTE DES PRÉLÈVEMENTS")));

        jScrollPane1.setMinimumSize(new java.awt.Dimension(22, 16));

        jTable2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jTable2.setModel(tablemodel);
        sorterJTable = new TableRowSorter<TableModel>(jTable2.getModel());
        jTable2.setRowSorter(sorterJTable);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);
        jTable2.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    int viewRow = jTable2.getSelectedRow();
                    if (viewRow < 0) {
                        //Selection got filtered away.
                        //statusText.setText("");
                    }
                    else {
                        int modelRow =
                        jTable2.convertRowIndexToModel(viewRow);
                        //statusText.setText(
                            //String.format("Selected Row in view: %d. " +
                                //"Selected Row in model: %d.",
                                //viewRow, modelRow));
                    }
                }
            }
        );

        scrollPane1.add(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setToolTipText(bundle.getString("TableDesMatieres_View.jToolBar1.toolTipText")); // NOI18N
        jToolBar1.setBorderPainted(false);
        jToolBar1.setMinimumSize(new java.awt.Dimension(24, 24));
        jToolBar1.setPreferredSize(new java.awt.Dimension(24, 24));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/door_out.png"))); // NOI18N
        jButton4.setToolTipText(bundle.getString("TableDesMatieres_View.jButton4.toolTipText")); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);
        jToolBar1.add(jSeparator1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/add.png"))); // NOI18N
        jButton1.setToolTipText(bundle.getString("TableDesMatieres_View.jButton1.toolTipText")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jButton1.setIconTextGap(0);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/delete.png"))); // NOI18N
        jButton2.setToolTipText(bundle.getString("TableDesMatieres_View.jButton2.toolTipText")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setIconTextGap(0);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/book_open.png"))); // NOI18N
        jButton3.setToolTipText(bundle.getString("TableDesMatieres_View.jButton3.toolTipText")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jButton3.setEnabled(false);

        jButton3.setIconTextGap(0);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jMenuBar1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jMenu1.setText(bundle.getString("TableDesMatieres_View.jMenu1.text")); // NOI18N

        jMenuItem6.setText(bundle.getString("TableDesMatieres_View.jMenuItem6.text")); // NOI18N
        jMenu1.add(jMenuItem6);

        jMenuItem5.setText(bundle.getString("TableDesMatieres_View.jMenuItem5.text")); // NOI18N
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);
        jMenu1.add(jSeparator3);

        jMenuItem4.setText(bundle.getString("TableDesMatieres_View.jMenuItem4.text")); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText(bundle.getString("TableDesMatieres_View.jMenuItem1.text")); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(bundle.getString("TableDesMatieres_View.jMenu2.text")); // NOI18N

        jMenuItem2.setText(bundle.getString("TableDesMatieres_View.jMenuItem2.text")); // NOI18N
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public TableDesMatieres_View(CustomModel tablemodel) {
        this.tablemodel = tablemodel;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        int i;

        int[] rows = jTable2.getSelectedRows();
        int nbremoved = 0;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:table_des_matieres.db");

            Statement statement = connection.createStatement();

            for (i = 0; i < rows.length; i++) {
                int ret = statement.executeUpdate("DELETE FROM ELEMENT WHERE ELEMENT_ID = '" + tablemodel.getValueAt(jTable2.convertRowIndexToModel(rows[i]), 0) + "'");
            }

            connection.close();
            LoadData();
        } catch (SQLException insertException) {
            insertException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == 8) {
            this.newFilter(new String[]{jTextField1.getText(), jTextField2.getText(), jTextField3.getText()});
            return;
        }

        if (jTextField1.getText().length() == 4) {
            jTextField1.setText(jTextField1.getText() + '/');
        } else {
            if (jTextField1.getText().length() == 7) {
                jTextField1.setText(jTextField1.getText() + '/');
            }
        }
        this.newFilter(new String[]{jTextField1.getText(), jTextField2.getText(), jTextField3.getText()});
}//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jMenuItem2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseReleased
        // TODO add your handling code here:
        Prelevement_View jdae = new Prelevement_View(this, Prelevement_View.TypeDialog.AJOUT);
        jdae.setVisible(true);
    }//GEN-LAST:event_jMenuItem2MouseReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        this.newFilter(new String[]{jTextField1.getText(), jTextField2.getText(), jTextField3.getText()});
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
        this.newFilter(new String[]{jTextField1.getText(), jTextField2.getText(), jTextField3.getText()});
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() >= 2) {
            Prelevement_View jdla = new Prelevement_View(this, Prelevement_View.TypeDialog.MODIF);
            jdla.setVisible(true);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:table_des_matieres.db");

            Statement statement = getConnection().createStatement();
            ResultSet ret = statement.executeQuery("SELECT ELEMENT_ID, DATE, NOM, TYPE, CASIER, CHEMIN "
                    + "FROM ELEMENT "
                    + "WHERE ELEMENT_ID = '" + jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 0).toString() + "' ");
            ret.next();
            Scanner scanner = new Scanner(new FileInputStream("default_soft.inf"));
            try {
                StringBuilder command = new StringBuilder();
                scanner.hasNextLine();
                command.append(scanner.nextLine());
                String commandStr = command.toString();
                String chemin = ret.getString("chemin");


                if(Desktop.isDesktopSupported()) {
                    Desktop bureau = Desktop.getDesktop();
                    if(bureau.isSupported(Desktop.Action.OPEN)) {
                        try {
                            File toOpen=new File(chemin);
                            bureau.open(toOpen);
                        }
                        catch(Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                //Runtime.getRuntime().exec(new String[]{commandStr, chemin});
                ret.close();
                connection.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("ERREUR RUNTIME"));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        

        Prelevement_View jdae = new Prelevement_View(this, Prelevement_View.TypeDialog.AJOUT);
        jdae.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        Apropos_View apropos = new Apropos_View(this, true);
        apropos.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        ApplicationPreferences_View pref = new ApplicationPreferences_View(this, false);
        pref.setVisible(true);
        pref.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jPanel3ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel3ComponentResized
        // TODO add your handling code here:
        this.pack();
    }//GEN-LAST:event_jPanel3ComponentResized

    private void newFilter(final String[] filter) {
        List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(2);

        String[] typeSplit = filter[2].split(" ");
        try {
        filters.add(RowFilter.regexFilter("^" + MyUtil.toUpperCaseExceptµ(filter[0]), 1));
        filters.add(RowFilter.regexFilter("^" + MyUtil.toUpperCaseExceptµ(filter[1]), 2));
        for(int i=0;i<typeSplit.length;i++) {
            filters.add(RowFilter.regexFilter("(" + MyUtil.toUpperCaseExceptµ(typeSplit[i]) +")", 3));
        }
        RowFilter<Object, Object> startsWithFilter = RowFilter.andFilter(filters);

        
            sorterJTable.setRowFilter(startsWithFilter);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                TableDesMatieres_View gcv = new TableDesMatieres_View();
                gcv.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JToolBar jToolBar1;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables

    void displaySQLErrors(SQLException e) {
        System.out.println(java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("SQLEXCEPTION: ") + e.getMessage());
        System.out.println(java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("SQLSTATE: ") + e.getSQLState());
        System.out.println(java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("VENDORERROR: ") + e.getErrorCode());
    }
}
