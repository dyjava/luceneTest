package my.swing.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/** 
 * by dyong 2010-7-17
 */
public class MainFrame implements ActionListener {
    boolean packFrame = false;
    /**
     * Construct and show the application.
     */
    public MainFrame() {
    	jbInit();
    	
    	JFrame frame = new CreateJFrame();
        // Validate frames that have preset sizes
        // Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            frame.pack();
        } else {
            frame.validate();
        }

        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

    private void jbInit() {
		// TODO Auto-generated method stub
//		Config.getConfig().init() ;
	}

	/**
     * Application entry point.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
    	if(args.length>0){
//    		Config.getConfig().init(args[0]) ;
    	}
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.
                                             getSystemLookAndFeelClassName());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                new MainFrame();
            }
        });
    }
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
