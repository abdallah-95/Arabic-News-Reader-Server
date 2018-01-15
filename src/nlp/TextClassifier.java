package nlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



import edu.stanford.nlp.classify.Classifier;
import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.Datum;
import net.sf.classifier4J.ClassifierException;
import net.sf.classifier4J.vector.HashMapTermVectorStorage;
import net.sf.classifier4J.vector.TermVectorStorage;
import net.sf.classifier4J.vector.VectorClassifier;

public class TextClassifier {

	VectorClassifier vc;
	public void train_c4j() throws IOException, ClassifierException{
		TermVectorStorage storage = new HashMapTermVectorStorage();
	    vc = new VectorClassifier(storage);


	    BufferedReader br;
		try {
			String str="";
			String s;
			br = new  BufferedReader(new FileReader("Classification dataset/sport/sport.txt"));
			while( (s=br.readLine())!=null){
				str += s+" ";;
			}
			vc.teachMatch("Sport", str);
			str="";
			 br = new  BufferedReader(new FileReader("Classification dataset/economy/economy.txt"));
			
			 while( (s=br.readLine())!=null){
				 str += s+" ";;
			}
			vc.teachMatch("Economy", str);
			  
			  str="";
			
				 br = new  BufferedReader(new FileReader("Classification dataset/egypt/egypt.txt"));
				
				 while( (s=br.readLine())!=null){
		        str += s+" ";
				}
				vc.teachMatch("Egypt", str);
				  br.close();
				   
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

    public void train_stan() throws IOException, ClassNotFoundException{
    	ColumnDataClassifier cdc = new ColumnDataClassifier(
    		           "Classifier/stanford-model.prop");

    		   Classifier<String, String> cl = cdc.makeClassifier(cdc.readTrainingExamples(
    		           "Classifier/stanford-model.txt"));

    		           IOUtils.writeObjectToFile(cl, "Classifier/stanford-cl");


	}
	public String classify_c4j(String text) throws ClassifierException, IOException{

		String[] modTxt=TextPreparator.remove_stopwords_char(text);

		text="";

		for(String word:modTxt){
			text += word + " ";
		}

		if (vc.isMatch("Sport", text))
		     return "sport";
		else if(vc.isMatch("Economy", text))
			return "economy";

		return "not classified";

	}
    public String classify_c4j_percen(String text) throws ClassifierException, IOException{

    	String[] modTxt=TextPreparator.remove_stopwords_char(text);

		text="";

		for(String word:modTxt){
			text += word + " ";
		}

	    double sportFreq= vc.classify("Sport", text);
	    double economyFreq= vc.classify("Economy", text);
	    double egyptFreq=vc.classify("Egypt", text);

	    if(sportFreq>economyFreq)
	    	return "sport";
	    else if(economyFreq>sportFreq)
	    	return "economy";

	    return "not classified";
	}

    public String classify_stan(String text) throws ClassNotFoundException, IOException{

        String[] modTxt=TextPreparator.remove_stopwords_char(text);

    	int sportFreq=0;
    	int economyFreq=0;
    	ColumnDataClassifier cdc = new ColumnDataClassifier(
		           "Classifier/stanford-model.prop");

	    Classifier<String, String> cl = IOUtils.readObjectFromFile(
	    		                            new File("Classifier/stanford-cl"));



	    for(int i=0;i<modTxt.length;i++){
	    	Datum<String, String> d = cdc.makeDatumFromLine("	"+modTxt[i]);

	    	switch(cl.classOf(d)){

	    	   case "1":
	    		   sportFreq++;
	    		   break;
	    	   case "2":
	    	   	   economyFreq++;
	    	   	   break;
	    	}
	    }


	    if(sportFreq>economyFreq)
	    	return "sport";
	    else if(economyFreq>sportFreq)
	    	return "economy";

		return "error";


	}

    public String classify_cfj_stan(String text) throws IOException, ClassifierException, ClassNotFoundException{

    	train_c4j();
    	int sportFreq=0;
    	int economyFreq=0;
    	int egyptFreq=0;
    	String[] modTxt=TextPreparator.remove_stopwords_char(text);
    	for(String word:modTxt){

    		//double d=vc.classify("Egypt", word);
    		if (vc.isMatch("Sport", word) || vc.classify("Sport", word)!=0)
    		sportFreq++;

		else if(vc.isMatch("Economy", word) || vc.classify("Economy", word)!=0)
			economyFreq++;
    		
		else if(vc.isMatch("Egypt", word) || vc.classify("Egypt", word)!=0)
			egyptFreq++;
    	}

    	if(sportFreq > 5 || economyFreq > 5 || egyptFreq > 5){
    	 if(sportFreq>economyFreq&&sportFreq>egyptFreq)
  	    	return "رياضة";
  	    else if(economyFreq>sportFreq&&economyFreq>egyptFreq)
  	    	return "اقتصاد";
  	    else if(egyptFreq>economyFreq&&egyptFreq>sportFreq)
  	    	return "اخبار مصر";
    	}
    	ColumnDataClassifier cdc = new ColumnDataClassifier(
		           "Classifier/stanford-model.prop");

	    Classifier<String, String> cl = IOUtils.readObjectFromFile(
	    		                            new File("Classifier/stanford-cl"));

	    for(int i=0;i<modTxt.length;i++){
	    	Datum<String, String> d = cdc.makeDatumFromLine("	"+modTxt[i]);

	    	switch(cl.classOf(d)){

	    	   case "1":
	    		   sportFreq++;
	    		   break;
	    	   case "2":
	    	   	   economyFreq++;
	    	   	   break;
	    	   case "3":
	    		   egyptFreq++;
	    		   break;
	    	}



    }
	    if(sportFreq>economyFreq&&sportFreq>egyptFreq)
  	    	return "رياضة";
  	    else if(economyFreq>sportFreq&&economyFreq>egyptFreq)
  	    	return "اقتصاد";
  	    else if(egyptFreq>economyFreq&&egyptFreq>sportFreq)
  	    	return "اخبار مصر";

 		return "";
    }


}
