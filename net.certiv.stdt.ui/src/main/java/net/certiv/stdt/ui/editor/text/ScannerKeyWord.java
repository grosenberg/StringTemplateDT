package net.certiv.stdt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.text.AbstractBufferedRuleBasedScanner;
import net.certiv.dsl.ui.text.rules.DslWordRule;
import net.certiv.stdt.core.preferences.PrefsKey;

public class ScannerKeyWord extends AbstractBufferedRuleBasedScanner {

	public static final String[] KEYWORDS = { "delimiters", "import", "group", "keys", "values", "default", "first",
			"last", "rest", "trunc", "strip", "trim", "length", "strlen", "reverse", "::=", "<!", "!>", "<%", "%>", "{",
			"}", "[", "]", "<", ">", "(", ")", "@", "=", ":", "|", ";", "@super", "@end", "if", "else", "endif",
			"elseif", "wrap", "anchor", "separator", };

	private String[] fgTokenProperties;

	public ScannerKeyWord(IDslPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (fgTokenProperties == null) {
			fgTokenProperties = new String[] { bind(PrefsKey.EDITOR_KEYWORDS_COLOR) };
		}
		return fgTokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		DslWordRule rule = new DslWordRule(new WordDetector());
		IToken keywordToken = getToken(bind(PrefsKey.EDITOR_KEYWORDS_COLOR));
		for (String element : KEYWORDS) {
			rule.addWord(element, keywordToken);
		}

		List<IRule> rules = new ArrayList<IRule>();
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		rules.add(rule);
		return rules;
	}
}
