package net.certiv.stdt.ui.editor.completion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

import net.certiv.dsl.ui.editor.text.completion.ContentAssistInvocationContext;
import net.certiv.dsl.ui.editor.text.completion.DslCollector;
import net.certiv.dsl.ui.editor.text.completion.DslCompletionProposalComputer;
import net.certiv.dsl.ui.editor.text.completion.DslContentAssistInvocationContext;
import net.certiv.stdt.ui.templates.STTemplateCompletionProcessor;

public class STCodeCompletionComputer extends DslCompletionProposalComputer {

	@Override
	protected DslCollector createCollector(DslContentAssistInvocationContext context) {
		return new STCollector(context.getSourceModule());
	}

	@Override
	protected TemplateCompletionProcessor createTemplateProposalComputer(DslContentAssistInvocationContext context) {
		return new STTemplateCompletionProcessor(context);
	}

	@Override
	public List<ICompletionProposal> computeCompletionProposals(ContentAssistInvocationContext context,
			IProgressMonitor monitor) {

		if (context instanceof DslContentAssistInvocationContext) {
			DslContentAssistInvocationContext dslContext = (DslContentAssistInvocationContext) context;
			int offset = dslContext.getInvocationOffset();
			List<ICompletionProposal> proposals = new ArrayList<>();
			// Language specific proposals (already sorted and etc.)
			proposals.addAll(computeDslCompletionProposals(offset, dslContext, monitor));
			return proposals;
		}
		return Collections.emptyList();
	}

}
