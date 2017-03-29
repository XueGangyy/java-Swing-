package com.ynu.dinnerorder.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

import com.ynu.dinnerorder.databasedao.WaiterDao;

public class WaiterRegisterView extends JFrame{
	/*
	 * @Author:杨坤
	 * @Test:通过
	 */
	private JTextField u_name_text;//
    private JPasswordField u_pass_text;//
    private JPasswordField u_pass_suretext;//
    private JTextField u_tel_text;//
    private JTextField u_a_text;//
    private JComboBox<String> sex;//
    private String[] s={"男","女"};//
    JPanel indexpanel;//
	
	
	public WaiterRegisterView(){
		init();
	}
	
	public void init(){
		/*图片素材*/
		ImageIcon x = new ImageIcon("E://素材//cha.jpg");
		ImageIcon v = new ImageIcon("E://素材//gou.jpg");
		
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

        JPanel actorpanel=new JPanel();
        JLabel actorLab=new JLabel("担任职位:");
        u_a_text=new JTextField(20);
        actorpanel.add(actorLab);
        actorpanel.add(u_a_text);
        //为担任职位这个选项分配一个空间
        
        JPanel sexpanel=new JPanel();
        
        JLabel sexLab=new JLabel("性别:");
        sex=new JComboBox<String>(s);
        sexpanel.add(sexLab,BorderLayout.EAST);
        sexpanel.add(sex,BorderLayout.EAST);
        sexpanel.setSize(10, 10);
        //为性别的选择分配一个空间
        
        JPanel bottonpanel=new JPanel();
        JButton j1=new JButton("确认注册");
        j1.setIcon(v);/**/
        j1.addActionListener(new registerAction());
        j1.setActionCommand("确认注册");
        JButton j2=new JButton("取消注册");
        j2.addActionListener(new registerAction());
        j2.setActionCommand("取消注册");
        j2.setIcon(x);/**/
        bottonpanel.add(j1);
        bottonpanel.add(j2);
        //为按钮组件添加事件和盒子
        
		indexpanel.add(usernamepanel);
		indexpanel.add(passwordpanel);
		indexpanel.add(passwordsurepanel);
		indexpanel.add(telephonepanel);
		indexpanel.add(actorpanel);
		indexpanel.add(sexpanel);
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
				String u_a=u_a_text.getText().trim();
				String u_sex;
				if(sex.getSelectedItem().equals("男")){
					u_sex="男";
				}else{
					u_sex="女";
				}
				WaiterDao wd;
				if(!u_pass.equals(u_surepass))
				{
					JOptionPane.showMessageDialog(indexpanel,"你的两次密码不一致");
				}else if(u_pass.length()>=6&&u_pass.length()<=20)
				{
					if(u_name.isEmpty()){
						JOptionPane.showMessageDialog(indexpanel,"你输入的名字是空的");
					}else{
				wd=new WaiterDao();
				boolean b=wd.registerWaiter(u_name, u_pass, u_tel, u_a, u_sex);
				if(b==true){
					JOptionPane.showMessageDialog(indexpanel,"注册成功");
						dispose();
						WaiterLoginView wlv=new WaiterLoginView();
						wlv.setP_s(u_pass);
						wlv.setU_s(u_name);
						wlv.setB_for_jr2(true);
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
				u_a_text.setText("");
			}
		}
		
	}
	
	public static void main(String[] args) {
		new WaiterRegisterView();
	}

}
