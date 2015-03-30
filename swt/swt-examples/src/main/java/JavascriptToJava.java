/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/


/*
 * Browser example snippet: call Java from JavaScript.
 * 
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.5
 */
import org.eclipse.swt.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class JavascriptToJava {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setBounds(10, 10, 600, 500);

		Browser browser = new Browser(shell, SWT.NONE);
		browser.setText(createHTML());
		new CustomFunction(browser, "theJavaFunction");

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	static class CustomFunction extends BrowserFunction {
		CustomFunction(Browser browser, String name) {
			super(browser, name);
		}

		@Override
		public Object function(Object[] arguments) {
			System.out.println("theJavaFunction():");
			for (int i = 0; i < arguments.length; i++) {
				Object arg = arguments[i];
				System.out.println("\t" + arg.getClass().getName() + ": " + arg.toString());
			}
			return 3;
		}
	}

	static String createHTML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<input type=\"text\" name=\"message\" id=\"message\" />");
		sb.append("<input type=\"button\" value=\"Send\" onclick=\"theJavaFunction(document.getElementById('message').value, 'second', true, 42);\">\n");
		return sb.toString();
	}

}