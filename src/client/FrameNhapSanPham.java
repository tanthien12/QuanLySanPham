package client;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import server.InterfaceQLSP;
import server.SanPham;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class FrameNhapSanPham extends JFrame {

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
					FrameNhapSanPham frame = new FrameNhapSanPham(null, null);
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
	public FrameNhapSanPham(String title, String chucNang) {
		
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
		
		JLabel lblNewLabel = new JLabel("Mã sản phẩm:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(84, 60, 130, 25);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(222, 112, 294, 25);
		contentPane.add(textField_1);
		
		JLabel lblTnSnPhm = new JLabel("Tên sản phẩm:");
		lblTnSnPhm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnSnPhm.setBounds(84, 112, 130, 25);
		contentPane.add(lblTnSnPhm);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(222, 166, 294, 25);
		contentPane.add(textField_2);
		
		JLabel lblGi = new JLabel("Giá:\r\n");
		lblGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGi.setBounds(84, 166, 130, 25);
		contentPane.add(lblGi);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(222, 220, 294, 25);
		contentPane.add(textField_3);
		
		JLabel lblMT = new JLabel("Mô tả:");
		lblMT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMT.setBounds(84, 220, 130, 25);
		contentPane.add(lblMT);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(222, 275, 294, 25);
		contentPane.add(textField_4);
		
		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSLng.setBounds(84, 275, 130, 25);
		contentPane.add(lblSLng);
		
		JButton btnSave = new JButton("Lưu");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Thực hiện lưu sản phẩm vào database tùy theo chức năng đã chọn
                if (chucNang.equals("Thêm Sản Phẩm")) {
                    themSanPham();
                } else if (chucNang.equals("Sửa Sản Phẩm")) {
                    suaSanPham();
                } else if (chucNang.equals("Xóa Sản Phẩm")) {
                    xoaSanPham();
                }
                // Sau khi lưu, đóng cửa sổ FrameNhapSanPham
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
				// Đóng cửa sổ FrameNhapSanPham khi hủy
				dispose();
			}
		});
		btnCanel.setForeground(Color.BLACK);
		btnCanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCanel.setBackground(Color.RED);
		btnCanel.setBounds(404, 341, 85, 30);
		contentPane.add(btnCanel);
	}
	 // Hàm thêm sản phẩm vào database
    private void themSanPham() {
    	try {
    	    int maSanPham = Integer.parseInt(textField.getText());
    	    String tenSanPham = textField_1.getText();
    	    double gia = Double.parseDouble(textField_2.getText());
    	    String moTa = textField_3.getText();
    	    int soLuong = Integer.parseInt(textField_4.getText());

    	    SanPham sp = new SanPham(maSanPham, tenSanPham, gia, moTa, soLuong);
    	    qlspService.themSanPham(sp);
    	    JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
    	} catch (NumberFormatException | RemoteException ex) {
    	    JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
    	    ex.printStackTrace();
    	}

    }

    // Hàm sửa thông tin sản phẩm trong database
    private void suaSanPham() {
    	try {
            int maSanPham = Integer.parseInt(textField.getText());
            String tenSanPham = textField_1.getText();
            double gia = Double.parseDouble(textField_2.getText());
            String moTa = textField_3.getText();
            int soLuong = Integer.parseInt(textField_4.getText());

            // Tạo một đối tượng SanPham mới với thông tin đã nhập
            SanPham sp = new SanPham(maSanPham, tenSanPham, gia, moTa, soLuong);

            // Gọi phương thức sửa sản phẩm từ qlspService
            boolean result = qlspService.capNhatSanPham(sp);

            // Kiểm tra kết quả và hiển thị thông báo tương ứng
            if (result) {
                JOptionPane.showMessageDialog(null, "Sửa sản phẩm thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Sửa sản phẩm thất bại!");
            }
        } catch (NumberFormatException | RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Sửa sản phẩm thất bại!");
            ex.printStackTrace();
        }
    }

    // Hàm xóa sản phẩm khỏi database
    private void xoaSanPham() {
    	try {
            int maSanPham = Integer.parseInt(textField.getText());

            // Gọi phương thức xóa sản phẩm từ qlspService
            boolean result = qlspService.xoaSanPham(maSanPham);

            // Kiểm tra kết quả và hiển thị thông báo tương ứng
            if (result) {
                JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Xóa sản phẩm thất bại!");
            }
        } catch (NumberFormatException | RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Xóa sản phẩm thất bại!");
            ex.printStackTrace();
        }
    }
    
}
