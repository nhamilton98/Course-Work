public class Lab2 {

	public static void main(String[] args) throws InterruptedException
	{
		Thread waiter = new Thread(new Runnable()
		{
			public void run()
			{
				boolean displayed = false;
				while (!displayed)
				{
					if (Thread.interrupted())
					{
						System.out.println("This is the waiter thread; The printer thread is over halfway done!");
						displayed = true;
					}
				}
				System.out.println("Waiter has done its job. Now terminating.");
				return;
			}
		});
		
		Thread printer = new Thread(new Runnable()
				{
					public void run()
					{
						for (int i = 0; i < 50; i++)
						{
							if (i == 0)
								System.out.println("This is the printer thread, printing for the " + (i + 1) + "st time.");
							else if (i == 1)
								System.out.println("This is the printer thread, printing for the " + (i + 1) + "nd time.");
							else if (i == 2)
								System.out.println("This is the printer thread, printing for the " + (i + 1) + "rd time.");
							else
								System.out.println("This is the printer thread, printing for the " + (i + 1) + "th time.");
							
							if (i == 24)
								waiter.interrupt();
						}
						System.out.println("Printer thread is done. Now terminating.");
					}
				});
		
		printer.start();
		waiter.start();
		
		printer.join();
		waiter.join();
		
		System.out.println("Both threads are terminated. Terminating the program.");
	}
}
