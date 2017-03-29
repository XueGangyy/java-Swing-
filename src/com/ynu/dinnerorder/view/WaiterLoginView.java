package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import com.ynu.dinnerorder.databasedao.NormalUserDao;
import com.ynu.dinnerorder.databasedao.WaiterDao;
import com.ynu.dinnerorder.databasemodel.NormalUserModel;
import com.ynu.dinnerorder.databasemodel.WaiterModel;

public class WaiterLoginView extends JFrame{
	/*
	 * @Author:余跃
	 * @Test:通过
	 */
	private JLabel lab_1;//总标题
	private JLabel usernameLabel;//用户名
	private JLabel passwordLabel;//密码
	private JPanel indexPanel;//主页容器
	private JPanel usernamePanel;//用户名这一项的容器
	private JPanel background;//背景这一项的容器
	private JPanel passwordPanel;//密码这一项容器
	private JPanel bottonPannel;//普通按钮这一选项的容器
	private JPanel radioPanel;//可选择按钮这一选项的容器
	private JTextField usernameText;//用户名文字输入框
	private String u_s;
	private String p_s;
	private boolean b_for_jr1;
	private boolean b_for_jr2;
	private JPasswordField passwordText;//密码文字输入框
	private JButton loginBotton;//登陆按钮
	private JButton registerBotton;//注册按钮
	private JRadioButton jr1;
	private JRadioButton jr2;
	
	public WaiterLoginView(){
		//构造方法，让它执行init方法
		init();
	}
	
/*
 * 当用户注册完之后，注册的时候所存的用户名和密码会显示在登陆页面上，调用这个方法就是达到这个目的
 */
public void changeT(){
	usernameText.setText(u_s);
	passwordText.setText(p_s);
}

public void changeB(){
	jr1.setSelected(b_for_jr1);
	jr2.setSelected(b_for_jr2);
}
	

public boolean isB_for_jr1() {
	return b_for_jr1;
}

public void setB_for_jr1(boolean b_for_jr1) {
	this.b_for_jr1 = b_for_jr1;
}

public boolean isB_for_jr2() {
	return b_for_jr2;
}

public void setB_for_jr2(boolean b_for_jr2) {
	this.b_for_jr2 = b_for_jr2;
}

public String getU_s() {
		return u_s;
	}


	public void setU_s(String u_s) {
		this.u_s = u_s;
	}


	public String getP_s() {
		return p_s;
	}


	public void setP_s(String p_s) {
		this.p_s = p_s;
	}


public void init(){
	   setTitle("登陆");
	   ImageIcon ic=new ImageIcon("images/loginindex.jpg");
	   JLabel  imageIndex=new JLabel();
	   imageIndex.setIcon(ic);
	   JPanel jp=new JPanel();
	   jp.add(imageIndex);
	   jp.setOpaque(false);//设置透明
//	   imageIndex.setBounds(0, 0, this.getWidth(), this.getHeight());
//	   getLayeredPane().add(imageIndex,new Integer(Integer.MIN_VALUE));
//	   JPanel jp=(JPanel)getContentPane(); 
//	   jp.setOpaque(false);//设置透明
	   
	
	   lab_1=new JLabel("欢迎进入**餐厅点餐界面");
	   JPanel lab_1panel=new JPanel();
	   lab_1panel.add(lab_1);
	   
	   ImageIcon img = new ImageIcon("e:\\素材\\jiaoye1.jpg");
	   JLabel label = new JLabel(img);
	   
	   indexPanel=new JPanel();
	   BoxLayout bl=new BoxLayout(indexPanel, BoxLayout.Y_AXIS);
	   indexPanel.setLayout(bl);
	   //进行盒式布局，垂直布局
	   indexPanel.setOpaque(false);
	   
	   
	   usernameLabel=new JLabel("用户名:");
	   usernameText=new JTextField(20);
	   usernamePanel=new JPanel();
	   usernamePanel.add(usernameLabel);
	   usernamePanel.add(usernameText);
	   usernamePanel.setSize(100, 100);
	   background = new JPanel();
	   background.add(label);
	   background.setSize(300, 300);
	   //用户名这一栏进行布局
	   
	   passwordLabel=new JLabel("密码：");
	   passwordText=new JPasswordField(20);
	   passwordText.setEchoChar('*');
	   passwordPanel=new JPanel();
	   passwordPanel.add(passwordLabel);
	   passwordPanel.add(passwordText);
	   //密码这一栏进行布局
	   
	   JPanel radiopanel=new JPanel();
	   jr1=new JRadioButton("普通用户");
	   jr2=new JRadioButton("服务员");
	   radiopanel.add(jr1);
	   radiopanel.add(jr2);
	   //添加普通用户和服务员按钮
	   
	   Icon denglu = new ImageIcon("E://素材//denglu.jpg");
	   Icon zhuce = new ImageIcon("E://素材//zhuce.jpg");
	  
	   
	   bottonPannel=new JPanel();
	   loginBotton=new JButton("登陆",denglu);
	   loginBotton.addActionListener(new loginAction());
	   loginBotton.setActionCommand("登陆");
	   //登陆按钮添加事件
	   registerBotton=new JButton("注册",zhuce);
	   registerBotton.addActionListener(new loginAction());
	   registerBotton.setActionCommand("注册");
	   //注册按钮添加事件
	   JButton j_not=new JButton("重置");
	   j_not.addActionListener(new loginAction());
	   j_not.setActionCommand("重置");
	   //重置按钮添加事件
	   bottonPannel.add(loginBotton);
	   bottonPannel.add(registerBotton);
	   bottonPannel.add(j_not);
	   //按钮组件进行布局
	   
	   indexPanel.add(lab_1panel);
	   indexPanel.add(usernamePanel);
	   indexPanel.add(passwordPanel);
	   indexPanel.add(radiopanel);
	   indexPanel.add(bottonPannel);
	   //把各个pannel加到主页pannel上去
	   
	   
	   this.add(indexPanel);
	 
	   //窗口加上主页
	   this.setResizable(false);
	   
	   this.setSize(300, 300);
	   this.setLocation(500, 200);
	   this.setVisible(true);
	   
   }
	
   private class loginAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("登陆")){
			//触发按钮事件的时候，对数据库进行连接并且进行比对
			String u_name=usernameText.getText().trim();
			String u_password=passwordText.getText().trim();
			if(jr2.isSelected()&&!jr1.isSelected()){
			WaiterDao wd=new WaiterDao();
			WaiterModel wm=wd.loginWaiter(u_name, u_password);
			if(wm==null){
				JOptionPane.showMessageDialog(indexPanel, "用户名或密码不正确，请重新试一下");
			}else{
				JOptionPane.showMessageDialog(indexPanel, "登陆成功");
				//如果登陆成功后，那么
				Integer i=wm.getW_id();
				i.toString();
				WaiterIndexView wiv=new WaiterIndexView();
				wiv.setW_id(i.toString());
				wiv.setW_name(wm.getW_name());
				wiv.setW_password(wm.getW_password());
				wiv.setW_telephone(wm.getW_telephone());
				wiv.setW_actor(wm.getW_actor());
				wiv.changeForLogin();
				dispose();
			}
			}else if(jr1.isSelected()&&!jr2.isSelected()){
				NormalUserDao nud=new NormalUserDao();
				NormalUserModel num=nud.loginNormalUser(u_name, u_password);
				if(num==null){
					JOptionPane.showMessageDialog(indexPanel, "用户名或密码不正确，请重新试一下");
				}else{
					JOptionPane.showMessageDialog(indexPanel, "登陆成功");
					//如果登陆成功后，那么
					Integer i=num.getNu_id();
					i.toString();
					UserIndexView wiv=new UserIndexView();
					wiv.setNu_id(i.toString());
					wiv.setNu_name(num.getNu_name());
					wiv.setNu_password(num.getNu_password());
					wiv.setNu_telephone(num.getNu_telephone());
					wiv.setNu_address(num.getNu_address());
					wiv.changeForLogin();
					dispose();
				}
				
			}else if(jr1.isSelected()&&jr2.isSelected()){
				JOptionPane.showMessageDialog(indexPanel, "只能选择一个角色");
			}else if(!jr1.isSelected()&&!jr2.isSelected()){
				JOptionPane.showMessageDialog(indexPanel, "请选择你的角色");
			}
		}else if(e.getActionCommand().equals("注册")){
			if(jr2.isSelected()&&!jr1.isSelected()){
				dispose();
			new WaiterRegisterView();
			}else if(jr1.isSelected()&&!jr2.isSelected()){
				dispose();
				new UserRegisterView();
			}else if(jr2.isSelected()&&jr1.isSelected()){
				JOptionPane.showMessageDialog(indexPanel, "只能选择一个角色注册");
			}else if(!jr2.isSelected()&&!jr1.isSelected()){
				JOptionPane.showMessageDialog(indexPanel, "请选择一个角色注册");
			}
		}else if(e.getActionCommand().equals("重置")){
			usernameText.setText("");
			passwordText.setText("");
		}
	}
	   
   }
   
	public static void main(String[] args) {
		new WaiterLoginView();
	}

}
