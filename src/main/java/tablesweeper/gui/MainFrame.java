package tablesweeper.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import tablesweeper.core.TableSweeperManager;

import javax.swing.*;
import java.awt.*;
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
    //    private JTextField urlField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox frequencyField;
    private JTextArea tableNameField;
    private JButton startButton;
    private JButton pauseButton;
    private JPanel panel;
    private JTextField ipField;
    private JTextField portField;
    private JTextField databaseNameField;

    public MainFrame() {
        $$$setupUI$$$();
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(MainFrame.this.panel, "该操作会清空目标表中的所有数据，并且不可恢复，是否确认?", "警告", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    pauseButton.setEnabled(true);
                    startButton.setEnabled(false);
                    driverField.setEnabled(false);
                    //                urlField.setEnabled(false);
                    usernameField.setEnabled(false);
                    passwordField.setEnabled(false);
                    frequencyField.setEnabled(false);
                    tableNameField.setEnabled(false);
                    ipField.setEnabled(false);
                    portField.setEnabled(false);
                    databaseNameField.setEnabled(false);
                    TableSweeperManager manager = TableSweeperManager.getInstance();
                    ClientData clientData = new ClientData();
                    getData(clientData);
                    manager.setClientData(clientData);
                    manager.startSweeperJob(Integer.valueOf(clientData.getFrequency()));
                }
            }
        });
        pauseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                driverField.setEnabled(true);
//                urlField.setEnabled(true);
                usernameField.setEnabled(true);
                passwordField.setEnabled(true);
                frequencyField.setEnabled(true);
                tableNameField.setEnabled(true);
                ipField.setEnabled(true);
                portField.setEnabled(true);
                databaseNameField.setEnabled(true);
                TableSweeperManager manager = TableSweeperManager.getInstance();

                manager.stopSweeperJob();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrame");
        frame.setContentPane(new MainFrame().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public void setData(ClientData data) {
        driverField.setText(data.getDriver());
        usernameField.setText(data.getUsername());
        passwordField.setText(data.getPassword());
        tableNameField.setText(data.getTableName());
        ipField.setText(data.getIp());
        portField.setText(data.getPort());
        databaseNameField.setText(data.getDatabaseName());
    }

    public void getData(ClientData data) {
        data.setDriver(driverField.getText());
        data.setUsername(usernameField.getText());
        data.setPassword(passwordField.getText());
        data.setTableName(tableNameField.getText());
        data.setIp(ipField.getText());
        data.setPort(portField.getText());
        data.setDatabaseName(databaseNameField.getText());
        data.setFrequency(frequencyField.getSelectedItem().toString());
    }

    public boolean isModified(ClientData data) {
        if (driverField.getText() != null ? !driverField.getText().equals(data.getDriver()) : data.getDriver() != null)
            return true;
        if (usernameField.getText() != null ? !usernameField.getText().equals(data.getUsername()) : data.getUsername() != null)
            return true;
        if (passwordField.getText() != null ? !passwordField.getText().equals(data.getPassword()) : data.getPassword() != null)
            return true;
        if (tableNameField.getText() != null ? !tableNameField.getText().equals(data.getTableName()) : data.getTableName() != null)
            return true;
        if (ipField.getText() != null ? !ipField.getText().equals(data.getIp()) : data.getIp() != null) return true;
        if (portField.getText() != null ? !portField.getText().equals(data.getPort()) : data.getPort() != null)
            return true;
        if (databaseNameField.getText() != null ? !databaseNameField.getText().equals(data.getDatabaseName()) : data.getDatabaseName() != null)
            return true;
        return false;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        frequencyField = new JComboBox(new Integer[]{1, 2, 3, 4, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60});
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(9, 2, new Insets(10, 10, 10, 10), -1, -1));
        panel.setEnabled(true);
        panel.setMaximumSize(new Dimension(239, 225));
        final JLabel label1 = new JLabel();
        label1.setText("驱动");
        panel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        driverField = new JTextField();
        driverField.setColumns(100);
        driverField.setEditable(false);
        driverField.setEnabled(false);
        driverField.setRequestFocusEnabled(false);
        driverField.setText("net.sourceforge.jtds.jdbc.Driver");
        panel.add(driverField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("用户名");
        panel.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usernameField = new JTextField();
        panel.add(usernameField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("密码");
        panel.add(label3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordField = new JPasswordField();
        panel.add(passwordField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("频率(分钟)");
        panel.add(label4, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        frequencyField.setAutoscrolls(false);
        frequencyField.setEditable(true);
        panel.add(frequencyField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tableNameField = new JTextArea();
        tableNameField.setLineWrap(true);
        tableNameField.setText("");
        panel.add(tableNameField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 22), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel1, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        startButton = new JButton();
        startButton.setLabel("开始");
        startButton.setText("开始");
        panel1.add(startButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pauseButton = new JButton();
        pauseButton.setEnabled(false);
        pauseButton.setText("暂停");
        panel1.add(pauseButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("数据库IP");
        panel.add(label5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ipField = new JTextField();
        panel.add(ipField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("端口");
        panel.add(label6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        portField = new JTextField();
        portField.setEditable(true);
        panel.add(portField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("数据库名");
        panel.add(label7, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        databaseNameField = new JTextField();
        panel.add(databaseNameField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel2, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("表名称");
        panel2.add(label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("（多张表用空格分开）");
        panel2.add(label9, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
