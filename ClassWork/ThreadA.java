
public class ThreadA extends Thread{
	int count = 1;
	int num;
	public ThreadA(int newNum){
		num = newNum;
		System.out.println("�����߳�"+num);
	}
	public void run(){
		while(true){
			System.out.println("�߳�"+num+":����"+count);
			count++;
			if(count==3)
				break;
		}
	}
	public static void main(String[] args) {
		ThreadA a1 = new ThreadA(1);
		ThreadA a2 = new ThreadA(2);
		ThreadA a3 = new ThreadA(3);
		a1.start();
		a2.start();
		a3.start();
		System.out.println("������main()���н�����");
	}
}
