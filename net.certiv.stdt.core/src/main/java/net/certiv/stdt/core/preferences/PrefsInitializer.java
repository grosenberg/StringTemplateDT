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

		setString(Prefs.FORMAT_SPACE_COLON, Prefs.AROUND);
		setString(Prefs.FORMAT_SPACE_ASSIGN, Prefs.NONE);
		setString(Prefs.FORMAT_SPACE_COMMA, Prefs.AFTER);
		setString(Prefs.FORMAT_SPACE_LBRACKET, Prefs.BEFORE);
		setString(Prefs.FORMAT_SPACE_RBRACKET, Prefs.NONE);
		setString(Prefs.FORMAT_SPACE_PAREN, Prefs.BEFORE);
		setString(Prefs.FORMAT_SPACE_TMPL_ASSIGN, Prefs.NONE);

		setString(Prefs.FORMAT_WRAP_COLON, Prefs.NONE);
		setString(Prefs.FORMAT_WRAP_COMMA, Prefs.NONE);
		setString(Prefs.FORMAT_WRAP_LBRACKET, Prefs.AFTER);
		setString(Prefs.FORMAT_WRAP_RBRACKET, Prefs.AFTER);
		setString(Prefs.FORMAT_WRAP_OBRACKET, Prefs.NONE);
		setString(Prefs.FORMAT_WRAP_CBRACKET, Prefs.NONE);

		setString(Prefs.FORMAT_LIST_COMMA, Prefs.AFTER);
	}
}
