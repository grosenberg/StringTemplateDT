package net.certiv.stdt.ui.preferences.page;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.preferences.blocks.AbstractConfigurationBlockPreferencePage;
import net.certiv.dsl.ui.preferences.blocks.IPreferenceConfigurationBlock;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

public class PrefPageSyntaxColoring extends AbstractConfigurationBlockPreferencePage {

	@Override
	protected void setDescription() {
		setDescription("ST Syntax Presentation Preferences");
	}

	@Override
	protected IPreferenceConfigurationBlock createConfigurationBlock(DslPrefsManagerDelta delta) {
		return new SyntaxColoringConfigBlock(delta, this);
	}

	@Override
	protected String getHelpId() {
		return null;
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
