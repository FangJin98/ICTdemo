import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Solve {
	public int facNum;
	public ArrayList<factory> facList=new ArrayList<factory>();
	public vehicleManager vManager=new vehicleManager();
	public double Dis[][];
	
	public void init() {
		try {
		        String encoding="GBK";
		        File file=new File("factoryData.txt");
		        if(file.isFile() && file.exists()){ //判断文件是否存在
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(file),encoding);
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String lineTxt = null;
		            int i=0;
		            while((lineTxt = bufferedReader.readLine()) != null){
		            	String[] data=lineTxt.split(" ");
			            facList.add(new factory(i,data[0],Double.parseDouble(data[1]),Double.parseDouble(data[2])));
			            i++;
		            }
		            read.close();
		}else{
		    System.out.println("找不到指定的文件");
		}
		} catch (Exception e) {
		    System.out.println("读取文件内容出错");
		    e.printStackTrace();
		}
		vManager.init();
		facNum=facList.size();
		Dis=new double[facNum+2][facNum+2]; 
		calDisArray();
	}
	
	public int selectVehicle(Task task) {		//选择合适的车辆
		int facId=task.getFacId();
		double minDis=ConstValues.INF;
		int vId=-1;
		Set<vehicle> vSet1=vManager.getInWorkingVehicles();
		Set<vehicle> vSet2=vManager.getNoWorkVehicles();
		
		for(vehicle v:vSet2) {	//没有执行任务的车，包括返程的或者在基地的
			double tmpDis=calDis(v.getLatitude(), v.getLongitude(),facList.get(facId).getLatitude(),facList.get(facId).getLongitude());
			if(tmpDis<minDis) {
				minDis=tmpDis;
				vId=v.getId();
			}
		}
		if(vId==-1) {	//车子都在执行任务
			for(vehicle v:vSet1) {
				if(v.satisfy(task)) {		//满足限制
					double tmpDis=calDis(v.getLatitude(), v.getLongitude(),facList.get(facId).getLatitude(),facList.get(facId).getLongitude());
					if(tmpDis<minDis) {
						minDis=tmpDis;
						vId=v.getId();
					}
				}
			}
		}
		
		return vId;
	}

	private void calDisArray() {
		for(int i=0;i<facNum;i++) {
			for(int j=0;j<facNum;j++) {
				double lat1=facList.get(i).getLatitude(),lng1=facList.get(i).getLongitude();
				double lat2=facList.get(j).getLatitude(),lng2=facList.get(j).getLongitude();
				Dis[i][j]=calDis(lat1,lng1,lat2,lng2);
				System.out.println(Dis[i][j]);
			}
		}
	}

	public static double calDis(double lat1,double lng1,double lat2,double lng2) {
		double radLat1 = lat1*Math.PI/180.0;
		double radLat2 = lat2*Math.PI/180.0;
		double a = radLat1 - radLat2;// 两点纬度差
		double b = lng1*Math.PI/180.0 - lng2*Math.PI/180.0;// 两点的经度差
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * 6378.137;
		return s;
	}

	
}
