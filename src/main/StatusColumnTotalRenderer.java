package main;/* See the file "LICENSE" for the full license governing this code. */

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StatusColumnTotalRenderer extends DefaultTableCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1746492923763752501L;

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        //Get the status for the current row.
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        if (value != null && value.equals("100")) {
            l.setBackground(Color.GREEN);
            l.setFont(l.getFont().deriveFont(Font.BOLD));
        } else {
            l.setBackground(Color.RED);
        }
        //Return the JLabel which renders the cell.
        return l;

    }
}