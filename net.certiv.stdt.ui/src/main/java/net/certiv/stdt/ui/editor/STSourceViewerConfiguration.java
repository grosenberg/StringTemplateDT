package net.certiv.stdt.ui.editor;

import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.MultiPassContentFormatter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.IColorManager;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.core.util.Strings;
import net.certiv.dsl.core.util.eclipse.TabStyle;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DoubleClickStrategy;
import net.certiv.dsl.ui.editor.DslPresentationReconciler;
import net.certiv.dsl.ui.editor.DslSourceViewerConfiguration;
import net.certiv.dsl.ui.editor.text.completion.DslCompletionProcessor;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.editor.completion.STCompletionProcessor;
import net.certiv.stdt.ui.editor.strategies.STAutoEditDocStrategy;
import net.certiv.stdt.ui.editor.strategies.STAutoEditStrategy;
import net.certiv.stdt.ui.editor.text.ScannerCommentSL;
import net.certiv.stdt.ui.editor.text.ScannerJDComment;
import net.certiv.stdt.ui.editor.text.ScannerKeyword;
import net.certiv.stdt.ui.editor.text.ScannerMLComment;
import net.certiv.stdt.ui.formatter.strategies.STCommentFormattingStrategy;

public class STSourceViewerConfiguration extends DslSourceViewerConfiguration {

	private DoubleClickStrategy doubleClickStrategy;
	private ScannerKeyword keywordScanner;
	private ScannerCommentSL commentSLScanner;
	private ScannerMLComment commentMLScanner;
	private ScannerJDComment commentJDScanner;

	public STSourceViewerConfiguration(IColorManager colorManager, IDslPrefsManager store, ITextEditor editor,
			String partitioning) {
		super(colorManager, store, editor, partitioning);
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
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return Partitions.getContentTypes(IDocument.DEFAULT_CONTENT_TYPE);
	}

	/**
	 * Loads content formatters into the SourceViewer for execution on receipt of a ISourceViewer.FORMAT
	 * command. Where nothing is selected in the document, <b>should</b> execute the master strategy for
	 * the default partition and then the slave strategies for the non-default partitions.
	 *
	 * @param sourceViewer the viewer that will contain the content to format
	 * @return the content formatter
	 */
	@Override
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		// IContentFormatter formatter =
		// super.getContentFormatter(sourceViewer);
		MultiPassContentFormatter formatter = (MultiPassContentFormatter) super.getContentFormatter(sourceViewer);
		formatter.setSlaveStrategy(new STCommentFormattingStrategy(), Partitions.COMMENT_JD);
		formatter.setSlaveStrategy(new STCommentFormattingStrategy(), Partitions.COMMENT_ML);
		formatter.setSlaveStrategy(new STCommentFormattingStrategy(), Partitions.COMMENT_SL);
		return formatter;
	}

	@Override
	public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType) {
		if (doubleClickStrategy == null) {
			doubleClickStrategy = new DoubleClickStrategy();
		}
		return doubleClickStrategy;
	}

	@Override
	public String[] getIndentPrefixes(ISourceViewer sourceViewer, String contentType) {
		if (STCore.getDefault().getPrefsManager().getTabStyle() == TabStyle.SPACES) {
			return new String[] { Strings.dup(STCore.getDefault().getPrefsManager().getIndentationSize(), " ") };
		} else {
			return new String[] { "\t" };
		}
	}

	@Override
	protected void initializeScanners() {
		IDslPrefsManager store = getPrefStore();
		keywordScanner = new ScannerKeyword(store);
		commentSLScanner = new ScannerCommentSL(store);
		commentMLScanner = new ScannerMLComment(store);
		commentJDScanner = new ScannerJDComment(store);
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new DslPresentationReconciler();
		reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
		buildRepairer(reconciler, commentSLScanner, Partitions.COMMENT_SL);
		buildRepairer(reconciler, commentMLScanner, Partitions.COMMENT_ML);
		buildRepairer(reconciler, commentJDScanner, Partitions.COMMENT_JD);
		buildRepairer(reconciler, keywordScanner, IDocument.DEFAULT_CONTENT_TYPE);
		return reconciler;
	}

	/**
	 * Adapts the behavior of the contained components to the change encoded in the given event.
	 * <p>
	 * Clients are not allowed to call this method if the old setup with text tools is in use.
	 * </p>
	 *
	 * @param event the event to which to adapt
	 * @see PythonSourceViewerConfiguration#ScriptSourceViewerConfiguration(IColorManager,
	 *      IPreferenceStore, ITextEditor, String)
	 */
	@Override
	public void handlePropertyChangeEvent(PropertyChangeEvent event) {
		if (keywordScanner.affectsBehavior(event)) keywordScanner.adaptToPreferenceChange(event);
		if (commentSLScanner.affectsBehavior(event)) commentSLScanner.adaptToPreferenceChange(event);
		if (commentMLScanner.affectsBehavior(event)) commentMLScanner.adaptToPreferenceChange(event);
		if (commentJDScanner.affectsBehavior(event)) commentJDScanner.adaptToPreferenceChange(event);
	}

	/**
	 * Determines whether the preference change encoded by the given event changes the behavior of one
	 * of its contained components.
	 *
	 * @param event the event to be investigated
	 * @return <code>true</code> if event causes a behavioral change
	 */
	@Override
	public boolean affectsTextPresentation(PropertyChangeEvent event) {
		return keywordScanner.affectsBehavior(event) || commentSLScanner.affectsBehavior(event)
				|| commentMLScanner.affectsBehavior(event) || commentJDScanner.affectsBehavior(event);
	}

	@Override
	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {

		String partitioning = getConfiguredDocumentPartitioning(sourceViewer);
		IAutoEditStrategy strategy;
		switch (contentType) {
			case Partitions.COMMENT_JD:
			case Partitions.COMMENT_ML:
				strategy = new STAutoEditDocStrategy(partitioning);
				break;
			case Partitions.COMMENT_SL:
			default:
				strategy = new STAutoEditStrategy(partitioning);
		}
		return new IAutoEditStrategy[] { strategy };
	}

	@Override
	protected void alterContentAssistant(ContentAssistant assistant) {
		DslCompletionProcessor processor;
		processor = new STCompletionProcessor(getEditor(), assistant, IDocument.DEFAULT_CONTENT_TYPE);
		assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);
	}

	@Override
	protected String getCommentPrefix() {
		return "//";
	}
}
