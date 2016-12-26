package net.certiv.stdt.ui.preferences.page;

import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Composite;

import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.ui.preferences.blocks.AbstractFoldingPreferenceBlock;
import net.certiv.stdt.core.preferences.PrefsKey;

public class FoldingPreferenceBlock extends AbstractFoldingPreferenceBlock {

	public FoldingPreferenceBlock(DslPrefsManagerDelta store, PreferencePage page) {
		super(store, page);
	}

	@Override
	public List<String> getFoldingKeys(List<String> keys) {
		keys.add(PrefsKey.EDITOR_FOLDING_COMMENTS);
		return keys;
	}

	@Override
	public void addCustomFoldingControls(Composite initialFolding) {
		addCheckBox(initialFolding, "Multi-Line Comments", PrefsKey.EDITOR_FOLDING_COMMENTS, 0);
	}
}
