package webscraping;

public class URL {

	String url;
	String title_selector_tag;
	String list_selector_tag;
	String article_tag;
	String load_more_tag;
	String site;
	
	private URL(String url,String title_selector_tag,String list_selector_tag,String article_tag,String load_more_tag,String site){
	
		this.url=url;
		this.title_selector_tag=title_selector_tag;
		this.list_selector_tag=list_selector_tag;
		this.article_tag=article_tag;
		this.load_more_tag=load_more_tag;
		this.site=site;
	}
	public static URL MASRAWY_SPORT_LOCAL = new URL("http://www.masrawy.com/Sports/Sports_News/section/577/أخبار-الرياضة",
			                                       "a","ul.ulListing li",
			                                       "div.details","lblLoadMore","http://www.masrawy.com");
	
	public static URL MASRAWY_Economy_LOCAL = new URL("http://www.masrawy.com/News/News_Economy/section/206/اقتصاد",
                                                  "a","ul.ulListing li",
                                                 "div.details","lblLoadMore","http://www.masrawy.com");
	public static URL MASRAWY_Egypt_LOCAL = new URL("http://www.masrawy.com/News/News_Egypt/section/35/أخبار-مصر",
                                                   "a","ul.ulListing li",
                                                "div.details","lblLoadMore","http://www.masrawy.com");
	
	public static URL CNN_Egypt = new URL("https://arabic.cnn.com/مصر",
            "h2.desktop-headline","section.news-container.clearfix article",
         "div.article-body",null,"https://arabic.cnn.com");
	
	public static URL ELMasryELYoum_Egypt = new URL("http://www.almasryalyoum.com/news/index?typeid=1&sectionid=3",
            "a","div.town_wrap div",
         "div#NewsStory",null,"http://www.almasryalyoum.com");
	
	public static URL ELMasryELYoum_Sport = new URL("http://www.almasryalyoum.com/news/index?typeid=1&sectionid=8",
            "a","div.town_wrap div",
         "div#NewsStory",null,"http://www.almasryalyoum.com");
	
	public static URL ELMasryELYoum_Economy = new URL("http://www.almasryalyoum.com/news/index?typeid=1&sectionid=4",
            "a","div.town_wrap div",
         "div#NewsStory",null,"http://www.almasryalyoum.com");
	
}
