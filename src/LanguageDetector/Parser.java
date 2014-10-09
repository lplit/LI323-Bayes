package LanguageDetector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.String;


public class Parser {

    private int[] letterOccurences; // a=letterOccurences[0], b=letterOccurences[1], etc...
    private ArrayList<String> words; // Lines
    private double[] percOf;
    private int totalLetters;
    private int totalWords;
    private String lang;

    /* CONSTRUCTORS AND HELPERS */

    /**
     * By default constructor method, initializes the fields.
     */
    public Parser() { 
	letterOccurences = new int[26];
	words  = new ArrayList<String>();
	percOf = new double[26]; 
	totalLetters = 0;
	totalWords = 0;
	lang="";
    }

    /**
     * Constructor, reads file, allocates the class variables etc etc.
     * @param filename Name of the file to read WITH extention (ex: english.txt). Attention, file MUST exist within /Data. Throws IOException if file not found.
     */
    public Parser(String filename) {
	this(); // Allocation
	try (BufferedReader br = new BufferedReader(new FileReader("./Data/"+filename))) {
		String sCurrentLine;
		lang=filename;
		while ((sCurrentLine = br.readLine()) != null) {
		    // Reading line by line, splitting lines into words arrays and increments total words count
		    String[] words_tmp = sCurrentLine.split("\\s");
		    for (int i =0 ; i <words_tmp.length ; i++) {
			words.add(words_tmp[i]);
			totalWords++;
		    }
		    // Decomposing words_tmp into chars and counting chars occurences
		    for (String word_tmp : words_tmp) {
			char[] chr = word_tmp.toCharArray();
			for (int i=0 ; i < chr.length ; i++) {
			    int currCharASCII = ((int) chr[i]);
			    letterOccurences[currCharASCII-97]++;
			    totalLetters++;
			}
		    }
		}
		percOf=percCalc(letterOccurences); 
	    } catch (IOException e) {
	    System.out.println("File read error!\n");
	    e.printStackTrace();
	}
    }

    /**
     * Used in constructor to create percOf
     * @param tab Table with # of occurences of each letter
     * @return double[] with percentage
     */
    private double[] percCalc(int[] tab) { 
	double[] ret = new double[26];
	int total = 0;
	for (int i: tab) {total+=i;}
	for (int i=0 ; i < tab.length ; i++) {
	    // Simplicity handles
	    int curr = tab[i];
	    double pourc= ((((double)curr)/total));
	    ret[i]=pourc;
	}
	return ret;
    }
    
    
    /* GETTERS */

    /**
     * letterOccurences getter
     * @return letterOccurences array
     */
    public int[] getLetterOccurences() {
	return letterOccurences;
    }

    /**
     * words field getter
     * @return ArrayList of String containing all the words
     */
    public ArrayList<String> getWords() {
	return words;
    }

    /**
     * Field percOf getter
     * @return percOf field
     */
    public double[] getPercOf() {
	return percOf;
    }

    /**
     * Field totalLetters getter
     * @return totalLetters field
     */
    public int getTotalLetters() {
	return totalLetters;
    }

    /** 
     * Calculates the total number of characters in the text
     * @param tab Table where each case corresponds to nbumer of occurences of each letter. By default tab[0]=a, tab[1]=b, etc...
     * @return Returns int with total number of characters.
     */
    public int getCharSum(int[] tab) {
	int ret=0;
	for (int i: tab) {
	    ret += i; 
	}
	return ret;
    }

    /**
     * totalWords field getter
     * @return totalWords int
     */
    public int getTotalWords() {
	return totalWords;
    }


    /**
     * @return Language string
     */
    public String getLang() {
	return lang;
    }

    /* STRING/VISUAL OUTPUT */

    /**
     * Method overwrite, toString
     */
    @Override public String toString() { 
	String s = "";
	int sum=getCharSum(letterOccurences);
	for (int i=0 ; i< letterOccurences.length ; i++) {
	    int curr = letterOccurences[i];
	    double pourc= ((((double)curr)/sum)*100);
	    s += (((char) (i+97)) + " " + curr + "\t" + pourc + "%\n");
	}
	return s;
    }

    /**
     * Calculates number of occurences for each letter, prints it, as well as % of total
     */
    public void printLettersStats(int[] charWord) {
	int sum=getCharSum(charWord);
	for (int i=0 ; i< charWord.length ; i++) {
	    // Simplicity handles
	    int curr = charWord[i];
	    double pourc= percOf[i];
	    System.out.println( ((char) (i+97)) + " " + curr + "\t" + pourc + "%");
	}
	System.out.println("Total:\t"+sum);
    }

    /**
     * Prints all the words to stdout
     */
    public void printWords() {
	for(int i =0 ; i< words.size();i++){
	    System.out.print(words.get(i)+" ");
	}
    }
}
