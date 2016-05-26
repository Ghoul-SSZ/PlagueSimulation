package plague;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
	
	public static int N;
	public static int S;
	public static int minDygn;
	public static int maxDygn;
	public static int L;
	public static int day=0;
	public static LinkedList<Location> ll = new LinkedList<Location>();
	public static boolean[] display = new boolean[7];




	public static void main(String[] args){
		
		init();
		select_print();
		World world = new World(N,ll);

		do{
			world.reset_count();
			day ++;
			world.spread();
			System.out.println("\n=====================================================" );
			System.out.println("DAY:\t" +day);
			if(display[0]){System.out.println("1.Number infected every day :\t\t" + world.daily_S);}
			if(display[1]){System.out.println("2.Number who died every day :\t\t" + world.daily_D);}
			if(display[2]){System.out.println("3.Number recovered every day :\t\t"+ world.daily_R);}
			if(display[3]){System.out.println("4.Number of sick every day :\t\t"+ world.sick_count);}
			if(display[4]){System.out.println("5.Accumulated number of infected every day :\t"+ world.total_sick);}
			if(display[5]){System.out.println("6.Accumulated number of deaths every day :\t"+ world.death_count);}
			if(display[6]){System.out.println(world);}
		}	while(world.not_finished());	
		
	}




	
	public static void init(){
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Select 1. personal setting else.default setting");
		if (sc.hasNextInt()&&sc.nextInt()==1)
		{
			System.out.print("Enter size: \t");
			N = sc.nextInt();
			System.out.println();

			System.out.print("Contagious(in percentage): \t");
			S = sc.nextInt();
			System.out.println();

			System.out.print("Minimal day: \t");
			minDygn = sc.nextInt();
			System.out.println();

			System.out.print("Maximum day: \t");
			maxDygn = sc.nextInt();
			System.out.println();

			System.out.print("Death rate(in percentage): \t");
			L = sc.nextInt();
			System.out.println();

			int temp = 0;
			ListIterator<Location> li = ll.listIterator();
			while(true){
				temp ++;
				System.out.print("Enter Location " +temp+" row:\t");
				if(!sc.hasNextInt()) {break;}
				int row = sc.nextInt();
				while(row>=N){
					if(!sc.hasNextInt()) {break;}
					row = sc.nextInt();
					System.out.print("please enter a valid location :\t");
				}
				System.out.println();


				System.out.print("Enter Location " +temp+" column:\t");
				if(!sc.hasNextInt()) {break;}
				int column = sc.nextInt();
				while(column>=N){
					if(!sc.hasNextInt()) {break;}
					column = sc.nextInt();
					System.out.print("please enter a valid location :\t");
				}
				System.out.println();
				
				li.add(new Location(row,column));				
			}
		}		
		else{
			df();
		}


	}

	public static void select_print(){
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Number infected every day");
		System.out.println("2.Number who died every day");
		System.out.println("3.Number of individuals who recover every day");
		System.out.println("4.Number of sick every day");
		System.out.println("5.Accumulated number of infected every day");
		System.out.println("6.Accumulated number of deaths every day");
		System.out.println("7.Show graph");
		System.out.println("Choose the services that you want to print:"); 

		String data = sc.nextLine();
		String[] pieces = data.split("\\s+");

		for(int i = 0; i< 7; i++){
			for(int j = 0; j<pieces.length;j++)
			{
				if (pieces[j].equals(Integer.toString(i+1))) {display[i] = true;}
			}
		}	
	}

	public static void df(){
		N = 20;
		S = 10;
		minDygn = 3;
		maxDygn = 7;
		L = 10;
		Location l1 = new Location(3,15);
		Location l2 = new Location(10,10);
		Location l3 = new Location(5,4);
		ListIterator<Location> li = ll.listIterator();
		li.add(l1);
		li.add(l2);
		li.add(l3);		
	}
}
