package net.certiv.stdt.ui.preferences.page;

import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Composite;

import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.ui.preferences.blocks.AbstractEditorConfigurationBlock;

public class EditorConfigurationBlock extends AbstractEditorConfigurationBlock {

	public EditorConfigurationBlock(DslPrefsManagerDelta delta, PreferencePage page) {
		super(delta, page);
	}

	@Override
	public void addCustomEditingControls(Composite composite) {}

	@Override
	public List<String> getEditingKeys(List<String> keys) {
		return keys;
	}
}
