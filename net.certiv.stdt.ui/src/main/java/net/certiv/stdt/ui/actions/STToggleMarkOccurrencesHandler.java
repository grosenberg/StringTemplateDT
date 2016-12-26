package net.certiv.stdt.ui.actions;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.handlers.ToggleMarkOccurrencesHandler;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

public class STToggleMarkOccurrencesHandler extends ToggleMarkOccurrencesHandler {

	public STToggleMarkOccurrencesHandler() {}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}
}
