package ca.magex.swt.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class CommandProcess implements SelectionListener {

	private String command;
	
	private Text output;
	
	private RunningListener listener;
	
	public CommandProcess(String command, Text output, RunningListener listener) {
		this.command = command;
		this.output = output;
		this.listener = listener;
	}
	
	public interface RunningListener {
		public void started();
		public void finished();
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		final String cmd = getCommand();
		new Thread(new Runnable() {

			@Override
			public void run() {
				listener.started();
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
					listener.finished();
				}
			}
		}).start();
	}

	public String getCommand() {
		return command;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

}
