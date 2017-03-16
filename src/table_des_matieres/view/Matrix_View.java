package table_des_matieres.view;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import table_des_matieres.lib.panel.RoundedPanel;

import java.awt.SystemColor;
import java.awt.Color;


public class Matrix_View extends JFrame implements ListSelectionListener{
	static final String MATRIX_DB_CONNECTION = "jdbc:sqlite:matrix.db";
	private Connection connection;
    private CustomModel tablemodel;
    private TableRowSorter<TableModel> sorterJTable;
    public int selectedRow;

	private JPanel contentPane;
	private static class CustomModel extends DefaultTableModel {

        private static final String RATIO = "RATIO";
		private static final String CI = "CI";
		private static final String AGENT = "AGENT";
		private final String[] columnNames = {java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("INDEX"), java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString(AGENT), java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString(CI), java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString(RATIO)};

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

	public Matrix_View() {
        initComponents();

        this.setLocationRelativeTo(null);
        sorterJTable = new TableRowSorter<TableModel>(jTable2.getModel());
        jTable2.setRowSorter(sorterJTable);

        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumn col = jTable2.getColumnModel().getColumn(0);
        //jTable2.getColumnModel().removeColumn(col);
        int width = 100;
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

    private Object[] newRow(String id, String agent, String ci, String ratio) {

        return new Object[]{id, agent, ci, ratio};
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
            buttonDeleteRatio.setEnabled(false);
            jButton3.setEnabled(false);
        } else {
            selectedRow = lsm.getMinSelectionIndex();
            buttonDeleteRatio.setEnabled(true);
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

            connection = DriverManager.getConnection(MATRIX_DB_CONNECTION);

            statement = connection.createStatement();
            try {
                rs = statement.executeQuery(
                        "SELECT ELEMENT_ID, CI, AGENT, RATIO FROM ELEMENT ORDER BY ELEMENT_ID");
                int i = 0;
                while (rs.next()) {
                    tablemodel.addRow(newRow(rs.getString("element_id"),  rs.getString("agent"), rs.getString("ci"), rs.getString("ratio")));
                    i++;
                }
                tablemodel.fireTableDataChanged();
                rs.close();
            } catch (SQLException e) {
                statement.executeUpdate("CREATE TABLE ELEMENT (ELEMENT_ID INTEGER PRIMARY KEY, AGENT TEXT, CI TEXT, RATIO TEXT);");
                connection.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new RoundedPanel();
        labelAgent = new javax.swing.JLabel();
        textFieldAgent = new javax.swing.JTextField();
        labelCI = new javax.swing.JLabel();
        textFieldCI = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
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
        buttonQuit = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        buttonAddRatio = new javax.swing.JButton();
        buttonDeleteRatio = new javax.swing.JButton();
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
        setPreferredSize(null);

        jPanel3.setBackground(SystemColor.activeCaptionBorder);
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(109, 40, 40)));
        jPanel3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel3ComponentResized(evt);
            }
        });

        jPanel2.setBackground(new Color(105, 105, 105));
        jPanel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5,5,5,5), "Search"));

        labelAgent.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        labelAgent.setForeground(new java.awt.Color(253, 250, 239));
        labelAgent.setText(bundle.getString("TableDesMatieres_View.jLabel2.text")); // NOI18N
        labelAgent.setMaximumSize(null);

        textFieldAgent.setDocument(new PlainDocument() {
            public void insertString(int offset, String str, AttributeSet attr) throws
            BadLocationException {
                if (str != null) {
                    super.insertString(offset, str, attr);
                }
            }
        });
        textFieldAgent.setColumns(40);
        textFieldAgent.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        textFieldAgent.setMaximumSize(null);
        textFieldAgent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        labelCI.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        labelCI.setForeground(new java.awt.Color(253, 250, 239));
        labelCI.setText(bundle.getString("TableDesMatieres_View.jLabel3.text")); // NOI18N
        labelCI.setMaximumSize(null);

        textFieldCI.setDocument(new PlainDocument() {
            public void insertString(int offset, String str, AttributeSet attr) throws
            BadLocationException {
                if (str != null) {
                    super.insertString(offset, str, attr);
                }
            }
        });
        textFieldCI.setColumns(15);
        textFieldCI.setFont(new java.awt.Font("VERDANA", 0, 14));
        textFieldCI.setMaximumSize(null);
        textFieldCI.setMinimumSize(new java.awt.Dimension(50, 10));
        textFieldCI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/broken_chain.png"))); // NOI18N
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("table_des_matieres/view/Bundle"); // NOI18N
        jToggleButton1.setText(bundle1.getString("TableDesMatieres_View.jToggleButton1.text")); // NOI18N
        jToggleButton1.setToolTipText(bundle.getString("LIEN BRISE")); // NOI18N
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldAgent, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldCI, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(labelAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldCI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {textFieldAgent, textFieldCI});

        jPanel1.setBackground(SystemColor.activeCaption);
        jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5,5,5,5), java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("PANNEAU_TABLEAU")));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        buttonQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/door_out.png"))); // NOI18N
        buttonQuit.setToolTipText(bundle.getString("TableDesMatieres_View.jButton4.toolTipText")); // NOI18N
        buttonQuit.setFocusable(false);
        buttonQuit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonQuit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonQuit);
        jToolBar1.add(jSeparator1);

        buttonAddRatio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/add.png"))); // NOI18N
        buttonAddRatio.setToolTipText(bundle.getString("TableDesMatieres_View.jButton1.toolTipText")); // NOI18N
        buttonAddRatio.setFocusable(false);
        buttonAddRatio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAddRatio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAddRatio.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        buttonAddRatio.setIconTextGap(0);
        buttonAddRatio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonAddRatio);

        buttonDeleteRatio.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        buttonDeleteRatio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table_des_matieres/image/delete.png"))); // NOI18N
        buttonDeleteRatio.setToolTipText(bundle.getString("TableDesMatieres_View.jButton2.toolTipText")); // NOI18N
        buttonDeleteRatio.setEnabled(false);
        buttonDeleteRatio.setIconTextGap(0);
        buttonDeleteRatio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonDeleteRatio);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }

    public Matrix_View(CustomModel tablemodel) {
        this.tablemodel = tablemodel;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        int i;
        int[] rows = jTable2.getSelectedRows();
        int nbremoved = 0;

        try {
            connection = DriverManager.getConnection(MATRIX_DB_CONNECTION);

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
    }

    private void jMenuItem2MouseReleased(java.awt.event.MouseEvent evt) {
        Ratio_View jdae = new Ratio_View(this, Ratio_View.TypeDialog.AJOUT);
        jdae.setVisible(true);
    }

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {
        this.newFilter(new String[]{ textFieldAgent.getText(), textFieldCI.getText()});
    }

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {
        this.newFilter(new String[]{ textFieldAgent.getText(), textFieldCI.getText()});
    }

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() >= 2) {
            Ratio_View jdla = new Ratio_View(this, Ratio_View.TypeDialog.MODIF);
            jdla.setVisible(true);
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            connection = DriverManager.getConnection(MATRIX_DB_CONNECTION);

            Statement statement = getConnection().createStatement();
            ResultSet ret = statement.executeQuery("SELECT ELEMENT_ID, AGENT, CI, RATIO"
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

                if (Desktop.isDesktopSupported()) {
                    Desktop bureau = Desktop.getDesktop();
                    if (bureau.isSupported(Desktop.Action.OPEN)) {
                        try {
                            File toOpen = new File(chemin);
                            bureau.open(toOpen);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                //Runtime.getRuntime().exec(new String[]{commandStr, chemin});
                ret.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("ERREUR RUNTIME"));
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Ratio_View jdae = new Ratio_View(this, Ratio_View.TypeDialog.AJOUT);
        jdae.setVisible(true);
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
        Apropos_View apropos = new Apropos_View(this, true);
        apropos.setVisible(true);
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {
        ApplicationPreferences_View pref = new ApplicationPreferences_View(this, false);
        pref.setVisible(true);
        pref.setLocationRelativeTo(null);
    }

    private void jPanel3ComponentResized(java.awt.event.ComponentEvent evt) {
        this.pack();
    }

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        AbstractButton abstractButton = (AbstractButton) evt.getSource();
        boolean selected = abstractButton.isSelected();

        if (selected == true) {
            try {
                System.out.println("Selected");

                connection = DriverManager.getConnection(MATRIX_DB_CONNECTION);

                Statement statement = getConnection().createStatement();
                ResultSet ret = statement.executeQuery("SELECT ELEMENT_ID, AGENT, CI, RATIO "
                        + "FROM ELEMENT ");

                StringBuilder sb = new StringBuilder();
                //for(int i=0; i < data.getRowCount(); i++) {
                //  String idS = (String)data.getValueAt(i, 0);
                //if(i==0) {
                //  sb.append("element_id="+idS);
                //} else {
                //  sb.append(" or element_id="+idS);
                //}

//                }
                //System.out.println(sb);
            } catch (SQLException ex) {
                Logger.getLogger(Matrix_View.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Not selected");
        }
    }

    private void newFilter(final String[] filter) {
   
    }

    private javax.swing.JButton buttonAddRatio;
    private javax.swing.JButton buttonDeleteRatio;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton buttonQuit;
    private javax.swing.JLabel labelAgent;
    private javax.swing.JLabel labelCI;
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
    private javax.swing.JTextField textFieldAgent;
    private javax.swing.JTextField textFieldCI;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    private java.awt.ScrollPane scrollPane1;

    void displaySQLErrors(SQLException e) {
        System.out.println(java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("SQLEXCEPTION: ") + e.getMessage());
        System.out.println(java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("SQLSTATE: ") + e.getSQLState());
        System.out.println(java.util.ResourceBundle.getBundle("table_des_matieres/Bundle").getString("VENDORERROR: ") + e.getErrorCode());
    }
}
