/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import controllers.ApplicantController;
import javax.swing.*;
import models.InternApplicant;

public class MainFrame extends JFrame {

    JTextField tfName = new JTextField();
    JTextField tfWriting = new JTextField();
    JTextField tfCoding = new JTextField();
    JTextField tfInterview = new JTextField();

    JComboBox<String> cbDivision =
            new JComboBox<>(
                    new String[]{
                        "UI/UX Designer",
                        "Backend Developer"
                    }
            );

    JButton btnAdd = new JButton("Tambah");
    JButton btnDelete = new JButton("Hapus");
    JButton btnUpdate = new JButton("Perbarui");
    JButton btnClear = new JButton("Bersihkan");

    JTable table = new JTable();

    ApplicantController controller =
            new ApplicantController();

    public MainFrame() {

        setTitle("Sistem Rekrutmen PT.OOP");
        setSize(700, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Nama");
        JLabel l2 = new JLabel("Divisi");
        JLabel l3 = new JLabel("Writing");
        JLabel l4 = new JLabel("Coding");
        JLabel l5 = new JLabel("Interview");

        l1.setBounds(20, 20, 100, 25);
        tfName.setBounds(120, 20, 150, 25);

        l2.setBounds(20, 60, 100, 25);
        cbDivision.setBounds(120, 60, 150, 25);

        l3.setBounds(20, 100, 100, 25);
        tfWriting.setBounds(120, 100, 150, 25);

        l4.setBounds(20, 140, 100, 25);
        tfCoding.setBounds(120, 140, 150, 25);

        l5.setBounds(20, 180, 100, 25);
        tfInterview.setBounds(120, 180, 150, 25);

        btnAdd.setBounds(20, 230, 100, 30);
        btnDelete.setBounds(140, 230, 100, 30);

        btnUpdate.setBounds(20, 280, 100, 30);
        btnClear.setBounds(140, 280, 100, 30);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(300, 20, 350, 350);

        add(l1);
        add(tfName);

        add(l2);
        add(cbDivision);

        add(l3);
        add(tfWriting);

        add(l4);
        add(tfCoding);

        add(l5);
        add(tfInterview);

        add(btnAdd);
        add(btnDelete);

        add(btnUpdate);
        add(btnClear);

        add(sp);

        controller.showData(table);

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row != -1) {

                tfName.setText(
                        table.getValueAt(row, 1).toString()
                );

                cbDivision.setSelectedItem(
                        table.getValueAt(row, 2).toString()
                );
            }
        });

        btnAdd.addActionListener(e -> {

            try {

                String name = tfName.getText();

                String division =
                        cbDivision.getSelectedItem().toString();

                double writing =
                        Double.parseDouble(tfWriting.getText());

                double coding =
                        Double.parseDouble(tfCoding.getText());

                double interview =
                        Double.parseDouble(tfInterview.getText());

                if (writing < 0 || writing > 100
                        || coding < 0 || coding > 100
                        || interview < 0 || interview > 100) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Nilai harus 0-100"
                    );

                    return;
                }

                InternApplicant applicant =
                        new InternApplicant(
                                0,
                                name,
                                division,
                                writing,
                                coding,
                                interview
                        );

                controller.insertApplicant(applicant);

                controller.showData(table);

                JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil ditambahkan"
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Input harus angka!"
                );
            }
        });

        btnUpdate.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "Pilih data dulu!"
                );

                return;
            }

            try {

                int id = Integer.parseInt(
                        table.getValueAt(row, 0).toString()
                );

                String name = tfName.getText();

                String division =
                        cbDivision.getSelectedItem().toString();

                double writing =
                        Double.parseDouble(tfWriting.getText());

                double coding =
                        Double.parseDouble(tfCoding.getText());

                double interview =
                        Double.parseDouble(tfInterview.getText());

                InternApplicant applicant =
                        new InternApplicant(
                                id,
                                name,
                                division,
                                writing,
                                coding,
                                interview
                        );

                controller.updateApplicant(applicant);

                controller.showData(table);

                JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil diperbarui"
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Input harus angka!"
                );
            }
        });

        btnDelete.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "Pilih data dulu!"
                );

                return;
            }

            int id = Integer.parseInt(
                    table.getValueAt(row, 0).toString()
            );

            controller.deleteApplicant(id);

            controller.showData(table);

            JOptionPane.showMessageDialog(
                    null,
                    "Data berhasil dihapus"
            );
        });

        btnClear.addActionListener(e -> {

            tfName.setText("");
            tfWriting.setText("");
            tfCoding.setText("");
            tfInterview.setText("");

            cbDivision.setSelectedIndex(0);

            table.clearSelection();
        });
    }
}