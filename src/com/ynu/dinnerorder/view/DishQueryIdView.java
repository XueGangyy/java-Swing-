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

import com.ynu.dinnerorder.databasedao.DishDao;
import com.ynu.dinnerorder.databasemodel.DishModel;

public class DishQueryIdView extends JFrame {
	private JScrollPane jsp;
	private JTextField jtf;
	private String[] s={"菜品id","菜品名字","菜品价格","菜品描述"};
	private DefaultTableModel dtm;
	
	private JTable table;
	
	
	public DishQueryIdView(){
		init();
	}

	public void init(){
		JPanel jp1=new JPanel();
		JLabel label1=new JLabel("请输入菜品id");
		jp1.add(label1);
		jtf=new JTextField(4);
		jp1.add(jtf);
		JButton jb1=new JButton("查询");
		jp1.add(jb1);
		jb1.addActionListener(new queryDishAction());
		jb1.setActionCommand("id");
		
         this.add(jp1,BorderLayout.NORTH);
		jsp=new JScrollPane();
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(jsp,BorderLayout.CENTER);
		setResizable(false);
		setVisible(true);
		setBounds(300, 150, 450, 300);
		setSize(400,400);
		setTitle("根据id查菜的信息");
	}
	
	
	private class queryDishAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("id")){
				String id=jtf.getText().trim();
				int i=Integer.valueOf(id);
				DishDao dd=new DishDao();
				DishModel dm=dd.equeryDishInfBy_id(i);
				if(dm==null){
					JOptionPane.showMessageDialog(jsp, "该id对应的菜不存在");
				}else{
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
				jsp.setViewportView(table);
				Vector v=new Vector();
				v.add(dm.getDish_id());
				v.add(dm.getDish_name());
				v.add(dm.getDish_price()+"元");
				v.add(dm.getDish_description());
				dtm.addRow(v);
				jsp.repaint();
			}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
	new DishQueryIdView();
		
	}

}
