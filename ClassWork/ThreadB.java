
public class ThreadB implements Runnable{
	int count = 1;
	int num;
	public ThreadB(int newNum){
		num = newNum;
		System.out.println("�����߳�"+num);
	}
	public void run(){
		while(true){
			System.out.println("�߳�"+num+":����"+count);
			count++;
			if(count == 3) break;
		}
	}
	public static void main(String[] args) {
		Thread a1 = new Thread(new ThreadB(1));
		Thread a2 = new Thread(new ThreadB(2));
		Thread a3 = new Thread(new ThreadB(3));
		a1.start();
		a2.start();
		a3.start();
		System.out.println("������main()���н�����");
	}
}
