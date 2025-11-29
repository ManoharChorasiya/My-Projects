package com.info.emicalc.ui;

//import dao.UserDAO;
//import daoimpl.UserDAOImpl;
//import model.User;

import javax.swing.*;

import com.info.emicalc.daoimpl.UserDAOImpl;
import com.info.emicalc.entity.User;

import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField txtUsername, txtFullName;
    private JPasswordField txtPassword;
    private JButton btnRegister, btnBack;

    public RegisterFrame() {

        setTitle("EMI Calculator - Register");
        setSize(550, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 248, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        add(mainPanel);

        JLabel lblHeader = new JLabel("Create New Account", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblHeader.setForeground(new Color(40, 70, 140));
        mainPanel.add(lblHeader, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridLayout(6, 1, 0, 8));

        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        JLabel lblFull = new JLabel("Full Name");
        lblFull.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtFullName = new JTextField();
        txtFullName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtFullName.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        formPanel.add(lblUser);
        formPanel.add(txtUsername);
        formPanel.add(lblPass);
        formPanel.add(txtPassword);
       // formPanel.add(lblFull);
       // formPanel.add(txtFullName);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(1, 2, 15, 0));

        btnRegister = createButton("Register", new Color(60, 170, 90));
        btnBack = createButton("Back", new Color(160, 70, 70));

        buttonPanel.add(btnRegister);
        buttonPanel.add(btnBack);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        btnRegister.addActionListener(e -> doRegister());
        btnBack.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void doRegister() {
        String username = txtUsername.getText().trim();
        String pwd = new String(txtPassword.getPassword()).trim();
        String full = txtFullName.getText().trim();

        if (username.isEmpty() || pwd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter username and password");
            return;
        }

        UserDAOImpl dao = new UserDAOImpl();

        if (dao.isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(this, "Username already taken");
            return;
        }

        User u = new User(username, pwd, full);

        if (dao.register(u)) {
            JOptionPane.showMessageDialog(this, "Registration successful");
            dispose();
            new LoginFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed");
        }
    }
}
