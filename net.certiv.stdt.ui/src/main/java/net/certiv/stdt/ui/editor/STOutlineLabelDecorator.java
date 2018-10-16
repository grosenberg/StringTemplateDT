package net.certiv.stdt.ui.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.certiv.dsl.core.model.IDslElement;
import net.certiv.dsl.ui.editor.OutlineLabelDecorator;
import net.certiv.stdt.core.model.ModelData;
import net.certiv.stdt.ui.STImages;
import net.certiv.stdt.ui.STUI;

public class STOutlineLabelDecorator extends OutlineLabelDecorator {

	public STOutlineLabelDecorator() {
		super();
	}

	private STImages getImageProvider() {
		return (STImages) STUI.getDefault().getImageProvider();
	}

	@Override
	public String decorateText(String text) {
		return text;
	}

	@Override
	public Image decorateImage(Image image) {
		// create the base image
		ImageDescriptor desc = createMissingImageDescriptor(image);
		switch (getElementKind()) {
			case IDslElement.MODULE:
				desc = getImageProvider().DESC_OBJ_MODULE;
				break;
			case IDslElement.STATEMENT:
				desc = getImageProvider().DESC_OBJ_STATEMENT;
				break;
			case IDslElement.BEG_BLOCK:
			case IDslElement.END_BLOCK:
				desc = getImageProvider().DESC_OBJ_BLOCK;
				break;
		}
		// and then apply overlays
		ModelData customData = (ModelData) getData();
		if (customData != null) {
			if (addOverlay(customData.decoration & ModelData.COMBINED)) {
				desc = createOverlayImageDescriptor(desc, getImageProvider().DESC_OVR_COMBINED, TOP_RIGHT);
			}
		}
		return findImage(desc);
	}
}
