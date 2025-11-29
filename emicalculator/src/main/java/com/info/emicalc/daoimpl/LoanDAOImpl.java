package com.info.emicalc.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.info.emicalc.dao.LoanDAO;
import com.info.emicalc.entity.LoanRecord;
import com.info.emicalc.util.DBConnection;

public class LoanDAOImpl implements LoanDAO {

    @Override
    public boolean saveLoan(LoanRecord lr) {
        String sql = "INSERT INTO loan_record(user_id, principal, annual_rate, tenure_months, emi, total_payment, total_interest) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, lr.getUserId());
            ps.setDouble(2, lr.getPrincipal());
            ps.setDouble(3, lr.getAnnualRate());
            ps.setInt(4, lr.getTenureMonths());
            ps.setDouble(5, lr.getEmi());
            ps.setDouble(6, lr.getTotalPayment());
            ps.setDouble(7, lr.getTotalInterest());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<LoanRecord> getLoansByUser(int userId) {
        List<LoanRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM loan_record WHERE user_id=? ORDER BY created_at DESC";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LoanRecord lr = new LoanRecord(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getDouble("principal"),
                            rs.getDouble("annual_rate"),
                            rs.getInt("tenure_months"),
                            rs.getDouble("emi"),
                            rs.getDouble("total_payment"),
                            rs.getDouble("total_interest"),
                            rs.getTimestamp("created_at")
                    );
                    list.add(lr);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteLoan(int loanId) {
        String sql = "DELETE FROM loan_record WHERE id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, loanId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}