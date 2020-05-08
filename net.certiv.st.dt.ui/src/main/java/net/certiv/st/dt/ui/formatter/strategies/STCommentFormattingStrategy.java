package net.certiv.st.dt.ui.formatter.strategies;

import net.certiv.dsl.core.preferences.PrefsManager;
import net.certiv.dsl.jdt.ui.formatter.strategies.CommentFormattingStrategy;
import net.certiv.st.dt.core.STCore;

public class STCommentFormattingStrategy extends CommentFormattingStrategy {

	@Override
	public PrefsManager getFormatterPrefs() {
		return STCore.getDefault().getPrefsManager();
	}
}
