package net.certiv.stdt.ui.preferences.page;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.preferences.Messages;
import net.certiv.dsl.ui.preferences.blocks.IPreferenceConfigBlock;
import net.certiv.dsl.ui.preferences.pages.AbstractPreferencePage;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

public class FoldingPage extends AbstractPreferencePage {

	public FoldingPage() {
		super();
	}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	protected IPreferenceConfigBlock createConfigurationBlock(DslPrefsManagerDelta delta) {
		return new FoldingConfigBlock(this, delta, getFormkit(), getColorMgr());
	}

	@Override
	protected void setDescription() {
		setDescription(Messages.EditorPreferencePage_folding_title);
	}
}
