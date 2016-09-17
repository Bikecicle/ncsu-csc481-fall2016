package part2;

/**
 * Changes I made:
 * 
 * - Added another thread i=1 and other=t1: Both print out
 * waiting but then only one does a second time and only one finishes
 * 
 * - Changing notify() to notifyAll() (line 42) allows both threads to finish
 * 
 * - Moving the thread.sleeps into synchronized blocks as suggested prevents "Waiting" print
 * outs
 * 
 * - Removing synchronized status from the second synchronized block causes
 * a IllegalMonitorStateException and completely unpredictable behavior in the
 * other two
 * 
 * @author Griffin
 *
 */

// Demonstrating multithreading and thread synchronization in Java
public class ForkExample implements Runnable {

	int i; // the ID of the thread, so we can control behavior
	boolean busy; // the flag, Thread 1 will wait until Thread 0 is no longer
					// busy before continuing
	ForkExample other; // reference to the other thread we will synchronize on.
						// This is needed so we can control behavior.
	String name;

	// create the runnable object
	public ForkExample(int i, ForkExample other, String name) {
		this.i = i; // set the thread ID (0 or 1)
		if (i == 0) {
			busy = true;
		} // set the busy flag so Thread 1 waits for Thread 0
		else {
			this.other = other;
		}
		this.name = name;
	}

	// synchronized method to test if thread is busy or not
	public synchronized boolean isBusy() {
		return busy;
	} // What happens if this isn't synchronized?

	// run method needed by runnable interface
	public void run() {
		if (i == 0) { // 1st thread, sleep for a while, then notify threads
						// waiting
			try {
				synchronized (this) {
					Thread.sleep(4000); // What happens if you put this sleep
										// inside the synchronized block?
					notify(); // notify() will only notify threads waiting on
								// *this* object;

				}
				busy = false; // must synchronize while editing the flag
				notifyAll();

			} catch (InterruptedException tie) {
				tie.printStackTrace();
			}
		} else {
			while (other.isBusy()) { // check if other thread is still working
				System.out.println(name + " is Waiting!");
				// must sychnronize to wait on other object
				try {
					synchronized (other) {
						other.wait();
					}
				} // note we have synchronized on the object we are going to
					// wait on
				catch (InterruptedException tie) {
					tie.printStackTrace();
				}
			}
			System.out.println(name + " Finished!");
		}
	}

	public static void main(String[] args) {
		ForkExample t1 = new ForkExample(0, null, "t1");
		ForkExample t2 = new ForkExample(1, t1, "t2");
		ForkExample t3 = new ForkExample(1, t1, "t3");
		(new Thread(t1)).start();
		(new Thread(t2)).start();
		(new Thread(t3)).start();
	}

}