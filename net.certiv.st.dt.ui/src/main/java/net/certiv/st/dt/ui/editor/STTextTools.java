package net.certiv.st.dt.ui.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import net.certiv.dsl.core.color.DslColorRegistry;
import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.ui.editor.DslEditor;
import net.certiv.dsl.ui.editor.DslSourceViewerConfiguration;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.st.dt.core.STCore;

public class STTextTools extends DslTextTools {

	private PartitionScanner partitionScanner;

	public STTextTools(boolean autoDispose) {
		super(Partitions.PARTITIONING, Partitions.getContentTypes(), autoDispose);
	}

	@Override
	public void createAutoClosePairs() {
		for (String contentType : Partitions.getAllContentTypes()) {
			addAutoClosePair(contentType, "{", "}");
			addAutoClosePair(contentType, "(", ")");
			addAutoClosePair(contentType, "[", "]");
			addAutoClosePair(contentType, "<", ">");
			addAutoClosePair(contentType, "$", "$");
			addAutoClosePair(contentType, "%", "%");
		}
	}

	@Override
	public void createAutoIndentPairs() {
		// addAutoIndentPair(IDocument.DEFAULT_CONTENT_TYPE, "{", "}");
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
	public DslSourceViewerConfiguration createSourceViewerConfiguraton(IPrefsManager store, DslEditor editor,
			String partitioning) {
		return new STSourceViewerConfiguration(getColorManager(), store, editor, partitioning);
	}

	@Override
	public SourceViewerConfiguration createSimpleSourceViewerConfiguration(IPrefsManager store,
			String partitioning) {
		return new STSimpleSourceViewerConfiguration(store, null, partitioning);
	}

	private DslColorRegistry getColorManager() {
		return STCore.getDefault().getColorRegistry();
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
