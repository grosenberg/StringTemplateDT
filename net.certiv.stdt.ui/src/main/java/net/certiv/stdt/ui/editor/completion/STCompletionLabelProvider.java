package net.certiv.stdt.ui.editor.completion;

import org.eclipse.jface.resource.ImageDescriptor;

import net.certiv.dsl.core.completion.CompletionProposal;
import net.certiv.dsl.ui.editor.text.completion.CompletionLabelProvider;
import net.certiv.stdt.ui.STUI;

public class STCompletionLabelProvider extends CompletionLabelProvider {

	public STCompletionLabelProvider() {
		super(STUI.getDefault());
	}

	@Override
	public String createLabel(CompletionProposal proposal) {
		return createSimpleLabel(proposal);
	}

	@Override
	public ImageDescriptor createImageDescriptor(CompletionProposal proposal) {
		return dslUI.getImageProvider().DESC_OBJS_KEYWORD;

	}
}
