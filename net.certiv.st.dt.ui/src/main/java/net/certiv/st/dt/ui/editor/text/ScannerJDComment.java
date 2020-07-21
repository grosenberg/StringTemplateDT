package net.certiv.st.dt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.core.preferences.consts.Editor;
import net.certiv.dsl.ui.editor.scanners.DslRuleBasedScanner;
import net.certiv.dsl.ui.editor.semantic.StylesManager;

public class ScannerJDComment extends DslRuleBasedScanner {

	private String[] tokens;

	public ScannerJDComment(IPrefsManager store, StylesManager stylesMgr) {
		super(store, stylesMgr);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokens == null) {
			tokens = new String[] { bind(Editor.EDITOR_COMMENT_DC_COLOR) };
		}
		return tokens;
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<>();
		IToken token = getToken(bind(Editor.EDITOR_COMMENT_DC_COLOR));
		rules.add(new MultiLineRule("/**", "*/", token, '\\'));
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		return rules;
	}
}
