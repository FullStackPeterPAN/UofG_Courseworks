// import what are necessary
import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.TextParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

// run test in the order of name
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyTest {
	
	/*
	 * Name: Yangjiangdai Pan
	 * Student ID: 2294163P
	 * Email: 2294163p@student.gla.ac.uk
	 */

	String crawlStorageFolder = "data/";
	int numberOfCrawlers = 1;

	private CrawlConfig config;
	private HtmlParseData htmlParseData;
	private TextParseData textParseData;
	private CrawlController controller;

	/*
	 * set textParseData to 0
	 * then get it with getTextContent method
	 * if get method does not return 0(actually 1)
	 * it is a defect
	 * this error is caused by getTextContent method can not return 0
	 */
	@Test
	public void oneTest() {
		textParseData = new TextParseData();
		
		// set to 0
		textParseData.setTextContent("0");
		String text = textParseData.getTextContent();

		// if not 0, then fails
		assertEquals("test 0", 0, text);
	}

	/*
	 *  max out going links is set to 5000
	 *  if getMaxOutgoingLinksToFollow method does no return 5000(actually 3)
	 *  it is a defect
	 *  the reason of the error is max out going link keeps being 3 no matter what to be set
	 */
	@Test
	public void outgoingLinksNum() {
		config = new CrawlConfig();
		config.setMaxOutgoingLinksToFollow(5000);
		assertEquals("number of out going link to follow", 5000, config.getMaxOutgoingLinksToFollow());
	}

	/*
	 * set parse data of an existed HTML to a char "A" 
	 * get this parse data with getParseData method (it actually gets "a")
	 * if can not get the same char 
	 * it is a defect
	 * this error seems to be caused by getParseData method
	 */
	@Test
	public void testCapital() {

		Page page = new Page(null);
		htmlParseData = new HtmlParseData();

		//set parse data to "A"
		htmlParseData.setHtml("A");
		page.setParseData(htmlParseData);
		htmlParseData = (HtmlParseData) page.getParseData();
		String text = htmlParseData.getHtml();

		// if not A, then fails
		assertEquals("test if capital", "A", text);
	}

	/*
	 * visit an page with "private" in URL
	 * if it fails to process page curURL set ifVisit to false (actually "true")
	 * a boolean shouldVisit to check if it should visit curURL (actually "false")
	 * if booleans not equal then there is a defect
	 * it is caused by shouldVisit method in MyCrawler only return true to public HTML
	 */
	@Test
	public void visitPrivate() throws Exception {
		config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setMaxDepthOfCrawling(-1);
		config.setIncludeBinaryContentInCrawling(false);

		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		controller = new CrawlController(config, pageFetcher, robotstxtServer);

		MyCrawler mc = new MyCrawler();
		mc.init(0, controller);
		WebURL curURL = new WebURL();

		// set URL to a private HTML
		curURL.setURL("http://www.dcs.gla.ac.uk/~bjorn/sem20172018/ae2private/IDA.html");
		Page page = new Page(curURL);
		boolean ifVisit = true;
		try {
			mc.processPage(curURL);
		} catch (Exception e) {

			// ifVisit to false if catch exception while process page
			ifVisit = false;
		}

		// if should visit the page return true
		boolean shouldVisit = mc.shouldVisit(page, curURL);
		assertEquals("check visit", ifVisit, shouldVisit);
	}

}
