package net.certiv.stdt.ui.editor.completion;

import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;

import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.completion.CompletionLabelProvider;
import net.certiv.dsl.ui.editor.text.completion.DslCompletionProcessor;
import net.certiv.dsl.ui.editor.text.completion.DslCompletionProposal;
import net.certiv.dsl.ui.templates.DslTemplateContext;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

public class STCompletionProcessor extends DslCompletionProcessor {

	public STCompletionProcessor(IEditorPart editor, ContentAssistant assistant, String partition) {
		super(editor, assistant, partition);
	}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public STCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	protected CompletionLabelProvider getProposalLabelProvider() {
		return new STCompletionLabelProvider();
	}

	@Override
	public void createCategories() {
		addCategory(DslCompletionProposal.ID, DslCompletionProposal.NAME, new STCodeCompletionComputer());
		addCategory(DslTemplateContext.ID, DslTemplateContext.NAME, new STTemplateCompletionComputer());
	}
}
