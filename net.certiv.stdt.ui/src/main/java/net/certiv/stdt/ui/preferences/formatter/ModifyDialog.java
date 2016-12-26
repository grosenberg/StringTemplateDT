package net.certiv.stdt.ui.preferences.formatter;

import net.certiv.dsl.ui.formatter.FormatterModifyDialog;
import net.certiv.dsl.ui.formatter.IDslFormatterFactory;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialogOwner;

public class ModifyDialog extends FormatterModifyDialog {

	public ModifyDialog(IFormatterModifyDialogOwner dialogOwner, IDslFormatterFactory dslFormatterFactory) {
		super(dialogOwner, dslFormatterFactory);
	}

	protected void addPages() {
		addTabPage("Indents", new TabPageIndentation(this));
		addTabPage("White Space", new TabPageWhiteSpace(this));
		addTabPage("Blank Lines", new TabPageBlankLines(this));
		// addTabPage("Line Breaks", new TabPageLineBreaks(this));
		// addTabPage("Comments", new TabPageComments(this));
	}
}
