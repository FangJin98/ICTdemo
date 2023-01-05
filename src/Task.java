
public class Task {
	private int facId;
	private double facLatitude;
	private double facLongitude;
	private int weight;
	private int time;
	public Task(int facId,double latitude,double longitude,int weight) {
		this.facId=facId;
		this.weight=weight;
		this.facLatitude=latitude;
		this.facLongitude=longitude;
		this.time=ConstValues.FILL_SPEED*weight;		//预计时间(min)
	}
	
	public Task(factory f,int weight) {
		this.facId=f.getId();
		this.weight=weight;
		this.facLatitude=f.getLatitude();
		this.facLongitude=f.getLongitude();
		this.time=ConstValues.FILL_SPEED*weight;		//预计时间(min)
	}
	public int getFacId() {
		return facId;
	}
	public int getWeight() {
		return weight;
	}
	
	public int getTimeLimit() {
		return time;
	}
	
	public double getLatitude() {
		return facLatitude;
	}
	
	public double getLongitude() {
		return facLongitude;
	}
}
