package dataset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import nlp.TextPreparator;
import nlp.Tokenizer;
import webscraping.TextExtractor;
import webscraping.URL;

public class Collector {

	
   	  static ArrayList<Collector> data;
	  int frequency;
	  String  word;
	
	  public static void collect(URL url,String cat) throws Exception{
	
		TextExtractor te =new TextExtractor(); 
		String newsdata=te.get_headline_story_in_String(url, 30);
		System.out.println("Data collected successfully.");
		String[] words = TextPreparator.remove_stopwords_char(newsdata);

		calcFreq(words);
		
		save(cat);
		
	}
	
	public static void calcFreq(String words[]){

		    data=new ArrayList<>();
		    int index=0;

		    for(int i=0;i<words.length;i++){


			if(check_if_exists(words[i])){
				continue;
			}

			int freq=0;
			for(int j=0;j<words.length;j++){



				if(words[i].equals(words[j]))
					freq++;

                if(j==(words.length-1)){

                	data.add(index, new Collector());

                	data.get(index).word=words[i];
                	data.get(index).frequency=freq;

                	index++;
                }
			}
		}
			
			Collections.sort(data, new Comparator<Collector>() {

				 @Override
					public int compare(Collector o1, Collector o2) {

						return o2.frequency - o1.frequency;
					}

			    });
	}
			private static boolean check_if_exists(String str){

				for(Collector object:data){
					if(str.equals(object.word))
						return true;
				}
				return false;
			}
			private static void save(String cat){
				try{
					
					FileWriter fw = new FileWriter("Classification dataset/"+cat+"/"+cat+".txt",true);
			    	  BufferedWriter bw = new BufferedWriter(fw);
			    	  PrintWriter pw = new PrintWriter(bw);
				  
			    	  for(int i=0;i<data.size();i++){
						    
					    	if(data.get(i).frequency<20)
					    		continue;
					    	
					    	pw.println(data.get(i).word);

					    }
					    pw.close();
					 /*   
					    fw = new FileWriter("Classification dataset/"+cat+"/"+cat+".txt",true);
					    bw = new BufferedWriter(fw);
					    pw = new PrintWriter(bw);
					    
				    for(int i=0;i<data.size();i++){
				    
				    	if(data.get(i).frequency<20)
				    		continue;
				    	
				    	if(cat.equals("sport"))
				    	pw.println("1	"+data.get(i).word);
				    	
				    	else if(cat.equals("economy"))
				    	pw.println("2	"+data.get(i).word);

				    }
				    pw.close();
				  */
				    SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy"); 
					Date date = new Date();
					String fileName = cat+"-"+dt.format(date)+".txt";
					   
					
						pw = new PrintWriter("Classification dataset/"+fileName);
						for(int i=0;i<data.size();i++){
						pw.println(data.get(i).word+"	"+data.get(i).frequency);						
						
						}

				        pw.close();
				} catch (IOException e) {
				   // do something
				}
			}
			
			public static void txt_To_ts_file(String path,String cat) throws FileNotFoundException, IOException{
				
				List<String> data = new ArrayList<>();
				try (BufferedReader br = new BufferedReader(new FileReader(path))) {
				    String line;
				    
				    while ((line = br.readLine()) != null) {
				       data.add(line);
				       
				    }
				    br.close();
				}
				  
				PrintWriter pw =new PrintWriter(new BufferedWriter(
						                     new FileWriter("Classification dataset/"+cat+"/"+cat+"_ts.txt",true)));
				    
				switch (cat){
				
				    case("sport"):
				    	for(int i=0;i<data.size();i++)
					    	pw.println("1	"+data.get(i));

				    	break;
				    	
				    case ("economy"):
				    	for(int i=0;i<data.size();i++)
					    	pw.println("2	"+data.get(i));

				    	break;
				    	
				    case ("egypt"):
				    	for(int i=0;i<data.size();i++)
					    	pw.println("3	"+data.get(i));

				    	break;
				}
				pw.close();
				
		      }
				
			}
	

