import java.util.Scanner;

class Student{
	private String password;
	protected String name;
	public Student (String name_,String password_){
		name = name_;
		password = password_;
	}
	public void show(){
		System.out.println(name+"\n"+password);
	}
}
public class test5 {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String name,password;
		System.out.println("�������֣�");
		name = reader.nextLine();
		do{
			System.out.println("��������:");
			password = reader.nextLine();
		}while(password.length()>16||password.length()<6);
		Student stu = new Student(name,password);
		stu.show();
		
	}
}
