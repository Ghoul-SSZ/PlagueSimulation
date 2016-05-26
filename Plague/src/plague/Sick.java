package plague;

import java.util.Random;

public class Sick extends Individual {
	
	public int sick_days;
	public int end_day;
	public int infect_day;
	
	public Sick(){
		status = "sick";
		sick_days = gen_days(Main.minDygn,Main.maxDygn);
		end_day = Main.day+sick_days;
		infect_day = Main.day;
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
        int diff = max-min+1;
        Random rec = new Random();
        int random= rec.nextInt(diff);
        int randomDays = random-1 + min;
        
        return randomDays;
    }

}
