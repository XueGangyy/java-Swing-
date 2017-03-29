package Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ynu.dinnerorder.databasedao.ItemDao;
import com.ynu.dinnerorder.databasedao.OrderDao;
import com.ynu.dinnerorder.databasemodel.ItemModel;

public class ItemDaoTest {

	public static void main(String[] args) {
//		ItemDao id=new ItemDao();
//		Date date;
//		try {
//			date = new SimpleDateFormat("yyyy.MM.dd").parse("2011.2.17");
//			System.out.println(date);
//			Timestamp t=new Timestamp(date.getTime());
//			System.out.println(t);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(date1);
//		Date date2=new Date(2016
//				, 2,5,00, 00, 00);
//		Timestamp t=new Timestamp(date.getTime());
//		System.out.println(t);
//		Date date3=new Date(date1.getYear()
//				, 1,date1.getDate()+1,00, 00, 00);
//		System.out.println(date3.getDate());
//		List<ItemModel> lim=id.equeryItemByDay(date2,date3);
//		for(ItemModel im:lim){
//			System.out.println(im.getItem_id()+" "+im.getItem_num()+" "+im.getItem_state()+
//					im.getItem_totalprice()+" 日期"+im.getItem_date()+"点餐人名字"+im.getN_user_m().getNu_name()
//					+"点餐人电话"+" 点餐人地址"+im.getN_user_m().getNu_address()+" 桌位"+im.getDeskm().getDesk_name()+
//					" 菜品名称："+im.getDishm().getDish_name()+" 菜品价格"+im.getDishm().getDish_price());
//		}
		
//		List<ItemModel> lim=id.equeryItemAll();
//		for(ItemModel im:lim){
//			System.out.println(im.getItem_id()+" "+im.getItem_num()+" "+im.getItem_state()+
//					im.getItem_totalprice()+" 日期"+im.getItem_date()+"点餐人名字"+im.getN_user_m().getNu_name()
//					+"点餐人电话"+" 点餐人地址"+im.getN_user_m().getNu_address()+" 桌位"+im.getDeskm().getDesk_name()+
//					" 菜品名称："+im.getDishm().getDish_name()+" 菜品价格"+im.getDishm().getDish_price());
//		}
//		System.out.println(im.getItem_id()+" "+im.getItem_num()+" "+im.getItem_state()+
//				im.getItem_totalprice()+" 日期"+im.getItem_date()+"点餐人名字"+im.getN_user_m().getNu_name()
//				+"点餐人电话"+" 点餐人地址"+im.getN_user_m().getNu_address()+" 桌位"+im.getDeskm().getDesk_name()+
//				" 菜品名称："+im.getDishm().getDish_name()+" 菜品价格"+im.getDishm().getDish_price());
		
//		int i=id.deleteItem(78);
//		System.out.println(i);
//		int i=id.updateTotalPrice(18, 500006);
//		System.out.println(i);
//		int i=id.AddOrderId("2014", 500007, 1);
//		System.out.println(i);
//		Date d=new Date();
//		Integer s1=d.getYear();
//		Integer s2=d.getMonth();
//		Integer s3=d.getDate();
//		Integer s4=d.getHours();
//		Integer s5=d.getMinutes();
//		Integer s6=d.getSeconds();
//		String stotal_id=s1.toString()+s2.toString()+s3.toString()+s4.toString()+s5.toString()+s6.toString();
//		
//		System.out.println(s6);
//		List<ItemModel> lim=id.equeryByOrderId("1161117172844");
//		for(ItemModel im:lim){
//			System.out.println(im.getItem_id()+" "+im.getItem_num()+" "+im.getItem_state()+
//					im.getItem_totalprice()+" 日期"+im.getItem_date()+"点餐人名字"+im.getN_user_m().getNu_name()
//					+"点餐人电话"+" 点餐人地址"+im.getN_user_m().getNu_address()+" 桌位"+im.getDeskm().getDesk_name()+
//					" 菜品名称："+im.getDishm().getDish_name()+" 菜品价格"+im.getDishm().getDish_price());
//		}
//		OrderDao OD=new OrderDao();
//		Date d=new Date();
//		Timestamp t=new Timestamp(d.getTime());
//		System.out.println(t);
//		OD.addOrder(d, "2", 3, "1", "1");
		Date date=new Date();
		Date date1=new Date(date.getYear()+1,00,01,00,00,00);
		Timestamp t=new Timestamp(date1.getTime());
		System.out.println(t);
	}
	

}
