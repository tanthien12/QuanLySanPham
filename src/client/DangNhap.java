package client;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import server.ConnectDatabaseJDBC;

import java.awt.event.*;
import java.sql.*;

public class DangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
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
	public DangNhap() {
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 343);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng nhập");
		lblNewLabel.setBounds(143, 22, 165, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.BOLD, 14));
		textField.setBounds(193, 108, 240, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 14));
		passwordField.setBounds(193, 161, 240, 30);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập :");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1.setBounds(53, 107, 114, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu :");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(53, 161, 114, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
                String password = new String(passwordField.getPassword());
                if (kiemTraDangNhap(username, password)) {
                    // Đăng nhập thành công, chuyển đến giao diện Menu
                    Menu menu = new Menu();
                    menu.setVisible(true);
                    dispose(); // Đóng giao diện đăng nhập sau khi chuyển đến giao diện Menu
                } else {
                    // Hiển thị thông báo lỗi đăng nhập
                    JOptionPane.showMessageDialog(contentPane, "Tên đăng nhập hoặc mật khẩu không đúng!");
                }
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBounds(193, 217, 115, 35);
		contentPane.add(btnNewButton);
	}
	
	// Phương thức kiểm tra đăng nhập với cơ sở dữ liệu
    private boolean kiemTraDangNhap(String username, String password) {
        // Thực hiện truy vấn cơ sở dữ liệu để kiểm tra đăng nhập
        try {
            Connection connection = ConnectDatabaseJDBC.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE TenDangNhap = ? AND MatKhau = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Nếu tồn tại dòng dữ liệu trong kết quả truy vấn, tức là đăng nhập thành công
                return true;
            }
            // Đóng kết nối và các tài nguyên liên quan
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
