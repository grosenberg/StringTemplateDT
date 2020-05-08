package net.certiv.st.dt.ui.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.consts.Editor;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DslEditor;
import net.certiv.dsl.ui.editor.text.DslWordFinder;
import net.certiv.dsl.ui.editor.text.folding.IFoldingStructureProvider;
import net.certiv.st.dt.core.STCore;
import net.certiv.st.dt.ui.STUI;
import net.certiv.st.dt.ui.editor.folding.STFoldingStructureProvider;

public class STEditor extends DslEditor {

	public static final String EDITOR_ID = "net.certiv.st.dt.ui.STEditor";
	public static final String EDITOR_CONTEXT = "#STEditorContext";
	public static final String RULER_CONTEXT = "#STRulerContext";

	private static final String[] EDITOR_KEY_SCOPE = new String[] { "net.certiv.st.dt.ui.stEditorScope" };
	private static final String MARK_OCCURRENCES_ANNOTATION_TYPE = "net.certiv.st.dt.ui.occurrences";
	private static final char[] PAIRS = new char[] { '{', '}', '(', ')', '[', ']', '<', '>', '$', '$', '%', '%' };

	private DefaultCharacterPairMatcher pairMatcher = new DefaultCharacterPairMatcher(PAIRS, Partitions.PARTITIONING);
	private DslWordFinder finder = new DslWordFinder();

	private IFoldingStructureProvider foldingProvider;

	public STEditor() {
		super();
	}

	@Override
	public String getEditorId() {
		return EDITOR_ID;
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
	public char[] getBrackets() {
		return PAIRS;
	}

	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		setEditorContextMenuId(EDITOR_CONTEXT);
		setRulerContextMenuId(RULER_CONTEXT);
	}

	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(EDITOR_KEY_SCOPE);
	}

	@Override
	public String getMarkOccurrencesAnnotationType() {
		return MARK_OCCURRENCES_ANNOTATION_TYPE;
	}

	@Override
	protected IRegion findWord(IDocument doc, int offset) {
		return finder.findWord(doc, offset);
	}

	// /////////////////////////////////////////////////////////////////////////

	@Override
	protected STOutlinePage doCreateOutlinePage() {
		return new STOutlinePage(getPreferenceStore());
	}

	@Override
	protected IFoldingStructureProvider createFoldingStructureProvider() {
		if (foldingProvider == null) {
			foldingProvider = new STFoldingStructureProvider();
		}
		return foldingProvider;
	}

	@Override
	protected void connectPartitioningToElement(IEditorInput input, IDocument document) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension = (IDocumentExtension3) document;
			if (extension.getDocumentPartitioner(Partitions.PARTITIONING) == null) {
				STDocumentSetupParticipant participant = new STDocumentSetupParticipant();
				participant.setup(document);
			}
		}
	}

	@Override
	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
		support.setCharacterPairMatcher(pairMatcher);
		support.setMatchingCharacterPainterPreferenceKeys(bind(Editor.EDITOR_MATCHING_BRACKETS),
				bind(Editor.EDITOR_MATCHING_BRACKETS_COLOR));
		super.configureSourceViewerDecorationSupport(support);
	}
}
