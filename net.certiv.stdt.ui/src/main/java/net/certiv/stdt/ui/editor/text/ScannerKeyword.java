package net.certiv.stdt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.editor.scanners.AbstractBufferedRuleBasedScanner;
import net.certiv.dsl.ui.editor.text.rules.CharSequenceRule;
import net.certiv.dsl.ui.editor.text.rules.CharsRule;
import net.certiv.dsl.ui.editor.text.rules.DslWordRule;
import net.certiv.stdt.core.preferences.Prefs;

public class ScannerKeyword extends AbstractBufferedRuleBasedScanner {

	public static final String[] KEYWORDS = { "delimiters", "import", "key", "default", "first", "last", "rest",
			"trunc", "strip", "trim", "length", "strlen", "reverse", "@super", "@end", "if", "else", "endif", "elseif",
			"group", "format", "wrap", "anchor", "separator", "true", "false" };

	public static final String[] SEQUENCES = { "::=", "<<", ">>", "<!", "!>", "<%", "%>" };

	public static final char[] OPERATORS = { '{', '}', '[', ']', '<', '>', '(', ')', '@', '=', ':', '|', ';', ':', ',',
			'.', '"', '$', '%', '!', '\\' };

	private String[] tokenProperties;

	public ScannerKeyword(IDslPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokenProperties == null) {
			tokenProperties = new String[] { bind(Prefs.EDITOR_KEYWORDS_COLOR) };
		}
		return tokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		IToken keyToken = getToken(bind(Prefs.EDITOR_KEYWORDS_COLOR));

		List<IRule> rules = new ArrayList<IRule>();
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		rules.add(new CharsRule(OPERATORS, keyToken));

		for (String seq : SEQUENCES) {
			rules.add(new CharSequenceRule(seq, keyToken));
		}

		DslWordRule rule = new DslWordRule(new WordDetector());
		for (String element : KEYWORDS) {
			rule.addWord(element, keyToken);
		}
		rules.add(rule);
		return rules;
	}
}
