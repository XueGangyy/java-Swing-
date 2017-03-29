package com.ynu.dinnerorder.view;

import java.awt.BasicStroke;  
import java.awt.Color;  
import java.awt.Component;  
import java.awt.Font;  
import java.awt.Graphics;  
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;  
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;  
import java.util.Calendar;
import java.util.List;

import com.ynu.dinnerorder.databasemodel.ItemModel;  
  
//实现Printable接口 用于创建打印内容  
public class OederDaYin implements Printable {  
    private List<ItemModel> list;   
    private Font font;
    private String total;
   
    // 构造函数  
    public OederDaYin(List<ItemModel> lcd,String total) {  
        this.list = lcd;  
       this.total=total;
    }  
  
    /** 
     * @param Graphic指明打印的图形环境 
     * @param PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595× 
     *            842点） 
     * @param pageIndex指明页号 
     **/  
    @Override  
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {  
        Component c = null;  
        // 转换成Graphics2D 拿到画笔  
        Graphics2D g2 = (Graphics2D) graphics;  
        // 设置打印颜色为黑色  
        g2.setColor(Color.black);  
  
        // 打印起点坐标  
        double x = pageFormat.getImageableX();  
        double y = pageFormat.getImageableY();  
  
        // 虚线  
        float[] dash1 = { 4.0f };  
        // width - 此 BasicStroke 的宽度。此宽度必须大于或等于 0.0f。如果将宽度设置为  
        // 0.0f，则将笔划呈现为可用于目标设备和抗锯齿提示设置的最细线条。  
        // cap - BasicStroke 端点的装饰  
        // join - 应用在路径线段交汇处的装饰  
        // miterlimit - 斜接处的剪裁限制。miterlimit 必须大于或等于 1.0f。  
        // dash - 表示虚线模式的数组  
        // dash_phase - 开始虚线模式的偏移量  
  
        // 设置画虚线  
        g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 4.0f, dash1, 0.0f));  
  
        // 设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）  
        font = new Font("宋体", Font.PLAIN, 11);  
        g2.setFont(font);// 设置字体  
        float heigth = font.getSize2D();// 字体高度  
        // 标题  
        g2.drawString("蕉叶泰国菜餐厅", (float) x, (float) y + heigth);  
        float line = 2 * heigth;  
  
        font = new Font("宋体", Font.PLAIN, 8);  
        g2.setFont(font);// 设置字体  
        heigth = font.getSize2D();// 字体高度  
  
        
       
  
        line += heigth;  
        // 显示标题  
        g2.drawString("菜名", (float) x + 20, (float) y + line);  
        g2.drawString("单价", (float) x + 60, (float) y + line);  
        g2.drawString("数量", (float) x + 85, (float) y + line);  
        g2.drawString("总额", (float) x + 115, (float) y + line);  
        line += heigth;  
        g2.drawLine((int) x, (int) (y + line), (int) x + 158, (int) (y + line));  
  
        // 第4行  
        line += heigth;  
  
        // 显示内容  
        for (ItemModel IM:list) {  
  
            
  
            g2.drawString(IM.getDishm().getDish_name(), (float) x, (float) y + line);  
            g2.drawString(String.valueOf(IM.getDishm().getDish_price())+"元", (float) x + 60, (float) y + line);  
            g2.drawString(String.valueOf(IM.getItem_num())+"份", (float) x + 90, (float) y + line);  
            g2.drawString(String.valueOf(IM.getItem_totalprice())+"元", (float) x + 120, (float) y + line);  
            line += heigth;  
  
        }  
        
        line += heigth;  
  
        g2.drawLine((int) x, (int) (y + line), (int) x + 158, (int) (y + line));  
        line += heigth;  
  
        
        g2.drawString("合计:" + total, (float) x + 70, (float) y + line);  
        line += heigth;  
        line += heigth;  
        g2.drawString("时间:" + Calendar.getInstance().getTime().toLocaleString(), (float) x, (float) y + line);  
  
        line += heigth;  
        g2.drawString("欢饮再次光临蕉叶泰国菜餐厅!", (float) x + 20, (float) y + line);  
  
        line += heigth;  
        g2.drawString("钱票请当面点清，离开柜台恕不负责", (float) x, (float) y + line);  
        switch (pageIndex) {  
        case 0:  
  
            return PAGE_EXISTS;  
        default:  
            return NO_SUCH_PAGE;  
  
        }  
  
    } 
}
