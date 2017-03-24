package main;/* See the file "LICENSE" for the full license governing this code. */

import DAO.DatabaseHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;


public class Main extends JFrame {
    /**
	 * 
	 */
    private static final long serialVersionUID = 5932098009134909723L;
    DatabaseHelper db;
    private JTable tableRatios = new JTable();
    JComboBox<Object> comboBoxCIs = new JComboBox<Object>();
    JComboBox<Object> comboBoxAgent = new JComboBox<Object>();
    JTextField txtRatio = new JTextField();
    private final JScrollPane scrollPane = new JScrollPane();


    public Main() {
        super("Matrix Administration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            db = new DatabaseHelper();
        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Problem occurs with the matrix loader",
                    "Error with matrix loader", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        JPanel mainPanel = new JPanel();

        GroupLayout group = new GroupLayout(getContentPane());
        group.setHorizontalGroup(group.createParallelGroup(Alignment.LEADING)
                .addGroup(
                        group.createSequentialGroup()
                                .addComponent(mainPanel,
                                        GroupLayout.DEFAULT_SIZE, 640,
                                        Short.MAX_VALUE).addGap(2)));
        group.setVerticalGroup(group.createParallelGroup(Alignment.LEADING)
                .addGroup(
                        Alignment.TRAILING,
                        group.createSequentialGroup()
                                .addComponent(mainPanel,
                                        GroupLayout.DEFAULT_SIZE, 395,
                                        Short.MAX_VALUE).addGap(4)));
        JButton btnAddCI = new JButton("Add");
        btnAddCI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ci = JOptionPane.showInputDialog(
                        ((JButton) e.getSource()).getParent(),
                        "Please enter the new CI", "Add a new CI");
                if (ci != null && !ci.equals("")) {
                    db.addCI(ci.toUpperCase());
                    comboBoxCIs.setEnabled(true);
                }

                refreshCIComboBox();
                refreshRatioTable();
            }
        });
        JButton btnDeleteCI = new JButton("Delete");
        btnDeleteCI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comboBoxCIs.getItemCount() > 0) {
                    db.delCI(comboBoxCIs.getSelectedItem().toString());
                    refreshRatioTable();
                    comboBoxCIs.removeItemAt(comboBoxCIs.getSelectedIndex());
                }
            }
        });
        JButton btnAddAgent = new JButton("Add");
        btnAddAgent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String agent = JOptionPane.showInputDialog(
                        ((JButton) e.getSource()).getParent(),
                        "Please enter the new Agent", "Add a new Agent");
                if (agent != null && !agent.equals("")) {
                    db.addAgent(agent.toUpperCase());
                    comboBoxAgent.setEnabled(true);
                }
                refreshAgentComboBox();
                refreshRatioTable();
            }
        });
        JButton btnDeleteAgent = new JButton("Delete");
        btnDeleteAgent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comboBoxAgent.getItemCount() > 0) {
                    db.delAgent(comboBoxAgent.getSelectedItem().toString());
                    refreshRatioTable();
                    comboBoxAgent.removeItemAt(comboBoxAgent.getSelectedIndex());
                }

            }
        });

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String agent = comboBoxAgent.getSelectedItem().toString();
                String ci = comboBoxCIs.getSelectedItem().toString();
                Integer ratio = Integer.parseInt(txtRatio.getText());
                db.updateRatio(agent, ci, ratio);
                refreshRatioTable();
            }
        });

        txtRatio.setColumns(10);

        JLabel lblCI = new JLabel("CI");
        lblCI.setLabelFor(comboBoxCIs);

        JLabel lblAgent = new JLabel("Agent");
        lblAgent.setLabelFor(comboBoxAgent);

        JLabel lblRatio = new JLabel("Ratio");
        lblRatio.setLabelFor(txtRatio);
        GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
        gl_mainPanel
                .setHorizontalGroup(gl_mainPanel
                        .createParallelGroup(Alignment.TRAILING)
                        .addGroup(
                                gl_mainPanel
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                gl_mainPanel
                                                        .createParallelGroup(
                                                                Alignment.LEADING)
                                                        .addComponent(
                                                                scrollPane,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                738,
                                                                Short.MAX_VALUE)
                                                        .addGroup(
                                                                gl_mainPanel
                                                                        .createSequentialGroup()
                                                                        .addGroup(
                                                                                gl_mainPanel
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                lblAgent)
                                                                                        .addComponent(
                                                                                                lblRatio)
                                                                                        .addComponent(
                                                                                                lblCI,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(18)
                                                                        .addGroup(
                                                                                gl_mainPanel
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addGroup(
                                                                                                gl_mainPanel
                                                                                                        .createParallelGroup(
                                                                                                                Alignment.LEADING,
                                                                                                                false)
                                                                                                        .addComponent(
                                                                                                                comboBoxAgent,
                                                                                                                0,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                        .addComponent(
                                                                                                                comboBoxCIs,
                                                                                                                0,
                                                                                                                545,
                                                                                                                Short.MAX_VALUE))
                                                                                        .addGroup(
                                                                                                gl_mainPanel
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                txtRatio,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                btnSave)))
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                gl_mainPanel
                                                                                        .createParallelGroup(
                                                                                                Alignment.TRAILING)
                                                                                        .addGroup(
                                                                                                gl_mainPanel
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                btnAddCI,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                63,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                btnDeleteCI))
                                                                                        .addGroup(
                                                                                                gl_mainPanel
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                btnAddAgent,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                63,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addComponent(
                                                                                                                btnDeleteAgent)))))
                                        .addContainerGap()));
        gl_mainPanel
                .setVerticalGroup(gl_mainPanel
                        .createParallelGroup(Alignment.LEADING)
                        .addGroup(
                                gl_mainPanel
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                gl_mainPanel
                                                        .createParallelGroup(
                                                                Alignment.LEADING)
                                                        .addGroup(
                                                                gl_mainPanel
                                                                        .createSequentialGroup()
                                                                        .addGroup(
                                                                                gl_mainPanel
                                                                                        .createParallelGroup(
                                                                                                Alignment.BASELINE)
                                                                                        .addComponent(
                                                                                                lblCI)
                                                                                        .addComponent(
                                                                                                comboBoxCIs,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                27,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                gl_mainPanel
                                                                                        .createParallelGroup(
                                                                                                Alignment.LEADING)
                                                                                        .addGroup(
                                                                                                gl_mainPanel
                                                                                                        .createSequentialGroup()
                                                                                                        .addComponent(
                                                                                                                comboBoxAgent,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                23,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(
                                                                                                                ComponentPlacement.RELATED)
                                                                                                        .addGroup(
                                                                                                                gl_mainPanel
                                                                                                                        .createParallelGroup(
                                                                                                                                Alignment.BASELINE)
                                                                                                                        .addComponent(
                                                                                                                                lblRatio)
                                                                                                                        .addComponent(
                                                                                                                                txtRatio,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                23,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                        .addComponent(
                                                                                                                                btnSave)))
                                                                                        .addComponent(
                                                                                                lblAgent)))
                                                        .addGroup(
                                                                gl_mainPanel
                                                                        .createSequentialGroup()
                                                                        .addGroup(
                                                                                gl_mainPanel
                                                                                        .createParallelGroup(
                                                                                                Alignment.BASELINE)
                                                                                        .addComponent(
                                                                                                btnAddCI,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                25,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(
                                                                                                btnDeleteCI,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                25,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addGroup(
                                                                                gl_mainPanel
                                                                                        .createParallelGroup(
                                                                                                Alignment.BASELINE)
                                                                                        .addComponent(
                                                                                                btnAddAgent)
                                                                                        .addComponent(
                                                                                                btnDeleteAgent))))
                                        .addGap(18)
                                        .addComponent(scrollPane,
                                                GroupLayout.PREFERRED_SIZE,
                                                367, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap()));
        tableRatios.setFillsViewportHeight(true);
        scrollPane.setViewportView(tableRatios);
        mainPanel.setLayout(gl_mainPanel);
        getContentPane().setLayout(group);
        try {
            db = new DatabaseHelper();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Problem with the matrix loader", "Matrix error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        refreshAgentComboBox();
        refreshCIComboBox();
        refreshRatioTable();
        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args) throws SQLException {
        // try {
        // for (UIManager.LookAndFeelInfo info : UIManager
        // .getInstalledLookAndFeels()) {
        // if ("Nimbus".equals(info.getName())) {
        // UIManager.setLookAndFeel(info.getClassName());
        // break;
        // }
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        new Main();
    }

    private void refreshCIComboBox() {
        List<String> cisList = db.getCIsList();
        if (cisList != null)
            comboBoxCIs.setModel(new DefaultComboBoxModel<Object>(cisList
                    .toArray()));
        if (comboBoxCIs.getItemCount() == 0) {
            comboBoxCIs.setEnabled(false);
        } else {
            comboBoxCIs.setEnabled(true);
        }
    }

    private void refreshAgentComboBox() {
        List<String> agentsList = db.getAgentsList();
        if (agentsList != null)
            comboBoxAgent.setModel(new DefaultComboBoxModel<Object>(agentsList
                    .toArray()));
        if (comboBoxAgent.getItemCount() == 0) {
            comboBoxAgent.setEnabled(false);
        } else {
            comboBoxAgent.setEnabled(true);
        }

    }

    private void refreshRatioTable() {
        List<String> agentsList = db.getAgentsList();
        List<String> cisList = db.getCIsList();
        DefaultTableModel dm = (DefaultTableModel) tableRatios.getModel();
        dm.setColumnCount(0);
        dm.getDataVector().removeAllElements();

        dm.addColumn("CI");
        tableRatios.getColumn("CI").setCellRenderer(new CIColumnRenderer());
        LinkedList<String> agentTotals = new LinkedList<>();
        for (String agent : agentsList) {
            dm.addColumn(agent);
            agentTotals.add(String.valueOf(db.getSumAgent(agent)));
        }
        dm.addColumn("Total");
        tableRatios.getColumn("Total").setCellRenderer(
                new StatusColumnTotalRenderer());

        Map<String, Integer> cisTotalRatios = new TreeMap<>();

        for (String ci : cisList) {
            Map<String, Integer> ratioByAgents = db.getAgentRatios(ci);
            LinkedList<String> row = createRatioRow(ratioByAgents.values());
            row.addFirst(ci);
            Integer sumCi = db.getSumCiRatios(ci);
            cisTotalRatios.put(ci, sumCi);
            row.addLast(String.valueOf(sumCi));
            dm.addRow(row.toArray());
        }
        agentTotals.addFirst("Total");
        dm.addRow(agentTotals.toArray());
    }

    private int sumCiRatios(Iterable<Integer> ratios) {
        int result = 0;
        for (int ratio : ratios) {
            result += ratio;
        }
        return result;
    }

    private LinkedList<String> createRatioRow(Iterable<Integer> values) {
        LinkedList<String> result = new LinkedList<>();
        for (Integer ratio : values) {
            result.add(Integer.toString(ratio));
        }
        return result;
    }
}
