package com.info.emicalc.ui;


//import daoimpl.UserDAOImpl;
//import model.User;

import javax.swing.*;

import com.info.emicalc.daoimpl.UserDAOImpl;
import com.info.emicalc.entity.User;

import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;

    public LoginFrame() {

        setTitle("EMI Calculator - Login");
        setSize(530, 430);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 248, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        add(mainPanel);

        JLabel lblHeader = new JLabel("User Login", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblHeader.setForeground(new Color(40, 70, 140));
        mainPanel.add(lblHeader, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridLayout(4, 1, 0, 8));

        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));

        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));

        formPanel.add(lblUser);
        formPanel.add(txtUsername);
        formPanel.add(lblPass);
        formPanel.add(txtPassword);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(1, 2, 15, 0));

        btnLogin = createButton("Login", new Color(40, 90, 200));
        btnRegister = createButton("Register", new Color(60, 170, 90));

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> doLogin());
        btnRegister.addActionListener(e -> {
            dispose();
            new RegisterFrame().setVisible(true);
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

    private void doLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter username and password");
            return;
        }

        UserDAOImpl dao = new UserDAOImpl();
        User user = dao.login(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful. Welcome, " +
                    (user.getFullName() == null ? user.getUsername() : user.getFullName()));

            SwingUtilities.invokeLater(() -> {
                dispose();
                new MainFrame(user).setVisible(true);
            });

        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
