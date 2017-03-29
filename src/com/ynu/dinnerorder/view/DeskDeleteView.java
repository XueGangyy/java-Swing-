package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.ynu.dinnerorder.databasedao.DeskDao;
import com.ynu.dinnerorder.databasemodel.DeskModel;

public class DeskDeleteView extends JFrame {
	
	private String[] s={"桌位id","桌位名字","桌位描述","桌位状态"};
	private JTable table;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JTextField jf;
	private String desk_name;
	
	public DeskDeleteView(){
		init();
	}

	public void init(){
		JPanel jp1=new JPanel();
		jp1.setBackground(Color.GRAY);
		JLabel jl1=new JLabel("输入桌位名字：");
		jf=new JTextField(6);
		JButton jb1=new JButton("查询");
		jb1.addActionListener(new DeskDeleteAction());
		jb1.setActionCommand("查询");
		JButton jb2=new JButton("删除该桌位信息");
		jb2.addActionListener(new DeskDeleteAction());
		jb2.setActionCommand("删除");
		
		jp1.add(jl1);
		jp1.add(jf);
		jp1.add(jb1);
		
		JPanel jp2=new JPanel();
		jp2.setBackground(Color.GRAY);
		jp2.add(jb2);
		
		jsp=new JScrollPane();
		
		this.add(jp1,BorderLayout.NORTH);
		this.add(jp2, BorderLayout.SOUTH);
		this.add(jsp,BorderLayout.CENTER);
		
		
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setTitle("删除桌位信息");
		this.setVisible(true);
		this.setSize(400, 400);
		this.setLocation(500, 200);
		
	}
	
	public class DeskDeleteAction implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("查询")){
				desk_name=jf.getText().trim();
				DeskDao DD=new DeskDao();
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				jsp.setViewportView(table);
				DeskModel dm=DD.equeryDeskByName(desk_name);
				if(dm==null){
					JOptionPane.showMessageDialog(jsp, "桌位名字不存在，请重新输");
				}else{
				Vector v=new Vector();
				v.add(dm.getDesk_id());
				v.add(dm.getDesk_name());
				v.add(dm.getDesk_description());
				String state="";
				table.setGridColor(Color.orange);
				if(dm.getDesk_state()==0){
					state="空的";
				}else{
					state="已有人";
				}
				v.add(state);
				dtm.addRow(v);
				jsp.repaint();
				}
			}else if(e.getActionCommand().equals("删除")){
				desk_name=jf.getText().trim();
				DeskDao DD=new DeskDao();
				boolean b=DD.deleteDesk(desk_name);
				if(b==true){
					JOptionPane.showMessageDialog(jsp, "删除成功");
					dtm.setColumnCount(0);
					jsp.repaint();
				}else{
					JOptionPane.showMessageDialog(jsp, "删除失败,名字输入错误");
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new DeskDeleteView();
	}

}
