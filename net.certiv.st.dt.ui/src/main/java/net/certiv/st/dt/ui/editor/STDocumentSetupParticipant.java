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

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;

import net.certiv.dsl.ui.editor.DslDocumentSetupParticipant;
import net.certiv.st.dt.ui.STUI;

/**
 * Reference in the extension point is used to associate the contextTypeId with a file extension
 */
public class STDocumentSetupParticipant extends DslDocumentSetupParticipant implements
		IDocumentSetupParticipant {

	public STDocumentSetupParticipant() {
		super(STUI.getDefault().getTextTools());
	}
}
