package net.certiv.stdt.ui.editor.text.hover;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.IEditorPart;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslWordFinder;
import net.certiv.dsl.ui.editor.text.hover.DslSourceHover;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

public class SourceHover extends DslSourceHover {

	private DslWordFinder finder;

	public SourceHover(IEditorPart editor, IDslPrefsManager store) {
		super(editor, store);
		finder = new DslWordFinder();
	}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	public IRegion findWord(IDocument doc, int offset) {
		return finder.findWord(doc, offset);
	}
}
