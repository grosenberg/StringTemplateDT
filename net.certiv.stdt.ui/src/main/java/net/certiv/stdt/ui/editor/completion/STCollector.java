package net.certiv.stdt.ui.editor.completion;

import java.util.Set;

import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.completion.CompletionProposal;
import net.certiv.dsl.core.model.DslModelException;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.text.completion.CompletionLabelProvider;
import net.certiv.dsl.ui.text.completion.DslCollector;
import net.certiv.dsl.ui.text.completion.DslCompletionProposal;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.editor.text.ScannerKeyWord;

public class STCollector extends DslCollector {

	public STCollector(ICodeUnit cu) {
		super(cu);
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
	protected CompletionLabelProvider createCompletionProposalLabelProvider() {
		return new STCompletionLabelProvider();
	}

	@Override
	protected DslCompletionProposal createDslCompletionProposal(String completion, int offset, int length, Image image,
			String label, int relevance) {
		return createDslCompletionProposal(completion, offset, length, image, new StyledString(label), relevance,
				false);
	}

	@Override
	protected DslCompletionProposal createDslCompletionProposal(String completion, int offset, int length, Image image,
			StyledString label, int relevance, boolean inDoc) {
		return new STCompletionProposal(completion, offset, length, image, label, relevance, inDoc);
	}

	@Override
	protected char[] getVarTrigger() {
		return VAR_TRIGGER;
	}

	/**
	 * @param offset invocation offset
	 */
	@Override
	public void prepareProposals(ICodeUnit unit, int offset) throws DslModelException {

		if (!parseValid()) return;

		// 1) handle lexer and parser rule names: captured as a list of tokens
		Set<IStatement> rules = getDslCore().getModelManager().getCodeAssistElements(unit);
		for (IStatement rule : rules) {
			char[] name = rule.getElementName().toCharArray();
			int type = CompletionProposal.METHOD_REF; // parser
			if (Character.isUpperCase(name[0])) type = CompletionProposal.FIELD_REF; // lexer
			CompletionProposal proposal = CompletionProposal.create(type, offset);
			proposal.setName(name);
			proposal.setCompletion(name);
			accept(proposal);
		}

		// 2) handle keywords: exists as a static list
		for (String word : ScannerKeyWord.KEYWORDS) {
			CompletionProposal proposal = CompletionProposal.create(CompletionProposal.KEYWORD, offset);
			char[] name = word.toCharArray();
			proposal.setName(name);
			proposal.setCompletion(name);
			accept(proposal);
		}
	}
}
