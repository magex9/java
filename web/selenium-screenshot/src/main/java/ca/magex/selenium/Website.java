package ca.magex.selenium;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Website {

	private static final Logger logger = Logger.getLogger(Website.class.getName());
	
	public static void main(String[] args) throws Exception {
		new Website().run("http://magex9.github.io/workspace/");
	}

	private String base;
	
	private WebDriver driver;
	
	private String datadir;
	
	private Set<String> processed;
	
	public void run(String url) throws Exception {
		this.base = url;
		if (!base.endsWith("/"))
			base = base.substring(0, base.lastIndexOf("/") + 1);
		this.processed = new HashSet<String>();
		this.driver = new FirefoxDriver();
		this.datadir = "target/data/" + base.replaceAll("[^a-z]", "_").replaceAll("__*", "_");
		process(url);
		driver.quit();
	}
	
	public void process(String url) throws Exception {
		if (!url.startsWith(base) || !(url.endsWith(".asp")
				|| url.endsWith(".html")))
			return;

		if (processed.contains(url))
			return;
		
		String filename = url.replaceFirst(base, "").replaceAll("/", "_");
		File screenshot = new File(datadir + "/" + filename + ".png");
		File source = new File(datadir + "/" + filename + ".html");
		File links = new File(datadir + "/" + filename + ".links");
		File complete = new File(datadir + "/" + filename + ".done");
		
		if (complete.exists())
			return;

		logger.info("Processing: " + url);
		
		if (!screenshot.exists() || !links.exists() || !source.exists()) {
			driver.get(url);
		}

		if (!screenshot.exists()) {
			logger.info("Writing screenshot: " + screenshot.getAbsolutePath());
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, screenshot);
		}
		
		if (!links.exists()) {
			StringBuilder sb = new StringBuilder();
			for (WebElement element : driver.findElements(By.tagName("a"))) {
				String href = element.getAttribute("href");
				logger.info("Found link: " + href);
				if (href.indexOf("#") > 0)
					href = href.substring(0, href.indexOf("#"));
				if (href.indexOf("?") > 0)
					href = href.substring(0, href.indexOf("?"));
				sb.append("\n");
				sb.append(href);
			}
			logger.info("Writing links: " + links.getAbsolutePath());
			FileUtils.writeStringToFile(links, sb.substring(1));
		}
		
		if (!source.exists()) {
			logger.info("Writing source: " + source.getAbsolutePath());
			FileUtils.writeStringToFile(source, driver.getPageSource());
		}
		
		processed.add(url);
		
		for (String link :  FileUtils.readFileToString(links).split("\n")) {
			process(link);
		}
		
		FileUtils.writeStringToFile(complete, new Date().toString());
	}
	
}
