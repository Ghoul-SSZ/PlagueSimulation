package plague;
import java.util.LinkedList;
import java.util.ListIterator;

public class World {
	
	public Individual[][] world;
	public int N;
	
	public int healthy_count=0;	
	public int sick_count=0;		// How many people are sick -> by calculating the board of sick people
	public int death_count=0;		// How many people are dead	-> by calculating the board of dead people
	public int daily_R = 0;			// How many people recovered today -> accumulating each time someone is recovered, reset every day
	public int daily_S = 0;			// How many people infected today	-> accumulating each time someone is infected, reset every day
	public int daily_D = 0;			// How many people died today	-> accumulating each time someone dies, reset every day
	public int total_sick;			// How many people have been sick -> initialize by how big array is, add newly infected every day
	
	// world of healthy people
	public World(int N){
		world = new Individual[N][N];
		for(int i =0 ; i<N; i++)
			for(int j=0; j<N; j++)
				world[i][j]= new Healthy();
		this.N = N;
	}
	
	// world with a few sick people
	public World(int N, LinkedList<Location> list){
		
		this.N = N;
		world = new Individual[N][N];
		for(int i =0 ; i<N; i++){
			for(int j=0; j<N; j++){
				world[i][j]= new Healthy();}
		}
		
		ListIterator<Location> li = list.listIterator();
		while(li.hasNext()){
			Location loc = li.next();
				world[loc.row][loc.column] = new Sick();	
		}
		total_sick = list.size();
		sick_count = total_sick;
	}
	
	public void reset_count(){
		total_sick += daily_S;
		daily_D = 0;
		daily_S = 0;
		daily_R = 0;
		healthy_count=0;
		sick_count=0;
		death_count=0;
	}
	
	public boolean not_finished(){
		if (sick_count==0)
			return false;
		else return true;
	}
	
	
	public void spread(){
		
		
		for(int i =0 ; i<N; i++){
			for(int j=0; j<N; j++){
				
				if (world[i][j].status== "sick")
				{
					Sick s = (Sick) world[i][j];
					if(s.infect_day != Main.day)
					{
						if (s.end_day==Main.day){
							world[i][j]= new Healthy(true);
							daily_R ++;
						}
						else {
							if (s.is_killed()==true)
							{
								world[i][j]= new Dead();
								daily_D ++;
							}
							else{
								local_spread(i,j);
								sick_count++;
							}
						}

					}
				}
				else if(world[i][j].status== "healthy")
					healthy_count++;
				else if(world[i][j].status== "dead")
					death_count++;

			}
		}
	}
	
	private void local_spread(int row, int column){
		Location[] array = get_neighbours(row, column);
		for(int i = 0 ; i<array.length; i++)
		{
			Location l = array[i];
			if (world[l.row][l.column].status=="healthy"){
				Healthy h = (Healthy) world[l.row][l.column];
				if (h.get_infected()== true)
				{
					world[l.row][l.column] = new Sick();
					daily_S ++;
				}
			}
		}
	}
	
	private Location[] get_neighbours(int row, int column){
		//upper left corner
		Location[] result;
		if (row == 0&& column ==0){
			Location[] array = {new Location(1,0) , new Location(1,1),new Location(0,1)};
			result = array;
		}
		else if (row == 0 && column == N-1){
			Location[] array = {new Location(0,N-2) , new Location(1,N-2),new Location(1,N-1)};
			result = array;
		}
		else if (row == N-1 && column == N-1){
			Location[] array = {new Location(N-2,N-2) , new Location(N-1,N-2),new Location(N-2,N-1)};
			result = array;
		}
		else if (row == N-1 && column == 0){
			Location[] array = {new Location(N-2,0) , new Location(N-2,1),new Location(N-1,1)};
			result = array;
		}else if (row == 0){
            Location[] array = {new Location(0,column-1) ,new Location(0,column+1) , new Location(1,column-1),new Location(1,column),new Location(0,column+1)};
            result = array;
        }else if (row==N-1){
            Location[] array = {new Location(N-1,column-1) ,new Location(N-1,column+1) , new Location(N-2,column-1),new Location(N-2,column),new Location(N-2,column+1)};
            result = array;
        }else if (column == 0){
            Location[] array = {new Location(row-1,0) ,new Location(row+1,0) , new Location(row-1,1),new Location(row,1),new Location(row+1,1)};
            result = array;
        }else if (column == N-1){
            Location[] array = {new Location(row-1,N-1) ,new Location(row+1,N-1) , new Location(row-1,column-1),new Location(row,column-1),new Location(row+1,column-1)};
            result = array;
        }
		else 
		{
            Location[] array = {new Location(row-1,column-1) ,new Location(row,column-1) , new Location(row+1,column-1),new Location(row-1,column),new Location(row+1,column),new Location(row+1,column+1),new Location(row,column+1),new Location(row-1,column+1)};
			result = array;
		}
		return result;
			
	}
	

	
	
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		
		for(int i =0 ; i<N; i++){
			
			for(int j=0; j<N; j++)
			{
				sb.append(world[i][j]+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
