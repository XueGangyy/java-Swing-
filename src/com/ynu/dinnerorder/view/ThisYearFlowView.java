package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Vector;

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

import com.ynu.dinnerorder.databasedao.OrderDao;
import com.ynu.dinnerorder.databasemodel.OrderModel;

public class ThisYearFlowView extends JFrame {
	private JPanel contentPane;
	private String[] s={"流水号","订单号","订单人","桌位","订单人电话","订单人地址","订单详细","订单总价","订单付款日期"};
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JTable table;
	private JTextField textID;
	private JPanel jpanel;
	private JTextField jtfTotalprice;
	private JPanel panel1;//下方的容器
	
	public ThisYearFlowView(){
		init();
	}
	
	public void init(){
		setTitle("本年流水");
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
		
        
		
		
		
		
		jpanel=new JPanel();//创建上方的容器，并且随着事件的发生而发生更新
		JLabel l=new JLabel("欢迎查看本年流水");
		
		panel1=new JPanel();//创建下方的容器，不用随着事件发生而发生更新
		JLabel label=new JLabel("流水总额：");
		jtfTotalprice=new JTextField(20);//创建可以呈现流水总额的文本框
		jtfTotalprice.setEditable(false);//让文本框不可以修改
		panel1.add(label);//添加流水总额的标签
		panel1.add(jtfTotalprice);
		
		jpanel.add(l);
		
		
		
		
		contentPane.add(panel1,BorderLayout.SOUTH);//下方的容器，用来呈现流水总额
		contentPane.add(jpanel,BorderLayout.NORTH);//上方的容器，可以随着事件发生而更新
		contentPane.add(jsp,BorderLayout.CENTER);//中间的容器，用来盛放表格
		
		
		
		dtm=new DefaultTableModel(s, 0);
		table=new JTable(dtm);
		table.setGridColor(Color.orange);
		jsp.setViewportView(table);
		Date date1=new Date();
		Date nowMonth=new Date(date1.getYear(), 00, 01, 00, 00, 00); 
		Date nextMonth=new Date(date1.getYear()+1, 00, 01, 00, 00, 00); 
		OrderDao OD=new OrderDao();
		List<OrderModel> lom=OD.queryByDay(nowMonth, nextMonth);
		double totalpriceDay=0;
		if(lom.size()==0){
			JOptionPane.showMessageDialog(contentPane, "餐厅今日没有流水哦");
		}else{
		for(OrderModel om:lom){
			Vector v=new Vector();
			v.add(om.getOrder_id());
			v.add(om.getOrderId());
			v.add(om.getNu_name());
			v.add(om.getDesk_name());
			v.add(om.getU_telephone());
			v.add(om.getU_address());
			v.add(om.getU_dish());
			v.add(om.getTotalprice()+"元");
			v.add(om.getTime());
			dtm.addRow(v);
			totalpriceDay=totalpriceDay+om.getTotalprice();
		}
		}
		Double di=new Double(totalpriceDay);
		jtfTotalprice.setText(di.toString()+"元");
		
		
		
		this.add(contentPane);//让主容器添加到窗口中
		
	}
	
	public static void main(String[] args) {
		new ThisYearFlowView();
	}
}
