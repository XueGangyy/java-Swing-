package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
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



public class FlowManageView extends JFrame {

	private JPanel contentPane;
	private String[] s={"流水号","订单号","订单人","桌位","订单人电话","订单人地址","订单详细","订单总价","订单付款日期"};
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JTable table;
	private JTextField textID;
	private JPanel jpanel;
	private JTextField jtfTotalprice;
	private JPanel panel1;//下方的容器
	
	private JLabel Year;//年的标签指针
	private JLabel Month;//月的标签指针
	private JLabel Day;//日的标签指针
	private JComboBox<Integer> YearForC;//年的复选框
	private JComboBox<Integer> MonthForC;//月的复选框
	private JComboBox<Integer> DayForC;//日的复选框
	private Integer[] yearI;//年的复选框内容
	private Integer[] monthI;//月的复选框内容
	private Integer[] dayI;//日的复选款内容
	private JButton buttonQuery;//添加查询按钮
	
	
	
	private int u_id;
	
	
	private JLabel l;
	

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public FlowManageView(){
		init();
	}
	
	public void init(){
		setTitle("流水管理");
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
		
		
		JLabel jb1=new JLabel("综合查询");
		
		JButton jb2=new JButton("本日流水");
		jb2.addActionListener(new queryDishAction());
		jb2.setActionCommand("本日流水");
		JButton jb3=new JButton("本月流水");
		jb3.addActionListener(new queryDishAction());
		jb3.setActionCommand("本月流水");
		JButton jb4=new JButton("本年流水");
		jb4.addActionListener(new queryDishAction());
		jb4.setActionCommand("本年流水");
		JButton jb5=new JButton("删除流水");
		jb5.addActionListener(new queryDishAction());
		jb5.setActionCommand("删除流水");
		menuBar.add(jb1);
		menuBar.add(jb2);
		menuBar.add(jb3);
		menuBar.add(jb4);
		menuBar.add(jb5);
		
		jpanel=new JPanel();//创建上方的容器，并且随着事件的发生而发生更新
		l=new JLabel("");
		
		panel1=new JPanel();//创建下方的容器，不用随着事件发生而发生更新
		JLabel label=new JLabel("流水总额：");
		jtfTotalprice=new JTextField(20);//创建可以呈现流水总额的文本框
		jtfTotalprice.setEditable(false);//让文本框不可以修改
		
		
		
		panel1.add(label);//添加流水总额的标签
		panel1.add(jtfTotalprice);
		
		Year=new JLabel("选择年：");
		Month=new JLabel("选择月：");
		Day=new JLabel("选择日：");
		yearI=new Integer[100];
		int b=2015;
		for(int i=0;i<100;i++){
			//进行for循环把年的数据填充到复选框中
			yearI[i]=b+1;
			b=b+1;
		}
		monthI=new Integer[12];
		int c=0;
		for(int i=0;i<12;i++){
			monthI[i]=c+1;
			c=c+1;
		}
		dayI=new Integer[31];
		int d=0;
		for(int i=0;i<31;i++){
			dayI[i]=d+1;
			d=d+1;
		}
		
		YearForC=new JComboBox<Integer>(yearI);
		MonthForC=new JComboBox<Integer>(monthI);
		DayForC=new JComboBox<Integer>(dayI);
		buttonQuery=new JButton("查询流水");
		buttonQuery.addActionListener(new ButtonAction());
		buttonQuery.setActionCommand("查询");
		
		jpanel.add(Year);
		jpanel.add(YearForC);
		jpanel.add(Month);
		jpanel.add(MonthForC);
		jpanel.add(Day);
		jpanel.add(DayForC);
		jpanel.add(buttonQuery);
		
		contentPane.add(panel1,BorderLayout.SOUTH);//下方的容器，用来呈现流水总额
		contentPane.add(jpanel,BorderLayout.NORTH);//上方的容器，可以随着事件发生而更新
		contentPane.add(jsp,BorderLayout.CENTER);//中间的容器，用来盛放表格
		
		
		this.add(contentPane);//让主容器添加到窗口中
		
	}
	private class queryDishAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("本日流水")){
				new TodayFlowView();
			}else if(e.getActionCommand().equals("本月流水")){
				new ThisMonthFlowView();
			}else if(e.getActionCommand().equals("本年流水")){
				new ThisYearFlowView();
			}else if(e.getActionCommand().equals("删除流水")){
				String s=JOptionPane.showInputDialog(null,"输入流水id");
				int i=Integer.valueOf(s);
				OrderDao OD=new OrderDao();
				int i1=OD.deleteOrder(i);
				if(i1==0){
					JOptionPane.showMessageDialog(null, "流水删除失败");
				}else{
					JOptionPane.showMessageDialog(null, "流水号为"+s+"的订单详情已被你删除了");
				}
			}
		}
		
	}
	
	public class ButtonAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("查询")){
				jtfTotalprice.setText("");
				l.setText("查看具体的流水");
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
				jsp.setViewportView(table);
				Integer year_l=(Integer)YearForC.getSelectedItem();
				Integer month_l=(Integer)MonthForC.getSelectedItem();
				Integer Day_l= (Integer)DayForC.getSelectedItem();
				int Day_2=Day_l.intValue()+1;
				Integer next_Day=new Integer(Day_2);
//				System.out.println("年："+year_l+" 月："+month_l+" 日："+Day_l);
				String now_year_m_d=year_l.toString()+"."+month_l.toString()+"."+Day_l.toString();
				String next_year_m_d=year_l.toString()+"."+month_l.toString()+"."+next_Day.toString();
				Date nowDay;
				Date nextDay;
				try {
					nowDay=new SimpleDateFormat("yyyy.MM.dd").parse(now_year_m_d);
					//进行格式转换
					nextDay=new SimpleDateFormat("yyyy.MM.dd").parse(next_year_m_d); 
//					System.out.println(nowDay);
					Timestamp t=new Timestamp(nowDay.getTime());
					Timestamp t1=new Timestamp(nextDay.getTime());
					OrderDao OD=new OrderDao();
					List<OrderModel> lom=OD.queryByDay(nowDay, nextDay);
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
					panel1.repaint();
				}catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				}
				
				jsp.repaint();
			}
		
		}
		
		
	
	public static void main(String[] args) {
		new FlowManageView();
	}

}
