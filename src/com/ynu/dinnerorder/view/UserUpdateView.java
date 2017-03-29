package com.ynu.dinnerorder.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ynu.dinnerorder.databasedao.NormalUserDao;
import com.ynu.dinnerorder.databasedao.WaiterDao;
import com.ynu.dinnerorder.databasemodel.NormalUserModel;


public class UserUpdateView extends JFrame{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private String nu_newname;
	private String nu_newaddress;
	private String nu_newtelephone;
	private String sop;
	private String son;

    
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    
    
    

	

	public void change(){
    	textField.setText(s1);
    	passwordField.setText(s2);
    	passwordField_1.setText(s2);
    	textField_3.setText(s3);
    	textField_4.setText(s4);
    	
    }
	
	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public String getS3() {
		return s3;
	}

	public void setS3(String s3) {
		this.s3 = s3;
	}

	public String getS4() {
		return s4;
	}

	public void setS4(String s4) {
		this.s4 = s4;
	}

	private int Nu_id;

	public int getNu_id() {
		return Nu_id;
	}

	public void setNu_id(int Nu_id) {
		this.Nu_id = Nu_id;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserUpdateView frame = new UserUpdateView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserUpdateView(){
		UserUpdateViewinit();
	}
	/**
	 * Create the frame.
	 */
	public void UserUpdateViewinit() {
		setVisible(true);
	    setTitle("用户修改个人信息");
		setSize(400, 400);
		setResizable(false);
		setLocation(500, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel label = new JLabel("用 户 名：");
		panel.add(label);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("原有密码：");
		panel_1.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		passwordField.setEchoChar('*');
		panel_1.add(passwordField);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JLabel label_1 = new JLabel("新的密码：");
		panel_2.add(label_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(20);
		passwordField_1.setEchoChar('*');
		panel_2.add(passwordField_1);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("电    话：");
		panel_3.add(lblNewLabel_1);
		
		textField_3 = new JTextField();
		panel_3.add(textField_3);
		textField_3.setColumns(20);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("地   址：");
		panel_4.add(lblNewLabel_2);
		
		textField_4 = new JTextField();
		panel_4.add(textField_4);
		textField_4.setColumns(20);
		
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		
		
		
		JButton button = new JButton("确认");
		button.addActionListener(new updateAction());
		button.setActionCommand("确认");
		panel_5.add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new updateAction());
		button_1.setActionCommand("取消");
		panel_5.add(button_1);
	}

private class updateAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("确认")){
			sop=passwordField.getText().trim();
			son=passwordField_1.getText().trim();
			nu_newname=textField.getText().trim();
			nu_newtelephone=textField_3.getText().trim();
			nu_newaddress=textField_4.getText().trim();
			if(son.length()<6){
				JOptionPane.showMessageDialog(contentPane, "密码过短");
			}else if(son.length()>20){
				JOptionPane.showMessageDialog(contentPane, "密码过长");
			}else{
			NormalUserDao nud=new NormalUserDao();
			nud.updateNormalUser(nu_newname, son, nu_newtelephone, nu_newaddress,Nu_id);
			JOptionPane.showMessageDialog(contentPane, "修改成功");
			dispose();
			Integer i=new Integer(Nu_id);
			UserIndexView uiv=new UserIndexView();
			uiv.setNu_name(nu_newname);
			uiv.setNu_id(i.toString());
			uiv.setNu_address(nu_newaddress);
			uiv.setNu_password(son);
			uiv.setNu_telephone(nu_newtelephone);
			uiv.changeForLogin();
			}
		}else if(e.getActionCommand().equals("取消")){
			dispose();
			sop=passwordField.getText().trim();
			son=passwordField_1.getText().trim();
			nu_newname=textField.getText().trim();
			nu_newtelephone=textField_3.getText().trim();
			nu_newaddress=textField_4.getText().trim();
			UserIndexView uiv=new UserIndexView();
			uiv.setNu_name(nu_newname);
			Integer i=new Integer(Nu_id);
			uiv.setNu_id(i.toString());
			uiv.setNu_address(nu_newaddress);
			uiv.setNu_password(son);
			uiv.setNu_telephone(nu_newtelephone);
			uiv.changeForLogin();
		}
	}
	
}

}
