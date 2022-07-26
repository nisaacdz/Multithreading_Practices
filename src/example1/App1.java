package example1;

public class App1 {

	Object o = new Object();
	private int count = 0;

	public static void main(String[] args) {
		App1 app = new App1();
		app.doWork();
	}

	private void add() {
		synchronized (o) {
			count++;
		}
	}

	private void doWork() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					add();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					add();
				}
			}
		});

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Count is : " + count);
	}
}
