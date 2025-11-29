package com.info.emicalc.ui;

//import dao.LoanDAO;
//import daoimpl.LoanDAOImpl;
//import model.LoanRecord;
//import model.User;
//import util.EMICalculator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.info.emicalc.dao.LoanDAO;
import com.info.emicalc.daoimpl.LoanDAOImpl;
import com.info.emicalc.entity.LoanRecord;
import com.info.emicalc.entity.User;
import com.info.emicalc.util.EMICalculator;

import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private User currentUser;

    private JTextField txtPrincipal, txtRate, txtTenure;
    private JLabel lblEmi, lblTotalPayment, lblTotalInterest;

    private JTable tblRecords;
    private DefaultTableModel tableModel;

    private LoanDAO loanDAO = new LoanDAOImpl();

    public MainFrame(User user) {

        this.currentUser = user;

        setTitle("EMI Calculator - Dashboard (" + user.getUsername() + ")");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(245, 248, 255));
        mainPanel.setBounds(0, 0, 900, 650);
        add(mainPanel);

        JPanel calcPanel = new JPanel();
        calcPanel.setLayout(null);
        calcPanel.setBounds(20, 20, 850, 200);
        calcPanel.setBackground(Color.WHITE);
        calcPanel.setBorder(BorderFactory.createTitledBorder(" EMI Calculator "));
        mainPanel.add(calcPanel);

        JLabel lblPrincipal = new JLabel("Principal Amount:");
        lblPrincipal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPrincipal.setBounds(30, 40, 150, 25);
        calcPanel.add(lblPrincipal);

        txtPrincipal = new JTextField();
        txtPrincipal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPrincipal.setBounds(170, 40, 150, 25);
        calcPanel.add(txtPrincipal);

        JLabel lblRate = new JLabel("Annual Rate (%):");
        lblRate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblRate.setBounds(350, 40, 130, 25);
        calcPanel.add(lblRate);

        txtRate = new JTextField();
        txtRate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtRate.setBounds(480, 40, 130, 25);
        calcPanel.add(txtRate);

        JLabel lblTenure = new JLabel("Tenure (months):");
        lblTenure.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTenure.setBounds(30, 80, 150, 25);
        calcPanel.add(lblTenure);

        txtTenure = new JTextField();
        txtTenure.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTenure.setBounds(170, 80, 150, 25);
        calcPanel.add(txtTenure);

        JButton btnCalc = new JButton("Calculate EMI");
        btnCalc.setBackground(new Color(40, 90, 200));
        btnCalc.setForeground(Color.WHITE);
        btnCalc.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCalc.setBounds(350, 80, 150, 30);
        btnCalc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        calcPanel.add(btnCalc);

        JButton btnSave = new JButton("Save Record");
        btnSave.setBackground(new Color(60, 170, 90));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSave.setBounds(520, 80, 150, 30);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        calcPanel.add(btnSave);

        lblEmi = new JLabel("EMI: -");
        lblEmi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblEmi.setBounds(30, 130, 300, 25);
        calcPanel.add(lblEmi);

        lblTotalPayment = new JLabel("Total Payment: -");
        lblTotalPayment.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotalPayment.setBounds(300, 130, 300, 25);
        calcPanel.add(lblTotalPayment);

        lblTotalInterest = new JLabel("Total Interest: -");
        lblTotalInterest.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotalInterest.setBounds(550, 130, 300, 25);
        calcPanel.add(lblTotalInterest);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Principal", "Rate", "Months", "EMI", "Total Pay", "Interest", "Created"},
                0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tblRecords = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(tblRecords);
        sp.setBounds(20, 240, 850, 300);
        mainPanel.add(sp);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(20, 560, 120, 30);
        btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 14));
        mainPanel.add(btnRefresh);

        JButton btnDelete = new JButton("Delete Selected");
        btnDelete.setBounds(150, 560, 150, 30);
        btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
        mainPanel.add(btnDelete);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(750, 560, 120, 30);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        mainPanel.add(btnLogout);

        btnCalc.addActionListener(e -> calculateEMI());
        btnSave.addActionListener(e -> saveRecord());
        btnRefresh.addActionListener(e -> loadRecords());
        btnDelete.addActionListener(e -> deleteRecord());
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        loadRecords();
    }

    private void calculateEMI() {
        try {
            double principal = Double.parseDouble(txtPrincipal.getText().trim());
            double rate = Double.parseDouble(txtRate.getText().trim());
            int months = Integer.parseInt(txtTenure.getText().trim());

            double emi = EMICalculator.calculateEMI(principal, rate, months);
            double total = EMICalculator.totalPayment(emi, months);
            double interest = total - principal;

            lblEmi.setText(String.format("EMI: %.2f", emi));
            lblTotalPayment.setText(String.format("Total Payment: %.2f", total));
            lblTotalInterest.setText(String.format("Total Interest: %.2f", interest));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Enter valid numbers.");
        }
    }

    private void saveRecord() {
        try {
            double principal = Double.parseDouble(txtPrincipal.getText().trim());
            double rate = Double.parseDouble(txtRate.getText().trim());
            int months = Integer.parseInt(txtTenure.getText().trim());

            double emi = EMICalculator.calculateEMI(principal, rate, months);
            double total = EMICalculator.totalPayment(emi, months);
            double interest = total - principal;

            LoanRecord lr = new LoanRecord(
                    currentUser.getId(),
                    principal,
                    rate,
                    months,
                    emi,
                    total,
                    interest
            );

            boolean ok = loanDAO.saveLoan(lr);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Record Saved Successfully!");
                loadRecords();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save record!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    private void loadRecords() {
        tableModel.setRowCount(0);

        List<LoanRecord> list = loanDAO.getLoansByUser(currentUser.getId());

        for (LoanRecord lr : list) {
            tableModel.addRow(new Object[]{
                    lr.getId(),
                    lr.getPrincipal(),
                    lr.getAnnualRate(),
                    lr.getTenureMonths(),
                    lr.getEmi(),
                    lr.getTotalPayment(),
                    lr.getTotalInterest(),
                    lr.getCreatedAt()
            });
        }
    }

    private void deleteRecord() {
        int row = tblRecords.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a record to delete!");
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);

        if (loanDAO.deleteLoan(id)) {
            JOptionPane.showMessageDialog(this, "Record Deleted!");
            loadRecords();
        } else {
            JOptionPane.showMessageDialog(this, "Delete Failed!");
        }
    }
}
