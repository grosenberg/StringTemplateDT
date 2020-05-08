package net.certiv.st.dt.ui;

import org.eclipse.jface.text.IAutoEditStrategy;

import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DefaultAutoEditSemicolonStrategy;
import net.certiv.dsl.ui.templates.CompletionManager;

public class STCompletionManager extends CompletionManager {

	public STCompletionManager(DslUI ui, String editorId) {
		super(ui, editorId, MODULE);
	}

	@Override
	public String getContentAssistScope(IStatement stmt) {
		switch (stmt.getModelType()) {
			case MODULE:
			case DECLARATION:
			case STATEMENT:
				return MODULE;

			case IMPORT:
			case FIELD:
			case BEG_BLOCK:
			case END_BLOCK:
			default:
				return null;
		}
	}

	@Override
	public IAutoEditStrategy getSmartTriggerStrategy(String contentType) {
		String partitioning = ui.getTextTools().getDocumentPartitioning();
		// TODO: customize the strategy
		return new DefaultAutoEditSemicolonStrategy(partitioning, mgr);
	}
}
