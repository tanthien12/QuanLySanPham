package client;

import java.awt.*;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import server.ChiTietHoaDon;
import server.InterfaceQLSP;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.awt.event.ActionEvent;

public class FormCTDH extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnHinThDs;
	private JTextField textField;
	
	private JComboBox<String> comboBox; 
	
	private static InterfaceQLSP qlspService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCTDH frame = new FormCTDH();
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
	public FormCTDH() {
		
		try {
			qlspService = (InterfaceQLSP) Naming.lookup("rmi://localhost/QLSPService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("Quản lý sản phẩm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 85, 734, 311);
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
				"STT", "M\u00E3 \u0111\u01A1n h\u00E0ng", "M\u00E3 s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "T\u1ED5ng gi\u00E1"
			}
		));
		
		btnHinThDs = new JButton("Hiển Thị DS\r\n");
		btnHinThDs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();

			}
		});
		btnHinThDs.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHinThDs.setBounds(170, 430, 150, 30);
		contentPane.add(btnHinThDs);
		
		comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã đơn hàng", "Mã sản phẩm"}));
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        comboBox.setBounds(78, 23, 150, 30);
        contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(254, 24, 298, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String selectedOption = comboBox.getSelectedItem().toString();
				 int keyword = Integer.parseInt(textField.getText());
	             search(selectedOption, keyword);
		    }
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(614, 23, 100, 30);
		contentPane.add(btnNewButton);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnThot.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThot.setBounds(490, 430, 150, 30);
		contentPane.add(btnThot);
		
		
	}
	// Hàm hiển chi tiết đơn hàng
	private void refreshTable() {
	    try {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); // Reset table

	        List<ChiTietHoaDon> donHangList = qlspService.xemSachDonHang(); 

	        for (ChiTietHoaDon dh : donHangList) {
	            model.addRow(new Object[]{
	                dh.getId(),
	                dh.getMaDonHang(),
	                dh.getMaSanPham(),
	                dh.getSoLuong(),
	                dh.getDonGia()
	            });
	        }
	    } catch (RemoteException ex) {
	        ex.printStackTrace();
	    }
	}
	
	// Hàm tìm kiếm chi tiết đơn hàng
    private void search(String selectedOption, int keyword) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Reset table

            List<ChiTietHoaDon> donHangList;
            if (selectedOption.equals("Mã đơn hàng")) {
                donHangList = qlspService.timKiemTheoMaDonHang(keyword);
            } else {
                donHangList = qlspService.timKiemTheoMaSanPham(keyword);
            }

            for (ChiTietHoaDon dh : donHangList) {
                model.addRow(new Object[]{
                    dh.getId(),
                    dh.getMaDonHang(),
                    dh.getMaSanPham(),
                    dh.getSoLuong(),
                    dh.getDonGia()
                });
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
