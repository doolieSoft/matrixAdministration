package main;/* See the file "LICENSE" for the full license governing this code. */

import DAO.DatabaseHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class Main extends JFrame implements WindowListener {
    DatabaseHelper db;
    private JFrame frame;
    private JTable tableRatios;
    private JPanel mainPanel;
    private JPanel panelRatioForm;
    private JComboBox comboBoxAgent;
    private JComboBox comboBoxCI;
    private JTextField ratioTextField;
    private JButton newAgentButton;
    private JButton deleteAgentButton;
    private JButton editCIButton;
    private JButton newCIButton;
    private JButton editAgentButton;
    private JButton deleteCIButton;
    private JButton saveButton;
    private JButton validateMatrixButton;

    public Main() throws SQLException {
        super("Matrix Administration");
        db = new DatabaseHelper();

        newAgentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewAgent newAgentDialog = new NewAgent();
                newAgentDialog.setMinimumSize(new Dimension(400, 200));
                newAgentDialog.setVisible(true);
                if (newAgentDialog.action == ACTION.SAVE) {
                    db.addAgent(newAgentDialog.getTextFieldLogin());
                    System.out.println(newAgentDialog.getTextFieldLogin());
                    refreshAgentComboBox();
                    refreshRatioTable();
                }
            }
        });

        newCIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewCI newCIDialog = new NewCI();
                newCIDialog.setMinimumSize(new Dimension(400, 200));
                newCIDialog.setVisible(true);
                if (newCIDialog.action == ACTION.SAVE) {
                    db.addCI(newCIDialog.getTextFieldCI());
                    System.out.println(newCIDialog.getTextFieldCI());
                    refreshCIComboBox();
                    refreshRatioTable();
                }
            }
        });
        refreshAgentComboBox();
        refreshCIComboBox();
        refreshRatioTable();


        this.setMinimumSize(new Dimension(500, 400));
        this.setContentPane(mainPanel);
        this.addWindowListener(this);
        this.pack();
        this.setVisible(true);

        deleteAgentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.delAgent(comboBoxAgent.getSelectedItem().toString());
                refreshAgentComboBox();
                refreshRatioTable();
            }
        });

        deleteCIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.delCI(comboBoxCI.getSelectedItem().toString());
                refreshCIComboBox();
                refreshRatioTable();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String agent = comboBoxAgent.getSelectedItem().toString();
                String ci = comboBoxCI.getSelectedItem().toString();
                Integer ratio = Integer.parseInt(ratioTextField.getText());
                db.updateRatio(agent, ci, ratio);
                refreshRatioTable();
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Main();
    }

    private void refreshCIComboBox() {
        List<String> cisList = db.getCIsList();
        comboBoxCI.setModel(new DefaultComboBoxModel(cisList.toArray()));
    }

    private void refreshAgentComboBox() {
        List<String> agentsList = db.getAgentsList();
        comboBoxAgent.setModel(new DefaultComboBoxModel(agentsList.toArray()));
    }

    private void refreshRatioTable() {
        List<String> agentsList = db.getAgentsList();
        List<String> cisList = db.getCIsList();

        DefaultTableModel dm = (DefaultTableModel) tableRatios.getModel();
        dm.setColumnCount(0);
        dm.getDataVector().removeAllElements();

        dm.addColumn("CI");
        LinkedList<String> agentTotals= new LinkedList<>();
        for (String agent : agentsList) {
            dm.addColumn(agent);
            agentTotals.add(String.valueOf(db.getSumAgent(agent)));
        }
        dm.addColumn("Total");
        Map<String, Integer> cisTotalRatios=new TreeMap<>();

        for(String ci : cisList ) {
            Map<String,Integer> ratioByAgents = db.getAgentRatios(ci);
            LinkedList<String> row = createRatioRow(ratioByAgents.values());
            row.addFirst(ci);
            int sumCi = db.getSumCiRatios(ci);
            cisTotalRatios.put(ci, sumCi);
            row.addLast(String.valueOf(sumCi));
            dm.addRow(row.toArray());
        }
        agentTotals.addFirst("Total");
        dm.addRow(agentTotals.toArray());
        tableRatios.getColumn("CI").setCellRenderer(new CIColumnRenderer());
        tableRatios.getColumn("Total").setCellRenderer(new StatusColumnTotalRenderer());
    }

    private int sumCiRatios(Iterable<Integer> ratios) {
        int result = 0;
        for(int ratio : ratios) {
            result+=ratio;
        }
        return result;
    }

    private LinkedList<String> createRatioRow(Iterable<Integer> values) {
        LinkedList<String> result = new LinkedList<>();
        for (Integer ratio :  values) {
            result.add(Integer.toString(ratio));
        }
        return result;
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
