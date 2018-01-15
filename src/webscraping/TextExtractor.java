package webscraping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TextExtractor {

	
	public String headline;
	public String story;
	static int index=0;
	public static ArrayList<TextExtractor> news;
    static String news_str;
	
	
	public ArrayList<TextExtractor> extract(URL url,boolean load_more,int pages_loaded) throws Exception{
		news=new ArrayList<TextExtractor>();
	    get_headline_story_in_ArrayList(url, load_more,pages_loaded);
	    
	    return news;
	}
	
	private static String load_more(URL url,int pages_loaded) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
	
		
		HtmlPage page;
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		 WebClient webClient = new WebClient();
		 webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		
		  webClient.waitForBackgroundJavaScript(10000);
		  
	        page = webClient.getPage(url.url);

	        webClient.waitForBackgroundJavaScript(10000);

	        HtmlAnchor btn = (HtmlAnchor) page.getElementById("lblLoadMore");

	        for(int i=0;i<pages_loaded;i++){
	        webClient.waitForBackgroundJavaScript(10000);

	        page = (HtmlPage) btn.click();
	        webClient.waitForBackgroundJavaScript(10000);
	        }
	        
	        webClient.getJavaScriptEngine().shutdown();
	        webClient.close();
	        webClient.getCache().clear();
        
	        return page.asXml();
		
		
	}
	
	
	
	private void get_headline_story_in_ArrayList(URL url,boolean load_more,int pages_loaded) throws Exception{
		Document doc ;
		if(load_more){
		while (true){
			  try
		        {Thread.sleep(0);}
		    catch (Exception e)
		        {e.printStackTrace();}
			try{
				    doc = Jsoup.parse(TextExtractor.load_more(url, pages_loaded));
			        break;
			  }catch(FailingHttpStatusCodeException  e){

					continue;
				}
			}
	
		}else {
		
			doc = Jsoup.connect(url.url).get();
	}
		Elements list = doc.select(url.list_selector_tag);
		index=0;

		for(Element el: list){
			
			if(url.site.equals("http://www.almasryalyoum.com"))
			    el.select("p.town").remove();
			
			String title = el.select(url.title_selector_tag).text();
			news.add(index, new TextExtractor());
			news.get(index).headline=title;

			String link=url.site;
			link += el.select("a").attr("href");

			String fullStory=getFullStory(link,url);
			news.get(index).story=fullStory;
			index++;
		}
		
	}
	
	public String get_headline_story_in_String(URL url,int pages_loaded) throws Exception{
		Document doc ;
		while (true){
			  try
		        {Thread.sleep(0);}
		    catch (Exception e)
		        {e.printStackTrace();}
			try{
				    doc = Jsoup.parse(TextExtractor.load_more(url, pages_loaded));
			        break;
			  }catch(FailingHttpStatusCodeException  e){

					continue;
				}
			}
		Elements list = doc.select(url.list_selector_tag);

		for(Element el: list){
			
			if(url.site.equals("http://www.almasryalyoum.com"))
		    el.select("p.town").remove();
		    
			String title = el.select(url.title_selector_tag).text();
			news_str  += title + " ";

			String link="http://www.masrawy.com";
			link += el.select("a").attr("href");

			String fullStory=getFullStory(link,url);
			news_str += fullStory + " ";
			
		}
		return news_str;
	}
		
		public static String getFullStory(String href,URL url) throws Exception{

			String result = java.net.URLDecoder.decode(href, "UTF-8");


			Document doc = Jsoup.connect(result).get();
			Elements el = doc.select(url.article_tag);

	            // el.select("p").get(0).remove();// removes first line in paragraph
			
			if(url.site.equals("http://www.almasryalyoum.com")){
				el.select("div.news_SMSBox").get(0).remove();
				
			}

				String txt = el.select("p").text();

			    return txt;
		}
		
}
