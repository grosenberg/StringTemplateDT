package net.certiv.stdt.ui.editor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.AbstractAnnotationImageProvider;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

public class STAnnotationImageProvider extends AbstractAnnotationImageProvider {

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}
}
