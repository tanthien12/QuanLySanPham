package client;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import server.DonHang;
import server.InterfaceQLSP;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.awt.event.ActionEvent;

public class FormDonHang extends JFrame {

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
					FormDonHang frame = new FormDonHang();
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
	public FormDonHang() {
		
		try {
			qlspService = (InterfaceQLSP) Naming.lookup("rmi://localhost/QLSPService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("Quản lý Đơn hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 85, 718, 279);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 \u0110\u01A1n H\u00E0ng", "T\u00EAn \u0110\u01A1n H\u00E0ng", "M\u00E3 Kh\u00E1ch H\u00E0ng", "M\u00E3 Nh\u00E2n Vi\u00EAn", "Ng\u00E0y \u0110\u1EB7t", "Tr\u1EA1ng Th\u00E1i"
			}
		));
		
		btnADD = new JButton("Thêm");
		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo và hiển thị giao diện FrameNhapDonHang cho chức năng thêm
                FrameTaoDonHang frameTaoDonHang = new FrameTaoDonHang("Thêm Đơn Hàng", "Thêm Đơn Hàng");
                frameTaoDonHang.setVisible(true);
			}
		});
		btnADD.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnADD.setBounds(119, 386, 150, 30);
		contentPane.add(btnADD);
		
		btnDEL = new JButton("Xóa");
		btnDEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                 // FrameNhapDonHang frameNhapDonHang = new FrameNhapDonHang("Xóa Đơn Hàng", "Xóa Đơn Hàng");
                 // frameNhapDonHang.setVisible(true);
		        // Kiểm tra xem người dùng đã chọn một hàng trong bảng hay chưa
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            // Lấy mã đơn hàng từ hàng đã chọn
		            int maDonHang = (int) table.getValueAt(selectedRow, 0);
		            
		            // Thực hiện xóa đơn hàng
		            xoaDonHang(maDonHang);
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một đơn hàng để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }

			}
		});
		btnDEL.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDEL.setBounds(119, 441, 150, 30);
		contentPane.add(btnDEL);
		
		btnSa = new JButton("Sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo và hiển thị giao diện FrameNhapSanPham cho chức năng sửa
                FrameNhapDonHang frameNhapDonHang = new FrameNhapDonHang("Sửa Đơn Hàng", "Sửa Đơn Hàng");
                frameNhapDonHang.setVisible(true);
			}
		});
		btnSa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSa.setBounds(386, 385, 150, 30);
		contentPane.add(btnSa);
		
		btnHinThDs = new JButton("Hiển Thị DS\r\n");
		btnHinThDs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();

			}
		});
		btnHinThDs.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHinThDs.setBounds(386, 441, 150, 30);
		contentPane.add(btnHinThDs);
		
		JLabel lblNewLabel = new JLabel("Tên đơn hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(100, 23, 107, 30);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(231, 23, 305, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy tên đơn hàng cần tìm kiếm từ textField
		        String tenDonHang = textField.getText();
		        
		        // Kiểm tra xem người dùng đã nhập tên đơn hàng hay chưa
		        if (!tenDonHang.isEmpty()) {
		            try {
		                // Gọi phương thức tìm kiếm đơn hàng từ qlspService
		                List<DonHang> ketQuaTimKiem = qlspService.timKiemDonHang(tenDonHang);

		                // Hiển thị kết quả tìm kiếm
		                if (!ketQuaTimKiem.isEmpty()) {
		                    DefaultTableModel model = (DefaultTableModel) table.getModel();
		                    model.setRowCount(0); // Xóa bảng hiện tại để hiển thị kết quả mới

		                    for (DonHang dh : ketQuaTimKiem) {
		                        model.addRow(new Object[]{
		                            dh.getMaDonHang(),
		                            dh.getTenDonHang(),
		                            dh.getMaKhachHang(),
		                            dh.getMaNhanVien(),
		                            dh.getNgayDatHang(),
		                            dh.getTrangThai()
		                        });
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Không tìm thấy đơn hàng nào với tên \"" + tenDonHang + "\"", "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
		                }
		            } catch (RemoteException ex) {
		                JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm đơn hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		                ex.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên đơn hàng cần tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(577, 23, 100, 30);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(599, 441, 150, 30);
		contentPane.add(btnExit);
		
		refreshTable();
	}
	
	// Hàm xóa đơn hàng
	private void xoaDonHang(int maDonHang) {
	    try {
	        // Gọi phương thức xóa đơn hàng từ qlspService
	        boolean result = qlspService.xoaChiTietDonHang(maDonHang);
	        
	        // Hiển thị thông báo kết quả
	        if (result) {
	            JOptionPane.showMessageDialog(null, "Xóa đơn hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	            qlspService.xoaDonHang(maDonHang);
	            refreshTable(); // Refresh lại bảng sau khi xóa
	        } else {
	            JOptionPane.showMessageDialog(null, "Xóa đơn hàng không thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (RemoteException ex) {
	        JOptionPane.showMessageDialog(null, "Lỗi khi xóa đơn hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}


	// Hàm hiển thị danh sách đơn hàng
    private void refreshTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Reset table

            List<DonHang> donHangList = qlspService.xemDonHang();
            for (DonHang dh : donHangList) {
                model.addRow(new Object[]{
                        dh.getMaDonHang(),
                        dh.getTenDonHang(),
                        dh.getMaKhachHang(),
                        dh.getMaNhanVien(),
                        dh.getNgayDatHang(),
                        dh.getTrangThai()
                });
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
