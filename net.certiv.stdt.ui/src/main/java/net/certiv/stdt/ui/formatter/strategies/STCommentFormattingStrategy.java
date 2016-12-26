package net.certiv.stdt.ui.formatter.strategies;

import org.eclipse.jface.preference.IPreferenceStore;

import net.certiv.dsl.ui.formatter.strategies.CommentFormattingStrategy;
import net.certiv.stdt.core.STCore;

public class STCommentFormattingStrategy extends CommentFormattingStrategy {

	@Override
	public IPreferenceStore getFormatterPrefs() {
		return STCore.getDefault().getPrefsManager();
	}
}
