package client;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import server.InterfaceQLSP;
import server.KhachHang;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class FrameNhapKhachHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private InterfaceQLSP qlspService;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameNhapKhachHang frame = new FrameNhapKhachHang(null, null);
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
	public FrameNhapKhachHang(String title, String chucNang) {
		
		try {
			qlspService = (InterfaceQLSP) Naming.lookup("rmi://localhost/QLSPService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setBounds(222, 60, 294, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Mã khách hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(84, 60, 130, 25);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(222, 112, 294, 25);
		contentPane.add(textField_1);
		
		JLabel lblTnSnPhm = new JLabel("Tên Khách hàng:");
		lblTnSnPhm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnSnPhm.setBounds(84, 112, 130, 25);
		contentPane.add(lblTnSnPhm);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(222, 166, 294, 25);
		contentPane.add(textField_2);
		
		JLabel lblGi = new JLabel("Địa chỉ:");
		lblGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGi.setBounds(84, 166, 130, 25);
		contentPane.add(lblGi);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(222, 220, 294, 25);
		contentPane.add(textField_3);
		
		JLabel lblMT = new JLabel("Số điện thoại");
		lblMT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMT.setBounds(84, 220, 130, 25);
		contentPane.add(lblMT);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(222, 275, 294, 25);
		contentPane.add(textField_4);
		
		JLabel lblSLng = new JLabel("Email:");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSLng.setBounds(84, 275, 130, 25);
		contentPane.add(lblSLng);
		
		JButton btnSave = new JButton("Lưu");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Thực hiện lưu Khách Hàng vào database tùy theo chức năng đã chọn
                if (chucNang.equals("Thêm Khách Hàng")) {
                    themKhachHang();
                } else if (chucNang.equals("Sửa Khách Hàng")) {
                    suaKhachHang();
                }
                // Sau khi lưu, đóng cửa sổ FrameNhapKhachHang
                dispose();
			}
		});
		btnSave.setForeground(new Color(0, 0, 0));
		btnSave.setBackground(new Color(0, 128, 255));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(241, 341, 85, 30);
		contentPane.add(btnSave);
		
		JButton btnCanel = new JButton("Hủy\r\n");
		btnCanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Đóng cửa sổ FrameNhapKhachHang khi hủy
				dispose();
			}
		});
		btnCanel.setForeground(Color.BLACK);
		btnCanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCanel.setBackground(Color.RED);
		btnCanel.setBounds(404, 341, 85, 30);
		contentPane.add(btnCanel);
	}
	// Hàm thêm khách hàng vào database
	private void themKhachHang() {
	    try {
	        // Lấy thông tin của khách hàng từ các trường nhập liệu
	        int maKhachHang = Integer.parseInt(textField.getText());
	        String tenKhachHang = textField_1.getText();
	        String diaChi = textField_2.getText();
	        String soDienThoai = textField_3.getText();
	        String email = textField_4.getText();

	        // Tạo một đối tượng KhachHang mới với thông tin đã nhập
	        KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, diaChi, soDienThoai, email);

	        // Gọi phương thức thêm khách hàng từ qlspService
	        qlspService.themKhachHang(kh);

	        // Hiển thị thông báo thêm khách hàng thành công
	        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
	    } catch (NumberFormatException | RemoteException ex) {
	        // Xử lý ngoại lệ và hiển thị thông báo thất bại
	        JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại! Vui lòng kiểm tra lại thông tin nhập vào.");
	        ex.printStackTrace();
	    }
	}

	// Hàm sửa thông tin khách hàng trong database
	private void suaKhachHang() {
	    try {
	        // Lấy thông tin của khách hàng từ các trường nhập liệu
	        int maKhachHang = Integer.parseInt(textField.getText());
	        String tenKhachHang = textField_1.getText();
	        String diaChi = textField_2.getText();
	        String soDienThoai = textField_3.getText();
	        String email = textField_4.getText();

	        // Tạo một đối tượng KhachHang mới với thông tin đã nhập
	        KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, diaChi, soDienThoai, email);

	        // Gọi phương thức sửa khách hàng từ qlspService
	        boolean result = qlspService.capNhatKhachHang(kh);

	        // Kiểm tra kết quả và hiển thị thông báo tương ứng
	        if (result) {
	            JOptionPane.showMessageDialog(null, "Sửa khách hàng thành công!");
	        } else {
	            JOptionPane.showMessageDialog(null, "Sửa khách hàng thất bại!");
	        }
	    } catch (NumberFormatException | RemoteException ex) {
	        JOptionPane.showMessageDialog(null, "Sửa khách hàng thất bại! Vui lòng kiểm tra lại thông tin nhập vào.");
	        ex.printStackTrace();
	    }
	}

}
