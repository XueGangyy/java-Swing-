package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
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

import com.ynu.dinnerorder.databasedao.DeskDao;
import com.ynu.dinnerorder.databasedao.ItemDao;
import com.ynu.dinnerorder.databasedao.NormalUserDao;
import com.ynu.dinnerorder.databasedao.OrderDao;
import com.ynu.dinnerorder.databasemodel.DeskModel;
import com.ynu.dinnerorder.databasemodel.ItemModel;
import com.ynu.dinnerorder.databasemodel.NormalUserModel;





public class UserPayView extends JFrame {

	private JPanel contentPane;
	//主容器
	private String[] s={"订单id","订单人","桌位","菜品栏id","菜品名字","菜品单价","菜品描述","菜品数量","菜品总价","菜品日期","订单状态","用户电话","用户地址"};
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JTable table;
	//表单指针创建
	
	private JTextField textID;
	//订单ID域
	private JPanel jpanel;
	private JTextField jtfTotalprice;
	//总价格文本域
	
	private int u_id;
	
	private JTextField textName;
	//名字文本域
	private JLabel l;
	//上方容器标签指针
	

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public UserPayView(){
		init();
	}
	
	public void init(){
		setTitle("结账");
		jsp=new JScrollPane();
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		//分配指针，并且实例化中间容器对象
		
		contentPane = new JPanel();
		//实例化主容器
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setResizable(true);
		setVisible(true);
		setBounds(70, 20, 450, 300);
		setSize(1200,700);
		
        JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.setLayout(new FlowLayout());
		
		JButton jb1=new JButton("未付款的订单");
		jb1.addActionListener(new queryDishAction());
		jb1.setActionCommand("未付款");
		JButton jb2=new JButton("已付款的订单");
		jb2.addActionListener(new queryDishAction());
		jb2.setActionCommand("已付款");
		menuBar.add(jb1);
		menuBar.add(jb2);
		
		jpanel=new JPanel();
		l=new JLabel();
		JLabel l1=new JLabel("输入用户姓名");
		textName=new JTextField(5);
		JButton jb=new JButton("查询");
		jb.addActionListener(new queryDishAction());
		jb.setActionCommand("查询用户姓名相关的订单信息");
		jpanel.add(l);
		jpanel.add(l1);
		jpanel.add(textName);
		jpanel.add(jb);
		
		JPanel panel1=new JPanel();
		JLabel label=new JLabel("输入订单号");
		textID=new JTextField(20);
		JButton jb5=new JButton("查看订单总额");
		jb5.addActionListener(new queryDishAction());
		jb5.setActionCommand("总额");
		JButton jb3=new JButton("确认付款");
		jb3.addActionListener(new queryDishAction());
		jb3.setActionCommand("确认付款");
		
		jtfTotalprice=new JTextField(15);
		jtfTotalprice.setEditable(false);
		JButton jb4=new JButton("打印订单");
		jb4.addActionListener(new queryDishAction());
		jb4.setActionCommand("打印");
		
		
		panel1.add(label);
		panel1.add(textID);
		panel1.add(jb5);
		panel1.add(jtfTotalprice);
		panel1.add(jb3);
		
		panel1.add(jb4);
		
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
			if(e.getActionCommand().equals("未付款")){
				l.setText("查看未付款订单！");
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
				jsp.setViewportView(table);
				//事件触发后进行一系列表单创建
				ItemDao ID=new ItemDao();
				List<ItemModel> lim=ID.equeryItemByState(4);
				//根据未付款状态查询数据库中所有数据
				for(ItemModel im:lim){
					//填充
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
						s="未送达";
					}else if(im.getItem_state()==4){
						s="已送达，未付款";
					}else if(im.getItem_state()==5){
						s="已付款";
					}
					v.add(s);
					v.add(im.getN_user_m().getNu_telephone());
					v.add(im.getN_user_m().getNu_address());
					dtm.addRow(v);
				}
				jsp.repaint();
			}else if(e.getActionCommand().equals("已付款")){
				l.setText("查看已付款订单！");
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
				jsp.setViewportView(table);
				
				ItemDao ID=new ItemDao();
				List<ItemModel> lim=ID.equeryItemByState(5);
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
						s="已送达，未付款";
					}else if(im.getItem_state()==5){
						s="已付款";
					}
					v.add(s);
					v.add(im.getN_user_m().getNu_telephone());
					v.add(im.getN_user_m().getNu_address());
					dtm.addRow(v);
				}
				jsp.repaint();
			}else if(e.getActionCommand().equals("确认付款")){
				String s=textID.getText().trim();
				ItemDao ID=new ItemDao();
				int i=0;
				List<ItemModel> lim=ID.equeryByOrderId(s);
				//通过id查询所有数据
				for(ItemModel im:lim){
					i=im.getItem_state();
				}
				if(i>=5){
					JOptionPane.showMessageDialog(null, "该订单已经付款");
				}else{
				int i_u_id=0;
				int i_desk_id=0;
				double totalprice=0;
				
				ItemDao ID1=new ItemDao();
				List<ItemModel> lim1=ID1.equeryByOrderId(s,4);
				if(lim==null){
					JOptionPane.showMessageDialog(null, "订单号不存在哦,请认真一些");
				}else{
					String u_dish="";
				for(ItemModel IM:lim1){
					totalprice=totalprice+IM.getItem_totalprice();
					//获取订单号对应的所有单品的价格。总价格
					i_u_id=IM.getN_user_m().getNu_id();
					//获取订单号对应的用户id
					i_desk_id=IM.getDeskm().getDesk_id();
					//获取桌位id
					u_dish=u_dish+(IM.getDishm().getDish_name()+"("+IM.getItem_num()+"份)  ");
					//叠加用户点的餐品的名字和点餐的份数
					System.out.println(u_dish);
				}
				NormalUserDao NUD=new NormalUserDao();
				NormalUserModel NUM=NUD.equeryN_User(i_u_id);
				//查询用户名
				
				DeskDao DD=new DeskDao();
				DeskModel DM=DD.equeryDeskById(i_desk_id);
				DD.updateDeskStateById(0,i_desk_id);
				//查询桌位id
				
				/*
				 * 把订单写入流水表
				 */
				OrderDao OD=new OrderDao();
				Date d=new Date();
				int i1=OD.addOrder(d, NUM.getNu_name(), totalprice, s,DM.getDesk_name(),NUM.getNu_address(),NUM.getNu_telephone(),u_dish);
				//更新菜品状态，设置为已付款
				if(i1>0){
			       JOptionPane.showMessageDialog(null, "确认成功了");
			       int i2=ID.UpdateStateByOrderId(s, 5);
				}else{
					JOptionPane.showMessageDialog(null, "付款失败了");
				}
				}
				Double ble=new Double(totalprice);
				//显示总价格
				jtfTotalprice.setText(ble.toString()+"元");
				repaint();
				}
			}else if(e.getActionCommand().equals("查询用户姓名相关的订单信息")){
				//根据姓名查询表单，填充表单
				l.setText("查看用户订单详细信息！");
				dtm=new DefaultTableModel(s, 0);
				table=new JTable(dtm);
				table.setGridColor(Color.orange);
				table.setAutoResizeMode(WIDTH);
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
			}
			else if(e.getActionCommand().equals("总额")){
				//查看订单总额
				String s=textID.getText().trim();
				double totalprice=0;
				ItemDao ID=new ItemDao();
				List<ItemModel> lim=ID.equeryByOrderId(s);
				if(lim==null){
					JOptionPane.showMessageDialog(null, "订单号不存在哦,请认真一些");
				}else{
				for(ItemModel IM:lim){
					totalprice=totalprice+IM.getItem_totalprice();
				}
				}
                 Double ble=new Double(totalprice);
				
				jtfTotalprice.setText(ble.toString()+"元");
				repaint();
			}else if(e.getActionCommand().equals("打印")){
				//打印的时候进行一系列操作
				String s1=textID.getText().trim();
				String s2=jtfTotalprice.getText().trim();
				ItemDao ID=new ItemDao();
				List<ItemModel> lim=ID.equeryByOrderId(s1);
				if(lim.get(0).getItem_state()==4){
					JOptionPane.showMessageDialog(contentPane, "订单还没付款");
				}else{
				OederDaYin ODY=new OederDaYin(lim, s2);
				Book book = new Book();  
			      
		        //    设置成竖打  
		      
		        PageFormat pf = new PageFormat();  
		      
		        pf.setOrientation(PageFormat.PORTRAIT);  
		      
		        //    通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。  
		      
		        Paper p = new Paper();  
		      
		        p.setSize(590,840);//纸张大小   
		      
		        p.setImageableArea(10,10, 590,840);//A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72  
		      
		        pf.setPaper(p);  
		        //    把 PageFormat 和 Printable 添加到书中，组成一个页面  
		        book.append(ODY, pf);  
		      
		      
		      
		         //获取打印服务对象  
		         
		         PrinterJob job = PrinterJob.getPrinterJob();        
		      
		         // 设置打印类  
		      
		         job.setPageable(book);      
		         try {  
		      
		             //可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印  
		      
		             boolean a=job.printDialog();  
		      
		             if(a)  
		      
		             {          
		      
		             job.print();  
		      
		             }  
		      
		      
		      
		         } catch (PrinterException e1) {  
		      
		             e1.printStackTrace();  
		      
		         }  
			}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new UserPayView();
	}

}
