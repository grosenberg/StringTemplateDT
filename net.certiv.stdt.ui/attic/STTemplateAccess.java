package net.certiv.stdt.ui.templates;

import org.eclipse.jface.preference.IPreferenceStore;

import net.certiv.dsl.ui.templates.DslTemplateAccess;
import net.certiv.stdt.core.STCore;

/**
 * Provides access to the template delta.
 */
public class STTemplateAccess extends DslTemplateAccess {

	private static STTemplateAccess instance;

	public static STTemplateAccess getInstance() {
		if (instance == null) {
			instance = new STTemplateAccess();
		}
		return instance;
	}

	protected String getContextTypeId() {
		return STTemplateContextType.ST_CONTEXT_TYPE_ID;
	}

	protected String getCustomTemplatesKey() {
		return STTemplateContextType.ST_CUSTOM_TEMPLATES_KEY;
	}

	protected IPreferenceStore getPreferenceStore() {
		return STCore.getDefault().getPrefsManager();
	}
}
