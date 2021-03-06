/*******************************************************************************
 * Copyright (c) 2012, 2020 Certiv Analytics.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package net.certiv.st.dt.ui.editor;

import static net.certiv.dsl.ui.editor.text.completion.engines.IPrefixStops.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.MultiPassContentFormatter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Image;

import net.certiv.common.util.Strings;
import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.DslColorRegistry;
import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.core.util.eclipse.TabStyle;
import net.certiv.dsl.ui.DslImageManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DoubleClickStrategy;
import net.certiv.dsl.ui.editor.DslEditor;
import net.certiv.dsl.ui.editor.DslSourceViewerConfiguration;
import net.certiv.dsl.ui.editor.reconcile.PresentationReconciler;
import net.certiv.dsl.ui.editor.semantic.StylesManager;
import net.certiv.dsl.ui.editor.text.completion.CompletionCategory;
import net.certiv.dsl.ui.editor.text.completion.CompletionProcessor;
import net.certiv.dsl.ui.editor.text.completion.engines.ICompletionEngine;
import net.certiv.dsl.ui.editor.text.completion.engines.KeywordEngine;
import net.certiv.dsl.ui.editor.text.completion.engines.TemplateEngine;
import net.certiv.st.dt.core.STCore;
import net.certiv.st.dt.ui.STUI;
import net.certiv.st.dt.ui.editor.strategies.STAutoEditDocStrategy;
import net.certiv.st.dt.ui.editor.strategies.STAutoEditStrategy;
import net.certiv.st.dt.ui.editor.text.ScannerCommentSL;
import net.certiv.st.dt.ui.editor.text.ScannerJDComment;
import net.certiv.st.dt.ui.editor.text.ScannerKeyword;
import net.certiv.st.dt.ui.editor.text.ScannerMLComment;
import net.certiv.st.dt.ui.formatter.strategies.STCommentFormattingStrategy;

public class STSourceViewerConfiguration extends DslSourceViewerConfiguration {

	private DoubleClickStrategy doubleClickStrategy;
	private ScannerKeyword keywordScanner;
	private ScannerCommentSL commentSLScanner;
	private ScannerMLComment commentMLScanner;
	private ScannerJDComment commentJDScanner;

	public STSourceViewerConfiguration(DslColorRegistry reg, IPrefsManager store, DslEditor editor,
			String partitioning) {
		super(STCore.getDefault(), reg, store, editor, partitioning);
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

	@Override
	public void specializeContentAssistant(ContentAssistant assistant) {
		STStatementLabelProvider provider = new STStatementLabelProvider();
		DslImageManager imgMgr = getDslUI().getImageManager();
		Image imgKeyword = imgMgr.get(imgMgr.IMG_OBJS_KEYWORD);
		Set<Character> stops = new HashSet<>(Arrays.asList(LBRACE, LBRACE, LPAREN, COLON, COMMA, SEMI, PIPE, AT));

		ICompletionEngine keywords = new KeywordEngine(imgKeyword, stops, ScannerKeyword.KEYWORDS);
		ICompletionEngine templates = new TemplateEngine(provider, stops);

		CompletionCategory lang = new CompletionCategory("ST", true, false, keywords);
		CompletionCategory tmpl = new CompletionCategory("ST Templates", false, true, templates);

		CompletionProcessor proc = new CompletionProcessor(getDslUI(), assistant, lang, tmpl);
		assistant.setContentAssistProcessor(proc, IDocument.DEFAULT_CONTENT_TYPE);
	}

	/**
	 * Loads content formatters into the SourceViewer for execution on receipt of a
	 * ISourceViewer.FORMAT command. Where nothing is selected in the document,
	 * <b>should</b> execute the master strategy for the default partition and then the
	 * slave strategies for the non-default partitions.
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
			return new String[] { Strings.dup(STCore.getDefault().getPrefsManager().getTabWidth(), Strings.SPACE) };
		} else {
			return new String[] { Strings.TAB };
		}
	}

	@Override
	protected void initializeScanners() {
		IPrefsManager store = getPrefStore();
		StylesManager mgr = getDslUI().getStylesManager();

		keywordScanner = new ScannerKeyword(store, mgr);
		commentSLScanner = new ScannerCommentSL(store, mgr);
		commentMLScanner = new ScannerMLComment(store, mgr);
		commentJDScanner = new ScannerJDComment(store, mgr);
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler(getDslUI());
		reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
		buildRepairer(reconciler, commentSLScanner, Partitions.COMMENT_SL);
		buildRepairer(reconciler, commentMLScanner, Partitions.COMMENT_ML);
		buildRepairer(reconciler, commentJDScanner, Partitions.COMMENT_JD);
		buildRepairer(reconciler, keywordScanner, IDocument.DEFAULT_CONTENT_TYPE);
		return reconciler;
	}

	/**
	 * Adapts the behavior of the contained components to the change encoded in the given
	 * event.
	 * <p>
	 * Clients are not allowed to call this method if the old setup with text tools is in
	 * use.
	 *
	 * @param event the event to which to adapt
	 * @see PythonSourceViewerConfiguration#ScriptSourceViewerConfiguration(DslColorRegistry,
	 *          IPreferenceStore, DslEditor, String)
	 */
	@Override
	public void handlePropertyChangeEvent(PropertyChangeEvent event) {
		if (keywordScanner.affectsBehavior(event)) keywordScanner.adaptToPreferenceChange(event);
		if (commentSLScanner.affectsBehavior(event)) commentSLScanner.adaptToPreferenceChange(event);
		if (commentMLScanner.affectsBehavior(event)) commentMLScanner.adaptToPreferenceChange(event);
		if (commentJDScanner.affectsBehavior(event)) commentJDScanner.adaptToPreferenceChange(event);
	}

	/**
	 * Determines whether the preference change encoded by the given event changes the
	 * behavior of one of its contained components.
	 *
	 * @param event the event to be investigated
	 * @return {@code true} if event causes a behavioral change
	 */
	@Override
	public boolean affectsTextPresentation(PropertyChangeEvent event) {
		return keywordScanner.affectsBehavior(event) || commentSLScanner.affectsBehavior(event)
				|| commentMLScanner.affectsBehavior(event) || commentJDScanner.affectsBehavior(event);
	}

	@Override
	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer viewer, String contentType) {
		String partitioning = getConfiguredDocumentPartitioning(viewer);
		IAutoEditStrategy strategy;
		switch (contentType) {
			case Partitions.COMMENT_JD:
			case Partitions.COMMENT_ML:
				strategy = new STAutoEditDocStrategy(partitioning);
				break;
			default:
				strategy = new STAutoEditStrategy(partitioning);
		}
		return new IAutoEditStrategy[] { strategy };
	}

	@Override
	protected String getCommentPrefix() {
		return "//";
	}
}
