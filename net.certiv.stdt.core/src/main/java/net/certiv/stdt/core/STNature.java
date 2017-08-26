package net.certiv.stdt.core;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.DslNature;

public class STNature extends DslNature {

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}
}
