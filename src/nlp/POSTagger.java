package nlp;
import java.util.ArrayList;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class POSTagger {

	private MaxentTagger tagger;
	
	public POSTagger(String s) {
		
		if(s.equals("trained_tagger"))	
			 tagger = new MaxentTagger("POST models/trained_tagger.model");
		else if(s.equals("arabic_tagger"))	
		     tagger = new MaxentTagger("POST models/arabic.tagger");
	}
	
	public String tag_text(String s){
		
		  String taggedtxt;
		  CharSequence cs="/A";

		taggedtxt = tagger.tagString( TextPreparator.prepare(s));
		
		if(taggedtxt.contains(cs)){
			return tag_unidentified_words(taggedtxt);
		}
		else
        return taggedtxt;
	}
	
	
	private String tag_unidentified_words(String s){
	   
		MaxentTagger tagger2 = new MaxentTagger("POST models/arabic.tagger");
		  CharSequence cs="/A";
		ArrayList<String> toklist=new ArrayList<String>();
		  StringBuffer word = new StringBuffer ();
		  s=s+" ";
		  
		  for ( int i=0;i<s.length();i++){	  
        // if the character is not a space, add it to a word
        if(( ! Character.isWhitespace(s.charAt(i))))
             {
            word.append(s.charAt(i));
             }
        else
           {
            if (word.length() != 0)
              {
          	  toklist.add(word.toString());
                word.setLength ( 0 );
              }
            }
          }	
		
		  for(int i=0;i<toklist.size();i++){
			 
			  if(toklist.get(i).contains(cs)){
                String ts=tagger2.tagString(toklist.get(i).substring(0,toklist.get(i).length()-2));
                toklist.set(i, ts);  
			  }
		  }
		  
		  String result="";
		   for (String t:toklist)
		      {
			 result=result+t+" ";
		      }
		
		result=result.substring(0,result.length()-1);
		return result;
	}

	
}





/*for(int i=0;i<s.length();i++){

	if(i!=0 && s.charAt(i)==','||s.charAt(i)=='\u060C'&& !Character.isWhitespace(s.charAt(i+1))){
		s= new StringBuilder(s).insert(i+1," ").toString();
      }
	if(i!=0 && s.charAt(i)==','||s.charAt(i)=='\u060C'&& !Character.isWhitespace(s.charAt(i-1))){
    	s= new StringBuilder(s).insert(i," ").toString();
    }
}
return s;
}*/
