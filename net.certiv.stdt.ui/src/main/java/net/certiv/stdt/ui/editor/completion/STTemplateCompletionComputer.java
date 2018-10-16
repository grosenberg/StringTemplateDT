package net.certiv.stdt.ui.editor.completion;

import net.certiv.dsl.ui.editor.text.completion.DslContentAssistInvocationContext;
import net.certiv.dsl.ui.editor.text.completion.tmpl.DslTemplateCompletionProposalComputer;
import net.certiv.dsl.ui.editor.text.completion.tmpl.DslTemplateEngine;

public class STTemplateCompletionComputer extends DslTemplateCompletionProposalComputer {

	@Override
	protected DslTemplateEngine computeCompletionEngine(DslContentAssistInvocationContext context) {
		return null;
	}

	// @Override
	// protected DslCollector createCollector(DslContentAssistInvocationContext context) {
	// return new STCollector(context.getSourceModule());
	// }

	// @Override
	// protected TemplateCompletionProcessor
	// createTemplateProposalComputer(DslContentAssistInvocationContext context) {
	// return new STTemplateCompletionProcessor(context);
	// }
	//
	// // Completion proposals
	// @Override
	// public List<ICompletionProposal> computeCompletionProposals(ContentAssistInvocationContext
	// context,
	// IProgressMonitor monitor) {
	//
	// if (context instanceof DslContentAssistInvocationContext) {
	// DslContentAssistInvocationContext dslContext = (DslContentAssistInvocationContext) context;
	// int offset = dslContext.getInvocationOffset();
	// List<ICompletionProposal> proposals = new ArrayList<>();
	// proposals.addAll(computeTemplateCompletionProposals(offset, dslContext, monitor));
	// return proposals;
	// }
	// return Collections.emptyList();
	// }
}
