import java.util.Scanner;

public class test {
	private static Solve solve=new Solve();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		solve.init();
		while(true) {
			int tmp=sc.nextInt();
			if(tmp!=0) {
				int tmpid=sc.nextInt();
				int weight=sc.nextInt();
				Task newtask=new Task(solve.getFactory(tmpid),weight);
				int tmpId=solve.selectVehicle(newtask);
				System.out.println(tmpId);
				solve.arrangeTask(solve.getVehicle(tmpId),newtask);
			}
			else {
				System.out.println("!!!");
			}
		}
	}
}
