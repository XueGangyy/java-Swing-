package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ynu.dinnerorder.databasedao.NormalUserDao;
import com.ynu.dinnerorder.databasedao.WaiterDao;


public class UserRegisterView extends JFrame{
	/*图片素材*/
	ImageIcon x = new ImageIcon("E://素材//cha.jpg");
	ImageIcon v = new ImageIcon("E://素材//gou.jpg");
	ImageIcon face = new ImageIcon("E://素材//jyface.jpg");
	
	private JTextField u_name_text;//
    private JPasswordField u_pass_text;//
    private JPasswordField u_pass_suretext;//
    private JTextField u_tel_text;//
    private JTextField u_add_text; 
    JPanel indexpanel;//
	
	
	public UserRegisterView(){
		init();
	}
	
	public void init(){
		/**/
		JLabel lbface = new JLabel();
		lbface.setIcon(face);
		
		indexpanel=new JPanel();
		//主页的容器
		BoxLayout box=new BoxLayout(indexpanel, BoxLayout.Y_AXIS);
		indexpanel.setLayout(box);
		//采用盒式布局管理器
		
		JPanel usernamepanel=new JPanel();
		JLabel usernameLab=new JLabel("用 户 名：");
		u_name_text=new JTextField(20);
		usernamepanel.add(usernameLab);
		usernamepanel.add(u_name_text);
		//为用户名分配一个空间
		
        JPanel passwordpanel=new JPanel();
        JLabel passwordLab=new JLabel("密       码:");
        u_pass_text=new JPasswordField(20);
        u_pass_text.setEchoChar('*');
        passwordpanel.add(passwordLab);
        passwordpanel.add(u_pass_text);
        //为密码分配一个空间
		
        JPanel passwordsurepanel=new JPanel();
        JLabel passwordsureLab=new JLabel("确认密码:");
        u_pass_suretext=new JPasswordField(20);
        u_pass_suretext.setEchoChar('*');
        passwordsurepanel.add(passwordsureLab);
        passwordsurepanel.add(u_pass_suretext);
        //为确认密码这个模块分配一个空间
        
        JPanel telephonepanel=new JPanel();
        JLabel telephoneLab=new JLabel("电话号码:");
        u_tel_text=new JTextField(20);
        telephonepanel.add(telephoneLab);
        telephonepanel.add(u_tel_text);
        //为电话号码分配一个空间

     
        JPanel addresspanel=new JPanel();
        JLabel addressLab=new JLabel("用户地址:");
        u_add_text=new JTextField(20);
        addresspanel.add(addressLab);
        addresspanel.add(u_add_text);
        //为电话号码分配一个空间
        
        JPanel bottonpanel=new JPanel();
        JButton j1=new JButton("确认注册");
        j1.setIcon(v);/**/
        j1.addActionListener(new registerAction());
        j1.setActionCommand("确认注册");
        JButton j2=new JButton("取消注册");
        j2.setIcon(x);/**/
        j2.addActionListener(new registerAction());
        j2.setActionCommand("取消注册");
        bottonpanel.add(j1);
        bottonpanel.add(j2);
        //为按钮组件添加事件和盒子
        
		indexpanel.add(usernamepanel);
		indexpanel.add(passwordpanel);
		indexpanel.add(passwordsurepanel);
		indexpanel.add(telephonepanel);

		indexpanel.add(addresspanel);
		indexpanel.add(bottonpanel);
		//把所有盒子添加到主页的空间上去。然后注册事件
		
		this.add(indexpanel);
		//把主页的空间添加到窗口中去
		
		this.setSize(400, 400);
		this.setLocation(500, 200);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("用户注册界面");
	}
	
	private class registerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals("确认注册")){
				//当确认按钮触发事件时候，就会调用相关数据库的操作，把数据写到数据库中。
				String u_name=u_name_text.getText().trim();
				String u_pass=u_pass_text.getText().trim();
				String u_surepass=u_pass_suretext.getText().trim();
				String u_tel=u_tel_text.getText().trim();
				String u_add=u_add_text.getText().trim();
				if(!u_pass.equals(u_surepass))
				{
					JOptionPane.showMessageDialog(indexpanel,"你的两次密码不一致");
				}else if(u_pass.length()>=6&&u_pass.length()<=20)
				{
					if(u_name.isEmpty()){
						JOptionPane.showMessageDialog(indexpanel,"你输入的名字是空的");
					}else{
						NormalUserDao nud=new NormalUserDao();
						boolean b=nud.registerNormalUser(u_name, u_pass, u_tel, u_add);
						if(b==true){
							JOptionPane.showMessageDialog(indexpanel,"注册成功");
								dispose();
								WaiterLoginView wlv=new WaiterLoginView();
								wlv.setP_s(u_pass);
								wlv.setU_s(u_name);
								wlv.setB_for_jr1(true);
								wlv.changeT();
								wlv.changeB();
						}else{
							JOptionPane.showMessageDialog(indexpanel,"你的名字已经存在，请换一个");
						}
							}
						}else if(u_pass.length()<6){
							JOptionPane.showMessageDialog(indexpanel,"你的密码过短，请在6~20位之间");
						}else if(u_pass.length()>20){
							JOptionPane.showMessageDialog(indexpanel,"你的密码过长，请在6~20位之间");
						}
					
				
			}else if(e.getActionCommand().equals("取消注册")){
				u_name_text.setText("");
				u_pass_text.setText("");
				u_pass_suretext.setText("");
				u_tel_text.setText("");
				u_add_text.setText("");
			}
		}
		
	}

	public static void main(String[] args) {
		new UserRegisterView();
	}

}
