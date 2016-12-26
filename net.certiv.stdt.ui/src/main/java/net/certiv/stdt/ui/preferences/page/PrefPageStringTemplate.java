package net.certiv.stdt.ui.preferences.page;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.ui.preferences.page.AbstractFieldEditorPreferencePage;
import net.certiv.stdt.core.STCore;

public class PrefPageStringTemplate extends AbstractFieldEditorPreferencePage {

	public PrefPageStringTemplate() {
		super(GRID);
		DslPrefsManagerDelta delta = STCore.getDefault().getPrefsManager().createDeltaManager();
		delta.setDefaultProject(null);
		setPreferenceStore(delta);
	}

	/** Creates the field editors. */
	public void createFieldEditors() {
		Composite parent = getFieldEditorParent();

		Group group = new Group(parent, SWT.NONE);
		GridDataFactory.fillDefaults().indent(0, 6).grab(true, false).span(2, 1).applyTo(group);
		GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(group);
		group.setText("StringTemplate Preferences");

		Composite awComp = new Composite(group, SWT.NONE);
		GridDataFactory.fillDefaults().indent(0, 4).grab(true, false).applyTo(awComp);

		GridLayoutFactory.fillDefaults().numColumns(3).applyTo(awComp);
	}
}
