package net.certiv.st.dt.ui.actions;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.handlers.ToggleMarkOccurrencesHandler;
import net.certiv.st.dt.core.STCore;
import net.certiv.st.dt.ui.STUI;

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
