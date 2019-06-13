package net.certiv.stdt.ui.formatter.strategies;

import net.certiv.dsl.core.preferences.DslPrefsManager;
import net.certiv.dsl.jdt.ui.formatter.strategies.CommentFormattingStrategy;
import net.certiv.stdt.core.STCore;

public class STCommentFormattingStrategy extends CommentFormattingStrategy {

	@Override
	public DslPrefsManager getFormatterPrefs() {
		return STCore.getDefault().getPrefsManager();
	}
}
