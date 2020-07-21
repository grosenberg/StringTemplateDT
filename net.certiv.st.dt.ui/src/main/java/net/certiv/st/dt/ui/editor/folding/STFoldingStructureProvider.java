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
package net.certiv.st.dt.ui.editor.folding;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.folding.AbstractFoldingStructureProvider;
import net.certiv.st.dt.core.STCore;
import net.certiv.st.dt.ui.STUI;
import net.certiv.st.dt.ui.editor.Partitions;

public class STFoldingStructureProvider extends AbstractFoldingStructureProvider {

	public STFoldingStructureProvider() {
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
	protected String getPartitioning() {
		return Partitions.PARTITIONING;
	}

	@Override
	protected String getMultiLineCommentPartition() {
		return Partitions.COMMENT_ML;
	}

	@Override
	protected String getSingleLineCommentPartition() {
		return Partitions.COMMENT_SL;
	}
}
