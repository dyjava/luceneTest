package my.applet.swing.game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

/**
 * 打字游戏
 * 统计打字速度，正确率
 * 按键开始计时，空格键结束，统计结果，再次按空格键退出。
 */
public class CreateF extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = -115593344199240572L;
	JPanel contentPane;	//
    XYLayout xYLayout1 = new XYLayout();
    JLabel countLab = new JLabel();
    JLabel timeLab = new JLabel();
    JLabel output = new JLabel();
    
    public JOptionPane jop1 = new JOptionPane();
    
    private int ringtCount = 0,errCount =0,min =0 ;
    private String out = "" ;
    public CreateF() {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(xYLayout1);
        contentPane.setBackground(SystemColor.controlLtHighlight);
        contentPane.setToolTipText("");
        this.setIconImage(null);
        this.setResizable(false);
        setSize(new Dimension(200, 200));
        setTitle("打字");
        
//        添加键盘按键监听
        KeyListener l = new GameFKeyListener();
		this.addKeyListener(l ) ;
        fresh() ;
        
//        
        timeLab.setText("TIME:"+min) ;
        
        output.setFont(new Font("宋体", Font.PLAIN, 50)) ;
        contentPane.add(countLab, new XYConstraints(30, 20, -1, -1));
        contentPane.add(timeLab, new XYConstraints(30, 40, -1, -1));
        contentPane.add(output, new XYConstraints(50, 60, -1, -1));
        
    }

    private void fresh() {
//    	65-90 48-57
    	String pre = out ;
    	output.setText("") ;

    	while(out.equals(pre)){
	    	int n = (int)(Math.random()*26)+65 ;
	    	out = String.valueOf((char)n);
    	}
		output.setText(String.valueOf(out)) ;
    }
    
    private void getTime(){
		min ++ ;
		timeLab.setText("时间:"+min+"秒  "+(ringtCount+errCount)*60/min+"字每分钟") ;
    }
    
    public void keyAction(String key){
		if(key.equals(out)){
			ringtCount ++ ;
		} else {
			errCount ++ ;
		}
		
		String text = "统计：正确"+ringtCount+"/错误"+errCount ;
		countLab.setText(text) ;
		
		fresh() ;
    }

    class GameFKeyListener implements KeyListener{
    @SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent e) {
    	int keycode = e.getKeyCode() ;
    	if(keycode<65 || keycode>90){
    		
//    		回车键推出
    		if(keycode==10){
    			System.exit(0) ;
    		}
    		
//    		空格键暂停
    		if(keycode==32){
    			//第二次按空格退出
    			if(!stop){
    				System.exit(0) ;
    			}
    			//未开始无效
    			if(errCount+ringtCount ==0){
    				return ;
    			}
				stop = false ;
				out= ringtCount*1000/(errCount+ringtCount)/10.0+"%";
				output.setText(out) ;
				
			}
    		System.out.println(keycode) ;
    		return ;
    	}
    	
    	String key = e.getKeyText(keycode) ;
    	keyAction(key) ;
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		按键离开开始计时
		if(start){
			start = false ;
			new TimeThread().start() ;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
//		jop1.showMessageDialog(this,"g") ;
	}
    }

    boolean stop = true,start=true ;
    class TimeThread extends Thread {
    	public void run(){
    		while(stop){
    		try {
				Thread.sleep(1000) ;
				getTime() ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		}
    	}
    }
}

