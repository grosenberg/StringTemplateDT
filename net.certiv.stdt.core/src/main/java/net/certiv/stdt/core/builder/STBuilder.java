package net.certiv.stdt.core.builder;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.builder.DslBuilder;
import net.certiv.dsl.core.util.Log;
import net.certiv.stdt.core.STCore;

public class STBuilder extends DslBuilder {

	public static final String BUILDER_ID = "net.certiv.stdt.core.builder";

	@Override
	public IStatus buildSourceModules(IProgressMonitor monitor, int ticks, List<IFile> srcModules)
			throws CoreException {
		Log.debug(this, "Builder invoked [name=" + this.getClass().getName() + "]");
		return null;
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	public String getBuilderID() {
		return BUILDER_ID;
	}
}
