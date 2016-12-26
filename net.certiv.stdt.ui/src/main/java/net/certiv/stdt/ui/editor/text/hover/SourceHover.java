package net.certiv.stdt.ui.editor.text.hover;

import org.eclipse.ui.IEditorPart;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.text.hover.DslSourceHover;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

public class SourceHover extends DslSourceHover {

	public SourceHover(IEditorPart editor, IDslPrefsManager store) {
		super(editor, store);
	}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}
}
