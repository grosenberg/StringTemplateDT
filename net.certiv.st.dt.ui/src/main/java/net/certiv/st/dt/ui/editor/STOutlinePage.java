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
package net.certiv.st.dt.ui.editor;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ILabelDecorator;

import net.certiv.dsl.ui.editor.outline.OutlinePage;
import net.certiv.st.dt.ui.STUI;

public class STOutlinePage extends OutlinePage {

	public STOutlinePage(IPreferenceStore store) {
		super(STUI.getDefault(), store);
	}

	@Override
	protected ILabelDecorator getLabelDecorator() {
		return new STStatementLabelProvider();
	}
}
