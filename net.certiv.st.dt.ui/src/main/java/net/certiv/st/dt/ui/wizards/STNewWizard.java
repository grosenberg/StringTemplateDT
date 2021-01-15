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
package net.certiv.st.dt.ui.wizards;

import org.eclipse.jface.resource.ImageDescriptor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslImageManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.wizard.DslFileWizard;
import net.certiv.st.dt.core.STCore;
import net.certiv.st.dt.ui.STUI;

/**
 * New file wizard. The wizard creates one file with the extension "st".
 */
public class STNewWizard extends DslFileWizard {

	private STNewWizardPage page;

	public STNewWizard() {
		super("STNewWizard");
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
	public String getWindowShellTitle() {
		return "New StringTemplate Group File";
	}

	@Override
	public ImageDescriptor getPageImageDescriptor() {
		DslImageManager imgMgr = getDslUI().getImageManager();
		return imgMgr.getDescriptor(imgMgr.IMG_WIZBAN_NEW_FILE);
	}

	@Override
	protected STNewWizardPage getMainPage() {
		if (page == null) {
			page = new STNewWizardPage(this, getSelection());
		}
		return page;
	}
}
