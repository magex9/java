package ca.magex.swt.examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CommandRunner {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		shell.setLayout(new GridLayout(1, true));
		shell.setSize(1000, 800);
		
		Text command = new Text(shell, SWT.BORDER);
		command.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		command.setText("curl http://www.google.ca");
		
		Button button = new Button(shell, SWT.BORDER);
		button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		button.setText("Run");
		
		Text output = new Text(shell, SWT.BORDER);
		output.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				final String cmd = command.getText();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						ProcessBuilder builder = new ProcessBuilder();
					    builder.command("sh", "-c", cmd);
						builder.redirectErrorStream(true);
						builder.directory(new File(System.getProperty("user.home")));
						
						String line = null;
						BufferedReader reader = null;
						try {
							Process process = builder.start();
							reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
							Display.getDefault().asyncExec(() -> {
								output.setText("");
							});
							while ((line = reader.readLine()) != null) {
								final String msg = line;
								Display.getDefault().asyncExec(() -> {
									output.append(msg + "\n");
								});
							}
							if (process != null)
								process.waitFor();
						} catch (Exception ex) {
							ex.printStackTrace();
						} finally {
							if (reader == null)
								return;
							try {
								reader.close();
							} catch (IOException ioe) {
								ioe.printStackTrace();
							}
						}
					}
				}).start();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
			
		});
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}
	

}