package tablesweeper.gui;

import tablesweeper.core.TableSweeperManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 7/30/13
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainFrame {
    private JTextField driverField;
    private JTextField urlField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JComboBox frequencyField;
    private JTextField tableNameField;
    private JButton startButton;
    private JButton pauseButton;
    private JPanel panel;

    public MainFrame() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseButton.setEnabled(true);
                startButton.setEnabled(false);
                driverField.setEnabled(false);
                urlField.setEnabled(false);
                usernameField.setEnabled(false);
                passwordField.setEnabled(false);
                frequencyField.setEnabled(false);
                tableNameField.setEnabled(false);

                TableSweeperManager manager = TableSweeperManager.getInstance();
                ClientData clientData = new ClientData();
                getData(clientData);
                manager.setClientData(clientData);
                manager.startSweeperJob(Integer.valueOf(clientData.getFrequency()));
            }
        });
        pauseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                driverField.setEnabled(true);
                urlField.setEnabled(true);
                usernameField.setEnabled(true);
                passwordField.setEnabled(true);
                frequencyField.setEnabled(true);
                tableNameField.setEnabled(true);

                TableSweeperManager manager = TableSweeperManager.getInstance();

                manager.stopSweeperJob();
            }
        });
    }

    public void setData(ClientData data) {
        driverField.setText(data.getDriver());
        urlField.setText(data.getUrl());
        usernameField.setText(data.getUsername());
        passwordField.setText(data.getPassword());
        tableNameField.setText(data.getTableName());
    }

    public void getData(ClientData data) {
        data.setDriver(driverField.getText());
        data.setUrl(urlField.getText());
        data.setUsername(usernameField.getText());
        data.setPassword(passwordField.getText());
        data.setTableName(tableNameField.getText());
        data.setFrequency(frequencyField.getSelectedItem().toString());
    }

    public boolean isModified(ClientData data) {
        if (driverField.getText() != null ? !driverField.getText().equals(data.getDriver()) : data.getDriver() != null)
            return true;
        if (urlField.getText() != null ? !urlField.getText().equals(data.getUrl()) : data.getUrl() != null) return true;
        if (usernameField.getText() != null ? !usernameField.getText().equals(data.getUsername()) : data.getUsername() != null)
            return true;
        if (passwordField.getText() != null ? !passwordField.getText().equals(data.getPassword()) : data.getPassword() != null)
            return true;
        if (tableNameField.getText() != null ? !tableNameField.getText().equals(data.getTableName()) : data.getTableName() != null)
            return true;
        return false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrame");
        frame.setContentPane(new MainFrame().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
