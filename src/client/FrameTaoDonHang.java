package client;

import java.awt.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import server.ChiTietHoaDon;
import server.DonHang;
import server.InterfaceQLSP;
import server.SanPham;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class FrameTaoDonHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private InterfaceQLSP qlspService;
	private JTextField textField_6;
	private JTextField textField_7;
	private JComboBox<String> comboBox;
	private JTextField textField_5;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					FrameTaoDonHang frame = new FrameTaoDonHang(null, null);
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
	public FrameTaoDonHang(String title, String chucNang) {
		
		try {
			qlspService = (InterfaceQLSP) Naming.lookup("rmi://localhost/QLSPService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 591);
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
		textField_1.setBounds(225, 83, 294, 25);
		contentPane.add(textField_1);
		
		JLabel lblTnSnPhm = new JLabel("Tên đơn hàng:");
		lblTnSnPhm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnSnPhm.setBounds(87, 83, 130, 25);
		contentPane.add(lblTnSnPhm);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(225, 179, 294, 25);
		contentPane.add(textField_2);
		
		JLabel lblGi = new JLabel("Mã khách hàng:");
		lblGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGi.setBounds(87, 179, 130, 25);
		contentPane.add(lblGi);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(225, 227, 294, 25);
		contentPane.add(textField_3);
		
		JLabel lblMT = new JLabel("Mã nhân viên:");
		lblMT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMT.setBounds(87, 227, 130, 25);
		contentPane.add(lblMT);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(225, 275, 294, 25);
		contentPane.add(textField_4);
		
		JLabel lblSLng = new JLabel("Ngày đặt hàng:");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSLng.setBounds(87, 275, 130, 25);
		contentPane.add(lblSLng);
		
		JButton btnSave = new JButton("Lưu");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Thực hiện lưu đơn hàng vào database tùy theo chức năng đã chọn
                if (chucNang.equals("Thêm Đơn Hàng")) {
                    themDonHang();
                } else if (chucNang.equals("Sửa Đơn Hàng")) {
                    suaDonHang();
                }
                // Sau khi lưu, đóng cửa sổ FrameNhapDonHang
                dispose();
			}
		});
		btnSave.setForeground(new Color(0, 0, 0));
		btnSave.setBackground(new Color(0, 128, 255));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(241, 480, 85, 30);
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
		btnCanel.setBounds(404, 480, 85, 30);
		contentPane.add(btnCanel);
		
		JLabel lblTrangThai = new JLabel("Trạng Thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrangThai.setBounds(87, 419, 130, 25);
		contentPane.add(lblTrangThai);
		
		JLabel lblSoluong = new JLabel("Số lượng:");
		lblSoluong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSoluong.setBounds(87, 323, 130, 25);
		contentPane.add(lblSoluong);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_6.setColumns(10);
		textField_6.setBounds(225, 323, 76, 25);
		contentPane.add(textField_6);
		
		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongTien.setBounds(87, 371, 130, 25);
		contentPane.add(lblTongTien);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_7.setColumns(10);
		textField_7.setBounds(225, 371, 294, 25);
		contentPane.add(textField_7);
		
        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Đã thanh toán", "Chưa thanh toán", "Đã hủy"}));
        comboBox.setBounds(225, 419, 155, 25);
		contentPane.add(comboBox);
		
		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaSP.setBounds(87, 131, 130, 25);
		contentPane.add(lblMaSP);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_5.setColumns(10);
		textField_5.setBounds(225, 131, 294, 25);
		contentPane.add(textField_5);
		
		// Thêm sự kiện ActionListener cho textField_6 để tính tổng tiền khi số lượng thay đổi
		textField_6.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        tinhTongTien();
		    }
		});

		// Thêm sự kiện ActionListener cho textField_5 để tính tổng tiền khi chọn sản phẩm
		textField_5.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        tinhTongTien();
		    }
		});
	}
	
	// Hàm thêm sản phẩm vào database
	private void themDonHang() {
	    try {	        
	        int maDonHang = Integer.parseInt(textField.getText());
	        String tenDonHang = textField_1.getText();
	        int maKhachHang = Integer.parseInt(textField_2.getText());
	        int maNhanVien = Integer.parseInt(textField_3.getText());
	        Date ngayDatHang = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(textField_4.getText()).getTime());
	        int maSanPham = Integer.parseInt(textField_5.getText());
	        int soLuong = Integer.parseInt(textField_6.getText());
	        double tongTien = Double.parseDouble(textField_7.getText());
	        String trangThai = (String) comboBox.getSelectedItem();
	        
	        // Kiểm tra số lượng nhập vào có lớn hơn số lượng tồn kho không
	        SanPham sp = qlspService.timKiemSanPham(maSanPham);
	        if (soLuong > sp.getSoLuong()) {
	            JOptionPane.showMessageDialog(null, "Số lượng nhập vào lớn hơn số lượng tồn kho!");
	            return; // Dừng việc thêm đơn hàng
	        }

	        // Tạo một đối tượng DonHang mới với thông tin đã nhập
	        DonHang dh = new DonHang(maDonHang, tenDonHang, maKhachHang, maNhanVien, ngayDatHang, trangThai);

	        // Gọi phương thức thêm đơn hàng từ qlspService
	        boolean result = qlspService.themDonHang(dh);

	        // Kiểm tra kết quả và hiển thị thông báo tương ứng
	        if (result) {
	            ChiTietHoaDon ctdh = new ChiTietHoaDon(maDonHang, maNhanVien, soLuong, tongTien);
	            qlspService.themChiTietDonHang(ctdh);
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
    	        String trangThai = (String) comboBox.getSelectedItem();

    	        // Tạo một đối tượng DonHang mới với thông tin đã nhập
    	        DonHang dh = new DonHang(maDonHang, tenDonHang, maKhachHang, maNhanVien, ngayDatHang, trangThai);

    	        // Gọi phương thức sửa đơn hàng từ qlspService
    	        boolean result = qlspService.capNhatDonHang(dh);

    	        // Kiểm tra kết quả và hiển thị thông báo tương ứng
    	        if (result) {
    	        	ChiTietHoaDon ctdh = new ChiTietHoaDon(maDonHang, maDonHang, maKhachHang, maNhanVien);
    	        	qlspService.capNhatCTDH(ctdh);
    	            JOptionPane.showMessageDialog(null, "Sửa đơn hàng thành công!");
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Sửa đơn hàng thất bại!");
    	        }
    	    } catch (NumberFormatException | ParseException | RemoteException ex) {
    	        JOptionPane.showMessageDialog(null, "Sửa đơn hàng thất bại!");
    	        ex.printStackTrace();
    	    }
    }
    
 // Thêm một phương thức trong lớp FrameTaoDonHang để tính toán tổng tiền dựa trên số lượng và giá của sản phẩm được chọn
    private void tinhTongTien() {
        try {
            int maSanPham = Integer.parseInt(textField_5.getText());
            int soLuong = Integer.parseInt(textField_6.getText());
            
            // Lấy thông tin sản phẩm từ service
            SanPham sp = qlspService.timKiemSanPham(maSanPham);
            
            // Tính tổng tiền
            double tongTien = sp.getGia() * soLuong;
            
            // Hiển thị tổng tiền lên textField_7
            textField_7.setText(String.valueOf(tongTien));
        } catch (NumberFormatException | RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tính tổng tiền!");
            ex.printStackTrace();
        }
    }
 // Hàm để thiết lập dữ liệu vào các trường của FrameTaoDonHang để chỉnh sửa thông tin của đơn hàng đã tồn tại
    public void setDataFormDonHang(int maDonHang, String tenDonHang, int maKhachHang, int maNhanVien, Date ngayDatHang, String trangThai, int maSanPham, int soLuong, double tongTien) {
        // Thiết lập dữ liệu cho chi tiết đơn hàng
        textField.setText(Integer.toString(maDonHang)); // Mã đơn hàng
        textField_1.setText(tenDonHang); // Tên đơn hàng
        textField_2.setText(Integer.toString(maKhachHang)); // Mã khách hàng
        textField_3.setText(Integer.toString(maNhanVien)); // Mã nhân viên
        textField_4.setText(ngayDatHang.toString()); // Ngày đặt hàng
        comboBox.setSelectedItem(trangThai); // Trạng thái

        // Thiết lập dữ liệu cho chi tiết sản phẩm
        textField_5.setText(Integer.toString(maSanPham)); // Mã sản phẩm
        textField_6.setText(Integer.toString(soLuong)); // Số lượng
        textField_7.setText(Double.toString(tongTien)); // Tổng tiền
    }
}
