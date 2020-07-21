package net.certiv.st.dt.ui.templates;

import org.eclipse.jface.text.templates.TemplateVariableResolver;

import net.certiv.dsl.ui.editor.text.completion.DslTemplateContextType;

public class STContextType extends DslTemplateContextType {

	public STContextType(String id, TemplateVariableResolver... resolvers) {
		super(id, resolvers);
	}
}
