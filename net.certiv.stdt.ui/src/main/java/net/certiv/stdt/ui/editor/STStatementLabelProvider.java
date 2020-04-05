package net.certiv.stdt.ui.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import net.certiv.dsl.ui.DslImageDescriptor;
import net.certiv.dsl.ui.editor.outline.OutlineLabelProvider;
import net.certiv.stdt.core.model.Specialization;
import net.certiv.stdt.ui.STImageManager;
import net.certiv.stdt.ui.STUI;

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
