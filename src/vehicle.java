import java.util.ArrayList;

public class vehicle {
	
	private int id;
	private String name;
	private double longitude;
	private double latitude;
	private int isWorking;		//车辆是否在工作
	private boolean isPacking;			//车辆是否在运泥场装载
	private boolean isInHome;				//车辆是否在基地
	private ArrayList<Task> taskSerial;	//车辆需要运泥的工厂序列
	private int maxCapacity;		//运泥车最大容量
	private int remainCapacity;		//运泥车剩余容量
	
	public vehicle(int id,String name) {
		this.id=id;
		this.name=name;
		this.latitude=-1;
		this.longitude=-1;
		this.isPacking=false;
		this.isWorking=0;
		this.isInHome=true;
		this.taskSerial=new ArrayList<Task>();
		this.maxCapacity=ConstValues.MAX_CAPACITY*ConstValues.TON_PER_BARREL;
		this.remainCapacity=maxCapacity;
	}
	
	public vehicle(int id,String name,double latitude,double longitude) {
		this.id=id;
		this.name=name;
		this.latitude=latitude;
		this.longitude=longitude;
		this.isPacking=false;
		this.isWorking=0;
		this.isInHome=true;
		this.taskSerial=new ArrayList<Task>();
		this.maxCapacity=ConstValues.MAX_CAPACITY*ConstValues.TON_PER_BARREL;
		this.remainCapacity=maxCapacity;
	}
	
	public void setId(int id) { this.id=id;}
	
	public void setName(String name) {this.name=name;}
	
	public void setLongitude(double longitude) {this.longitude=longitude;}
	
	public void setLatitude(double latitude) {this.latitude=latitude;}
	
	public void setPosition(double longitude,double latitude) {
		this.longitude=longitude;
		this.latitude=latitude;
	}
	
	public int getId() {return id;}
	
	public String getName() {return name;}
	
	public double getLongitude() {return longitude;}
	
	public double getLatitude() {return latitude;}
	
	public int getIsWorking() {return isWorking;}
	
	public boolean getIsPacking() {return isPacking;}
	
	public int getMaxCapacity() {return maxCapacity;}
	
	public int getRemainCapacity() {return remainCapacity;}
	
	public ArrayList<Task> getSerial() {return taskSerial;}

	public void appendTask(Task t) {
		remainCapacity-=t.getWeight();
		isInHome=false;
		isWorking++;
		taskSerial.add(t);
	}

	public boolean satisfy(Task task) {
		double tmpDis=Solve.calDis(latitude,longitude,task.getLatitude(),task.getLongitude());
		int tmpTime=(int) tmpDis/ConstValues.VEHICLE_SPEED;		//粗略预估时间 路程÷速度
		if((tmpTime<=task.getTimeLimit())&&(remainCapacity>=task.getWeight())) return true;
		return false;
	}
	
	public void finishTask(Task t) {
		remainCapacity+=t.getWeight();
		isWorking--;
		taskSerial.remove(t);
	}
}
