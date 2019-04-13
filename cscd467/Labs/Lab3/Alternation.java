public class Alternation
{
	private class Conditional
	{
		boolean isT1sTurn;
		
		public Conditional() { this.isT1sTurn = true; }
		
		public synchronized void setTurn(boolean bool) { this.isT1sTurn = bool; }
		
		public synchronized boolean getTurn() { return this.isT1sTurn; }
	}
	
	private Thread t1;
	private Thread t2;
	private Conditional cond;
	
	public Alternation()
	{
		cond = new Conditional();
		
		t1 = new Thread(new Runnable()
		{
	        @Override
	        public void run()
	        {
                for (int i = 1; i <= 50; i += 2)
                {
	                while(!cond.getTurn());
	                System.out.println("T1=" + i);
	                cond.setTurn(false);
	                
	                try { Thread.sleep(1000); }
	                catch (InterruptedException e) {}
                }
	        }
	    });
	    t2 = new Thread(new Runnable() {

	        @Override
	        public void run() {
                for (int i = 2; i <= 50; i += 2)
                {
	                while (!cond.getTurn());
	                try { Thread.sleep(1000); }
	                catch (InterruptedException e) {}
	                        
	                System.out.println("T2=" + i);
	                cond.setTurn(true);
                }
	        }
	    });
	    t1.start();
	    t2.start();
	}

	public static void main(String[] args) { new Alternation(); }
}