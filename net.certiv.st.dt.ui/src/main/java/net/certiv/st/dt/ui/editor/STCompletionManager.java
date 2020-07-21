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

import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.templates.CompletionManager;

public class STCompletionManager extends CompletionManager {

	public static final String GROUP = "group";

	public STCompletionManager(DslUI ui, String editorId) {
		super(ui, editorId, GROUP);
	}

	@Override
	public String getContentAssistScope(IStatement stmt) {
		switch (stmt.getModelType()) {
			case STATEMENT:
				return GROUP;

			default:
				return null;
		}
	}
}
