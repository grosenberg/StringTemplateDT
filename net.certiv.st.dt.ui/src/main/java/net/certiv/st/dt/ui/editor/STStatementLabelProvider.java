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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.certiv.dsl.ui.DslImageDescriptor;
import net.certiv.dsl.ui.editor.outline.OutlineLabelProvider;
import net.certiv.st.dt.core.model.Specialization;
import net.certiv.st.dt.ui.STImageManager;
import net.certiv.st.dt.ui.STUI;

public class STStatementLabelProvider extends OutlineLabelProvider {

	public STStatementLabelProvider() {
		super(STUI.getDefault().getImageManager());
	}

	@Override
	public String decorateText(String text) {
		return text;
	}

	@Override
	public Image decorateImage(Image image) {
		STImageManager mgr = (STImageManager) imgMgr;
		ImageDescriptor desc = null;
		switch (getStatementType()) {
			case MODULE:
				desc = mgr.getDescriptor(mgr.IMG_OBJ_MODULE);
				break;
			case STATEMENT:
				desc = mgr.getDescriptor(mgr.IMG_OBJ_STATEMENT);
				break;
			case BEG_BLOCK:
				desc = mgr.getDescriptor(mgr.IMG_OBJ_BLOCK);
				break;

			case END_BLOCK:
				break;

			default:
				desc = ImageDescriptor.getMissingImageDescriptor();
		}

		// and then apply overlays
		Specialization customData = (Specialization) getData();
		if (customData != null) {
			if (hasOverlay(customData.decoration & Specialization.COMBINED)) {
				desc = createOverlayImageDescriptor(desc, mgr.getDescriptor(mgr.IMG_OVR_COMBINED),
						DslImageDescriptor.TOP_RIGHT);
			}
		}
		return mgr.get(desc);
	}
}
