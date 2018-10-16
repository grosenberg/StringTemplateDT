package net.certiv.stdt.ui.preferences.formatter;

import java.net.URL;

import org.eclipse.swt.widgets.Composite;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.formatter.FormatterModifyTabPage;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialog;
import net.certiv.dsl.ui.preferences.bind.IControlCreationManager;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.core.preferences.Prefs;
import net.certiv.stdt.ui.STUI;

public class TabBlankLines extends FormatterModifyTabPage {

	public TabBlankLines(IFormatterModifyDialog dialog) {
		super(dialog);
	}

	@Override
	protected void createOptions(IControlCreationManager manager, Composite parent) {
		Composite blanks = createOptionGroup(parent, "Blank Lines", 2);

		manager.createNumber(blanks, bind(Prefs.FORMATTER_EMPTY_LINES_TO_PRESERVE), "Blank lines to preserve: ");
		manager.createNumber(blanks, bind(Prefs.FORMATTER_EMPTY_LINES_AFTER_TERMINAL),
				"Required blank lines after terminal: ");
	}

	@Override
	protected URL getPreviewContent() {
		return getClass().getResource("PreviewBlanklines.stg"); //$NON-NLS-1$
	}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}
}
