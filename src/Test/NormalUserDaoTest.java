package Test;

import com.ynu.dinnerorder.databasedao.NormalUserDao;
import com.ynu.dinnerorder.databasemodel.NormalUserModel;

public class NormalUserDaoTest {

	public static void main(String[] args) {
		NormalUserDao nud=new NormalUserDao();
//		boolean b=nud.registerNormalUser("小华", "12345678", "158745", "华东校区");
//		System.out.println(b);
//		NormalUserModel num=nud.loginNormalUser("小华", "12345678");
//		if(num==null){
//			System.out.println("登录失败");
//		}else{
//			System.out.println("登陆成功");
//			System.out.println(num.getNu_address()+num.getNu_id()+num.getNu_password()+num.getNu_telephone()+num.getNu_name());
//		}
//		nud.updateNormalUser("小光", "00", "35", "25", 1001);
//		nud.registerNormalUser("xiao", "","","",500001);
//		NormalUserModel num=nud.equeryN_User(500001);
//		System.out.println(num.getNu_id()+num.getNu_name()+num.getNu_password()+num.getNu_address()+num.getNu_telephone());
		boolean b=nud.deleteNormalUser(500001);
		if(b=false){
			System.out.println("删除失败");
		}else{
			System.out.println("删除成功");
		}
	}

}
