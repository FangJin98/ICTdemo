
public class factory {
	private int id;
	private int maxCapacity;
	private int remainCapacity;
	private String name;
	private double longitude;
	private double latitude;
	
	public factory(int id,String name) {
		this.id=id;
		this.name=name;
		this.latitude=-1;
		this.longitude=-1;
		this.maxCapacity=ConstValues.MAX_BARREL*ConstValues.TON_PER_BARREL;
		this.remainCapacity=this.maxCapacity;
	}
	
	public factory(int id,String name,double latitude,double longitude) {
		this.id=id;
		this.name=name;
		this.latitude=latitude;
		this.longitude=longitude;
		this.maxCapacity=ConstValues.MAX_BARREL*ConstValues.TON_PER_BARREL;
		this.remainCapacity=this.maxCapacity;
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
	
}
