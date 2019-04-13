import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;


public class Lab1 extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	private JTextArea output;
	private Thread t1, t2;
	private boolean allMessage = false;
	
	public Lab1(String name) {
		super(name);
		
		output = new JTextArea(20,30);
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		add(new JScrollPane(output));
		setSize(500, 500);
		setVisible(true);
		
		t1 = new Thread(
	    		new Runnable() {
	    			public void run()
	    			{
	    				for (int i = 0; i < Integer.MAX_VALUE; i++)
	    				{
	    					try
	    					{
	    						output.append("Message from thread -> 1\n");
	    						t1.sleep(1000);
	    					} catch (InterruptedException e)
	    					{
	    						output.append("Thread-1 Gets Interrupted! Terminate!\n");
	    						return;
	    					}
	    				}
	    			}
	    		});
		t2 = new Thread(
	    		new Runnable() {
	    			public void run()
	    			{
	    				for (int i = 0; i < Integer.MAX_VALUE; i++)
	    				{
	    					try
	    					{
	    						output.append("Message from thread -> 2\n");
	    						t2.sleep(1000);
	    					} catch (InterruptedException e)
	    					{
	    						output.append("Thread-2 Gets Interrupted! Terminate!\n");
	    						return;
	    					}
	    				}
	    			}
	    		});
		
		output.addKeyListener(this);
		t1.start();
		t2.start();
	}
		
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (t1.isAlive())
			t1.interrupt();
		else if (!t1.isAlive() && !t2.isAlive() && !allMessage)
		{
			output.append("All threads interrupted\n");
			allMessage = true;
		}
		else
			t2.interrupt();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ENTER)
			output.append("Key Released, you just pressed an Enter Key!\n");
		else
			output.append("Key Released, you just pressed the character \'" + e.getKeyChar() + "\'\n" );
	}
	
	public static void main(String[] args)
	{
		Lab1 lab1 = new Lab1("Lab 1");
	    lab1.addWindowListener(
	    		new WindowAdapter() {
					public void windowClosing( WindowEvent e)
					{
						System.exit(0);
					}
	    		});
	}	
}