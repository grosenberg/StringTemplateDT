package net.certiv.st.dt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.core.preferences.consts.Editor;
import net.certiv.dsl.ui.editor.scanners.AbstractBufferedRuleBasedScanner;
import net.certiv.dsl.ui.editor.text.rules.CharSequenceRule;
import net.certiv.dsl.ui.editor.text.rules.CharsRule;
import net.certiv.dsl.ui.editor.text.rules.DslWordRule;

public class ScannerKeyword extends AbstractBufferedRuleBasedScanner {

	public static final String[] KEYWORDS = { "delimiters", "import", "key", "default", "first", "last", "rest",
			"trunc", "strip", "trim", "length", "strlen", "reverse", "@super", "@end", "if", "else", "endif", "elseif",
			"group", "format", "wrap", "anchor", "separator", "true", "false" //
	};
	public static final String[] SEQUENCES = { "::=", "<<", ">>", "<!", "!>", "<%", "%>", "\\n" };
	public static final char[] OPERATORS = { '{', '}', '[', ']', '<', '>', '(', ')', //
			'@', '=', ':', '|', ';', ':', ',', '.', '$', '%', '!' //
	};
	public static final char[] STRINGS = { '"', '\\' };

	private String[] tokenProperties;

	public ScannerKeyword(IPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokenProperties == null) {
			tokenProperties = new String[] { bind(Editor.EDITOR_KEYWORDS_COLOR), bind(Editor.EDITOR_SYMBOLS_COLOR),
					bind(Editor.EDITOR_STRING_COLOR) };
		}
		return tokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		IToken keyToken = getToken(bind(Editor.EDITOR_KEYWORDS_COLOR));
		IToken strToken = getToken(bind(Editor.EDITOR_STRING_COLOR));
		IToken symToken = getToken(bind(Editor.EDITOR_SYMBOLS_COLOR));

		List<IRule> rules = new ArrayList<>();
		rules.add(new WhitespaceRule(new WhitespaceDetector()));

		DslWordRule rule = new DslWordRule(new WordDetector());
		for (String element : KEYWORDS) {
			rule.addWord(element, keyToken);
		}
		rules.add(rule);

		rules.add(new CharsRule(OPERATORS, symToken));
		rules.add(new CharsRule(STRINGS, strToken));

		for (String seq : SEQUENCES) {
			rules.add(new CharSequenceRule(seq, symToken));
		}
		return rules;
	}
}
