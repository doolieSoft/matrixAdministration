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
	private JPanel panelRatioForm;
	private JTextField ratioTextField;
	private JButton newAgentButton;
	private JButton deleteAgentButton;
	private JButton editCIButton;
	private JButton newCIButton;
	private JButton deleteCIButton;
	private JButton saveButton;
	private JButton validateMatrixButton;
	private JTextField textField;
	private JTable tableRatios;
	private JComboBox comboBoxAgent;
	private JComboBox comboBoxCI;
	private JPanel mainPanel;

	public Main() throws SQLException {
		super("Matrix Administration");
		db = new DatabaseHelper();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		mainPanel = new JPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 0;
		getContentPane().add(mainPanel, gbc_mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 0, 0 };
		gbl_mainPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_mainPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		mainPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 507, 507, 0 };
		gbl_panel.rowHeights = new int[] { 412, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 28, 28, 86, 89, 89, 89, 89, 0 };
		gbl_panel_1.rowHeights = new int[] { 23, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		comboBoxCI = new JComboBox();
		GridBagConstraints gbc_comboBoxCI = new GridBagConstraints();
		gbc_comboBoxCI.gridwidth = 5;
		gbc_comboBoxCI.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCI.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCI.gridx = 0;
		gbc_comboBoxCI.gridy = 0;
		panel_1.add(comboBoxCI, gbc_comboBoxCI);

		newAgentButton = new JButton("New button");
		GridBagConstraints gbc_btnNewAgentButton = new GridBagConstraints();
		gbc_btnNewAgentButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewAgentButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewAgentButton.gridx = 5;
		gbc_btnNewAgentButton.gridy = 0;
		panel_1.add(newAgentButton, gbc_btnNewAgentButton);

		deleteAgentButton = new JButton("Delete");
		GridBagConstraints gbc_deleteAgentButton = new GridBagConstraints();
		gbc_deleteAgentButton.anchor = GridBagConstraints.NORTH;
		gbc_deleteAgentButton.insets = new Insets(0, 0, 5, 0);
		gbc_deleteAgentButton.gridx = 6;
		gbc_deleteAgentButton.gridy = 0;
		panel_1.add(deleteAgentButton, gbc_deleteAgentButton);

		comboBoxAgent = new JComboBox();
		GridBagConstraints gbc_comboBoxAgent = new GridBagConstraints();
		gbc_comboBoxAgent.gridwidth = 5;
		gbc_comboBoxAgent.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAgent.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxAgent.gridx = 0;
		gbc_comboBoxAgent.gridy = 1;
		panel_1.add(comboBoxAgent, gbc_comboBoxAgent);

		newCIButton = new JButton("New");
		GridBagConstraints gbc_newCIButton = new GridBagConstraints();
		gbc_newCIButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_newCIButton.insets = new Insets(0, 0, 5, 5);
		gbc_newCIButton.gridx = 5;
		gbc_newCIButton.gridy = 1;
		panel_1.add(newCIButton, gbc_newCIButton);

		deleteCIButton = new JButton("Delete");
		GridBagConstraints gbc_deleteCIButton = new GridBagConstraints();
		gbc_deleteCIButton.insets = new Insets(0, 0, 5, 0);
		gbc_deleteCIButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_deleteCIButton.gridx = 6;
		gbc_deleteCIButton.gridy = 1;
		panel_1.add(deleteCIButton, gbc_deleteCIButton);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridwidth = 5;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);

		saveButton = new JButton("Save");
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.insets = new Insets(0, 0, 0, 5);
		gbc_saveButton.gridx = 5;
		gbc_saveButton.gridy = 2;
		panel_1.add(saveButton, gbc_saveButton);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		mainPanel.add(panel_2, gbc_panel_2);

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		tableRatios = new JTable();
		tableRatios.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableRatios);

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
		this.setMinimumSize(new Dimension(500, 400));
		//this.setContentPane(mainPanel);
		this.addWindowListener(this);
		this.pack();
		this.setVisible(true);
		refreshAgentComboBox();
		refreshCIComboBox();
		refreshRatioTable();
	}

	public static void main(String[] args) throws SQLException {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
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
		LinkedList<String> agentTotals = new LinkedList<>();
		for (String agent : agentsList) {
			dm.addColumn(agent);
			agentTotals.add(String.valueOf(db.getSumAgent(agent)));
		}
		dm.addColumn("Total");
		Map<String, Integer> cisTotalRatios = new TreeMap<>();

		for (String ci : cisList) {
			Map<String, Integer> ratioByAgents = db.getAgentRatios(ci);
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
		tableRatios.getColumn("Total").setCellRenderer(
				new StatusColumnTotalRenderer());
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
