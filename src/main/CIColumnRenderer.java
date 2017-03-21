package main;/* See the file "LICENSE" for the full license governing this code. */

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CIColumnRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        l.setFont(l.getFont().deriveFont(Font.BOLD));
        l.setBackground(new Color(45,151,232));
        //Return the JLabel which renders the cell.
        return l;

    }
}