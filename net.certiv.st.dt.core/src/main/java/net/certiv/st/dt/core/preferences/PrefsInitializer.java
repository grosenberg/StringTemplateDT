/*******************************************************************************
 * Copyright (c) 2012, 2020 Certiv Analytics.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package net.certiv.st.dt.core.preferences;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.DslPrefsInit;
import net.certiv.dsl.core.preferences.consts.Formatter;
import net.certiv.st.dt.core.STCore;

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

		setString(Formatter.FORMATTER_CORPUS_LANGUAGE, "stg");

		setString(Prefs.FORMAT_SPACE_COLON, Formatter.AROUND);
		setString(Prefs.FORMAT_SPACE_ASSIGN, Formatter.NONE);
		setString(Prefs.FORMAT_SPACE_COMMA, Formatter.AFTER);
		setString(Prefs.FORMAT_SPACE_LBRACKET, Formatter.BEFORE);
		setString(Prefs.FORMAT_SPACE_RBRACKET, Formatter.NONE);
		setString(Prefs.FORMAT_SPACE_PAREN, Formatter.BEFORE);
		setString(Prefs.FORMAT_SPACE_TMPL_ASSIGN, Formatter.NONE);

		setString(Prefs.FORMAT_WRAP_COLON, Formatter.NONE);
		setString(Prefs.FORMAT_WRAP_COMMA, Formatter.NONE);
		setString(Prefs.FORMAT_WRAP_LBRACKET, Formatter.AFTER);
		setString(Prefs.FORMAT_WRAP_RBRACKET, Formatter.AFTER);
		setString(Prefs.FORMAT_WRAP_OBRACKET, Formatter.NONE);
		setString(Prefs.FORMAT_WRAP_CBRACKET, Formatter.NONE);

		setString(Prefs.FORMAT_LIST_COMMA, Formatter.AFTER);
	}
}
