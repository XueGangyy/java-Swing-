package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
import com.ynu.dinnerorder.databasemodel.DishModel;
import com.ynu.dinnerorder.databasemodel.ItemModel;



public class OederManagerView extends JFrame {
	
	
	private JPanel contentPane;
	private String[] s={"订单id","菜品名字","菜品单价","菜品描述","菜品数量","菜品总价","菜品日期","订单状态"};
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JTable table;
	private JTextField textID;
	
	private int u_id;
	
	

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public OederManagerView(){
		init();
	}
	
	public void init(){
		setTitle("个人订单管理");
		jsp=new JScrollPane();
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setResizable(true);
		setVisible(true);
		setBounds(300, 150, 450, 300);
		setSize(800,500);
		
        JMenuBar menuBar = new JMenuBar();
		
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("订单操作");
		menuBar.add(menu);
		
		JMenu menu1 = new JMenu("终极选择");
		menuBar.add(menu1);
		
		JMenuItem menuItem_1 = new JMenuItem("所有已选订单");
		menuItem_1.addActionListener(new queryDishAction());
		menuItem_1.setActionCommand("查询已选订单");
		menu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("提交已确认订单");
		menuItem_2.addActionListener(new queryDishAction());
		menuItem_2.setActionCommand("最终选择");
		menu1.add(menuItem_2);
		
		JPanel jpanel=new JPanel();
		JLabel l=new JLabel("查看你的所有订单信息：");
		jpanel.setBackground(Color.GREEN);
		jpanel.setForeground(Color.black);
		jpanel.add(l);
		
		JLabel label=new JLabel("输入菜品栏号");
		textID=new JTextField(4);
		JPanel panel1=new JPanel();
		JButton jb1=new JButton("取消菜品");
		jb1.addActionListener(new queryDishAction());
		jb1.setActionCommand("取消");
		JButton jb2=new JButton("确认菜品");
		jb2.addActionListener(new queryDishAction());
		jb2.setActionCommand("确认");
		
		panel1.add(label);
		panel1.add(textID);
		panel1.add(jb1);
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
			dtm=new DefaultTableModel(s, 0);
			table=new JTable(dtm);
			table.setGridColor(Color.orange);
			
			if(e.getActionCommand().equals("查询已选订单")){
				ItemDao ID=new ItemDao();
				List<ItemModel> LIM=ID.equeryItemByUserId(u_id);
//				DefaultTableModel dtk=(DefaultTableModeltable)table.getModel();
				
				jsp.setViewportView(table);
				for(ItemModel im:LIM){
					Vector v=new Vector();
					v.add(im.getItem_id());
					v.add(im.getDishm().getDish_name());
					v.add(im.getDishm().getDish_price());
					v.add(im.getDishm().getDish_description());
					v.add(im.getItem_num());
					v.add(im.getItem_totalprice()+"元");
					v.addElement(im.getItem_date());
					String s="";
					if(im.getItem_state()==0){
						s="用户未确认";
					}else if(im.getItem_state()==1)
					{
						s="用户已经确认";
					}else if(im.getItem_state()==2){
						s="已提交到餐厅";
					}else if(im.getItem_state()==3){
						s="服务员已确认";
					}else if(im.getItem_state()==4){
						s="菜品已经送达";
					}else if(im.getItem_state()==5){
						s="已经付款";
					}
					v.add(s);
					dtm.addRow(v);
				}
				jsp.repaint();
				int row=table.getSelectedColumn();
//				System.out.println("选中的行数"+row);
			}
			else if(e.getActionCommand().equals("取消")){
				
				String sID=textID.getText().trim();
				if(sID.isEmpty()){
					JOptionPane.showMessageDialog(contentPane, "订单号不能为空");
				}else{
				Integer item_id=Integer.valueOf(sID);
				ItemDao ID=new ItemDao();
				ItemModel IM=ID.equeryItemById(item_id);
				if(IM.getItem_state()==1){
					JOptionPane.showMessageDialog(contentPane, "你已经确认该订单,不能取消了,请跟服务员商量");
				}else if(IM.getItem_state()==0){
				int i2=ID.deleteItem(item_id);
				if(i2==0){
				JOptionPane.showMessageDialog(contentPane, "操作失败");
				}else{
					JOptionPane.showMessageDialog(contentPane, "操作成功");
				}
				}
				}
				
			}else if(e.getActionCommand().equals("确认")){
				
				String sID=textID.getText().trim();
				Integer item_id=Integer.valueOf(sID);
				ItemDao ID=new ItemDao();
				ItemModel IM=ID.equeryItemById(item_id);
				if(IM.getItem_state()>=1){
					JOptionPane.showMessageDialog(contentPane, "你已经确认该订单，不必那么麻烦的重复确认哦");
				}else if(IM.getItem_state()==0){
				int i_state=1;
				int i1=ID.updateState(item_id, i_state);
				if(i1==0){
					JOptionPane.showMessageDialog(contentPane, "确认失败"); 
				}else{
					JOptionPane.showMessageDialog(contentPane, "确认成功");
				}
				}
		}else if(e.getActionCommand().equals("最终选择")){
			OrderFinalChooseView ofc=new OrderFinalChooseView();
			ofc.setU_id(u_id);
			ofc.creatTable();
		}
		}
		
	}
	
	public static void main(String[] args) {
		new OederManagerView();
	}

}
