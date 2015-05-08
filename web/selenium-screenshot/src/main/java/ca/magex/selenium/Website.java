package ca.magex.selenium;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Website {

	public static void main(String[] args) throws Exception {
		new Website().run("http://csszengarden.com/");
	}

	private String base;
	
	private WebDriver driver;
	
	private Set<String> processed;
	
	private String dir;
	
	public void run(String url) throws Exception {
		this.base = url;
		if (!base.endsWith("/"))
			base = base.substring(0, base.lastIndexOf("/") + 1);
		this.driver = new FirefoxDriver();
		this.processed = new HashSet<String>();
		this.dir = Calendar.getInstance().getTimeInMillis() + "";
		screenshot(url);
		followLinks();
		driver.quit();
	}	
		
	private void followLinks() throws Exception {
		System.out.println("Following links");
		List<String> links = new ArrayList<String>();
		for (WebElement element : driver.findElements(By.tagName("a"))) {
			String href = element.getAttribute("href");
			if (href.indexOf("#") > 0)
				href = href.substring(0, href.indexOf("#"));
			if (href.indexOf("?") > 0)
				href = href.substring(0, href.indexOf("?"));
			links.add(href);
			System.out.println("Found: " + href);
		}

		for (String key : links) {
			if (screenshot(key)) {
				followLinks();
			}
		}
		
	}
	
	public boolean screenshot(String href) throws Exception {
		if (!href.startsWith(base) || href.endsWith(".pdf")
				|| href.endsWith(".css") || href.endsWith(".js"))
			return false;
		
		if (processed.contains(href)) {
			System.out.println("Skipping: " + href);
			return false;
		}
		
		driver.get(href);
		String name = driver.getTitle();
		System.out.println("Goto: " + href + " for " + name);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String filename = href.replaceFirst(base, "").replaceAll("/", "_") + " - " + name;
		File output = new File("target/" + dir + "/" + filename + ".png");
		FileUtils.copyFile(scrFile, output);
		
		processed.add(href);
		return true;
	}
	
}
