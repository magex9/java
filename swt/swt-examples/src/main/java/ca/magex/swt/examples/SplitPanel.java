package ca.magex.swt.examples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SplitPanel {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		SashForm eastSash = new SashForm(shell, SWT.HORIZONTAL);

		Text east = new Text(eastSash, SWT.CENTER);
		east.setText("East");

		Text west = new Text(eastSash, SWT.CENTER);
		west.setText("West");

		eastSash.setWeights(new int[] { 1, 2 });

		shell.setSize(600, 300);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}
	
}
