package plague;

import java.util.Random;

public class Infected extends Individual {
	
	public int sick_days;
	public int end_day;
	
	
	public Infected(){
		status = "sick";
		sick_days = gen_days(Main.minDygn,Main.maxDygn);
		end_day = Main.day+sick_days;
	}
	
	public boolean is_killed(){
		Random rand = new Random();
        int random = rand.nextInt(100);
        int Percentage = Main.L;
        if (random > Percentage)
        {
            return false;
        }else
            {return true;}
		
	}
	public int gen_days(int min, int max)
    {
        int diff = max-min;
        Random rec = new Random();
        int random= rec.nextInt(diff);
        int randomDays = random + min;
        
        return randomDays;
    }

}
