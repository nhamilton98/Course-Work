import java.util.concurrent.BrokenBarrierException;


public class Worker implements Runnable {

	private MyCyclicBarrier myb;
	private final int NUM = 15;
	
	public Worker(MyCyclicBarrier barrier) {
		this.myb = barrier;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int arriveOrder = 0;
		for(int i = 0; i < NUM; i ++) {

			System.out.println(Thread.currentThread().getName() + "-->" + 
					"message output " + i );
			if(i % 5 == 0){
				try {
					arriveOrder = myb.await();
				} catch (BrokenBarrierException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "-->" + 
				" WAKE UP WITH ARRIVE RANK " + arriveOrder);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end of for
	}
}