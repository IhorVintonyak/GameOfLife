import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner; 


public class GameOfLife extends JFrame implements ActionListener  { 

	private JLabel[] gridCell;
	private JButton step;
	public int cont=0;
	public Icon iconWhite = new ImageIcon(getClass().getResource("immagini/white.png"));
	public Icon iconBlack = new ImageIcon(getClass().getResource("immagini/black.png"));

	public GameOfLife(ArrayList<Integer> input) {
		super();
		creaGUI(input);
	}
  
	private void creaGUI(ArrayList<Integer> input) {
		
		System.out.println(input);
	

	  	JPanel grid = new JPanel();
	  	grid.setBackground(Color.GRAY); 
	  	this.add(grid, BorderLayout.CENTER);
	  	grid.setLayout(new GridLayout(8, 4, 5, 5));
		
	  	gridCell = new JLabel[64];
		for (int i = 0; i < 64; i++){
			if(chekValue(input, i)){
				gridCell[i] = new JLabel(iconBlack);
			}else{
				gridCell[i] = new JLabel(iconWhite);
			}
			grid.add(gridCell[i]);	
		}

		JPanel pannelloSud = new JPanel();
		pannelloSud.setBackground(Color.WHITE);
		this.add(pannelloSud, BorderLayout.SOUTH);
		pannelloSud.setLayout(new GridLayout(1, 4, 30, 30));

		step = new JButton("STEP");
		pannelloSud.add(step);
		step.addActionListener(this);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("JPanel demo...");
		setSize(800, 800);
		setVisible(true); 
		
    }
	


	public void actionPerformed(ActionEvent e) {
		for (int i=0;i<64;i++){
			controlColor(gridCell[i],i);
		}
		cleanGrid();
	}

	public void controlColor(JLabel cell, int i){
		if(cell.getIcon()==iconBlack){
			controlBlack(cell,i);
		}else if(cell.getIcon()==iconWhite){
			controlWhite(cell,i);
		}
	} 


	public void controlBlack(JLabel cell, int i){
		int sumOfBlack = calcSumOfBlack(i);
		if(sumOfBlack<2 || sumOfBlack>3){
			gridCell[i].setText("0");
		}else{
			gridCell[i].setText("1");
		}
	}


	public void controlWhite(JLabel cell, int i){

		int sumOfBlack=calcSumOfBlack(i);
		if(sumOfBlack==3){
			gridCell[i].setText("1");
		}else{
			gridCell[i].setText("0");
		}

	}


	public void cleanGrid(){
		for (int i = 0; i < 64; i++){
			if(gridCell[i].getText()=="0"){
				gridCell[i].setIcon(iconWhite);
			}else{
				gridCell[i].setIcon(iconBlack);
			}
			gridCell[i].setText("");
		} 
	}


	public int calcSumOfBlack(int i){
		int sumOfBlack = 0;
		if (i-9>=0  && intDivision(i,8)==(intDivision(i-9,8)+1)){
			if(gridCell[i-9].getIcon()==iconBlack){
				sumOfBlack++;
			}
		}
		if (i-8>=0  && intDivision(i,8)==(intDivision(i-8,8)+1)){
			if(gridCell[i-8].getIcon()==iconBlack){
				sumOfBlack++;
			}
		}
		if (i-7>=0  && intDivision(i,8)==(intDivision(i-7,8)+1)){
			if(gridCell[i-7].getIcon()==iconBlack){
				sumOfBlack++;
			}
		}
		if (i-1>=0 && intDivision(i,8)==intDivision(i-1,8)){
			if(gridCell[i-1].getIcon()==iconBlack){
				sumOfBlack++;
			}
		}
		if (i+1<=63 && intDivision(i,8)==intDivision(i+1,8)){
			if(gridCell[i+1].getIcon()==iconBlack){
				sumOfBlack++;
			}
		}
		if (i+7<=63  && intDivision(i,8)==(intDivision(i+7,8)-1)){
			if(gridCell[i+7].getIcon()==iconBlack){
				sumOfBlack++;
			}
		}
		if (i+8<=63 && intDivision(i,8)==(intDivision(i+8,8)-1)){
			if(gridCell[i+8].getIcon()==iconBlack){
				sumOfBlack++;
			}
		}
		if (i+9<=63  && intDivision(i,8)==(intDivision(i+9,8)-1)){
			if(gridCell[i+9].getIcon()==iconBlack){
				sumOfBlack++;
			}
		}
		return sumOfBlack;
	}

	
	public int intDivision(int a,int b)
	{ 
		if(a==b){
			return 0;
		}
		else{
			return a/b;
		}
	}

	
	public Boolean chekValue(ArrayList<Integer> input, int i){
		for (int element : input) {
			if (element == i) {
				return true;
			}
		}
		return false;
	}


	public static void main(String[] args) {
		ArrayList<Integer> input = new ArrayList<Integer>();

		try {
			File myObj = new File("input.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
			  String data = myReader.nextLine();
			  int number = Integer.parseInt(data);
			  input.add(number);
			}
			myReader.close();
		  } catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		  }
		new GameOfLife(input);
	}



	// ********* Gestione JFrame *******************
	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	// *********************************************

}
/*
System.out.println(cont/4+"OK");
 */