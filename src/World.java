package plague;

public class World {
	
	public Individual[][] world;
	public int N;
	
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
			world[row][column]= new Infected();
			
		}
	}
	
	public void spread(){
		
	}
	
	private Location[] get_neighbours(int row, int column){
		//upper left corner
		Location[] result;
		if (row == 0&& column ==0){
			Location[] array = new Location[] {new Location(1,0) , new Location(1,1),new Location(0,1)};
			result = array;
		}
		else if (row == 0 && column == N-1){
			Location[] array = new Location[] {new Location(0,N-1) , new Location(1,N-1),new Location(1,N)};
			result = array;
		}
		else if (row == 0 && column == N-1){
			Location[] array = new Location[] {new Location(0,N-1) , new Location(1,N-1),new Location(1,N)};
			result = array;
		}
		
		else if (row == N-1 && column == N-1){
			Location[] array = new Location[] {new Location(N-1,N-1) , new Location(N,N-1),new Location(N-1,N)};
			result = array;
		}
		else if (row == N-1 && column == 0){
			Location[] array = new Location[] {new Location(N-1,0) , new Location(N-1,1),new Location(N,1)};
			result = array;
		}
		else 
		{
			result = null;
		}
		return result;
			
	}
	
	private Individual get_individual(Location location){
		return world[location.row][location.column];
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
