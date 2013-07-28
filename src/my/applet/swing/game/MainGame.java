package my.applet.swing.game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;


/** 
 * by dyong 2010-9-10
 */
public class MainGame implements ActionListener{
    boolean packFrame = false;
    /**
     * Construct and show the application.
     */
    CreateF frame ;
    public MainGame() {
    	jbInit();
    	
    	frame = new CreateF();
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

    private void jbInit() {}

	/**
     * Application entry point.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                new MainGame();
            }
        });
    }
	public void actionPerformed(ActionEvent e) {
		
	}

}
