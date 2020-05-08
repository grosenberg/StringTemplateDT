package net.certiv.st.dt.ui.editor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.AbstractAnnotationImageProvider;
import net.certiv.st.dt.core.STCore;
import net.certiv.st.dt.ui.STUI;

public class AnnotationImageProvider extends AbstractAnnotationImageProvider {

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}
}
