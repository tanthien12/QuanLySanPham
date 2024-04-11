package client;

import java.awt.*;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormSanPham extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnADD;
	private JButton btnDEL;
	private JButton btnSa;
	private JButton btnHinThDs;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormSanPham frame = new FormSanPham();
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
	public FormSanPham() {
		setTitle("Quản lý sản phẩm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 85, 532, 405);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "Gi\u00E1", "M\u00F4 T\u1EA3", "S\u1ED1 L\u01B0\u1EE3ng T\u1ED3n"
			}
		));
		
		btnADD = new JButton("Thêm");
		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo và hiển thị giao diện FrameNhapSanPham cho chức năng thêm
                FrameNhapSanPham frameNhapSanPham = new FrameNhapSanPham("Thêm Sản Phẩm", "Thêm Sản Phẩm");
                frameNhapSanPham.setVisible(true);
			}
		});
		btnADD.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnADD.setBounds(614, 137, 150, 30);
		contentPane.add(btnADD);
		
		btnDEL = new JButton("Xóa");
		btnDEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                FrameNhapSanPham frameNhapSanPham = new FrameNhapSanPham("Xóa Sản Phẩm", "Xóa Sản Phẩm");
                frameNhapSanPham.setVisible(true);
			}
		});
		btnDEL.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDEL.setBounds(614, 192, 150, 30);
		contentPane.add(btnDEL);
		
		btnSa = new JButton("Sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo và hiển thị giao diện FrameNhapSanPham cho chức năng sửa
                FrameNhapSanPham frameNhapSanPham = new FrameNhapSanPham("Sửa Sản Phẩm", "Sửa Sản Phẩm");
                frameNhapSanPham.setVisible(true);
			}
		});
		btnSa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSa.setBounds(614, 245, 150, 30);
		contentPane.add(btnSa);
		
		btnHinThDs = new JButton("Hiển Thị DS\r\n");
		btnHinThDs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnHinThDs.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHinThDs.setBounds(614, 301, 150, 30);
		contentPane.add(btnHinThDs);
		
		JLabel lblNewLabel = new JLabel("Tên sản phẩm:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(224, 23, 107, 30);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(358, 24, 200, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(626, 23, 100, 30);
		contentPane.add(btnNewButton);
	}
}
