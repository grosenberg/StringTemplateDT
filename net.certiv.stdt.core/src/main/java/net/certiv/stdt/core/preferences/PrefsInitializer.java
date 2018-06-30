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

		setString(PrefsKey.FORMAT_SPACE_COLON, PrefsKey.AROUND);
		setString(PrefsKey.FORMAT_SPACE_ASSIGN, PrefsKey.NONE);
		setString(PrefsKey.FORMAT_SPACE_COMMA, PrefsKey.AFTER);
		setString(PrefsKey.FORMAT_SPACE_LBRACKET, PrefsKey.BEFORE);
		setString(PrefsKey.FORMAT_SPACE_RBRACKET, PrefsKey.NONE);
		setString(PrefsKey.FORMAT_SPACE_PAREN, PrefsKey.BEFORE);
		setString(PrefsKey.FORMAT_SPACE_TMPL_ASSIGN, PrefsKey.NONE);

		setString(PrefsKey.FORMAT_WRAP_COLON, PrefsKey.NONE);
		setString(PrefsKey.FORMAT_WRAP_COMMA, PrefsKey.NONE);
		setString(PrefsKey.FORMAT_WRAP_LBRACKET, PrefsKey.AFTER);
		setString(PrefsKey.FORMAT_WRAP_RBRACKET, PrefsKey.AFTER);
		setString(PrefsKey.FORMAT_WRAP_OBRACKET, PrefsKey.NONE);
		setString(PrefsKey.FORMAT_WRAP_CBRACKET, PrefsKey.NONE);

		setString(PrefsKey.FORMAT_LIST_COMMA, PrefsKey.AFTER);
	}
}
