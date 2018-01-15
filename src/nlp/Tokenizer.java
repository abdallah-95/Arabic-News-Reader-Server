package nlp;
import java.io.StringReader;
import java.util.List;

import edu.stanford.nlp.international.arabic.process.ArabicTokenizer;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.TokenizerFactory;

public class Tokenizer {

	private TokenizerFactory<CoreLabel> tf;
	
	
	public Tokenizer() {
		tf = ArabicTokenizer.factory();
	}
	
	public String[] tokenize(String s){
		
		List<CoreLabel> toklist=tf.getTokenizer(new StringReader(s)).tokenize();
		
		int size=toklist.size(); 
		String[] tokens=new String[size];

		for(int i=0;i<size;i++){
			tokens[i]=toklist.get(i).value();
		}
		return tokens;
	}
}

