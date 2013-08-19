package my.swing.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import my.swing.frame.table.TableFactory;


import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

/**
 * 
 */
public class CreateJFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -115593344199240572L;
	JPanel contentPane;	//
    XYLayout xYLayout1 = new XYLayout();
    JLabel inputLab = new JLabel();
    JTextArea outputLab = new JTextArea();
    
    JTextField inputText = new JTextField();
    JTextField inputText2 = new JTextField();
    JTextArea outputText = new JTextArea();
    JComboBox box = new JComboBox() ;
    JOptionPane jop1 = new JOptionPane();
    
//    table
    JScrollPane jScrollPane1 = new JScrollPane();
//    AbstractTableModel tableModel;
    JTable table = new JTable();
    public CreateJFrame() {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(xYLayout1);
        contentPane.setBackground(SystemColor.controlLtHighlight);
        contentPane.setToolTipText("");
        this.setIconImage(null);
        this.setResizable(false);
        setSize(new Dimension(800, 700));
        setTitle("查询工具");
        
//        输入输出
        inputLab.setText("输入");
        inputLab.setFont(new Font("宋体", Font.PLAIN, 12)) ;
        outputLab.setText("输出");
        outputText.setFont(new Font("UTF-8", Font.PLAIN, 20)) ;
//        outputText.setEnabled(false) ;
//        outputText.setOpaque(true) ;
//        outputText.setWrapStyleWord(true) ;
        outputText.setLineWrap(true) ;
        outputText.setBackground(new Color(100,150,140)) ;
        
//      
        box.addItem("邮编") ;
        box.addItem("拼音") ;
        box.addItem("城市") ;
        box.addItem("手机号") ;
//        按钮
        JButton runBut = new JButton();
        runBut.setText("提交");
        runBut.addActionListener(new RunButtonActionAdapter());
        JButton cancelBut = new JButton();
        cancelBut.setText("退出");
        cancelBut.addActionListener(new CancelButtonActionAdapter());
        
//  	contentPane.add(jop1, new XYConstraints(800, 600, 800, 600));
        contentPane.add(inputLab, new XYConstraints(50, 24, -1, -1));
        contentPane.add(inputText, new XYConstraints(120, 24, 200, 20));
        contentPane.add(box, new XYConstraints(360, 24, -1, -1));
        contentPane.add(runBut, new XYConstraints(430, 24, -1, -1));
        contentPane.add(cancelBut, new XYConstraints(500, 24, -1, -1));

//        table
        jScrollPane1.getViewport().setBackground(SystemColor.info);
        List<Object> data = new ArrayList<Object>();
        List<String> title = new ArrayList<String>();
        table = TableFactory.createtable(title, data) ;
        jScrollPane1.getViewport().add(table);
    	this.add(jScrollPane1, new XYConstraints(50, 50, 700, 600));
    }

    private void cancelBut_actionPerformed(ActionEvent e) {
        System.exit(0) ;
    }

    private void runBut_actionPerformed(ActionEvent e) {
//    	更新表格数据
        List<Object> data = new ArrayList<Object>();
        List<String> title = new ArrayList<String>();
    	new Config().getTableData(title, data, this) ;
    	
    	TableFactory.freshTableData(table, title, data) ;
    }
    class RunButtonActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            runBut_actionPerformed(e) ;
        }
    }

    class CancelButtonActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cancelBut_actionPerformed(e);
        }
    }
}

