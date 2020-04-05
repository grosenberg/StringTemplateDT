package net.certiv.stdt.ui;

import org.osgi.framework.Bundle;

import org.eclipse.jface.resource.ImageRegistry;

import net.certiv.dsl.core.util.Chars;
import net.certiv.dsl.ui.DslImageManager;

public class STImageManager extends DslImageManager {

	private static final Bundle locBundle = STUI.getDefault().getBundle();
	private static final String locPrefix = locBundle.getSymbolicName() + Chars.DOT;

	public final String IMG_OBJ_MODULE = create(locBundle, OBJ, locPrefix + "module_blue.png");
	public final String IMG_OBJ_STATEMENT = create(locBundle, OBJ, locPrefix + "statement.png");
	public final String IMG_OBJ_BLOCK = create(locBundle, OBJ, locPrefix + "block.png");

	public final String IMG_OVR_COMBINED = create(locBundle, OVR, locPrefix + "combined_ovr.png");

	public STImageManager() {
		super();
		IMG_OBJS_UNIT = create(locBundle, OBJ, locPrefix + "stg.png"); //$NON-NLS-1$
	}

	@Override
	public ImageRegistry getRegistry() {
		return STUI.getDefault().getImageRegistry();
	}
}
