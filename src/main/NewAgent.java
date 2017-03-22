package main;

import javax.swing.*;
import java.awt.event.*;

public class NewAgent extends JDialog {
    protected JPanel contentPane;
    protected JButton buttonSave;
    protected JButton buttonCancel;
    ACTION action;
    private JTextField textFieldLogin;

    public NewAgent() {
    	contentPane = new JPanel();
    	buttonSave = new JButton("Save");
    	buttonCancel = new JButton("Cancel");
    	textFieldLogin = new JTextField();
    	contentPane.add(textFieldLogin);
    	contentPane.add(buttonSave);
    	contentPane.add(buttonCancel);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSave);
        
        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSave();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    protected void onCancel() {
        action = ACTION.CANCEL;
        dispose();
    }

    protected void onSave() {
        action = ACTION.SAVE;
        dispose();
    }

    public String getTextFieldLogin() {
        return textFieldLogin.getText();
    }
}