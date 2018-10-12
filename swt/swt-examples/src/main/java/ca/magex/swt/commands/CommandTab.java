package ca.magex.swt.commands;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class CommandTab {

	private String name;
	
	private String url;

	public CommandTab(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUrl() {
		return url;
	}

	private void createServerGroup(SashForm sash) {

		Group serverGroup = new Group(sash, SWT.NONE);
		serverGroup.setLayout(new GridLayout());

		ToolBar toolbar = new ToolBar(serverGroup, SWT.HORIZONTAL);
		
		Text output = new Text(serverGroup, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 3;
		gridData.heightHint = 150;
		output.setLayoutData(gridData);

		String tomcat = "~/apache-tomcat-8.5.34";
		
		ToolItem deploy = new ToolItem(toolbar, SWT.PUSH);
		deploy.setText("Deploy");
		deploy.addSelectionListener(new CommandProcessListener("tail -f " + tomcat + "/logs/catalina.out", output));

		ToolItem access = new ToolItem(toolbar, SWT.PUSH);
		access.setText("Access");
		access.addSelectionListener(new CommandProcessListener("tail -f " + tomcat + "/logs/localhost_access_log.*", output));

		ToolItem start = new ToolItem(toolbar, SWT.PUSH);
		start.setText("Start");
		start.addSelectionListener(new CommandProcessListener("sh " + tomcat + "/bin/startup.sh", output));

		ToolItem stop = new ToolItem(toolbar, SWT.PUSH);
		stop.setText("Stop");
		stop.addSelectionListener(new CommandProcessListener("sh " + tomcat + "/bin/shutdown.sh", output));

	}

	private void createBrowserGroup(SashForm sash) {
		
		Group browserGroup = new Group(sash, SWT.NONE);
		browserGroup.setLayout(new GridLayout());

		ToolBar toolbar = new ToolBar(browserGroup, SWT.HORIZONTAL);
		
		Browser browser = new Browser(browserGroup, SWT.BORDER);
		browser.setLayoutData(new GridData(GridData.FILL_BOTH));
		browser.setUrl(url);
		
		ToolItem home = new ToolItem(toolbar, SWT.PUSH);
	    home.setText("Home");
		home.addSelectionListener(new CommandBrowserListener("http://localhost:8080", browser));
		
		ToolItem logs = new ToolItem(toolbar, SWT.PUSH);
	    logs.setText("Docs");
		logs.addSelectionListener(new CommandBrowserListener("http://localhost:8080/docs/", browser));
	}

	public Composite createTabFolderPage(TabFolder tabFolder) {
		Composite panel = new Composite(tabFolder, SWT.NULL);
		panel.setLayout(new FillLayout());
		SashForm sash = new SashForm(panel, SWT.VERTICAL);
		createServerGroup(sash);
		createBrowserGroup(sash);
		return panel;
	}

}