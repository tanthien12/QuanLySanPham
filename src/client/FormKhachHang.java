package client;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import server.InterfaceQLSP;
import server.KhachHang;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.awt.event.ActionEvent;

public class FormKhachHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnADD;
	private JButton btnDEL;
	private JButton btnSa;
	private JButton btnHinThDs;
	private JTextField textField;
	
	private static InterfaceQLSP qlspService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormKhachHang frame = new FormKhachHang();
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
	public FormKhachHang() {
		
		try {
			qlspService = (InterfaceQLSP) Naming.lookup("rmi://localhost/QLSPService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("Quản lý Khách hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 85, 532, 389);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, "", null},
				{null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 kh\u00E1ch h\u00E0ng", "T\u00EAn kh\u00E1ch h\u00E0ng", "\u0110\u1ECBa ch\u1EC9", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Email"
			}
		));
		
		btnADD = new JButton("Thêm");
		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo và hiển thị giao diện FrameNhapNhanVien cho chức năng thêm
                FrameNhapKhachHang frameNhapKhachHang = new FrameNhapKhachHang("Thêm Khách Hàng", "Thêm Khách Hàng");
                frameNhapKhachHang.setVisible(true);
                
			}
		});
		btnADD.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnADD.setBounds(614, 153, 150, 30);
		contentPane.add(btnADD);
		
		btnDEL = new JButton("Thoát");
		btnDEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnDEL.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDEL.setBounds(614, 425, 150, 30);
		contentPane.add(btnDEL);
		
		btnSa = new JButton("Sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Kiểm tra xem người dùng đã chọn một hàng trong bảng hay chưa
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            // Lấy thông tin khách hàng từ hàng đã chọn
		            int maKhachHang = (int) table.getValueAt(selectedRow, 0);
		            String tenKhachHang = (String) table.getValueAt(selectedRow, 1);
		            String diaChi = (String) table.getValueAt(selectedRow, 2);
		            String soDienThoai = (String) table.getValueAt(selectedRow, 3);
		            String email = (String) table.getValueAt(selectedRow, 4);

		            // Tạo và hiển thị giao diện FrameNhapKhachHang cho chức năng sửa
		            FrameNhapKhachHang frameNhapKhachHang = new FrameNhapKhachHang("Sửa Khách Hàng", "Sửa Khách Hàng");
		            frameNhapKhachHang.setDataFormKhachHang(maKhachHang, tenKhachHang, diaChi, soDienThoai, email);
		            frameNhapKhachHang.setVisible(true);
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một khách hàng từ bảng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		btnSa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSa.setBounds(614, 221, 150, 30);
		contentPane.add(btnSa);
		
		btnHinThDs = new JButton("Hiển Thị DS\r\n");
		btnHinThDs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();

			}
		});
		btnHinThDs.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHinThDs.setBounds(614, 290, 150, 30);
		contentPane.add(btnHinThDs);
		
		JLabel lblNewLabel = new JLabel("Tên khách hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(199, 23, 132, 30);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(358, 24, 200, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy tên khách hàng cần tìm kiếm từ textField
			    String tenKhachHang = textField.getText();

			    // Kiểm tra xem người dùng đã nhập tên khách hàng hay chưa
			    if (!tenKhachHang.isEmpty()) {
			        try {
			            // Gọi phương thức tìm kiếm khách hàng từ qlspService
			            List<KhachHang> ketQuaTimKiem = qlspService.timKiemKhachHang(tenKhachHang);

			            // Hiển thị kết quả tìm kiếm
			            if (!ketQuaTimKiem.isEmpty()) {
			                DefaultTableModel model = (DefaultTableModel) table.getModel();
			                model.setRowCount(0); // Xóa bảng hiện tại để hiển thị kết quả mới

			                for (KhachHang kh : ketQuaTimKiem) {
			                    model.addRow(new Object[]{
			                        kh.getMaKhachHang(),
			                        kh.getTenKhachHang(),
			                        kh.getDiaChi(),
			                        kh.getSoDienThoai(),
			                        kh.getEmail()
			                    });
			                }
			            } else {
			                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng nào với tên \"" + tenKhachHang + "\"", "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
			            }
			        } catch (RemoteException ex) {
			            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm khách hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
			            ex.printStackTrace();
			        }
			    } else {
			        JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng cần tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			    }
		    }
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(626, 23, 100, 30);
		contentPane.add(btnNewButton);
		refreshTable();
	}
	
	// Hàm hiển thị danh sách Khách Hàng
	private void refreshTable() {
	    try {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); // Reset table

	        List<KhachHang> khachHangList = qlspService.xemKhachHang(); 

	        for (KhachHang kh : khachHangList) {
	            model.addRow(new Object[]{
	                kh.getMaKhachHang(),
	                kh.getTenKhachHang(),
	                kh.getDiaChi(),
	                kh.getSoDienThoai(),
	                kh.getEmail()
	            });
	        }
	    } catch (RemoteException ex) {
	        ex.printStackTrace();
	    }
	}
}
