package plague;

public class Individual {
//	public Location location;
	public String status;
	
	public String toString(){
		switch (status){
			case "healthy":
				return "-";
			case "immune":
				return "I";
			case "dead":
				return "*";
			case "sick":
				return "S";
			default:
				return null;
		}
	}
}
