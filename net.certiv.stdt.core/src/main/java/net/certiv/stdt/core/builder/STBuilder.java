package net.certiv.stdt.core.builder;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.builder.DslBuilder;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.stdt.core.STCore;

public class STBuilder extends DslBuilder {

	private static final String TASK_ID = "StringTemplate";

	@Override
	protected IStatus buildUnits(List<ICodeUnit> srcModules, IProgressMonitor monitor, int ticks) throws CoreException {
		return null;
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	protected String taskId() {
		return TASK_ID;
	}
}
