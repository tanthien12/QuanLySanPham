package client;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import server.InterfaceQLSP;
import server.SanPham;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
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
	
	private static InterfaceQLSP qlspService;
	private JButton btnExit;

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
		
		try {
			qlspService = (InterfaceQLSP) Naming.lookup("rmi://localhost/QLSPService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("Quản lý Sản phẩm");
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
                refreshTable();
			}
		});
		btnADD.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnADD.setBounds(614, 137, 150, 30);
		contentPane.add(btnADD);
		
		btnDEL = new JButton("Xóa");
		btnDEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                //FrameNhapSanPham frameNhapSanPham = new FrameNhapSanPham("Xóa Sản Phẩm", "Xóa Sản Phẩm");
                //frameNhapSanPham.setVisible(true);
				// Kiểm tra xem người dùng đã chọn một hàng trong bảng hay chưa
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            // Lấy mã sản phẩm từ hàng đã chọn
		            int maSanPham = (int) table.getValueAt(selectedRow, 0);
		            
		            // Thực hiện xóa sản phẩm
		            xoaSanPham(maSanPham);
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
				
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
				refreshTable();

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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Lấy tên sản phẩm cần tìm kiếm từ textField
		        String tenSP = textField.getText();
		        
		        // Kiểm tra xem người dùng đã nhập tên sản phẩm hay chưa
		        if (!tenSP.isEmpty()) {
		            try {
		                // Gọi phương thức tìm kiếm sản phẩm từ qlspService
		                List<SanPham> ketQuaTimKiem = qlspService.timKiemSanPham(tenSP);

		                // Hiển thị kết quả tìm kiếm
		                if (!ketQuaTimKiem.isEmpty()) {
		                    DefaultTableModel model = (DefaultTableModel) table.getModel();
		                    model.setRowCount(0); // Xóa bảng hiện tại để hiển thị kết quả mới

		                    for (SanPham sp : ketQuaTimKiem) {
		                        model.addRow(new Object[]{
		                            sp.getMaSanPham(),
		                            sp.getTenSanPham(),
		                            sp.getGia(),
		                            sp.getMoTa(),
		                            sp.getSoLuong()
		                        });
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm nào với tên \"" + tenSP + "\"", "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
		                }
		            } catch (RemoteException ex) {
		                JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm sản phẩm!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		                ex.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm cần tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
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
	// Phương thức xóa sản phẩm dựa trên mã sản phẩm (kiểu int)
	private void xoaSanPham(int maSanPham) {
	    try {
	        // Gọi phương thức xóa sản phẩm từ qlspService
	        boolean result = qlspService.xoaSanPham(maSanPham);
	        
	        // Hiển thị thông báo kết quả
	        if (result) {
	            JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	            refreshTable(); // Refresh lại bảng sau khi xóa
	        } else {
	            JOptionPane.showMessageDialog(null, "Xóa sản phẩm không thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (RemoteException ex) {
	        JOptionPane.showMessageDialog(null, "Lỗi khi xóa sản phẩm!", "Thông báo", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}

	
	// Hàm hiển thị danh sách sản phẩm
    private void refreshTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Reset table

            List<SanPham> sanPhamList = qlspService.xemSanPham();
            for (SanPham sp : sanPhamList) {
                model.addRow(new Object[]{
                        sp.getMaSanPham(),
                        sp.getTenSanPham(),
                        sp.getGia(),
                        sp.getMoTa(),
                        sp.getSoLuong()
                });
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
