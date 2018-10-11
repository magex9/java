package ca.magex.swt.commands;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

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
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		serverGroup.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		serverGroup.setLayoutData(data);

		final Button deploy = new Button(serverGroup, SWT.PUSH);
		deploy.setText("Deploy");
		deploy.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Button start = new Button(serverGroup, SWT.PUSH);
		start.setText("Start");
		start.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Button stop = new Button(serverGroup, SWT.PUSH);
		stop.setText("Stop");
		stop.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		stop.setEnabled(false);

		Text output = new Text(serverGroup, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 3;
		gridData.heightHint = 150;
		output.setLayoutData(gridData);

		String tomcat = "~/apache-tomcat-8.5.34";
		
		CommandProcess.RunningListener listener = new CommandProcess.RunningListener() {
			@Override
			public void started() {
				System.out.println("Started");
			}
			@Override
			public void finished() {
				System.out.println("Finished");
			}
		};
		
		deploy.addSelectionListener(new CommandProcess("tail -f " + tomcat + "/logs/catalina.out", output, listener));
		start.addSelectionListener(new CommandProcess("sh " + tomcat + "/bin/startup.sh", output, listener));
		stop.addSelectionListener(new CommandProcess("sh " + tomcat + "/bin/shutdown.sh", output, listener));

	}

	private void createBrowserGroup(SashForm sash) {
		Group browserGroup = new Group(sash, SWT.NONE);
		browserGroup.setLayout(new GridLayout());

		Browser browser = new Browser(browserGroup, SWT.BORDER);
		browser.setLayoutData(new GridData(GridData.FILL_BOTH));
		browser.setUrl(url);
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