package ca.magex.swt.examples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class SimplePanel {

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		FormToolkit toolkit = new FormToolkit(display);
		Form form = toolkit.createForm(shell);
		toolkit.decorateFormHeading(form);
		form.setText("Test Title");
		form.getBody().setLayout(new GridLayout());

		Label label = new Label(form.getBody(), SWT.WRAP);
		label.setText("Body.");
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}