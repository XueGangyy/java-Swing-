package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ynu.dinnerorder.databasedao.DeskDao;
import com.ynu.dinnerorder.databasedao.ItemDao;
import com.ynu.dinnerorder.databasemodel.DeskModel;
import com.ynu.dinnerorder.databasemodel.ItemModel;



public class OrderFinalChooseView extends JFrame {
	
	private JPanel contentPane;
	private String[] s={"订单id","菜品名字","菜品单价","菜品描述","菜品数量","菜品总价","菜品日期","订单状态"};
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JTable table;
	private JTextField textDeskID;
	private JTextField textTotal;


	
    private int u_id;
	
	

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	
	public OrderFinalChooseView(){
		init();
	}

	public void creatTable(){
		double	b=0;
		dtm=new DefaultTableModel(s, 0);
		table=new JTable(dtm);
		table.setGridColor(Color.orange);
		ItemDao ID=new ItemDao();
		List<ItemModel> LIM=ID.equeryItemByUserId_state(this.getU_id(),1);
		for(ItemModel im:LIM){
		//显示总价格文本域
		b=b+im.getItem_totalprice();
		
		}
		
		Integer i=(int)b;
		textTotal.setText(i.toString()+"元");
		
		
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
				s="用户已确认";
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
	}
	
	public void init(){
		setTitle("提交已确认订单");
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
		
		JPanel jpanel=new JPanel();
		JLabel l=new JLabel("查看你刚刚已经确认的订单信息：");
		jpanel.setBackground(Color.GREEN);
		jpanel.setForeground(Color.black);
		jpanel.add(l);
		
		JLabel label=new JLabel("选择桌位");
		textDeskID=new JTextField(4);
		JPanel panel1=new JPanel();
		JButton jb2=new JButton("确认所有菜品");
		jb2.addActionListener(new queryDishAction());
		jb2.setActionCommand("确认");
		JLabel label1=new JLabel("订单总额：");
		textTotal=new JTextField(10);
		textTotal.setEditable(false);
		panel1.add(label);
		panel1.add(textDeskID);
		panel1.add(jb2);
		panel1.add(label1);
		panel1.add(textTotal);
		
		contentPane.add(jpanel,BorderLayout.NORTH);
		contentPane.add(jsp,BorderLayout.CENTER);
		contentPane.add(panel1,BorderLayout.SOUTH);
		
		this.add(contentPane);	
	
	}
	
	private class queryDishAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("确认")){
				String desk_id=textDeskID.getText().trim();
				int i=Integer.valueOf(desk_id);
				DeskDao Desk=new DeskDao();
				DeskModel dm=Desk.equeryDeskById(i);
				if(dm.getDesk_state()==0){
				ItemDao ID=new ItemDao();
				int i1=ID.AddDesk(i, u_id, 1);
				

				Date d=new Date();
				Integer s1=d.getYear();
				Integer s2=d.getMonth();
				Integer s3=d.getDate();
				Integer s4=d.getHours();
				Integer s5=d.getMinutes();
				Integer s6=d.getSeconds();
				String stotal_id=s1.toString()+s2.toString()+s3.toString()+s4.toString()+s5.toString()+s6.toString();
				int i2=ID.AddOrderId(stotal_id, u_id, 1);
				
				if(i1==0&i2==0){
					JOptionPane.showMessageDialog(contentPane, "提交失败");
				}else{
					JOptionPane.showMessageDialog(contentPane, "你已经成功预订"+dm.getDesk_name()+"!请耐心等待，顺便记好你的订单id号，以便留作收据");
					JOptionPane.showMessageDialog(contentPane, "订单ID："+stotal_id);	
				}
				ID.UpdateStateByAll(u_id, 1, 2);
			}else{
				JOptionPane.showMessageDialog(contentPane, "桌位已经被预定");
			}
		}
		
	}
	}
	
	public static void main(String[] args) {
		new OrderFinalChooseView();
	}

}
