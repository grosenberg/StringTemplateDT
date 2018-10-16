package net.certiv.stdt.ui.preferences.page;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.IColorManager;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DslSourceViewerConfiguration;
import net.certiv.dsl.ui.formatter.IDslFormatterFactory;
import net.certiv.dsl.ui.preferences.pages.DslFormatterPreferencePage;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.editor.Partitions;
import net.certiv.stdt.ui.editor.STSimpleSourceViewerConfiguration;
import net.certiv.stdt.ui.preferences.formatter.FormatterFactory;

/** Preference page for formatting */
public class FormatterPage extends DslFormatterPreferencePage {

	private FormatterFactory factory;

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	protected DslSourceViewerConfiguration createSimpleSourceViewerConfiguration(IColorManager colorMgr,
			IPreferenceStore store, ITextEditor editor, boolean configureFormatter) {

		return new STSimpleSourceViewerConfiguration(colorMgr, (IDslPrefsManager) store, editor,
				Partitions.PARTITIONING, configureFormatter);
	}

	@Override
	protected IDslFormatterFactory getFormatterFactory() {
		if (factory == null) {
			factory = new FormatterFactory();
		}
		return factory;
	}
}
