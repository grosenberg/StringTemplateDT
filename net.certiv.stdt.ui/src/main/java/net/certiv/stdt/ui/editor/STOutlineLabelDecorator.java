package net.certiv.stdt.ui.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.certiv.dsl.core.model.IDslElement;
import net.certiv.dsl.ui.editor.OutlineLabelDecorator;
import net.certiv.stdt.core.parser.ModelData;
import net.certiv.stdt.ui.STImages;
import net.certiv.stdt.ui.STUI;

public class STOutlineLabelDecorator extends OutlineLabelDecorator {

	public STOutlineLabelDecorator() {
		super();
	}

	private STImages getImageProvider() {
		return (STImages) STUI.getDefault().getImageProvider();
	}

	public String decorateText(String text) {
		switch (getElementKind()) {
			case IDslElement.MODULE_DECLARATION:
				return text;
			case IDslElement.STATEMENT:
				return text;
			case IDslElement.BLOCK:
				// convert the custom Object as needed
				ModelData customData = (ModelData) getData();
				if (customData != null) {
					return customData.name;
				}
		}
		return text;
	}

	public Image decorateImage(Image image) {
		// create the base image
		ImageDescriptor baseImage = createBaseImageDescriptor(image);
		int type = 0;
		switch (getElementKind()) {
			case IDslElement.MODULE_DECLARATION:
				baseImage = getImageProvider().DESC_OBJ_MODULE;
				type = 1;
				break;
			case IDslElement.STATEMENT:
				baseImage = getImageProvider().DESC_OBJ_STATEMENT;
				type = 2;
				break;
			case IDslElement.BLOCK:
				baseImage = getImageProvider().DESC_OBJ_BLOCK;
				type = 3;
				break;
		}
		// and then apply overlays
		ModelData customData = (ModelData) getData();
		if (customData != null) {
			if (addOverlay(customData.decoration & ModelData.COMBINED)) {
				baseImage = createOverlayImageDescriptor(baseImage, getImageProvider().DESC_OVR_COMBINED, TOP_RIGHT);
				type += 100;
			}
		}
		Image img = fetchImage(type);
		if (img == null) {
			img = baseImage.createImage();
			storeImage(type, img);
		}
		return img;
	}
}
