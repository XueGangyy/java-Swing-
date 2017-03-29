package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ynu.dinnerorder.databasedao.ItemDao;
import com.ynu.dinnerorder.databasemodel.ItemModel;



public class WaiterManageOrderView extends JFrame {
	private JPanel contentPane;
	private String[] s={"订单id","订单人","桌位","菜品栏id","菜品名字","菜品单价","菜品描述","菜品数量","菜品总价","菜品日期","订单状态","用户电话","用户地址"};
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JTable table;
	private JTextField textID;
	private JPanel jpanel;
	
	private int u_id;
	
	private JTextField textName;
	private JLabel l;
	

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public WaiterManageOrderView(){
		init();
	}
	
	public void init(){
		setTitle("服务员管理订单");
		jsp=new JScrollPane();
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setResizable(true);
		setVisible(true);
		setBounds(70, 20, 450, 300);
		setSize(1200,700);
		
        JMenuBar menuBar = new JMenuBar();
		
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("当日订单");
		menuBar.add(menu);
		
		JMenu menu1 = new JMenu("已送达");
		menuBar.add(menu1);
		
		JMenu menu2 = new JMenu("结账");
		menuBar.add(menu2);
		
		JMenuItem menuItem_1 = new JMenuItem("未确认");
		menuItem_1.addActionListener(new queryDishAction());
		menuItem_1.setActionCommand("未确认");
		menu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("已确认");
		menuItem_2.addActionListener(new queryDishAction());
		menuItem_2.setActionCommand("查询已确认订单");
		menu.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("设置订单送达");
		menuItem_3.addActionListener(new OrderReceiveAction());
		menuItem_3.setActionCommand("订单送达");
		menu1.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("结账统计");
		menuItem_4.addActionListener(new queryDishAction());
		menuItem_4.setActionCommand("结账");
		menu2.add(menuItem_4);
		
		jpanel=new JPanel();
		l=new JLabel();
		JLabel l1=new JLabel("输入用户姓名：");
		textName=new JTextField(5);
		JButton jb=new JButton("查询");
		jb.addActionListener(new queryDishAction());
		jb.setActionCommand("查询用户姓名相关的订单信息");
		jpanel.add(l);
		jpanel.add(l1);
		jpanel.add(textName);
		jpanel.add(jb);
		
		JPanel panel1=new JPanel();
		JLabel label=new JLabel("输入订单号：");
		textID=new JTextField(20);
		JButton jb2=new JButton("确认订单");
		jb2.addActionListener(new queryDishAction());
		jb2.setActionCommand("确认订单");
		
		panel1.add(label);
		panel1.add(textID);
		panel1.add(jb2);
		contentPane.add(panel1,BorderLayout.SOUTH);
		
		
		contentPane.add(jpanel,BorderLayout.NORTH);
		
		contentPane.add(jsp,BorderLayout.CENTER);
		
		
		this.add(contentPane);
//		dtm=new DefaultTableModel(s, 0);
//		table=new JTable();
//		table.setGridColor(Color.orange);
		
	}
	
	private class queryDishAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("未确认")){
				//查看未确认订单并且填充表单
				l.setText("查看未确认订单！");
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
				jsp.setViewportView(table);
				
				ItemDao ID=new ItemDao();
				List<ItemModel> lim=ID.equeryItemByState(2);
				for(ItemModel im:lim){
					Vector v=new Vector();
					v.add(im.getOrderId());
					v.add(im.getN_user_m().getNu_name());
					v.add(im.getDeskm().getDesk_name());
					v.add(im.getItem_id());
					v.add(im.getDishm().getDish_name());
					v.add(im.getDishm().getDish_price());
					v.add(im.getDishm().getDish_description());
					v.add(im.getItem_num());
					v.add(im.getItem_totalprice()+"元");
					v.addElement(im.getItem_date());
					String s="";
					if(im.getItem_state()==2){
						s="未确认";
					}else if(im.getItem_state()==3){
						s="确认";
					}else if(im.getItem_state()==4){
						s="已送达";
					}else if(im.getItem_state()==5){
						s="已付款";
					}
					v.add(s);
					v.add(im.getN_user_m().getNu_telephone());
					v.add(im.getN_user_m().getNu_address());
					dtm.addRow(v);
				}
				jsp.repaint();
			}else if(e.getActionCommand().equals("查询已确认订单")){
				l.setText("查看已确认订单！");
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
				jsp.setViewportView(table);
				
				ItemDao ID=new ItemDao();
				List<ItemModel> lim=ID.equeryItemByState(3);
				for(ItemModel im:lim){
					Vector v=new Vector();
					v.add(im.getOrderId());
					v.add(im.getN_user_m().getNu_name());
					v.add(im.getDeskm().getDesk_name());
					v.add(im.getItem_id());
					v.add(im.getDishm().getDish_name());
					v.add(im.getDishm().getDish_price());
					v.add(im.getDishm().getDish_description());
					v.add(im.getItem_num());
					v.add(im.getItem_totalprice()+"元");
					v.addElement(im.getItem_date());
					String s="";
					if(im.getItem_state()==2){
						s="未确认";
					}else if(im.getItem_state()==3){
						s="确认";
					}else if(im.getItem_state()==4){
						s="已送达";
					}else if(im.getItem_state()==5){
						s="已付款";
					}
					v.add(s);
					v.add(im.getN_user_m().getNu_telephone());
					v.add(im.getN_user_m().getNu_address());
					dtm.addRow(v);
				}
				jsp.repaint();
			}else if(e.getActionCommand().equals("确认订单")){
				//确认订单，改变状态
				String s=textID.getText().trim();
				ItemDao ID=new ItemDao();
				int i=0;
				List<ItemModel> lim=ID.equeryByOrderId(s);
				for(ItemModel im:lim){
					i=im.getItem_state();
				}
				if(i>=3){
					JOptionPane.showMessageDialog(null, "该订单你已经确认过了");
				}else{
				int i1=ID.UpdateStateByOrderId(s, 3);
				if(i1==0){
					JOptionPane.showMessageDialog(null, "订单号不存在哦,请认真一些");
				}else{
					JOptionPane.showMessageDialog(null, "确认成功了哦");
				}
				}
				
			}else if(e.getActionCommand().equals("查询用户姓名相关的订单信息")){
				l.setText("查看用户订单详细信息！");
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
				jsp.setViewportView(table);
				
				ItemDao ID=new ItemDao();
				List<ItemModel> lim=ID.equeryItemByUserName(textName.getText().trim());
				for(ItemModel im:lim){
					Vector v=new Vector();
					v.add(im.getOrderId());
					v.add(im.getN_user_m().getNu_name());
					v.add(im.getDeskm().getDesk_name());
					v.add(im.getItem_id());
					v.add(im.getDishm().getDish_name());
					v.add(im.getDishm().getDish_price());
					v.add(im.getDishm().getDish_description());
					v.add(im.getItem_num());
					v.add(im.getItem_totalprice()+"元");
					v.addElement(im.getItem_date());
					String s="";
					if(im.getItem_state()==2){
						s="未确认";
					}else if(im.getItem_state()==3){
						s="已确认,未送达";
					}else if(im.getItem_state()==4){
						s="已送达,未付款";
					}else if(im.getItem_state()==5){
						s="已付款";
					}
					v.add(s);
					v.add(im.getN_user_m().getNu_telephone());
					v.add(im.getN_user_m().getNu_address());
					dtm.addRow(v);
				}
				jsp.repaint();
			}else if(e.getActionCommand().equals("结账")){
				//触发另结账窗口
				new UserPayView();
			}
			
		}
		
	}
	
	private class OrderReceiveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("订单送达")){
				//触发设置订单状态的窗口
				new OrderReceiveView();
			}
		}
		
	}

	public static void main(String[] args) {
		new WaiterManageOrderView();
	}

}
