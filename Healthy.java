package plague;
import java.util.Random;

public class Healthy extends Individual {
	
	boolean immune;
	
	public Healthy(){
		status = "healthy";
		immune = false;
	}
	
	public boolean get_infected(){
		if (immune== true)
			return false;
		Random rand = new Random();
        int random = rand.nextInt(100);
        int Percentage = Main.S;
        if (random > Percentage)
        {
            return false;
        }else
            {return true;}
	}

	


}
