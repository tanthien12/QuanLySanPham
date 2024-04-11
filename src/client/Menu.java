package client;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSanPham = new JButton("SẢN PHẨM");
		btnSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo và hiển thị giao diện FormSanPham
                FormSanPham formSanPham = new FormSanPham();
                formSanPham.setVisible(true);
			}
		});
		btnSanPham.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSanPham.setBounds(135, 26, 250, 30);
		contentPane.add(btnSanPham);
		
		JButton btnNhanVien = new JButton("NHÂN VIÊN");
		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNhanVien.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNhanVien.setBounds(135, 82, 250, 30);
		contentPane.add(btnNhanVien);
		
		JButton btnKhachHang = new JButton("KHÁCH HÀNG");
		btnKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnKhachHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnKhachHang.setBounds(135, 138, 250, 30);
		contentPane.add(btnKhachHang);
		
		JButton btnDonHang = new JButton("ĐƠN HÀNG");
		btnDonHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDonHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDonHang.setBounds(135, 194, 250, 30);
		contentPane.add(btnDonHang);
		
		JButton btnChiTietDH = new JButton("CHI TIẾT ĐƠN HÀNG");
		btnChiTietDH.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnChiTietDH.setBounds(135, 250, 250, 30);
		contentPane.add(btnChiTietDH);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Đóng cửa sổ giao diện
                dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(218, 306, 85, 25);
		contentPane.add(btnExit);
	}
}
