public class MyPrimeTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		if (args.length < 3) {
			System.out.println("Usage: MyPrimeTest numThread low high \n");
			return;
		}
		int nthreads = Integer.parseInt(args[0]);
		int low = Integer.parseInt(args[1]);
		int high = Integer.parseInt(args[2]);
		Counter c = new Counter();
		
		//test cost of serial code
		long start = System.currentTimeMillis();
		int numPrimeSerial = SerialPrime.numSerailPrimes(low, high);
		long end = System.currentTimeMillis();
		long timeCostSer = end - start;
		System.out.println("Time cost of serial code: " + timeCostSer + " ms.");
		
		//test of concurrent code
		// **************************************
        // Write me here
		ThreadPrime[] threads = new ThreadPrime[nthreads];
		int segSize = (high - low) / nthreads;
		int tLow = low, tHigh = low + segSize;
		start = System.currentTimeMillis();
		for (int i = 0; i < nthreads; i++)
		{
			ThreadPrime t = new ThreadPrime(tLow, tHigh, c);
			threads[i] = t;
			t.start();
			tLow = tHigh + 1;
			tHigh += segSize + 1;
		}
		for (ThreadPrime t: threads)
			t.join();
		end = System.currentTimeMillis();
		long timeCostCon = end - start;
		
		// **************************************
		System.out.println("Time cost of parallel code: " + timeCostCon + " ms.");
		System.out.format("The speedup ration is by using concurrent programming: %5.2f. %n", (double)timeCostSer / timeCostCon);
		
		System.out.println("Number prime found by serial code is: " + numPrimeSerial);
		System.out.println("Number prime found by parallel code is " + c.total());
	}
		

}
