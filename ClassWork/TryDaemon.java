
public class TryDaemon {
	public static void main(String[] args) {
		Thread t = new Thread(new DaemonThread());
		t.setDaemon(true);
		t.start();
		System.out.println("main()��������");
	}
}

class DaemonThread implements Runnable{
	public void run(){
		while(true){
			System.out.println("�߳���������");
		}
	}
}
