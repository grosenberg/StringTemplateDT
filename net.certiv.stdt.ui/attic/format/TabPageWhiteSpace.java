package net.certiv.stdt.ui.preferences.formatter;

import java.net.URL;

import org.eclipse.swt.widgets.Composite;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.formatter.FormatterModifyTabPage;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialog;
import net.certiv.dsl.ui.preferences.IControlCreationManager;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.core.preferences.PrefsKey;
import net.certiv.stdt.ui.STUI;

public class TabPageWhiteSpace extends FormatterModifyTabPage {

	String[] values = new String[] { PrefsKey.BEFORE, PrefsKey.AFTER, PrefsKey.AROUND, PrefsKey.NONE };
	String[] labels = new String[] { "Before", "After", "Around", "None" };

	public TabPageWhiteSpace(IFormatterModifyDialog dialog) {
		super(dialog);
	}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	protected void createOptions(final IControlCreationManager manager, Composite parent) {

		Composite spacing = createOptionGroup(parent, "Spaces", 2);

		manager.createCombo(spacing, bind(PrefsKey.FORMAT_SPACE_COMMA), "Comma ( \',\' )", values, labels);
		manager.createCombo(spacing, bind(PrefsKey.FORMAT_SPACE_TMPL_ASSIGN), "Template Assign ( \'::=\' )", values,
				labels);
		manager.createCombo(spacing, bind(PrefsKey.FORMAT_SPACE_ASSIGN), "Equal ( \'=\' )", values, labels);

		manager.createCombo(spacing, bind(PrefsKey.FORMAT_SPACE_PAREN), "Parens ( \'(\' & \')\' )", values, labels);
		manager.createCombo(spacing, bind(PrefsKey.FORMAT_SPACE_COLON), "Colon ( \':\' )", values, labels);
		manager.createCombo(spacing, bind(PrefsKey.FORMAT_SPACE_LBRACKET), "Open bracket ( \'[\' )", values, labels);
		manager.createCombo(spacing, bind(PrefsKey.FORMAT_SPACE_RBRACKET), "Close bracket ( \']\' )", values, labels);

		manager.createCombo(spacing, bind(PrefsKey.FORMAT_LIST_COMMA), "List separator", values, labels);

		// /////////////////////////////////

		Composite wrap = createOptionGroup(parent, "Line wrap", 2);

		manager.createCombo(wrap, bind(PrefsKey.FORMAT_WRAP_COMMA), "Comma ( \',\' )", values, labels);
		manager.createCombo(wrap, bind(PrefsKey.FORMAT_WRAP_COLON), "Equals ( \'=\' )", values, labels);

		manager.createCombo(wrap, bind(PrefsKey.FORMAT_WRAP_LBRACKET), "Open bracket ( \'[\' )", values, labels);
		manager.createCombo(wrap, bind(PrefsKey.FORMAT_WRAP_RBRACKET), "Close bracket ( \']\' )", values, labels);

		manager.createCombo(wrap, bind(PrefsKey.FORMAT_WRAP_OBRACKET), "Object open ( \'[[\' )", values, labels);
		manager.createCombo(wrap, bind(PrefsKey.FORMAT_WRAP_CBRACKET), "Object close ( \']]\' )", values, labels);

	}

	protected URL getPreviewContent() {
		return getClass().getResource("WhiteSpacePreview.stg"); //$NON-NLS-1$
	}
}
