package ca.magex.swt.examples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class ExpandableText {

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		FormToolkit toolkit = new FormToolkit(display);
		Form form = toolkit.createForm(shell);
		toolkit.decorateFormHeading(form);
		form.setText("Test Title");
		form.getBody().setLayout(new GridLayout());

		ExpandableComposite ec = toolkit.createExpandableComposite(form.getBody(), Section.TWISTIE);
		ec.setText("Test Expandable Composite");
		ec.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Composite client = new Composite(ec, SWT.BORDER);
		client.setLayout(new GridLayout());

		Label label = new Label(client, SWT.WRAP);
		label.setText("This snippet shows a Expandable Composite with a twistie.");
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		ec.setClient(client);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}