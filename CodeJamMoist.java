package net.garyscorner.codejammoist;

//File:		CodeJamMoist.java
//Author:	Gary Bezet
//Date:		2016-06-14
//Desc:		Designed for google apac test "Practice Round APAC test 2016"  Written as practice
//Problem:	https://code.google.com/codejam/contest/6234486/dashboard#s=p2
//Results:	

//Imports here
import java.io.*;



public class CodeJamMoist {

	//class variables here
	private String infileopt; //filename from commandline
	private String outfileopt;  //"
	
	private BufferedReader infile;  //infile reader
	private PrintStream outfile;  //outfile writer
	
	private int totalcases;
	private DataStructure[] testcases;  //test cases array
	
	//Main program entry point
	public static void main(String[] args) {
		
		CodeJamMoist prog = new CodeJamMoist();
		prog.initargs(args);  //find the command line args
		
		System.err.println("Opening files...");
		prog.openFiles();  //open files

		System.err.println("Loading data...");
		prog.loadData();  //load data into datastucture
		
		prog.printOpts();  //print the program options
		
		System.err.println("Solving cases...");
		prog.solve();  //solve all cases
		
		System.err.println("\nOutputing solution...");
		prog.writeSolution();
		
		System.err.println("Finished!");
		prog.closeFiles();  //close files
	}
	
	private void writeSolution() {
		
		for( DataStructure testcase : testcases )  {
			outfile.printf("Case #%1$d: %2$d\n", testcase.casenum, testcase.solution);
		}		
		
	}
	
	private void solve() {
		for( DataStructure testcase : testcases )  {
			testcase.solve();
			
			//output the solution to stderr
			System.err.printf("Case %1$d solved Ans=%2$d\n", testcase.casenum, testcase.solution);
			
		}
	}
	
	//print program options and useful data
	private void printOpts() {
		System.err.printf("Infile:\t%1$s\n", infileopt);
		System.err.printf("Outfile:\t%1$s\n", outfileopt);
		System.err.printf("Totalcases:  %1$d\n\n", totalcases);
		
	}
	
	//loads the contents of infile
	private void loadData() {
		
		//get first line with total cases
		try {
			totalcases = Integer.parseInt(infileReadLine());
		} catch(NumberFormatException e) {
			System.err.printf("Total cases could not be read on line 1 of \"%1$s\"", infileopt);
			System.exit(5);
		}
		
		testcases = new DataStructure[totalcases];
		
		for( int i = 0; i < totalcases; i++ ) {  //get all cases
			
			testcases[i] = new DataStructure();  //initalize data structure
			testcases[i].casenum = i + 1;  
			
			//try and get the number of cards
			try {
				testcases[i].totalCards = Integer.valueOf(infileReadLine());
			} catch(NumberFormatException e) {
				System.err.printf("Could not parseInt for number of cases in \"%1$s\" case %2$d", infileopt, testcases[i].casenum);
				System.exit(6);
			}
			
			//initialize card array and load it
			testcases[i].cards = new String[testcases[i].totalCards];
			for( int c=0; c < testcases[i].totalCards; c++) {
				testcases[i].cards[c] = infileReadLine();
			}
			
		}
		
	}
	
	
	
	private String infileReadLine() {  //reads a line throw error if problem
		String line = null;
		
		try {
			line = infile.readLine();
		} catch (IOException e) {
			System.err.printf("Unable to read line from \"%1$s\"\n", infileopt);
			System.exit(4);
		}
		
		return line;
	}
	
	private void closeFiles() {
		try {
			infile.close();
		} catch (IOException e) {
			//do nothing we are done
		}
		outfile.close();
	}
	
	//open files
	private void openFiles() {
		
		try {
			infile = new BufferedReader(new FileReader(infileopt));
		} catch (FileNotFoundException e) {
			System.err.printf("Could not open: %1$s\n", infileopt);
			System.exit(2);
		}
		
		if( outfileopt == null ) {  //set outfile to stdout if blank otherwise open file for writing
			outfileopt = new String("Stdout");
			outfile = System.out;
		} else {
			try {
				outfile = new PrintStream(new File(outfileopt));
			} catch (FileNotFoundException e) {
				System.err.printf("Couldn't open \"%1$s\" for writing!\n", outfileopt);
				System.exit(3);
			}
			
		}
		
	}
	
	//initial command line args
	private void initargs(String[] args) {
		
		if(  2 < args.length || args.length == 0 ) {
			System.err.println("Program requires 1 or two arguments.  First arg is infile name, 2nd arg is outfile name Stderr by default.");
			System.exit(1);  //exit the system if arguments not correct
		}
		
		infileopt = args[0];
		
		if( args.length == 2 ) {  //set the outfile if exists
			outfileopt = args[1];
		}
		
	}
	
	

}
