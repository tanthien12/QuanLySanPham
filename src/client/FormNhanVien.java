package client;

import java.awt.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import server.InterfaceQLSP;
import server.NhanVien;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class FormNhanVien extends JFrame {

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
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormNhanVien frame = new FormNhanVien();
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
	public FormNhanVien() {
		
		try {
			qlspService = (InterfaceQLSP) Naming.lookup("rmi://localhost/QLSPService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("Quản lý Nhân viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);

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
				{null, null, null, "", null},
				{null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn", "Ch\u1EE9c V\u1EE5", "L\u01B0\u01A1ng", "Ng\u00E0y b\u1EAFt \u0111\u1EA7u"
			}
		));
		
		btnADD = new JButton("Thêm");
		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo và hiển thị giao diện FrameNhapNhanVien cho chức năng thêm
                FrameNhapNhanVien frameNhapNhanVien = new FrameNhapNhanVien("Thêm Nhân Viên", "Thêm Nhân Viên");
                frameNhapNhanVien.setVisible(true);
			}
		});
		btnADD.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnADD.setBounds(614, 137, 150, 30);
		contentPane.add(btnADD);
		
		btnDEL = new JButton("Xóa");
		btnDEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                // FrameNhapNhanVien frameNhapNhanVien = new FrameNhapNhanVien("Xóa Nhân Viên", "Xóa Nhân Viên");
                // frameNhapNhanVien.setVisible(true);
				// Kiểm tra xem người dùng đã chọn một hàng trong bảng hay chưa
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            // Lấy mã nhân viên từ hàng đã chọn
		            int maNhanVien = (int) table.getValueAt(selectedRow, 0);
		            
		            // Thực hiện xóa nhân viên
		            xoaNhanVien(maNhanVien);
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		btnDEL.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDEL.setBounds(614, 192, 150, 30);
		contentPane.add(btnDEL);
		
		btnSa = new JButton("Sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				// Tạo và hiển thị giao diện FrameNhapNhanVien cho chức năng sửa
//                FrameNhapNhanVien frameNhapNhanVien = new FrameNhapNhanVien("Sửa Nhân Viên", "Sửa Nhân Viên");
//                frameNhapNhanVien.setVisible(true);
				 // Kiểm tra xem người dùng đã chọn một hàng trong bảng hay chưa
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            // Lấy thông tin nhân viên từ hàng đã chọn
		            int maNhanVien = (int) table.getValueAt(selectedRow, 0);
		            String tenNhanVien = (String) table.getValueAt(selectedRow, 1);
		            String chucVu = (String) table.getValueAt(selectedRow, 2);
		            double luong = (double) table.getValueAt(selectedRow, 3);
		            Date ngayBatDau = (Date) table.getValueAt(selectedRow, 4);

		            // Tạo và hiển thị giao diện FrameNhapNhanVien cho chức năng sửa
		            FrameNhapNhanVien frameNhapNhanVien = new FrameNhapNhanVien("Sửa Nhân Viên", "Sửa Nhân Viên");
		            frameNhapNhanVien.setDataFormNhanVien(maNhanVien, tenNhanVien, chucVu, luong, ngayBatDau);
		            frameNhapNhanVien.setVisible(true);
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên từ bảng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		btnSa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSa.setBounds(614, 245, 150, 30);
		contentPane.add(btnSa);
		
		btnHinThDs = new JButton("Hiển Thị DS\r\n");
		btnHinThDs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();

			}
		});
		btnHinThDs.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHinThDs.setBounds(614, 301, 150, 30);
		contentPane.add(btnHinThDs);
		
		JLabel lblNewLabel = new JLabel("Tên nhân viên:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(210, 23, 121, 30);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(358, 24, 200, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        // Lấy tên nhân viên cần tìm kiếm từ textField
		        String tenNV = textField.getText();

		        // Kiểm tra xem người dùng đã nhập tên nhân viên hay chưa
		        if (!tenNV.isEmpty()) {
		            try {
		                // Gọi phương thức tìm kiếm nhân viên từ qlspService
		                List<NhanVien> ketQuaTimKiem = qlspService.timKiemNhanVien(tenNV);

		                // Hiển thị kết quả tìm kiếm
		                if (!ketQuaTimKiem.isEmpty()) {
		                    DefaultTableModel model = (DefaultTableModel) table.getModel();
		                    model.setRowCount(0); // Xóa bảng hiện tại để hiển thị kết quả mới

		                    for (NhanVien nv : ketQuaTimKiem) {
		                        model.addRow(new Object[]{
		                            nv.getMaNhanVien(),
		                            nv.getTenNhanVien(),
		                            nv.getChucVu(),
		                            nv.getLuong(),
		                            nv.getNgayBatDau()
		                        });
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên nào với tên \"" + tenNV + "\"", "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
		                }
		            } catch (RemoteException ex) {
		                JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm nhân viên!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		                ex.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhân viên cần tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(626, 23, 100, 30);
		contentPane.add(btnNewButton);
		
		btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(614, 425, 150, 30);
		contentPane.add(btnExit);
		refreshTable();
	}
	
	// Hàm xóa Nhân Viên
	private void xoaNhanVien(int maNhanVien) {
	    try {
	        // Gọi phương thức xóa nhân viên từ qlspService
	        boolean result = qlspService.xoaNhanVien(maNhanVien);
	        
	        // Hiển thị thông báo kết quả
	        if (result) {
	            JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	            refreshTable(); // Refresh lại bảng sau khi xóa
	        } else {
	            JOptionPane.showMessageDialog(null, "Xóa nhân viên không thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (RemoteException ex) {
	        JOptionPane.showMessageDialog(null, "Lỗi khi xóa nhân viên!", "Thông báo", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}

	// Hàm hiển thị danh sách Nhân viên
	private void refreshTable() {
	    try {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); // Reset table

	        List<NhanVien> nhanVienList = qlspService.xemNhanVien(); 

	        for (NhanVien nv : nhanVienList) {
	            model.addRow(new Object[]{
	                nv.getMaNhanVien(),
	                nv.getTenNhanVien(),
	                nv.getChucVu(),
	                nv.getLuong(),
	                nv.getNgayBatDau()
	            });
	        }
	    } catch (RemoteException ex) {
	        ex.printStackTrace();
	    }
	}
}
