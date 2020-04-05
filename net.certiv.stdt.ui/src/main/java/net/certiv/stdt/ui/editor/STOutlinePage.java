package net.certiv.stdt.ui.editor;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ILabelDecorator;

import net.certiv.dsl.ui.editor.outline.OutlinePage;
import net.certiv.stdt.ui.STUI;

public class STOutlinePage extends OutlinePage {

	public STOutlinePage(IPreferenceStore store) {
		super(STUI.getDefault(), store);
	}

	@Override
	protected ILabelDecorator getLabelDecorator() {
		return new STStatementLabelProvider();
	}
}
