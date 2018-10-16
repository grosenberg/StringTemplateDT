package net.certiv.stdt.ui.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.color.IColorManager;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.editor.DslSourceViewerConfiguration;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.stdt.core.STCore;

public class STTextTools extends DslTextTools {

	private PartitionScanner partitionScanner;

	public STTextTools(boolean autoDispose) {
		super(Partitions.PARTITIONING, Partitions.getContentTypes(), autoDispose);
	}

	@Override
	public void createAutoClosePairs() {
		for (String contentType : Partitions.getAllContentTypes()) {
			addAutoClosePair(contentType, '{', '}');
			addAutoClosePair(contentType, '(', ')');
			addAutoClosePair(contentType, '[', ']');
			addAutoClosePair(contentType, '<', '>');
			addAutoClosePair(contentType, '$', '$');
			addAutoClosePair(contentType, '%', '%');
		}
	}

	@Override
	public void createAutoIndentPairs() {
		// addAutoIndentPair(IDocument.DEFAULT_CONTENT_TYPE, '{', '}');
	}

	@Override
	public void createAutoCommentPairs() {
		addAutoCommentPair(IDocument.DEFAULT_CONTENT_TYPE, "/**", "*/");
		addAutoCommentPair(IDocument.DEFAULT_CONTENT_TYPE, "/*", "*/");
	}

	@Override
	public void createStringDelimPairs() {
		for (String contentType : Partitions.getAllContentTypes()) {
			addStringDelimPairs(contentType, '\'');
			addStringDelimPairs(contentType, '"');
		}
	}

	@Override
	public DslSourceViewerConfiguration createSourceViewerConfiguraton(IDslPrefsManager store, ITextEditor editor,
			String partitioning) {
		return new STSourceViewerConfiguration(getColorManager(), store, editor, partitioning);
	}

	@Override
	public SourceViewerConfiguration createSimpleSourceViewerConfiguration(IDslPrefsManager store,
			String partitioning) {
		return new STSimpleSourceViewerConfiguration(store, null, partitioning);
	}

	private IColorManager getColorManager() {
		return STCore.getDefault().getColorManager();
	}

	@Override
	public IPartitionTokenScanner createPartitionScanner() {
		if (partitionScanner == null) {
			partitionScanner = new PartitionScanner();
		}
		return partitionScanner;
	}

	@Override
	public String[] getStringContentPartitions() {
		return Partitions.STRING_TYPES;
	}

	@Override
	public String[] getCommentContentPartitions() {
		return Partitions.COMMENT_TYPES;
	}

	@Override
	public String[] getStringAndCommentContentPartitions() {
		return Partitions.STRING_AND_COMMENT_TYPES;
	}
}
