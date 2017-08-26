package net.certiv.stdt.ui.editor;

import net.certiv.dsl.core.model.DslModelManager;
import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.ui.editor.hyperlink.DslHyperlinkDetector;
import net.certiv.stdt.core.STCore;

public class STHyperlinkDetector extends DslHyperlinkDetector {

	public STHyperlinkDetector() {
		super();
	}

	/** Filter/modify the elements for display on the popup */
	@Override
	public IStatement[] selectOpenableElements(IStatement[] elements) {
		return elements;
	}

	@Override
	public DslModelManager getModelMgr() {
		return STCore.getDefault().getModelManager();
	}

}
