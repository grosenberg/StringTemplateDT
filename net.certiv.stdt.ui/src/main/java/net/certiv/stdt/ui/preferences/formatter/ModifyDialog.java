package net.certiv.stdt.ui.preferences.formatter;

import net.certiv.dsl.ui.formatter.FormatterModifyDialog;
import net.certiv.dsl.ui.formatter.IDslFormatterFactory;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialogOwner;

public class ModifyDialog extends FormatterModifyDialog {

	public ModifyDialog(IFormatterModifyDialogOwner dialogOwner, IDslFormatterFactory dslFormatterFactory) {
		super(dialogOwner, dslFormatterFactory);
	}

	@Override
	protected void addPages() {
		addTabPage("General", new TabPageGeneral(this));
		addTabPage("Blank Lines", new TabPageBlankLines(this));
	}
}
