package LanguageDetector;

public class Bayes {
    private int nblanguages;
    private Parser p;
    private String word;
    private char[] charArr;

    /**
     * Constructor method
     * @param language Language to perform calculus on, must be present in ./Data/
     * @param word Word to analyse
     */
    public Bayes(String language , String word){
	nblanguages++;
	p=new Parser(language);
	this.word=word;
	charArr = word.toCharArray();
    }

    /**
     * Alternate constructor method with Parser instead of Language
     * @param p Parser that analyses the text
     * @param word Word to be analysed
     */
    public Bayes(Parser p , String word){
	nblanguages++;
	this.p=p;
	this.word=word;
	charArr = word.toCharArray();
    }

    /**
     * Calculates probability of a word in a given language
     * @return Percentage of chances the word is in a given language
     */
    public double wordProb(){
	double ret = 0.0;
	double [] charArr2= p.getPercOf();
	for(int i=0 ; i < charArr.length ; i++){
	    int ind = ((int)(charArr[i]-97)); // Simplicity handle
	    double add = Math.log((charArr2[ind]));
	    ret+=add;
	}
	return ret;
    }


    public Parser getParser() {
	return p;
    }

    /**
     * Automatically calculates 'probabilities' for languages array.
     * Prints highest prob language with the % to stdout.
     * @param langList Array of Bayes for different languages
     * @return Language -'english.txt', 'french.txt' etc - with highest value
     */
    public static String  wordProbLang(Bayes[] langList) {
	String langHigh="";
	double max = -9999.0;
	for (int i = 0 ; i < langList.length ; i++ ) {
	    String lang = langList[i].getParser().getLang();
	    double curr = langList[i].wordProb(); //  Simplicity handle 
	    System.out.println("Current: " + curr + " for " + lang);
	    if (max < curr) {
		//System.out.println("New high! (old: "+max+" new: "+curr+")");
		max=curr;
		langHigh=langList[i].p.getLang();
	    }
	}
	System.out.println("Highest score: " + max + " for " + langHigh+"\n");
	return langHigh;
    }
    
    /**
     * ProbabilityLanguage
     * @param Given language - format language.txt - french.txt, english.txt etc
     * @return % of chances for a language
     */
    public static double probLang(String lang) {
	return LanguageDetermination.probTestWords(lang);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override public String toString() {
	return ""+wordProb();
    }
}