package net.certiv.stdt.ui.editor;

import org.eclipse.jface.preference.IPreferenceStore;
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
import net.certiv.dsl.core.IColorManager;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.core.util.Strings;
import net.certiv.dsl.core.util.TabStyle;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DoubleClickStrategy;
import net.certiv.dsl.ui.text.DslPresentationReconciler;
import net.certiv.dsl.ui.text.DslSourceViewerConfiguration;
import net.certiv.dsl.ui.text.completion.DslCompletionProcessor;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.editor.completion.STCompletionProcessor;
import net.certiv.stdt.ui.editor.strategies.STAutoEditDocStrategy;
import net.certiv.stdt.ui.editor.strategies.STAutoEditStrategy;
import net.certiv.stdt.ui.editor.strategies.STAutoEditStringStrategy;
import net.certiv.stdt.ui.editor.text.ScannerCommentML;
import net.certiv.stdt.ui.editor.text.ScannerCommentSL;
import net.certiv.stdt.ui.editor.text.ScannerKeyWord;
import net.certiv.stdt.ui.editor.text.ScannerTemplateSL;
import net.certiv.stdt.ui.formatter.strategies.STCommentFormattingStrategy;

public class STSourceViewerConfiguration extends DslSourceViewerConfiguration {

	private DoubleClickStrategy doubleClickStrategy;
	private ScannerKeyWord keyScanner;
	private ScannerTemplateSL templateSLScanner;
	private ScannerCommentSL commentSLScanner;
	private ScannerCommentML commentMLScanner;

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
	 * @param sourceViewer
	 *            the viewer that will contain the content to format
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
			return new String[] { Strings.getNSpaces(STCore.getDefault().getPrefsManager().getIndentationSize()) };
		} else {
			return new String[] { "\t" };
		}
	}

	@Override
	protected void initializeScanners() {
		IDslPrefsManager store = getPrefStore();
		keyScanner = new ScannerKeyWord(store);
		templateSLScanner = new ScannerTemplateSL(store);
		commentSLScanner = new ScannerCommentSL(store);
		commentMLScanner = new ScannerCommentML(store);
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new DslPresentationReconciler();
		reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
		buildRepairer(reconciler, templateSLScanner, Partitions.TEMPLATE_SL);
		buildRepairer(reconciler, commentSLScanner, Partitions.COMMENT_SL);
		buildRepairer(reconciler, commentMLScanner, Partitions.COMMENT_ML);
		buildRepairer(reconciler, keyScanner, IDocument.DEFAULT_CONTENT_TYPE);
		return reconciler;
	}

	/**
	 * Adapts the behavior of the contained components to the change encoded in the given event.
	 * <p>
	 * Clients are not allowed to call this method if the old setup with text tools is in use.
	 * </p>
	 * 
	 * @param event
	 *            the event to which to adapt
	 * @see PythonSourceViewerConfiguration#ScriptSourceViewerConfiguration(IColorManager,
	 *      IPreferenceStore, ITextEditor, String)
	 */
	@Override
	public void handlePropertyChangeEvent(PropertyChangeEvent event) {
		if (keyScanner.affectsBehavior(event)) keyScanner.adaptToPreferenceChange(event);
		if (templateSLScanner.affectsBehavior(event)) templateSLScanner.adaptToPreferenceChange(event);
		if (commentSLScanner.affectsBehavior(event)) commentSLScanner.adaptToPreferenceChange(event);
		if (commentMLScanner.affectsBehavior(event)) commentMLScanner.adaptToPreferenceChange(event);
	}

	/**
	 * Determines whether the preference change encoded by the given event changes the behavior of one
	 * of its contained components.
	 * 
	 * @param event
	 *            the event to be investigated
	 * @return <code>true</code> if event causes a behavioral change
	 */
	@Override
	public boolean affectsTextPresentation(PropertyChangeEvent event) {
		return keyScanner.affectsBehavior(event) //
				|| templateSLScanner.affectsBehavior(event) || commentSLScanner.affectsBehavior(event)
				|| commentMLScanner.affectsBehavior(event);
	}

	@Override
	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {

		String partitioning = getConfiguredDocumentPartitioning(sourceViewer);
		IAutoEditStrategy strategy;
		if (Partitions.COMMENT_ML.equals(contentType)) {
			strategy = new STAutoEditDocStrategy(partitioning);
		} else if (Partitions.TEMPLATE_SL.equals(contentType)) {
			strategy = new STAutoEditStringStrategy(partitioning);
		} else {
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

	// @Override
	// public IHyperlinkDetector getDslElementHyperlinkDetector(ITextEditor textEditor) {
	// return new STHyperlinkDetector(textEditor);
	// }

	@Override
	protected String getCommentPrefix() {
		return "//";
	}

	// @Override
	// protected IInformationControlCreator
	// getOutlinePresenterControlCreator(ISourceViewer
	// sourceViewer,
	// final String commandId) {
	// return new IInformationControlCreator() {
	//
	// public IInformationControl createInformationControl(Shell parent) {
	// int shellStyle = SWT.RESIZE;
	// int treeStyle = SWT.V_SCROLL | SWT.H_SCROLL;
	// return new STOutlineInformationControl(parent, shellStyle, treeStyle,
	// commandId);
	// }
	// };
	// }

	// @Override
	// public IContentAssistant getContentAssistant(ISourceViewer sourceViewer)
	// {
	// ContentAssistant ca = new ContentAssistant();
	// ca.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
	// ca.setRestoreCompletionProposalSize(getSettings("completion_proposal_size"));
	//
	// IContentAssistProcessor antlrProcessor = new
	// ProtoBufCompletionProcessor(getEditor(), ca,
	// IDocument.DEFAULT_CONTENT_TYPE);
	// ca.setContentAssistProcessor(antlrProcessor,
	// IDocument.DEFAULT_CONTENT_TYPE);
	//
	// IContentAssistProcessor singleLineProcessor = new
	// JavaCompletionProcessor(getEditor(), ca,
	// Partitions.COMMENT_SL);
	// ca.setContentAssistProcessor(singleLineProcessor, Partitions.COMMENT_SL);
	//
	// IContentAssistProcessor stringProcessor = new
	// JavaCompletionProcessor(getEditor(),
	// ca,
	// Partitions.STRING);
	// ca.setContentAssistProcessor(stringProcessor, Partitions.STRING);
	//
	// IContentAssistProcessor multiLineProcessor = new
	// JavaCompletionProcessor(getEditor(), ca,
	// Partitions.COMMENT_ML);
	// ca.setContentAssistProcessor(multiLineProcessor, Partitions.COMMENT_ML);
	//
	// ContentAssistPreference.configure(ca, store);
	//
	// ca.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
	// ca.setInformationControlCreator(getInformationControlCreator(sourceViewer));
	// return ca;
	// }

	// protected void initializeQuickOutlineContexts(InformationPresenter
	// presenter,
	// IInformationProvider provider) {
	// presenter.setInformationProvider(provider, Partitions.COMMENT_ML);
	// }

}
