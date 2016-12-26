package net.certiv.stdt.ui.templates;

import net.certiv.dsl.core.model.ITranslationUnit;
import net.certiv.dsl.ui.templates.DslTemplateContext;
import net.certiv.dsl.ui.templates.DslTemplateContextType;

import org.eclipse.jface.text.IDocument;

public class STTemplateContextType extends DslTemplateContextType {

	public static final String ST_CONTEXT_TYPE_ID = "net.certiv.stdt.ui.DefaultContext";
	public static final String ST_CUSTOM_TEMPLATES_KEY = "net.certiv.stdt.ui.CustomContext.templates";

	public STTemplateContextType() {}

	public STTemplateContextType(String id) {
		super(id);
	}

	public STTemplateContextType(String id, String name) {
		super(id, name);
	}

	@Override
	public DslTemplateContext createContext(IDocument document, int completionPosition, int length,
			ITranslationUnit sourceModule) {

		return new STTemplateContext(this, document, completionPosition, length, sourceModule);
	}

	@Override
	protected void addDslResolvers() {}
}
