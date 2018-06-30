package net.certiv.stdt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.editor.text.AbstractBufferedRuleBasedScanner;
import net.certiv.stdt.core.preferences.PrefsKey;

public class ScannerMLComment extends AbstractBufferedRuleBasedScanner {

	private String[] tokens;

	public ScannerMLComment(IDslPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokens == null) {
			tokens = new String[] { bind(PrefsKey.EDITOR_COMMENT_ML_COLOR) };
		}
		return tokens;
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<IRule>();
		IToken token = getToken(bind(PrefsKey.EDITOR_COMMENT_ML_COLOR));
		// setDefaultReturnToken(token);
		rules.add(new MultiLineRule("/*", "*/", token, '\\'));
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		return rules;
	}
}
