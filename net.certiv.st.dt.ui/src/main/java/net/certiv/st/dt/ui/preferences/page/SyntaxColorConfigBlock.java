package net.certiv.st.dt.ui.preferences.page;

import java.io.InputStream;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import net.certiv.dsl.core.color.DslColorRegistry;
import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.core.preferences.PrefsDeltaManager;
import net.certiv.dsl.core.preferences.consts.Editor;
import net.certiv.dsl.ui.editor.DslEditor;
import net.certiv.dsl.ui.editor.DslSourceViewer;
import net.certiv.dsl.ui.editor.DslSourceViewerConfiguration;
import net.certiv.dsl.ui.preferences.blocks.AbstractSyntaxColorConfigBlock;
import net.certiv.st.dt.ui.STUI;
import net.certiv.st.dt.ui.editor.Partitions;
import net.certiv.st.dt.ui.editor.STSimpleSourceViewerConfiguration;

public class SyntaxColorConfigBlock extends AbstractSyntaxColorConfigBlock {

	private static final String PREVIEW_FILE_NAME = "ColorPreview.g4";  // $NON-NLS-1$

	public SyntaxColorConfigBlock(SyntaxColorPage page, PrefsDeltaManager delta, FormToolkit formkit,
			DslColorRegistry reg) {
		super(page, delta, formkit, reg);
	}

	@Override
	protected List<String> createDeltaMatchKeys(List<String> keys) {
		keys.add(Editor.EDITOR_COMMENT_DC_COLOR);
		keys.add(Editor.EDITOR_COMMENT_BL_COLOR);
		keys.add(Editor.EDITOR_COMMENT_LN_COLOR);
		keys.add(Editor.EDITOR_KEYWORDS_COLOR);
		keys.add(Editor.EDITOR_STRING_COLOR);
		keys.add(Editor.EDITOR_SYMBOLS_COLOR);
		return keys;
	}

	@Override
	protected void initCatPrefsModel() {
		addColorPreference("Comments", "JavaDoc", Editor.EDITOR_COMMENT_DC_COLOR);
		addColorPreference("Comments", "Block", Editor.EDITOR_COMMENT_BL_COLOR);
		addColorPreference("Comments", "Single line", Editor.EDITOR_COMMENT_LN_COLOR);
		addColorPreference("Template", "Keywords", Editor.EDITOR_KEYWORDS_COLOR);
		addColorPreference("Template", "Strings", Editor.EDITOR_STRING_COLOR);
		addColorPreference("Template", "Symbols", Editor.EDITOR_SYMBOLS_COLOR);
	}

	@Override
	protected ProjectionViewer createPreviewViewer(Composite parent, IVerticalRuler verticalRuler,
			IOverviewRuler overviewRuler, boolean showAnnotationsOverview, int styles, IPrefsManager store) {
		return new DslSourceViewer(page.getDslUI(), parent, verticalRuler, overviewRuler, showAnnotationsOverview,
				styles, store);
	}

	@Override
	protected DslSourceViewerConfiguration createSimpleSourceViewerConfiguration(DslColorRegistry colorMgr,
			IPrefsManager store, DslEditor editor, boolean configFormatter) {
		return new STSimpleSourceViewerConfiguration(colorMgr, store, editor, Partitions.PARTITIONING, configFormatter);
	}

	@Override
	protected void setDocumentPartitioning(IDocument document) {
		STUI.getDefault().getTextTools().setupDocumentPartitioner(document, Partitions.PARTITIONING);
	}

	@Override
	protected InputStream getPreviewContentStream() {
		return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
	}
}
