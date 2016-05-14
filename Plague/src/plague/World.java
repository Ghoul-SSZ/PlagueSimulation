package plague;

public class World {
	
	public Individual[][] world;
	public int N;
	
	public int healthy_count=0;
	public int sick_count=0;
	public int death_count=0;
	
	// world of healthy people
	public World(int N){
		world = new Individual[N][N];
		for(int i =0 ; i<N; i++)
			for(int j=0; j<N; j++)
				world[i][j]= new Healthy();
		this.N = N;
	}
	
	// world with a few sick people
	public World(int N, Location[] array){
		
		this.N = N;
		world = new Individual[N][N];
		for(int i =0 ; i<N; i++){
			for(int j=0; j<N; j++){
				world[i][j]= new Healthy();}
		}
		for(int i = 0; i< array.length; i++){
			
			int row = array[i].row;
			int column = array[i].column;
			world[row][column]= new Sick();
			
		}
	}
	
	public void reset_count(){
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
					sick_count++;
					if(s.infect_day != Main.day)
					{
						if (s.end_day==Main.day){
							world[i][j]= new Healthy(true);
						}
						else {
							if (s.is_killed()==true)
							{
								world[i][j]= new Dead();
							}
							else
								local_spread(i,j);
						}
					}
				}
				else if(world[i][j].status== "healthy")
					healthy_count++;
				else if(world[i][j].status== "dead")
					death_count++;
//				System.out.print(world[i][j]+" ");
			}
//			System.out.println();
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
