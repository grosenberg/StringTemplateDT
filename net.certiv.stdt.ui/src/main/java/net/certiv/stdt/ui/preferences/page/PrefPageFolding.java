package net.certiv.stdt.ui.preferences.page;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.preferences.Messages;
import net.certiv.dsl.ui.preferences.blocks.AbstractConfigurationBlockPreferencePage;
import net.certiv.dsl.ui.preferences.blocks.IPreferenceConfigurationBlock;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

public class PrefPageFolding extends AbstractConfigurationBlockPreferencePage {

	@Override
	protected IPreferenceConfigurationBlock createConfigurationBlock(DslPrefsManagerDelta delta) {
		return new FoldingConfigBlock(delta, this);
	}

	@Override
	protected String getHelpId() {
		return null;
	}

	@Override
	protected void setDescription() {
		setDescription(Messages.EditorPreferencePage_folding_title);
	}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}
}
