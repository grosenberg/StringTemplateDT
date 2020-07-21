package net.certiv.st.dt.ui.editor;

import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.templates.CompletionManager;

public class STCompletionManager extends CompletionManager {

	public static final String GROUP = "group";

	public STCompletionManager(DslUI ui, String editorId) {
		super(ui, editorId, GROUP);
	}

	@Override
	public String getContentAssistScope(IStatement stmt) {
		switch (stmt.getModelType()) {
			case STATEMENT:
				return GROUP;

			default:
				return null;
		}
	}
}
