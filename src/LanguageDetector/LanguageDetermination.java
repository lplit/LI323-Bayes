package LanguageDetector;

public class LanguageDetermination { 
    
    private static String[] testWords = {"fatta", "ora", "che", "dato", "volta",
					 "by", "other", "mean", "statistics", "chocolate",
					 "president", "thanks", "patatoes", "constitutionnellement",
					 "peter", "pomme", "daar"};

    private static String[] testWordsLangs = {"italian.txt", "italian.txt", "italian.txt", "italian.txt", "italian.txt",
					      "english.txt", "english.txt", "english.txt", "english.txt", "english.txt", 
					      "english.txt", "english.txt", "english.txt", "french.txt", "english.txt", 
					      "french.txt", "dutch.txt"};


    /**
     * Calculates the probability of a language from list of languages
     * @param lang Language to calculate probability of, for example english.txt or french.txt
     * @return Given language's probability
     */
    public static double probTestWords(String lang) {
	double tmp=0.0;
	for (int i = 0 ; i < testWordsLangs.length ; i++ ) {
	    if (lang.equals(testWordsLangs[i])) {
		tmp+=1.0;
	    }
	}
	double ret = (tmp/testWordsLangs.length);
	System.out.println("Prob for "+lang+" off the list is: "+ ret);
	return ret;
    }

    public static void main(String[] args) {
	int wrongs=0;
	
	Parser p_en = new Parser("english.txt");
	Parser p_fr = new Parser("french.txt");
	Parser p_it = new Parser("italian.txt"); 
	Parser p_du = new Parser("dutch.txt");

	Bayes b_fr = new Bayes(p_fr, "probability");
	Bayes b_en = new Bayes(p_en, "probability");
	Bayes b_it = new Bayes(p_it, "probability");
	Bayes b_du = new Bayes(p_du, "probability");

	Bayes[] b = {b_fr, b_en, b_it, b_du};


	Parser p_en_test1 = new Parser("english.txt");
	Bayes b_en_test1 = new Bayes(p_en_test1, "statistics");


	System.out.println("***************************\nFirst tests...\n\n");
	System.out.println("Tests for \"probability\""); // stdout for prob of "statistics" | english

	String s = Bayes.wordProbLang(b); // stdout for prob of "probability"

	System.out.println("Score of \"statistics\" in english: "+b_en_test1.wordProb()+"\n\n\n");
	System.out.println("***************************");
	for (int i = 0 ; i < testWords.length ; i++) {
	    b_fr = new Bayes(p_fr, testWords[i]);
	    b_en = new Bayes(p_en, testWords[i]);
	    b_it = new Bayes(p_it, testWords[i]);
	    b_du = new Bayes(p_du, testWords[i]);
	    
	    /* Testing outputs, can be uncommented for massive verbose mode
	       System.out.println(b_fr.toString());
	       System.out.println(b_en.toString());
	       System.out.println(b_it.toString());
	       System.out.println(b_du.toString());
	    */
	    
	    System.out.println("Analyzing "+testWords[i]+"...");
	    Bayes[] bb = {b_fr, b_en, b_it, b_du};
	    String ss = Bayes.wordProbLang(bb);
	    if (!s.equals(testWordsLangs[i])) wrongs++;

	}
	System.out.println(wrongs +" wrong answers!");
	System.out.println("Succes rate is: "+ (1-((double)wrongs/testWords.length)));

	double it=(probTestWords("italian.txt"));
	double en=(probTestWords("english.txt"));
	double du=(probTestWords("dutch.txt"));
	double fr=(probTestWords("french.txt"));
	
	return;
    }
    
}