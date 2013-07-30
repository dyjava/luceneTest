package my.swing.frame.table;

import java.awt.Font;
import java.util.List;

import javax.swing.JTable;

/** 
 * by dyong 2010-9-2
 */
public class TableFactory {

	//创建表格
	public static JTable createtable(List<String> title,List<Object> data){
        TableModel tableModel = new TableModel(title,data);
        JTable table = new JTable(tableModel) ;
//        table.setModel(tableModel);					// 生成自己的数据模型
        table.setToolTipText("Display Query Result"); // 设置帮助提示
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // 设置表格调整尺寸模式
        table.setCellSelectionEnabled(false); // 设置单元格选择方式
        table.setShowHorizontalLines(true); // 设置是否显示单元格之间的分割线
        table.setShowVerticalLines(true);
        table.setFont(new Font("UTF-8", Font.PLAIN, 12)) ;
        return table ;
    }
    
    public static void freshTableData(JTable table,List<String> title,List<Object> data){
    	TableModel model = (TableModel) table.getModel() ;
    	
//    	更新表格数据
    	model.data = data ;
    	model.title = title ;

//    	刷新到表格
    	model.fireTableStructureChanged() ;
    }
}
