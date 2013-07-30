package my.swing.frame.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

/** 
 * by dyong 2010-9-1
 */
public class TableModel extends AbstractTableModel{
	
	protected List<Object> data ;
	protected List<String> title ;
	
	public TableModel(List<String> title,List<Object> data){
		this.title = title ;
		this.data = data ;
	}
    public int getColumnCount() { // 取得表格列数
        return title.size() ;
    }
    public int getRowCount() { // 取得表格行数
        return data.size();
    }
    public Object getValueAt(int row, int col) { // 取得单元格中的属性值
        if (!data.isEmpty()) {
        	return ((List)data.get(row)).get(col) ;
        } else {
            return null;
        }
    }
    public void setValueAt(Object value, int row, int column) { // 数据模型不可编辑，该方法设置为空

    }
    public String getColumnName(int col) { // 取得表格列名
        return (String) title.get(col) ;
    }
    @SuppressWarnings("unchecked")
	public Class getColumnClass(int c) { // 取得列所属对象类
        return getValueAt(0, c).getClass();
    }
    public boolean isCellEditable(int row, int column) { // 设置单元格不可编辑，为缺省实现
        return true;
    }
}