package net.certiv.stdt.ui.preferences.page;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.IColorManager;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.formatter.DslFormatterPreferencePage;
import net.certiv.dsl.ui.text.DslSourceViewerConfiguration;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.editor.Partitions;
import net.certiv.stdt.ui.editor.STSimpleSourceViewerConfiguration;

/** Preference page for formatting */
public class PrefPageFormatter extends DslFormatterPreferencePage {

	@Override
	protected DslSourceViewerConfiguration createSimpleSourceViewerConfiguration(IColorManager colorMgr,
			IPreferenceStore store, ITextEditor editor, boolean configureFormatter) {

		return new STSimpleSourceViewerConfiguration(colorMgr, (IDslPrefsManager) store, editor,
				Partitions.ST_PARTITIONING, configureFormatter);
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
