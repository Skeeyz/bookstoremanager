package org.example;

import java.awt.EventQueue;

import org.example.DAO.MyConnect;
import org.example.GUI.DangNhap;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (ClassNotFoundException ex) {
				
				} catch (InstantiationException ex) {
					
				} catch (IllegalAccessException ex) {
					
				} catch (javax.swing.UnsupportedLookAndFeelException ex) {
					
				}
				try {
					new MyConnect();
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
