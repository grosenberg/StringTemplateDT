package net.certiv.stdt.ui.templates;

import org.eclipse.jface.text.IDocument;

import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.ui.editor.text.completion.tmpl.DslTemplateContext;
import net.certiv.dsl.ui.editor.text.completion.tmpl.DslTemplateContextType;

public class STTemplateContextType extends DslTemplateContextType {

	public static final String ST_CONTEXT_TYPE_ID = "net.certiv.stdt.ui.DefaultContext";
	public static final String ST_CUSTOM_TEMPLATES_KEY = "net.certiv.stdt.ui.CustomContext.templates";

	public STTemplateContextType(String id) {
		super(id);
	}

	public STTemplateContextType(String id, String name) {
		super(id, name);
	}

	@Override
	public DslTemplateContext createContext(IDocument document, int completionPosition, int length,
			ICodeUnit sourceModule) {

		return new STTemplateContext(this, document, completionPosition, length, sourceModule);
	}

	@Override
	protected void addDslResolvers() {}
}
