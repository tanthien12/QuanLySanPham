package client;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import server.DonHang;
import server.InterfaceQLSP;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class FrameNhapDonHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private InterfaceQLSP qlspService;
	private JTextField textField_5;
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
	public FrameNhapDonHang(String title, String chucNang) {
		
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
		textField.setBounds(225, 35, 294, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Mã đơn hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(87, 35, 130, 25);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(225, 84, 294, 25);
		contentPane.add(textField_1);
		
		JLabel lblTnSnPhm = new JLabel("Tên đơn hàng:");
		lblTnSnPhm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnSnPhm.setBounds(87, 84, 130, 25);
		contentPane.add(lblTnSnPhm);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(225, 133, 294, 25);
		contentPane.add(textField_2);
		
		JLabel lblGi = new JLabel("Mã khách hàng:");
		lblGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGi.setBounds(87, 133, 130, 25);
		contentPane.add(lblGi);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(225, 182, 294, 25);
		contentPane.add(textField_3);
		
		JLabel lblMT = new JLabel("Mã nhân viên:");
		lblMT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMT.setBounds(87, 182, 130, 25);
		contentPane.add(lblMT);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(225, 231, 294, 25);
		contentPane.add(textField_4);
		
		JLabel lblSLng = new JLabel("Ngày đặt hàng:");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSLng.setBounds(87, 230, 130, 25);
		contentPane.add(lblSLng);
		
		JButton btnSave = new JButton("Lưu");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Thực hiện lưu đơn hàng vào database tùy theo chức năng đã chọn
                if (chucNang.equals("Thêm Đơn Hàng")) {
                    themDonHang();
                } else if (chucNang.equals("Sửa Đơn Hàng")) {
                    suaDonHang();
                } else if (chucNang.equals("Xóa Đơn Hàng")) {
                    xoaDonHang();
                }
                // Sau khi lưu, đóng cửa sổ FrameNhapDonHang
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
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_5.setColumns(10);
		textField_5.setBounds(225, 280, 294, 25);
		contentPane.add(textField_5);
		
		JLabel lblSLng_1 = new JLabel("Trạng Thái:");
		lblSLng_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSLng_1.setBounds(87, 281, 130, 25);
		contentPane.add(lblSLng_1);
	}
	 // Hàm thêm sản phẩm vào database
    private void themDonHang() {
    	try {
            int maDonHang = Integer.parseInt(textField.getText());
            String tenDonHang = textField_1.getText();
            int maKhachHang = Integer.parseInt(textField_2.getText());
            int maNhanVien = Integer.parseInt(textField_3.getText());
            Date ngayDatHang = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(textField_4.getText()).getTime());
            String trangThai = textField_5.getText();

            // Tạo một đối tượng DonHang mới với thông tin đã nhập
            DonHang dh = new DonHang(maDonHang, tenDonHang, maKhachHang, maNhanVien, ngayDatHang, trangThai);

            // Gọi phương thức thêm đơn hàng từ qlspService
            boolean result = qlspService.themDonHang(dh);

            // Kiểm tra kết quả và hiển thị thông báo tương ứng
            if (result) {
                JOptionPane.showMessageDialog(null, "Thêm đơn hàng thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Thêm đơn hàng thất bại!");
            }
        } catch (NumberFormatException | ParseException | RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Thêm đơn hàng thất bại!");
            ex.printStackTrace();
        }

    }

    // Hàm sửa thông tin sản phẩm trong database
    private void suaDonHang() {
    	 try {
    	        int maDonHang = Integer.parseInt(textField.getText());
    	        String tenDonHang = textField_1.getText();
    	        int maKhachHang = Integer.parseInt(textField_2.getText());
    	        int maNhanVien = Integer.parseInt(textField_3.getText());
    	        Date ngayDatHang = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(textField_4.getText()).getTime());
    	        String trangThai = textField_5.getText();

    	        // Tạo một đối tượng DonHang mới với thông tin đã nhập
    	        DonHang dh = new DonHang(maDonHang, tenDonHang, maKhachHang, maNhanVien, ngayDatHang, trangThai);

    	        // Gọi phương thức sửa đơn hàng từ qlspService
    	        boolean result = qlspService.capNhatDonHang(dh);

    	        // Kiểm tra kết quả và hiển thị thông báo tương ứng
    	        if (result) {
    	            JOptionPane.showMessageDialog(null, "Sửa đơn hàng thành công!");
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Sửa đơn hàng thất bại!");
    	        }
    	    } catch (NumberFormatException | ParseException | RemoteException ex) {
    	        JOptionPane.showMessageDialog(null, "Sửa đơn hàng thất bại!");
    	        ex.printStackTrace();
    	    }
    }

    // Hàm xóa sản phẩm khỏi database
    private void xoaDonHang() {
    	try {
            int maDonHang = Integer.parseInt(textField.getText());

            // Gọi phương thức xóa đơn hàng từ qlspService
            boolean result = qlspService.xoaDonHang(maDonHang);

            // Kiểm tra kết quả và hiển thị thông báo tương ứng
            if (result) {
                JOptionPane.showMessageDialog(null, "Xóa đơn hàng thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Xóa đơn hàng thất bại!");
            }
        } catch (NumberFormatException | RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Xóa đơn hàng thất bại!");
            ex.printStackTrace();
        }
    }
}
