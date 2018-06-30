package net.certiv.stdt.core.formatter;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.formatter.DslCodeFormatter;
import net.certiv.stdt.core.STCore;

public class STSourceFormatter extends DslCodeFormatter {

	public STSourceFormatter() {
		super();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}
}
