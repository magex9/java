package ca.magex.swt.commands;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class CommandLayout {

	public CommandLayout(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.NULL);
		CommandTab[] tabs = new CommandTab[] { 
			new CommandTab("Web", "http://google.ca"), 
			new CommandTab("Server", "http://www.apple.ca"),
			new CommandTab("Cache", "http://www.stackoverflow.com"),
			new CommandTab("Database", "http://redis.com") 
		};
		for (int i = 0; i < tabs.length; i++) {
			TabItem item = new TabItem(tabFolder, SWT.NULL);
			item.setText(tabs[i].getName());
			item.setControl(tabs[i].createTabFolderPage(tabFolder));
		}
	}

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setSize(1000, 1200);
		shell.setLayout(new FillLayout());
		
		new CommandLayout(shell);
		
		shell.setText("Commander");
		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				Shell[] shells = display.getShells();
				for (int i = 0; i < shells.length; i++) {
					if (shells[i] != shell)
						shells[i].close();
				}
			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
