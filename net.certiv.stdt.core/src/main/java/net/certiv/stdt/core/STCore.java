package net.certiv.stdt.core;

import org.osgi.framework.BundleContext;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.Log.LogLevel;
import net.certiv.stdt.core.parser.STSourceParser;

public class STCore extends DslCore {

	private static final String[] EXTENSIONS = new String[] { "st", "stg" };
	public static final String DSL_NAME = "stg";

	public static STCore plugin;

	public STCore() {
		super();
		Log.defLevel(LogLevel.Debug);
	}

	public static STCore getDefault() {
		return plugin;
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	public void start(BundleContext context) throws Exception {
		plugin = this;
		super.start(context);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	@Override
	public String getPluginId() {
		return getDefault().getBundle().getSymbolicName();
	}

	@Override
	public String[] getDslFileExtensions() {
		return EXTENSIONS;
	}

	@Override
	public DslSourceParser createSourceParser(ICodeUnit unit, String contentType) {
		if (DSL_NAME.equals(contentType) || getContentTypeId().equals(contentType)) {
			return new STSourceParser(unit.getParseRecord());
		}
		return null;
	}
}
