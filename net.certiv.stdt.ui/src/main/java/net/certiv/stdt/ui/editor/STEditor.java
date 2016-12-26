package net.certiv.stdt.ui.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.DslPrefsKey;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DslEditor;
import net.certiv.dsl.ui.text.folding.IFoldingStructureProvider;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.editor.folding.STFoldingStructureProvider;

public class STEditor extends DslEditor {

	public static final String EDITOR_ID = "net.certiv.stdt.ui.STEditor";
	public static final String EDITOR_CONTEXT = "#STEditorContext";
	public static final String RULER_CONTEXT = "#STRulerContext";

	private static final String[] EDITOR_KEY_SCOPE = new String[] { "net.certiv.stdt.ui.stEditorScope" };
	private static final String MARK_OCCURRENCES_ANNOTATION_TYPE = "net.certiv.stdt.ui.occurrences";
	private static final char[] PAIRS = new char[] { '{', '}', '(', ')', '[', ']', '<', '>', '$', '$' };

	private DefaultCharacterPairMatcher pairMatcher = null;
	private IFoldingStructureProvider foldingProvider = null;

	public STEditor() {
		super();
		pairMatcher = new DefaultCharacterPairMatcher(PAIRS, Partitions.ST_PARTITIONING);
	}

	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		Log.debug(this, "ST editor started");
		setEditorContextMenuId(EDITOR_CONTEXT);
		setRulerContextMenuId(RULER_CONTEXT);
	}

	@Override
	public String getEditorId() {
		return EDITOR_ID;
	}

	@Override
	public String getMarkOccurrencesAnnotationType() {
		return MARK_OCCURRENCES_ANNOTATION_TYPE;
	}

	// //////////////////////////////////////////////////////////////////////////

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	public char[] getBrackets() {
		return STTextTools.PAIRS;
	}

	@Override
	protected IFoldingStructureProvider createFoldingStructureProvider() {
		if (foldingProvider == null) {
			foldingProvider = new STFoldingStructureProvider();
		}
		return foldingProvider;
	}

	// /////////////////////////////////////////////////////////////////////////

	@Override
	protected STOutlinePage doCreateOutlinePage() {
		return new STOutlinePage(this, getPreferenceStore());
	}

	@Override
	protected void connectPartitioningToElement(IEditorInput input, IDocument document) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension = (IDocumentExtension3) document;
			if (extension.getDocumentPartitioner(Partitions.ST_PARTITIONING) == null) {
				STDocumentSetupParticipant participant = new STDocumentSetupParticipant();
				participant.setup(document);
			}
		}
	}

	// //////////////////////////////////////////////////////////////////////////

	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(EDITOR_KEY_SCOPE);
	}

	@Override
	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
		support.setCharacterPairMatcher(pairMatcher);
		support.setMatchingCharacterPainterPreferenceKeys(
				getDslCore().getPrefsManager().bind(DslPrefsKey.EDITOR_MATCHING_BRACKETS),
				getDslCore().getPrefsManager().bind(DslPrefsKey.EDITOR_MATCHING_BRACKETS_COLOR));
		super.configureSourceViewerDecorationSupport(support);
	}
}
