package net.certiv.stdt.ui.editor;

import net.certiv.dsl.core.model.DslModel;
import net.certiv.dsl.ui.editor.hyperlink.DslHyperlinkDetector;
import net.certiv.stdt.core.STCore;

public class STHyperlinkDetector extends DslHyperlinkDetector {

	public STHyperlinkDetector() {
		super();
	}

	@Override
	public DslModel getDslModel() {
		return STCore.getDefault().getDslModel();
	}

}
