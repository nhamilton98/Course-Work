public class MyBarrierTester {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int numThr = 3;
		LastCommand lc = new LastCommand();
		MyCyclicBarrier myb = new MyCyclicBarrier(numThr,lc);
		
		Thread pool[] = new Thread[numThr];
		for(int i=0; i < numThr; i ++ ) {
			pool[i] = new Thread(new Worker(myb));
		}
		for(int i=0; i < numThr; i ++ ) {
			pool[i].start();
		}
		for(int i=0; i < numThr; i ++ ) {
			pool[i].join();
		}
	}
}