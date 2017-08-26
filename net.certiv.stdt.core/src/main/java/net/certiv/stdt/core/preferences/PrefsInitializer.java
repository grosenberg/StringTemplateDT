package net.certiv.stdt.core.preferences;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.DslPrefsInit;
import net.certiv.stdt.core.STCore;

public class PrefsInitializer extends DslPrefsInit {

	public PrefsInitializer() {
		super();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	public void initializeDefaultPreferences() {
		super.initializeDefaultPreferences();

		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_SPACE_COLON, PrefsKey.AROUND);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_SPACE_ASSIGN, PrefsKey.NONE);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_SPACE_COMMA, PrefsKey.AFTER);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_SPACE_LBRACKET, PrefsKey.BEFORE);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_SPACE_RBRACKET, PrefsKey.NONE);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_SPACE_PAREN, PrefsKey.BEFORE);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_SPACE_TMPL_ASSIGN, PrefsKey.NONE);

		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_WRAP_COLON, PrefsKey.NONE);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_WRAP_COMMA, PrefsKey.NONE);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_WRAP_LBRACKET, PrefsKey.AFTER);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_WRAP_RBRACKET, PrefsKey.AFTER);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_WRAP_OBRACKET, PrefsKey.NONE);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_WRAP_CBRACKET, PrefsKey.NONE);

		getDslCore().getPrefsManager().setValue(PrefsKey.FORMAT_LIST_COMMA, PrefsKey.AFTER);

		getDslCore().getPrefsManager().setValue(PrefsKey.FORMATTER_COMMENT_HEADER_ENABLE, false);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMATTER_COMMENT_FORMAT, false);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMATTER_INDENT_COMMENT_MULTILINE, false);
		getDslCore().getPrefsManager().setValue(PrefsKey.FORMATTER_INDENT_COMMENT_SINGLELINE, false);
	}
}
