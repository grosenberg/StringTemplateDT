package net.certiv.stdt.ui.editor.strategies;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.strategies.AutoEditStringStrategy;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

public class STAutoEditStringStrategy extends AutoEditStringStrategy {

	public STAutoEditStringStrategy(String partitioning) {
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
