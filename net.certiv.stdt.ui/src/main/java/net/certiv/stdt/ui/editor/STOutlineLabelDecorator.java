package net.certiv.stdt.ui.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.certiv.dsl.core.model.IDslElement;
import net.certiv.dsl.ui.DslImageDescriptor;
import net.certiv.dsl.ui.editor.OutlineLabelDecorator;
import net.certiv.stdt.core.model.ModelData;
import net.certiv.stdt.ui.STImageManager;
import net.certiv.stdt.ui.STUI;

public class STOutlineLabelDecorator extends OutlineLabelDecorator {

	public STOutlineLabelDecorator() {
		super(STUI.getDefault().getImageManager());
	}

	@Override
	public String decorateText(String text) {
		return text;
	}

	@Override
	public Image decorateImage(Image image) {
		STImageManager stMgr = (STImageManager) imgMgr;
		// create the base image
		ImageDescriptor desc = ImageDescriptor.getMissingImageDescriptor();
		switch (getElementKind()) {
			case IDslElement.MODULE:
				desc = stMgr.getDescriptor(stMgr.IMG_OBJ_MODULE);
				break;
			case IDslElement.STATEMENT:
				desc = stMgr.getDescriptor(stMgr.IMG_OBJ_STATEMENT);
				break;
			case IDslElement.BEG_BLOCK:
			case IDslElement.END_BLOCK:
				desc = stMgr.getDescriptor(stMgr.IMG_OBJ_BLOCK);
				break;
		}
		// and then apply overlays
		ModelData customData = (ModelData) getData();
		if (customData != null) {
			if (hasOverlay(customData.decoration & ModelData.COMBINED)) {
				desc = createOverlayImageDescriptor(desc, stMgr.getDescriptor(stMgr.IMG_OVR_COMBINED),
						DslImageDescriptor.TOP_RIGHT);
			}
		}
		return stMgr.get(desc);
	}
}
