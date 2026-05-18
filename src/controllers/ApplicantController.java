/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import connection.DBConnection;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.InternApplicant;

/**
 *
 * @author USER
 */
public class ApplicantController {
    
  Connection conn;

    public ApplicantController() {
        conn = DBConnection.getConnection();
    }

    public void insertApplicant(InternApplicant a) {

        try {
            String query = "INSERT INTO applicants VALUES(NULL,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, a.getName());
            ps.setString(2, a.getDivision());
            ps.setDouble(3, a.getWritingScore());
            ps.setDouble(4, a.getCodingScore());
            ps.setDouble(5, a.getInterviewScore());
            ps.setDouble(6, a.calculateFinalScore());
            ps.setString(7, a.getStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Insert Error : " + e.getMessage());
        }
    }

    public void showData(JTable table) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Divisi");
        model.addColumn("Final Score");
        model.addColumn("Status");

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM applicants");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("division"),
                    rs.getDouble("final_score"),
                    rs.getString("status")
                });
            }

            table.setModel(model);

        } catch (SQLException e) {
            System.out.println("Read Error : " + e.getMessage());
        }
    }

    public void deleteApplicant(int id) {

        try {
            String query = "DELETE FROM applicants WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete Error : " + e.getMessage());
        }
    }
    
    public void updateApplicant(InternApplicant a) {

    try {

        String query =
                "UPDATE applicants SET name=?, division=?, writing_score=?, coding_score=?, interview_score=?, final_score=?, status=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, a.getName());
        ps.setString(2, a.getDivision());
        ps.setDouble(3, a.getWritingScore());
        ps.setDouble(4, a.getCodingScore());
        ps.setDouble(5, a.getInterviewScore());
        ps.setDouble(6, a.calculateFinalScore());
        ps.setString(7, a.getStatus());
        ps.setInt(8, a.getId());

        ps.executeUpdate();

    } catch (SQLException e) {

        System.out.println("Update Error : " + e.getMessage());
    }
}
}