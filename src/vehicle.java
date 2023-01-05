import java.util.ArrayList;

public class vehicle {
	
	private int id;
	private String name;
	private double longitude;
	private double latitude;
	private int isWorking;		//�����Ƿ��ڹ���
	private boolean isPacking;			//�����Ƿ������ೡװ��
	private boolean isInHome;				//�����Ƿ��ڻ���
	private ArrayList<Task> taskSerial;	//������Ҫ����Ĺ�������
	private int maxCapacity;		//���೵�������
	private int remainCapacity;		//���೵ʣ������
	
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
		int tmpTime=(int) tmpDis/ConstValues.VEHICLE_SPEED;		//����Ԥ��ʱ�� ·�̡��ٶ�
		if((tmpTime<=task.getTimeLimit())&&(remainCapacity>=task.getWeight())) return true;
		return false;
	}
	
	public void finishTask(Task t) {
		remainCapacity+=t.getWeight();
		isWorking--;
		taskSerial.remove(t);
	}
}
