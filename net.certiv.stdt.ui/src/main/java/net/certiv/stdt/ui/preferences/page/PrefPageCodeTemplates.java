package net.certiv.stdt.ui.preferences.page;

import org.eclipse.jface.text.IDocument;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.IColorManager;
import net.certiv.dsl.core.preferences.DslPrefsManager;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.dsl.ui.templates.DslTemplateAccess;
import net.certiv.dsl.ui.templates.DslTemplatePreferencePage;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.editor.Partitions;
import net.certiv.stdt.ui.editor.STSimpleSourceViewerConfiguration;
import net.certiv.stdt.ui.editor.STSourceViewerConfiguration;
import net.certiv.stdt.ui.templates.STTemplateAccess;

public class PrefPageCodeTemplates extends DslTemplatePreferencePage {

	public PrefPageCodeTemplates() {
		super();
		DslPrefsManager delta = STCore.getDefault().getPrefsManager();
		delta.setDefaultProject(null);
		setPreferenceStore(delta);
	}

	protected STSourceViewerConfiguration createSourceViewerConfiguration() {
		return new STSimpleSourceViewerConfiguration(getColorManager(), (IDslPrefsManager) getPreferenceStore(), null,
				Partitions.ST_PARTITIONING, false);
	}

	protected void setDocumentPartitioner(IDocument document) {
		getTextTools().setupDocumentPartitioner(document, Partitions.ST_PARTITIONING);
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
	protected DslTemplateAccess getTemplateAccess() {
		return STTemplateAccess.getInstance();
	}
}
