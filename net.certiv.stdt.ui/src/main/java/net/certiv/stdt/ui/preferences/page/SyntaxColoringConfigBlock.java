package net.certiv.stdt.ui.preferences.page;

import java.io.InputStream;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.IColorManager;
import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DslSourceViewer;
import net.certiv.dsl.ui.preferences.blocks.AbstractEditorColoringConfigurationBlock;
import net.certiv.dsl.ui.preferences.blocks.IPreferenceConfigurationBlock;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.core.preferences.PrefsKey;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.editor.Partitions;
import net.certiv.stdt.ui.editor.STDocumentSetupParticipant;
import net.certiv.stdt.ui.editor.STSimpleSourceViewerConfiguration;
import net.certiv.stdt.ui.editor.STSourceViewerConfiguration;

public class SyntaxColoringConfigBlock extends AbstractEditorColoringConfigurationBlock
		implements IPreferenceConfigurationBlock {

	private static final String PREVIEW_FILE_NAME = "PrefsColorSample.stg";

	private static final String[][] fSyntaxColorListModel = new String[][] {
			{ "JavaDoc Comment", PrefsKey.EDITOR_COMMENT_JD_COLOR, CmtsCat },
			{ "Multi-line Comment", PrefsKey.EDITOR_COMMENT_ML_COLOR, CmtsCat },
			{ "Single line Comment", PrefsKey.EDITOR_COMMENT_SL_COLOR, CmtsCat },
			{ "Keywords", PrefsKey.EDITOR_KEYWORDS_COLOR, CoreCat } };

	public SyntaxColoringConfigBlock(DslPrefsManagerDelta delta, PreferencePage page) {
		super(delta, page);
	}

	@Override
	protected String[][] getSyntaxColorListModel() {
		return fSyntaxColorListModel;
	}

	@Override
	protected ProjectionViewer createPreviewViewer(Composite parent, IVerticalRuler verticalRuler,
			IOverviewRuler overviewRuler, boolean showAnnotationsOverview, int styles, IPreferenceStore store) {
		return new DslSourceViewer(this, parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles, store);
	}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	protected STSourceViewerConfiguration createSimpleSourceViewerConfiguration(IColorManager colorMgr,
			IPreferenceStore store, ITextEditor editor, boolean configureFormatter) {
		return new STSimpleSourceViewerConfiguration(colorMgr, (IDslPrefsManager) store, editor,
				Partitions.ST_PARTITIONING, configureFormatter);
	}

	@Override
	protected void setDocumentPartitioning(IDocument document) {
		STDocumentSetupParticipant participant = new STDocumentSetupParticipant();
		participant.setup(document);
	}

	@Override
	protected InputStream getPreviewContentReader() {
		return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
	}
}
