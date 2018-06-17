package com.chinasofti.etc.hiq.view;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;


public class Hyperlink extends JLabel { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 7909300266096357906L;
	private String text, url; 
	private boolean isSupported; 
	public Hyperlink(String text, String url) { 
		this.text = text; 
		this.url = url; 
		//���ϵͳ�Ƿ�֧�������
		try { 
			this.isSupported = Desktop.isDesktopSupported() 
			&& Desktop.getDesktop().isSupported(Desktop.Action.BROWSE); 
		} catch (Exception e) { 
			this.isSupported = false; 
		} 
		setText(false); 
		addMouseListener(new MouseAdapter() { 
		public void mouseEntered(MouseEvent e) { 
			setText(isSupported); 
			if (isSupported) 
				setCursor(new Cursor(Cursor.HAND_CURSOR)); 
		} 
		public void mouseExited(MouseEvent e) { 
			setText(false); 
		} 
		//����Ĭ�������������������ʾָ����ַ
		public void mouseClicked(MouseEvent e) { 
			try { 
				Desktop.getDesktop().browse( 
						new java.net.URI(Hyperlink.this.url)); 
			} catch (Exception ex) { 
			} 
		} 
		}); 
	} 
	private void setText(boolean b) { 
		if (!b) 
			setText("<html><h4><font color=black ><u>" + text); 
		else 
			setText("<html><font color=red><u>" + text); 
	} 
	
}
 