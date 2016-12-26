package net.certiv.stdt.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;

import net.certiv.dsl.ui.DslImages;

public class STImages extends DslImages {

	private static final Bundle locBundle = STUI.getDefault().getBundle();
	private static final String locPrefix = locBundle.getSymbolicName() + '.';

	public final ImageDescriptor DESC_OBJ_MODULE = create(locBundle, OBJ, locPrefix + "module_blue.gif");
	public final ImageDescriptor DESC_OBJ_STATEMENT = create(locBundle, OBJ, locPrefix + "statement.gif");
	public final ImageDescriptor DESC_OBJ_BLOCK = create(locBundle, OBJ, locPrefix + "block.gif");

	public final ImageDescriptor DESC_OVR_COMBINED = create(locBundle, OVR, locPrefix + "combined_ovr.gif");

	public STImages() {
		super();
		IMG_OBJS_TUNIT = locPrefix + "stg.png"; //$NON-NLS-1$
		DESC_OBJS_TUNIT = create(locBundle, OBJ, IMG_OBJS_TUNIT);
	}

	@Override
	public ImageRegistry getImageRegistry() {
		return STUI.getDefault().getImageRegistry();
	}
}
