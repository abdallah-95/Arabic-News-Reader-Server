package nlp;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class TextPreparator {

	
	
	public static String prepare(String s){
		TextPreparator tp=new TextPreparator();
		
		s=Normalizer.normalize(s);
		s=tp.separate_punctuations(s);
		s=tp.removeDiacritics(s);
		return s;
	}
	
	public static String[] remove_stopwords_char(String str) throws IOException{
		Tokenizer tk =new Tokenizer();
        //String definite_article_content = new String(Files.readAllBytes(Paths.get("StemmerFiles/definite_article.txt")),StandardCharsets.UTF_16);
        String stopwords_content = new String(Files.readAllBytes(Paths.get("StemmerFiles/stopwords.txt")),StandardCharsets.UTF_16);
        String punc_content = new String(Files.readAllBytes(Paths.get("StemmerFiles/punctuation.txt")),StandardCharsets.UTF_8);
      
        //String definite_article[]= tk.tokenize(definite_article_content);
        String stopwords[]= tk.tokenize(stopwords_content);
        String punctuation[]= tk.tokenize(punc_content);
        

        for(String punc:punctuation){
        	str =str.replace(punc, " ");
        }
        String [] tokens = tk.tokenize(str);
 
        
  
        List<String> myList = new ArrayList<String>();

        Collections.addAll(myList, tokens);
        
        
        for(String stopword : stopwords){
        	
        	Iterator <String> iterator = myList.iterator();
        	while(iterator.hasNext()){
        	    String currentword = iterator.next();
        	    if(stopword.equals(currentword)){
        			iterator.remove();
        		}
        	  
        	}
        }
        tokens= new String[myList.size()];
        tokens=myList.toArray(tokens);
        for (int j=0;j<tokens.length;j++)
		{

        	if(tokens[j].length()<=3)
        		continue;
        	
			if(tokens[j].charAt(0)=='ا' && tokens[j].charAt(1)=='ل' && tokens[j].length()>3){ 
				tokens[j] = tokens[j].substring(2);
			}
		}
       
         return tokens;
	}
	
	private String separate_punctuations(String s){
		
        //insert spaces between punctuations and tokens
		for(int i=0;i<s.length();i++){

			if(i!=0 && i!=s.length()-1 && Constants.punctuations.contains(s.substring(i, i+1)) && !Character.isWhitespace(s.charAt(i+1)) ){
				s= new StringBuilder(s).insert(i+1," ").toString();
		      }
			if(i!=0 && Constants.punctuations.contains(s.substring(i, i+1)) && !Character.isWhitespace(s.charAt(i-1))){
		    	s= new StringBuilder(s).insert(i," ").toString();
		    }
  	  }
		return s;
	}
	

	private String removeDiacritics(String s){
		
		for(int i=0;i<Constants.diacritics.size();i++){
			
			s=s.replaceAll(Constants.diacritics.get(i), "");
				
		}
		
		return s;
	}
}
