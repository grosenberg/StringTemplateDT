package net.certiv.st.dt.ui.preferences.page;

import org.eclipse.jface.text.IDocument;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.IColorManager;
import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.core.preferences.PrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.dsl.ui.preferences.pages.DslTemplatePreferencePage;
import net.certiv.dsl.ui.templates.CompletionManager;
import net.certiv.st.dt.core.STCore;
import net.certiv.st.dt.ui.STUI;
import net.certiv.st.dt.ui.editor.Partitions;
import net.certiv.st.dt.ui.editor.STSimpleSourceViewerConfiguration;
import net.certiv.st.dt.ui.editor.STSourceViewerConfiguration;

public class TemplatesPage extends DslTemplatePreferencePage {

	public TemplatesPage() {
		super();
		PrefsManager delta = STCore.getDefault().getPrefsManager();
		setPreferenceStore(delta);
	}

	@Override
	protected STSourceViewerConfiguration createSourceViewerConfiguration() {
		return new STSimpleSourceViewerConfiguration(getColorManager(), (IPrefsManager) getPreferenceStore(), null,
				Partitions.PARTITIONING, false);
	}

	@Override
	protected void setDocumentPartitioner(IDocument document) {
		getTextTools().setupDocumentPartitioner(document, Partitions.PARTITIONING);
	}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	private IColorManager getColorManager() {
		return STCore.getDefault().getColorManager();
	}

	private DslTextTools getTextTools() {
		return STUI.getDefault().getTextTools();
	}

	@Override
	protected CompletionManager getCompletionMgr() {
		return STUI.getDefault().getCompletionMgr();
	}
}