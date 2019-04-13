import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class Input extends JFrame implements KeyListener
{
	private class DisplayThread extends Thread
	{
		private String message;
		private JTextArea output;
		
		public DisplayThread(String message, JTextArea output)
		{
			this.message = message;
			this.output = output;
		}
		
		@Override
		public void run()
		{
			while(true)
			{
				try
				{
					this.output.append(this.message);
					Thread.sleep(1000);
				}
				catch (InterruptedException e) { return; }
			}
		}
	}
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea output;
	private String message;
	private DisplayThread displayer;
	
	public Input(String name)
	{
		super(name);
		
		this.output = new JTextArea(20, 30);
		DefaultCaret caret = (DefaultCaret)this.output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		add(new JScrollPane(output));
		setSize(500, 500);
		setVisible(true);
		
		this.message = "";
		this.output.addKeyListener(this);
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE)
			this.message += e.getKeyChar();
		if (this.message.equalsIgnoreCase("exit"))
			System.exit(0);
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (this.displayer != null && this.displayer.isAlive())
		{
			this.displayer.interrupt();
			this.message = "";
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			this.displayer = new DisplayThread(this.message, this.output);
			this.displayer.start();
		}
		else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && !this.message.equals(""))
			this.message = this.message.substring(0, this.message.length() - 2);
	}
	
	public static void main(String[] args)
	{
		Input input = new Input("Assignment 1");
		input.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e) { System.exit(0); }
		});
	}
}
