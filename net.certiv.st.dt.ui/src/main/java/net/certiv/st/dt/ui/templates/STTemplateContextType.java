package net.certiv.st.dt.ui.templates;

import net.certiv.dsl.ui.editor.text.completion.DslTemplateContextType;

public class STTemplateContextType extends DslTemplateContextType {

	public static final String ST_CONTEXT_TYPE_ID = "net.certiv.st.dt.ui.DefaultContext";
	public static final String ST_CUSTOM_TEMPLATES_KEY = "net.certiv.st.dt.ui.CustomContext.templates";

	public STTemplateContextType(String id) {
		super(id);
	}

	public STTemplateContextType(String id, String name) {
		super(id, name);
	}
}
