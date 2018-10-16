package net.certiv.stdt.core.preferences;

import net.certiv.dsl.core.preferences.DslPrefsKey;

/**
 * Constant definitions for plug-in preferences
 */
public class Prefs extends DslPrefsKey {

	// custom formatter options
	private static final String FORMAT_SPACE = "formatSpace";
	private static final String FORMAT_WRAP = "formatWrap";
	private static final String FORMAT_LIST = "formatList";

	public static final String FORMAT_SPACE_COMMA = FORMAT_SPACE + "Comma";
	public static final String FORMAT_SPACE_COLON = FORMAT_SPACE + "Colon";
	public static final String FORMAT_SPACE_PAREN = FORMAT_SPACE + "Paren";
	public static final String FORMAT_SPACE_ASSIGN = FORMAT_SPACE + "Assign";
	public static final String FORMAT_SPACE_TMPL_ASSIGN = FORMAT_SPACE + "TmplAssign";
	public static final String FORMAT_SPACE_LBRACKET = FORMAT_SPACE + "LBracket";
	public static final String FORMAT_SPACE_RBRACKET = FORMAT_SPACE + "RBracket";

	public static final String FORMAT_WRAP_COMMA = FORMAT_WRAP + "Comma";
	public static final String FORMAT_WRAP_COLON = FORMAT_WRAP + "Colon";
	public static final String FORMAT_WRAP_OBRACKET = FORMAT_WRAP + "OBracket";
	public static final String FORMAT_WRAP_CBRACKET = FORMAT_WRAP + "CBracket";
	public static final String FORMAT_WRAP_LBRACKET = FORMAT_WRAP + "LBracket";
	public static final String FORMAT_WRAP_RBRACKET = FORMAT_WRAP + "RBracket";

	public static final String FORMAT_LIST_COMMA = FORMAT_LIST + "Comma";

}
