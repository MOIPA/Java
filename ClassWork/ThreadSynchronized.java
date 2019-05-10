
class common{
	private char ch;
	private boolean available = false;
	synchronized char get(){
		while(available==false)
			try{
				wait();
			}catch(InterruptedException e){}
		available =false;
		notify();
		return ch;
	}
	synchronized void put(char newch){
		while(available==true)
			try{
				wait();
			}catch(InterruptedException e){}
		ch = newch;
		available = true;
		notify();
	}
}

class producer extends Thread{
	private common comm;
	public producer(common thiscomm){
		comm = thiscomm;
	}
	public void run(){
		char c ;
		for(c = 'a';c<='e';c++){
			System.out.println("�����������ǣ�"+c);
			comm.put(c);
		}
	}
}

class consumer extends Thread{
	private common comm;
	public consumer(common thiscomm){
		comm = thiscomm;
	}
	public void run(){
		char c;
		for(int i=0;i<5;i++){
			c = comm.get();
			System.out.println("�����ߵõ��������ǣ�"+c);
		}
	}
}

public class ThreadSynchronized {
	public static void main(String[] args) {
		common comm = new common();
		producer p = new producer(comm);
		consumer c = new consumer(comm);
		p.start();
		c.start();
	}
}
