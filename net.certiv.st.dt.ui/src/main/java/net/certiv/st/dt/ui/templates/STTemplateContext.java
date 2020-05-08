package net.certiv.st.dt.ui.templates;

import org.eclipse.jface.text.IRegion;

import net.certiv.dsl.ui.editor.text.completion.CompletionContext;
import net.certiv.dsl.ui.editor.text.completion.DslTemplateContext;

public class STTemplateContext extends DslTemplateContext {

	protected STTemplateContext(STTemplateContextType type, CompletionContext context, IRegion region) {
		super(type, context, region);
	}
}
