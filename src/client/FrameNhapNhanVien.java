package client;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import server.InterfaceQLSP;
import server.NhanVien;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class FrameNhapNhanVien extends JFrame {

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
	public FrameNhapNhanVien(String title, String chucNang) {
		
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
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(84, 60, 130, 25);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(222, 112, 294, 25);
		contentPane.add(textField_1);
		
		JLabel lblTnSnPhm = new JLabel("Tên nhân viên:");
		lblTnSnPhm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnSnPhm.setBounds(84, 112, 130, 25);
		contentPane.add(lblTnSnPhm);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(222, 166, 294, 25);
		contentPane.add(textField_2);
		
		JLabel lblGi = new JLabel("Chức vụ:\r\n");
		lblGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGi.setBounds(84, 166, 130, 25);
		contentPane.add(lblGi);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(222, 220, 294, 25);
		contentPane.add(textField_3);
		
		JLabel lblMT = new JLabel("Lương:");
		lblMT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMT.setBounds(84, 220, 130, 25);
		contentPane.add(lblMT);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(222, 275, 294, 25);
		contentPane.add(textField_4);
		
		JLabel lblSLng = new JLabel("Ngày bắt đầu");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSLng.setBounds(84, 275, 130, 25);
		contentPane.add(lblSLng);
		
		JButton btnSave = new JButton("Lưu");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Thực hiện lưu nhân Viên vào database tùy theo chức năng đã chọn
                if (chucNang.equals("Thêm Nhân Viên")) {
                    themNhanVien();
                } else if (chucNang.equals("Sửa Nhân Viên")) {
                    suaNhanVien();
                }
                // Sau khi lưu, đóng cửa sổ FrameNhapNhanVien
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
				// Đóng cửa sổ FrameNhapNhanVien khi hủy
				dispose();
			}
		});
		btnCanel.setForeground(Color.BLACK);
		btnCanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCanel.setBackground(Color.RED);
		btnCanel.setBounds(404, 341, 85, 30);
		contentPane.add(btnCanel);
	}
	 // Hàm thêm nhân viên vào database
	private void themNhanVien() {
	    try {
	        // Lấy thông tin của nhân viên từ các trường nhập liệu
	        int maNhanVien = Integer.parseInt(textField.getText());
	        String tenNhanVien = textField_1.getText();
	        String chucVu = textField_2.getText();
	        double luong = Double.parseDouble(textField_3.getText());
	        Date ngayBatDau = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(textField_4.getText()).getTime()); 

	        // Tạo một đối tượng NhanVien mới với thông tin đã nhập
	        NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, chucVu, luong, ngayBatDau);

	        // Gọi phương thức thêm nhân viên từ qlspService
	        qlspService.themNhanVien(nv);

	        // Hiển thị thông báo thêm nhân viên thành công
	        JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
	    } catch (NumberFormatException | ParseException | RemoteException ex) {
	        // Xử lý ngoại lệ và hiển thị thông báo thất bại
	        JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại! Vui lòng kiểm tra lại thông tin nhập vào.");
	        ex.printStackTrace();
	    }
	}

    // Hàm sửa thông tin sản phẩm trong database
    private void suaNhanVien() {
        try {
            // Lấy thông tin của nhân viên từ các trường nhập liệu
            int maNhanVien = Integer.parseInt(textField.getText());
            String tenNhanVien = textField_1.getText();
            String chucVu = textField_2.getText();
            double luong = Double.parseDouble(textField_3.getText());
            Date ngayBatDau = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(textField_4.getText()).getTime());

            // Tạo một đối tượng NhanVien mới với thông tin đã nhập
            NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, chucVu, luong, ngayBatDau);

            // Gọi phương thức sửa nhân viên từ qlspService
            boolean result = qlspService.capNhatNhanVien(nv);

            // Kiểm tra kết quả và hiển thị thông báo tương ứng
            if (result) {
                JOptionPane.showMessageDialog(null, "Sửa nhân viên thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Sửa nhân viên thất bại!");
            }
        } catch (NumberFormatException | ParseException | RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Sửa nhân viên thất bại! Vui lòng kiểm tra lại thông tin nhập vào.");
            ex.printStackTrace();
        }
    }
    public void setDataFormNhanVien(int maNhanVien, String tenNhanVien, String chucVu, double luong, Date ngayBatDau) {
        textField.setText(Integer.toString(maNhanVien));
        textField_1.setText(tenNhanVien);
        textField_2.setText(chucVu);
        textField_3.setText(Double.toString(luong));
        textField_4.setText(new SimpleDateFormat("yyyy-MM-dd").format(ngayBatDau));
    }


    
}
