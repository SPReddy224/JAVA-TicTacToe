package com.java.isi.Tictoctoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.lang.Math;

import com.isi.Airplane.model.InvalidActionException;









//import jdk.jfr.internal.test.WhiteBox;


public class TicTocToe extends JFrame{


	//private Adapter adapter;

	private static JButton[][] buttons;
	private static JButton reSet;
	private static JLabel playerTurn;
	private static JLabel xWonTheGameLabel;
	private static JLabel OWonTheGameLabel;
	private static JLabel xWonTheGameLabelVal;
	private static JLabel OWonTheGameLabelVal;

	private static JPanel contentPane;
	private static JPanel gamePanel;
	private static JPanel messagePanel;
	private static JPanel resetPanel;
	private static JPanel winCountPanel;
	private static JPanel XPanel;
	private static JPanel OPanel;

	int bordSizeGlobal;
	int count=0;
	private static String arr[] = {};
	private static int playerID;
	private static int mode;
	private static int xWinningTimes=0;
	private static int oWinningTimes=0;
	public TicTocToe(int boardSize,int mode) {
		// TODO Auto-generated constructor stub
		super("TicTocToe");
		this.mode=mode;
		this.buttons=new JButton[boardSize][boardSize];
		this.reSet=new JButton("Reset");

		//this.playerTurn=new JTextArea().setBackground(Color.white);
		bordSizeGlobal = boardSize;
		createPanels(boardSize,boardSize);
		createComponents(boardSize);
		addCompomemtsToPanels();
		setActionListenersToButtons(boardSize);



		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();//auto window size according to the elements
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}

	private void createPanels(int dimension1,int dimension2) {
		// TODO Auto-generated method stub
		contentPane=(JPanel) getContentPane();


		gamePanel=new JPanel();
		messagePanel=new JPanel();
		resetPanel=new JPanel();
		XPanel = new JPanel();
		OPanel = new JPanel();
		winCountPanel=new JPanel();


		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		winCountPanel.setLayout(new BoxLayout(winCountPanel, BoxLayout.Y_AXIS));
		winCountPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		GridLayout gridLayout=new GridLayout(dimension1, dimension2);
		gamePanel.setLayout(new GridLayout(dimension1, dimension2));


		messagePanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
		messagePanel.setLayout(new BoxLayout(messagePanel,BoxLayout.Y_AXIS));




		resetPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,20));
		resetPanel.setLayout(new BoxLayout(resetPanel,BoxLayout.Y_AXIS));




	}
	public  void createComponents(int dimension) {
		// TODO Auto-generated method stub


		for(int row = 0; row<dimension ;row++) {
			for(int column = 0; column<dimension ;column++) {
				buttons[row][column] = new JButton();
				buttons[row][column].setPreferredSize(new Dimension(75,75));
				buttons[row][column].setText("");
				buttons[row][column].setName(row+":"+column);
				gamePanel.add(buttons[row][column]);

			}
		}
		playerTurn = new JLabel("Player X Should Make Move ");

		xWonTheGameLabel=new JLabel("X Winnig Times :");
		OWonTheGameLabel=new JLabel("O Winning Times :" );
		xWonTheGameLabelVal=new JLabel("0");
		OWonTheGameLabelVal=new JLabel("0");

		reSet.setAlignmentX(0.5f);
		reSet.setHorizontalAlignment(SwingUtilities.CENTER);
		xWonTheGameLabel.setAlignmentX(0.5f);
		xWonTheGameLabel.setHorizontalAlignment(SwingUtilities.LEFT);
		OWonTheGameLabel.setAlignmentX(0.5f);
		OWonTheGameLabel.setHorizontalAlignment(SwingUtilities.RIGHT);
		xWonTheGameLabelVal.setAlignmentX(0.5f);
		xWonTheGameLabelVal.setHorizontalAlignment(SwingUtilities.RIGHT);
		playerTurn.setAlignmentX(0.5f);
		playerTurn.setHorizontalAlignment(SwingUtilities.CENTER);
		setVisible(true);

	}
	public void addCompomemtsToPanels() {

		messagePanel.add(playerTurn,CENTER_ALIGNMENT);
		resetPanel.add(reSet,CENTER_ALIGNMENT);

		XPanel.add(xWonTheGameLabel);
		XPanel.add(xWonTheGameLabelVal);

		OPanel.add(OWonTheGameLabel);
		OPanel.add(OWonTheGameLabelVal);

		winCountPanel.add(XPanel);
		winCountPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		winCountPanel.add(OPanel);
		winCountPanel.add(Box.createRigidArea(new Dimension(10, 0)));


		contentPane.add(gamePanel);
		contentPane.add(messagePanel);
		contentPane.add(winCountPanel);
		contentPane.add(resetPanel,CENTER_ALIGNMENT);


	}
	public void setActionListenersToButtons(int boardSize) {
		reSet.addActionListener((ActionEvent e) ->
		{
			reset(boardSize);
		});
		for(int i=0;i<boardSize;i++) {
			for(int j=0;j<boardSize;j++) {
				buttons[i][j].addActionListener((ActionEvent e) -> {
					try {
						//	checkWinCodition(boardSize,buttons);

						JButton button=(JButton)e.getSource();
						String row=button.getName();
						String[] coordinates =row.split(":");
						int row1=Integer.parseInt(coordinates[0]);
						int collum=Integer.parseInt(coordinates[1]);


						if(count%2==0) {
							playerTurn.setText("Player X Should Make Move");
						}
						else {
							playerTurn.setText("Player O Should Make Move");
						}


						if(button.getText()=="") {
							if(count%2==0) {
								playerTurn.setText("Player O Should Make Move");
								buttons[row1][collum].setText("X");
								buttons[row1][collum].setEnabled(false);
								count++;
								if(isWinner(buttons,"X") ){
									declareWinner("X");
									xWinningTimes=xWinningTimes+1;
									xWonTheGameLabelVal.setText(String.valueOf(xWinningTimes));
								}
								if(count==buttons.length*buttons.length && !isWinner(buttons, "X")) {
									draw("The Match Ended Up In A Draw");

								}
								if(mode==2 && count%2!=0  ) {
									playerTurn.setText("Player X Should Make Move");
									JButton AIbutton=getComputerMove();
									AIbutton.setText("O");
									AIbutton.setEnabled(false);
									count++;
									if(isWinner(buttons,"O") ){
										declareWinner("O");
										oWinningTimes=oWinningTimes+1;
										OWonTheGameLabelVal.setText(String.valueOf(oWinningTimes));
									}
								}
							}
							else if(mode==1 && count%2!=0 ){
								playerTurn.setText("Player X Should Make Move");
								button.setText("O");
								button.setEnabled(false);
								count++;
								if(isWinner(buttons,"O") ){
									declareWinner("O");
									oWinningTimes=oWinningTimes+1;
									OWonTheGameLabelVal.setText(String.valueOf(oWinningTimes));
								}
							}
						}

					}
					catch (Exception e1) {
						System.out.println(e1.getMessage());
					}

				});
			}
		}
	}
	private JButton getComputerMove() {
		// TODO Auto-generated method stub
		JButton[][] buttons_copy=copyButtondTobuttons_copy(buttons);
		//Check If Winning Move Is Possible
		int con=0;
		for(int i=0;i<buttons.length;i++) {
			for(int j=0;j<buttons.length;j++) {

				if(buttons_copy[i][j].getText()!="X" && buttons_copy[i][j].getText()!="O" ) {
					buttons_copy[i][j].setText("O");
					if(isWinner(buttons_copy,"O")) {
						return buttons_copy[i][j];
					}
					else {
						buttons_copy[i][j].setText("");
					}
				}

			}
		}
		//Check If Blocking Move Is Possible
		for(int i=0;i<buttons.length;i++) {
			for(int j=0;j<buttons.length;j++) {
				if(buttons_copy[i][j].getText()!="X" && buttons_copy[i][j].getText()!="O" ) {
					buttons_copy[i][j].setText("X");
					if(isWinner(buttons_copy,"X")) {
						return buttons_copy[i][j];
					}
					else {
						buttons_copy[i][j].setText("");
					}
				}

			}
		}
		//Choose a corner if available 
		if(buttons_copy[0][0].getText() != "X" && buttons_copy[0][0].getText() != "O") {
			return buttons_copy[0][0];
		}	

		if(buttons_copy[buttons_copy.length-1][buttons_copy.length-1].getText() != "O" && buttons_copy[buttons_copy.length-1][buttons_copy.length-1].getText() != "X") {
			return buttons_copy[buttons_copy.length-1][buttons_copy.length-1];
		}

		if(buttons_copy[0][buttons_copy.length-1].getText() != "X" && buttons_copy[0][buttons_copy.length-1].getText() != "O") {
			return buttons_copy[0][buttons_copy.length-1];
		}

		if(buttons_copy[buttons_copy.length-1][0].getText() != "X" && buttons_copy[buttons_copy.length-1][0].getText() != "O") {
			return buttons_copy[buttons_copy.length-1][0];
		}
		else {
			return generateRandomAiMove();

		}


	}

	private JButton[][] copyButtondTobuttons_copy(JButton[][] buttons2) {
		// TODO Auto-generated method stub
		for(int i=0;i<buttons.length;i++) {
			for(int j=0;j<buttons.length;j++) {
				buttons2[i][j]=buttons[i][j];
			}
		}
		return buttons2;
	}




	public boolean isWinner(JButton[][] button,String userId) {
		int countRDiag = 0;
		int countLDiag = 0;
		
		
		for(int i=0;i<button.length;i++) {
			int countRow = 0;
			for(int j=0;j<button.length;j++) {
				if(button[i][j].getText()== userId) 
					countRow++;
				if(countRow==button.length)
					return true;
			}

		}
		for(int i=0;i<button.length;i++) {
			int countCol = 0;
			for(int j=0;j<button.length;j++) {
				if(button[j][i].getText()== userId) 
					countCol++;
				if(countCol==button.length)
					return true;
			}

		}
		for(int i=0;i<button.length;i++) {

			//for(int j=0;j<button.length;j++) {

			if(button[i][i].getText() == userId) {
				countRDiag++;
				if(countRDiag==button.length)
					return true;
				//}
			}
		}
			for(int i=0;i<button.length;i++) {

				if(button[button.length-1-i][i].getText()== userId) 
					countLDiag++;
				if(countLDiag==button.length)
					return true;
			}


			return false;
		}



		public void reset(int boardSize ) {
			playerTurn.setText("Player X Should Make Move");
			count=0;
			for(int row = 0; row<boardSize ;row++) {
				for(int column = 0; column<boardSize ;column++) {
					buttons[row][column].setEnabled(true);
					buttons[row][column].setText("");
					count=0;
					gamePanel.setEnabled(true);

				}
			}


		}
		public void declareWinner(String userId) {
			String message="The Player "+userId + "Has Won The Game";
			JOptionPane.showMessageDialog(this, message,"Error",JOptionPane.WARNING_MESSAGE);
			gamePanel.setEnabled(false);
			count=0;
			reset(buttons.length);

		}
		public void draw(String message) {
			count=0;
			JOptionPane.showMessageDialog(this, message,"Error",JOptionPane.WARNING_MESSAGE);
			gamePanel.setEnabled(false);
			reset(buttons.length);

		}




		public JButton generateRandomAiMove() {
			int randomRowValue,randomColValue;
			Random random=new Random();
			boolean found=false;
			randomRowValue=random .nextInt(3);
			randomColValue=random .nextInt(3);



			for(int i=0;i<buttons.length;i++) {

				for(int j=0;j<buttons.length;j++) {
					
					randomRowValue=random .nextInt(3);
					randomColValue=random .nextInt(3);
					String checkText=buttons[randomRowValue][randomColValue].getText();
					if(checkText.isEmpty()) {
						found=true;
						return buttons[randomRowValue][randomColValue];
						
					}
					if(found==true) {
						break;
					}
				}
			}
			return buttons[randomRowValue][randomColValue];

		}



	}
