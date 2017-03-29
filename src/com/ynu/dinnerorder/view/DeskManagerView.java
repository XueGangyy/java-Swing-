package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ynu.dinnerorder.databasedao.DeskDao;
import com.ynu.dinnerorder.databasemodel.DeskModel;

public class DeskManagerView extends JFrame {
	
	private JPanel contentPane;
	private String[] s={"桌位id","桌位名字","桌位描述","桌位状态"};
	private JTable table;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JLabel label;
	private JPanel panel;
	
	
	public DeskManagerView(){
		DeskManagerViewinit();
	}
	
	public void DeskManagerViewinit() {

		/*图片素材*/
		ImageIcon xz = new ImageIcon("E://素材//jia.jpg");
		ImageIcon sc = new ImageIcon("E://素材//cha.jpg");
		ImageIcon xq = new ImageIcon("E://素材//chakan.jpg");
			
		
		setTitle("桌位管理");
		setBounds(300, 150, 450, 300);
		setVisible(true);
		setSize(600, 500);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("新增桌位(NEW)");
		menu.setIcon(xz);/**/
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("add");
		menuItem.addActionListener(new AddDeskAction());
		menuItem.setActionCommand("add");
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("删除桌位(DELETE)");
		menu_1.setIcon(sc);/**/
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("delete");
		menuItem_1.addActionListener(new DeleteDeskAction());
		menuItem_1.setActionCommand("delete");
		menu_1.add(menuItem_1);
		
		JMenu menu_2 = new JMenu("查看桌位信息(STATE)");
		menu_2.setIcon(xq);/**/
		menuBar.add(menu_2);
		
		JMenuItem menuItem_2 = new JMenuItem("桌位全部信息");
		menuItem_2.addActionListener(new EqueryDeskAction());
		menuItem_2.setActionCommand("全部");
		menu_2.add(menuItem_2);
		JMenuItem menuItem_3 = new JMenuItem("没预定的桌位");
		menuItem_3.addActionListener(new EqueryDeskAction());
		menuItem_3.setActionCommand("没预定的桌位");
		menu_2.add(menuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
        label = new JLabel("");
		panel.add(label);
//		panel.setBackground(Color.PINK);
		
		jsp=new JScrollPane();
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(jsp, BorderLayout.CENTER);
	}
	
	private class AddDeskAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("add")){
				new DeskAddView();
			}
		}
		
	}
	
	private class DeleteDeskAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("delete")){
				new DeskDeleteView();
			}
		}
		
	}
	
	private class EqueryDeskAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("全部")){
//				label = new JLabel("欢迎查看桌位所有信息哦！");
//				panel.add(label);
//				panel.setBackground(Color.PINK);
				
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
				DeskDao DD=new DeskDao();
				List<DeskModel> ldm=DD.equeryDesk();
				for(DeskModel dm:ldm){
					String state="";
					Vector v=new Vector();
					v.add(dm.getDesk_id());
					v.add(dm.getDesk_name());
					v.add(dm.getDesk_description());
					if(dm.getDesk_state()==0){
						state="空的";
					}else{
						state="已有人";
					}
					v.add(state);
					dtm.addRow(v);
				}
				jsp.setViewportView(table);
				jsp.repaint();
				label.setText("欢迎查看桌位所有信息哦！");
				panel.setBackground(Color.PINK);
				panel.repaint();
				
			}else if(e.getActionCommand().equals("没预定的桌位")){
//				label = new JLabel("欢迎查看没被预定的桌位！");
//				panel.add(label);
//				panel.setBackground(Color.green);
				
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
//				panel.repaint();
				jsp.repaint();
				
				label.setText("欢迎查看没预订的桌位信息!");
				panel.setBackground(Color.green);
				panel.repaint();
			}
		}
		
	}

	public static void main(String[] args) {
		new DeskManagerView();
	}

}
