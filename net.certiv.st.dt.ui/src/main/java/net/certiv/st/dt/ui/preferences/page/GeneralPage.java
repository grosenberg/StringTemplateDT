/*******************************************************************************
 * Copyright (c) 2012, 2020 Certiv Analytics.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package net.certiv.st.dt.ui.preferences.page;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.widgets.FormToolkit;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.DslColorRegistry;
import net.certiv.dsl.core.preferences.PrefsDeltaManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.preferences.blocks.IPreferenceConfigBlock;
import net.certiv.dsl.ui.preferences.pages.AbstractFieldEditorPreferencePage;
import net.certiv.st.dt.core.STCore;
import net.certiv.st.dt.ui.STUI;

public class GeneralPage extends AbstractFieldEditorPreferencePage {

	public GeneralPage() {
		super(GRID);
	}

	/** Creates the field editors. */
	@Override
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

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	protected IPreferenceConfigBlock createConfigurationBlock(AbstractFieldEditorPreferencePage page, Composite parent,
			PrefsDeltaManager delta, FormToolkit formkit, DslColorRegistry reg) {
		return null;
	}

	@Override
	protected void adjustSubLayout() {}
}
