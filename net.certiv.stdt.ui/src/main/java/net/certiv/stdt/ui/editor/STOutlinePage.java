package net.certiv.stdt.ui.editor;

import net.certiv.dsl.ui.editor.DslEditor;
import net.certiv.dsl.ui.editor.DslOutlinePage;
import net.certiv.stdt.ui.STUI;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ILabelDecorator;

public class STOutlinePage extends DslOutlinePage {

	public STOutlinePage(DslEditor editor, IPreferenceStore store) {
		super(STUI.getDefault(), editor, store);
	}

	@Override
	protected ILabelDecorator getLabelDecorator() {
		return new STOutlineLabelDecorator();
	}
}
