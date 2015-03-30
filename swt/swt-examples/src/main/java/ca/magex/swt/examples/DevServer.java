package ca.magex.swt.examples;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

public class DevServer {

	private Map<String, Widget> fields;

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		new DevServer(shell);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public DevServer(Shell shell) {
		Display display = shell.getDisplay();
		shell.setSize(800, 600);
		shell.setText("Server");
		GridLayout gridLayout = new GridLayout(4, false);
		shell.setLayout(gridLayout);

		fields = new HashMap<String, Widget>();
		fields.put("host", addTextField(shell, "Context Path: "));
		fields.put("port", addTextField(shell, "Port: "));
		fields.put("configs",
				addComboBox(shell, "Configurations: ", new String[] {
						"bootstrap-spring-config.xml", 
						"xyz-spring-config.xml" }));
		fields.put("environment",
				addComboBox(shell, "Environment: ", new String[] { 
						"Dev", "QA", "Production" }));
		fields.put("server",
				addComboBox(shell, "Server: ", new String[] { "Tomcat",
						"JBoss", "Jetty", "Web Logic", "Web Sphere" }));
		fields.put("authentication",
				addComboBox(shell, "Authentication: ", new String[] { "None",
						"OS", "Username/Password" }));

		addEnterButton(shell);
		addBrowser(shell);

		// Fill information.
		loadData();

		shell.open();

		// Set up the event loop.
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// If no more entries in event queue
				display.sleep();
			}
		}

	}

	private void loadData() {
		((Text) fields.get("host")).setText("/app");
		((Text) fields.get("port")).setText("9999");
		((Combo) fields.get("configs")).setText("bootstrap-spring-config.xml");
		((Combo) fields.get("environment")).setText("Dev");
		((Combo) fields.get("server")).setText("Tomcat");
		((Combo) fields.get("authentication")).setText("Username/Password");
	}

	private Button addEnterButton(Shell shell) {
		Button enter = new Button(shell, SWT.PUSH);
		enter.setText("Start Server");

		GridData gridData = new GridData();
		gridData.horizontalSpan = 4;
		gridData.horizontalAlignment = GridData.END;
		enter.setLayoutData(gridData);
		return enter;
	}
	
	private Browser addBrowser(Shell shell) {
		Browser browser = new Browser(shell,  SWT.BORDER);
		//browser.setText("Hello There. Start the server to see more");
		browser.setUrl("http://www.google.com");

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 4;
		browser.setLayoutData(gridData);

		return browser;
	}

	private Combo addComboBox(Shell shell, String labelText, String[] values) {
		Label label = new Label(shell, SWT.NULL);
		label.setText(labelText);

		Combo combo = new Combo(shell, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		for (String value : values) {
			combo.add(value);
		}

		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		combo.setLayoutData(gridData);
		return combo;
	}

	private Text addTextField(Shell shell, String labelText) {
		Label label = new Label(shell, SWT.NULL);
		label.setText(labelText);

		Text field = new Text(shell, SWT.SINGLE | SWT.BORDER);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		field.setLayoutData(gridData);
		return field;
	}

}
