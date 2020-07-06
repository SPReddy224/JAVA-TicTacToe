package com.java.isi.Tictoctoe;

import java.net.ServerSocket;
import java.util.*;
public class Main {
	static Scanner sc=new Scanner(System.in);
	
	static int boardChoice = 0;
	static int modeChoice = 0;
	static int choosePlayerId=0;
	
	public static void main(String[] args) {
		
		
	
		
		
		while(boardChoice==0)
		{
		
			System.out.println("Choose One Option From The Below");
			System.out.println("1 – 3*3\n2 - 4*4\n3 - 5*5");
			boardChoice=sc.nextInt();
		
			//int boardchoice = Integer.parseInt(string,boardChoice);
		
				switch(boardChoice)
				{
				case 1:
					new TicTocToe(3,chooseMode());
					boardChoice=0;
					break;
				case 2 :
					new TicTocToe(4,chooseMode());
					boardChoice=0;
					break;
				case 3:
					new TicTocToe(5,chooseMode());
					boardChoice=0;
					break;
				
				
					

				}
			/*	System.out.println("Choose Game Mode");
				System.out.println("1 - User and AI\n2 - Multiple users on one computer\n3 - Multiple users on network\n4 - Exit ");
				modeChoice=sc.nextInt();
				switch(modeChoice) {
				case 4:
					exit();
					break;
				
				}*/
				
				}
		

	}
public static int chooseMode() {
	System.out.println("Choose Game Mode");
	System.out.println("1 - User VS User\n2 -User VS AI ");
	modeChoice=sc.nextInt();
	switch(modeChoice) {
	case 1:
		return 1;
	case 2:
		return 2;
	
	}
	return 0;
}
	private static void exit() {
		// TODO Auto-generated method stub
		System.exit(0);
		
	}
	public static String setPlayerID() {
		 String playerID = "";
		 System.out.println("player Id :"+choosePlayerId);
		if(choosePlayerId==0) {
			System.out.println("Choose Options For Player 1");
			System.out.println("5 – X\n6 - O");
			Scanner test=new Scanner(System.in);
			choosePlayerId=test.nextInt();
			System.out.println(choosePlayerId+"after input");
		    switch (choosePlayerId) {
			case 5:
				  playerID= "X";
				  System.out.println("5");
				  choosePlayerId=1;
				  test.close();
				  break;
			case 6:
				 playerID= "O";
				 System.out.println("6");
				 choosePlayerId=1;
				 test.close();
				 break;
			}
		}
		
		return playerID;
	}
}
