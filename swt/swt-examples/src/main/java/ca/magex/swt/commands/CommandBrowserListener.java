package ca.magex.swt.commands;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class CommandBrowserListener implements SelectionListener {

	private String url;
	
	private Browser browser;
	
	public CommandBrowserListener(String url, Browser browser) {
		this.url = url;
		this.browser = browser;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		browser.setUrl(url);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

}
