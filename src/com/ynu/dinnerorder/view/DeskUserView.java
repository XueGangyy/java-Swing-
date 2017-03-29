package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ynu.dinnerorder.databasedao.DeskDao;
import com.ynu.dinnerorder.databasemodel.DeskModel;

public class DeskUserView extends JFrame {
	
	private JPanel contentPane;
	private String[] s={"桌位id","桌位名字","桌位描述","桌位状态"};
	private JTable table;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JLabel label;
	private JPanel panel;

	public static void main(String[] args) {
		new DeskUserView();
	}
	
	public DeskUserView(){
		init();
	}
	
	public void init(){
		
		setBounds(300, 150, 450, 300);
		setVisible(true);
		setSize(600, 500);
		setResizable(false);
		setTitle("未预定的桌位信息");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		jsp=new JScrollPane();
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(jsp, BorderLayout.CENTER);
		
		panel=new JPanel();
		JLabel j=new JLabel("欢迎查看未预定的桌位详细信息");
		panel.add(j);
		contentPane.add(panel, BorderLayout.NORTH);
		
		dtm=new DefaultTableModel(s, 0);
		table=new JTable(dtm);
		table.setGridColor(Color.orange);
		DeskDao DD=new DeskDao();
		List<DeskModel> ldm=DD.equeryDesk();
		for(DeskModel dm:ldm){
			if(dm.getDesk_state()==0){
				Vector v=new Vector();
				v.add(dm.getDesk_id());
				v.add(dm.getDesk_name());
				v.add(dm.getDesk_description());
				v.add("未预定");
				dtm.addRow(v);
			}
		}
		jsp.setViewportView(table);
//		panel.repaint();
		jsp.repaint();
	}

}
