package net.certiv.stdt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.text.AbstractBufferedRuleBasedScanner;
import net.certiv.stdt.core.preferences.PrefsKey;

public class ScannerCommentML extends AbstractBufferedRuleBasedScanner {

	private String[] fgTokenProperties;

	public ScannerCommentML(IDslPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (fgTokenProperties == null) {
			fgTokenProperties = new String[] { bind(PrefsKey.EDITOR_COMMENT_JD_COLOR),
					bind(PrefsKey.EDITOR_COMMENT_ML_COLOR) };
		}
		return fgTokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<IRule>();

		IToken token = getToken(bind(PrefsKey.EDITOR_COMMENT_JD_COLOR));
		rules.add(new MultiLineRule("/**", "*/", token, '\\'));
		
		token = getToken(bind(PrefsKey.EDITOR_COMMENT_ML_COLOR));
		setDefaultReturnToken(token);

		rules.add(new MultiLineRule("/*", "*/", token, '\\'));
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		return rules;
	}
}
