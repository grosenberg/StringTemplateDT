package net.certiv.st.dt.ui.editor.strategies;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.AbstractAutoEditStrategy;
import net.certiv.st.dt.core.STCore;
import net.certiv.st.dt.ui.STUI;

public class STAutoEditStrategy extends AbstractAutoEditStrategy {

	public STAutoEditStrategy(String partitioning) {
		super(partitioning);
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
