
public class source {
	public static final int vehicleMaxNum=8;
	private int id;
	private String name;
	private int remainVehicleNum;
	private double longitude;
	private double latitude;
	public source(String name, double longitude, double latitude) {
		this.name=name;
		this.latitude=latitude;
		this.longitude=longitude;
		remainVehicleNum=vehicleMaxNum;
		id=0;
	}
}
