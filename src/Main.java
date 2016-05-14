package plague;
import java.util.Random;

public class Main {
	
	public static int N;
	public static int S;
	public static int minDygn;
	public static int maxDygn;
	public static int L;
	public static Individual[] p_init;
	
	public static int day=0;
	
	public static void main(String[] args){
		N = 50;
		S = 10;
		minDygn = 5;
		maxDygn = 8;
		L = 10;
		
		Location l1 = new Location(1,5);
		Location l2 = new Location(15,9);
		Location l3 = new Location(7,19);
		Location l4 = new Location(48,5);
		Location l5 = new Location(36,40);
		Location[] a = {l1,l2,l3};
		World world = new World(20,a);
		
		int ran = new Random().nextInt(10);
		System.out.println(ran);
		System.out.println(world);
		
	}
}
