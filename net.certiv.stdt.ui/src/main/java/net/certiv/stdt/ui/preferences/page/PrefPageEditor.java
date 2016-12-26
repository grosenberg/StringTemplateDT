package net.certiv.stdt.ui.preferences.page;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.preferences.blocks.AbstractConfigurationBlockPreferencePage;
import net.certiv.dsl.ui.preferences.blocks.IPreferenceConfigurationBlock;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.preferences.PrefsMessages;

public class PrefPageEditor extends AbstractConfigurationBlockPreferencePage {

	@Override
	protected String getHelpId() {
		return "";
	}

	@Override
	protected void setDescription() {
		String description = PrefsMessages.EditorPreferencePage_general;
		setDescription(description);
	}

	@Override
	protected Label createDescriptionLabel(Composite parent) {
		return null;
	}

	@Override
	protected IPreferenceConfigurationBlock createConfigurationBlock(DslPrefsManagerDelta delta) {
		return new EditorConfigurationBlock(delta, this);
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
