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
package net.certiv.st.dt.ui.formatter.strategies;

import net.certiv.dsl.core.preferences.PrefsManager;
import net.certiv.dsl.jdt.ui.formatter.strategies.CommentFormattingStrategy;
import net.certiv.st.dt.core.STCore;

public class STCommentFormattingStrategy extends CommentFormattingStrategy {

	@Override
	public PrefsManager getFormatterPrefs() {
		return STCore.getDefault().getPrefsManager();
	}
}
