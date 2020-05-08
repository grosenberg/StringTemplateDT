package net.certiv.st.dt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.ui.editor.scanners.AbstractBufferedRuleBasedScanner;
import net.certiv.st.dt.core.preferences.Prefs;

public class ScannerJDComment extends AbstractBufferedRuleBasedScanner {

	private String[] tokens;

	public ScannerJDComment(IPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokens == null) {
			tokens = new String[] { bind(Prefs.EDITOR_COMMENT_DC_COLOR) };
		}
		return tokens;
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<IRule>();
		IToken token = getToken(bind(Prefs.EDITOR_COMMENT_DC_COLOR));
		rules.add(new MultiLineRule("/**", "*/", token, '\\'));
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		return rules;
	}
}
