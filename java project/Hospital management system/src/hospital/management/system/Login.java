package hospital.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

import hospital.management.conn; // Ensure `conn` class is correctly defined
import hospital.management.Reception; // Ensure `Reception` is correctly imported

public class Login extends JFrame implements ActionListener {
    JTextField textfield;
    JPasswordField jPasswordField;
    JButton b1, b2;

    public Login() {
        // Username label
        JLabel namelabel = new JLabel("Username");
        namelabel.setBounds(50, 30, 100, 30);
        namelabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        namelabel.setForeground(Color.BLACK);
        add(namelabel);

        // Password label
        JLabel password = new JLabel("Password");
        password.setBounds(50, 80, 100, 30);
        password.setFont(new Font("Tahoma", Font.BOLD, 16));
        password.setForeground(Color.BLACK);
        add(password);

        // Username text field
        textfield = new JTextField();
        textfield.setBounds(160, 30, 200, 30);
        textfield.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textfield.setBackground(new Color(255, 179, 0));
        add(textfield);

        // Password field
        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(160, 80, 200, 30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jPasswordField.setBackground(new Color(255, 179, 0));
        add(jPasswordField);

        // Image icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/login.jpg"));
        Image i1 = icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(i1);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(380, 30, 100, 100);
        add(label);

        // Login button
        b1 = new JButton("Login");
        b1.setBounds(100, 140, 100, 30);
        b1.setFont(new Font("Serif", Font.BOLD, 15));
        b1.addActionListener(this);
        add(b1);

        // Cancel button
        b2 = new JButton("Cancel");
        b2.setBounds(220, 140, 100, 30);
        b2.setFont(new Font("Serif", Font.BOLD, 15));
        b2.addActionListener(this);
        add(b2);

        // Frame settings
        getContentPane().setBackground(new Color(109, 164, 170));
        setSize(550, 250);
        setLocation(400, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                conn c = new conn();
                Connection connection = c.getConnection();

                String user = textfield.getText();
                String pass = new String(jPasswordField.getPassword());

                String query = "SELECT * FROM Login WHERE ID = ? AND PW = ?";
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1, user);
                pst.setString(2, pass);

                ResultSet resultSet = pst.executeQuery();

                if (resultSet.next()) {
                    new Reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");
                }

                pst.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            dispose();
        }
    }
}
