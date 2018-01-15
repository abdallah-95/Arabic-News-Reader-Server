package nlp;
import java.util.ArrayList;

public class Normalizer {

	
	// normalize the text (Alif, Haa, Yaa, and Tatweel) 	
    public static String normalize(String currentText)
       {
		ArrayList<String> tokens=new ArrayList<String>();
    	StringBuffer modifiedWord=new StringBuffer("");
	    ArrayList<String> modifiedText=removeExtraSpaces(currentText);
	    
	    // for each token in the text
	    for (int i=0;i<modifiedText.size();i++)
	     {
	    	 modifiedWord.setLength(0);
	    	 String ctoken=modifiedText.get(i);
	    	 // normalization of hamzated alif to bare alif 
	    	 //ctoken=ctoken.replace(Constants.ALIF_HAMZA_ABOVE,Constants.ALIF);
	    	// ctoken=ctoken.replace(Constants.ALIF_HAMZA_BELOW, Constants.ALIF);
	    	 //ctoken=ctoken.replace(Constants.ALIF_HAMZA_ABOVE, Constants.ALIF);
	    	 //ctoken=ctoken.replace(Constants.ALIF_MADDA, Constants.ALIF);
	    	 
	    	 // normalization of taa  marbutah to haa Or replacing only last char  
	    	
	    	 ctoken= ctoken.substring(0, ctoken.length()-1)+
	    			 ctoken.substring(ctoken.length()-1, ctoken.length()).replace(Constants.HAA,Constants.TAA_MARBUTA);
	    	// ctoken=ctoken.replace(Constants.HAA,Constants.TAA_MARBUTA);
	    	 
	    	 // normalization of yaa to dotless yaa
	    	 ctoken=ctoken.replace(Constants.DOTLESS_YAA, Constants.YAA);

	    	 for (int j=0; j<ctoken.length(); j++)
	    	{
	    		 if ( ! (Constants.TATWEEL.contains(ctoken.substring(j,j+1))))
	                 modifiedWord.append(ctoken.substring(j,j+1));
	             else
	             {
	                 
	             }
	    	}
	    	 tokens.add(modifiedWord.toString());
	     }  
	        String result="";
	
	   for (String t:tokens)
	      {
		 result=result+t+" ";
	      }
	
	result=result.substring(0,result.length()-1);
	return result;
	   // return tokens;
        }
    
    
    private static ArrayList<String> removeExtraSpaces(String currentText) 
	{
		ArrayList<String> tt=new ArrayList<String>();
		  StringBuffer word = new StringBuffer ( );
		    currentText=currentText+" ";
		  for ( int i=0;i<currentText.length();i++)
              {
			  
            // if the character is not a space, add it to a word
            if(( ! Character.isWhitespace(currentText.charAt(i))))
                 {
                word.append(currentText.charAt(i));
                 }
            else
               {
                if (word.length() != 0)
                  {
                    tt.add(word.toString());
                    word.setLength ( 0 );
                  }
                }
              }
		return tt;
	  }
}
