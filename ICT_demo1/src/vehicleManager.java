import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class vehicleManager {
	Set<vehicle> noWorkVehicles;
	Set<vehicle> inWorkingVehicles;
	
	public vehicleManager() {
		noWorkVehicles=new HashSet<vehicle> ();
		inWorkingVehicles=new HashSet<vehicle> ();
	}
	public void init(){
		try {
	        String encoding="GBK";
	        File file=new File("vehicleData.txt");
	        if(file.isFile() && file.exists()){ //判断文件是否存在
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file),encoding);
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            int i=0;
	            while((lineTxt = bufferedReader.readLine()) != null){
	            	String[] data=lineTxt.split(" ");
		            noWorkVehicles.add(new vehicle(i,data[0],Double.parseDouble(data[1]),Double.parseDouble(data[2])));
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
	}
	
	public Set<vehicle> getNoWorkVehicles(){
		return this.noWorkVehicles;
	}
	
	public Set<vehicle> getInWorkingVehicles(){
		return this.inWorkingVehicles;
	}
	
	public void arrangeTask(vehicle v,Task t) {
		if(noWorkVehicles.contains(v)) {
			noWorkVehicles.remove(v);
			inWorkingVehicles.add(v);
			v.appendTask(t);
		}
		else {
			v.appendTask(t);
		}
	}
	public vehicle getVehicle(int i) {
		for(vehicle v:noWorkVehicles) {
			if(v.getId()==i) return v;
		}
		for (vehicle v:inWorkingVehicles) {
			if(v.getId()==i) return v;
		}
		return null;
	}
	
	public void finishTask(vehicle v,Task t) {
		v.finishTask(t);
		if(v.getIsWorking()==0) {		//任务数为0的车即可被优先考虑，不需要回基地
			inWorkingVehicles.remove(v);
			noWorkVehicles.add(v);
		}
	}
}
