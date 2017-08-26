package net.certiv.stdt.core;

import org.osgi.framework.BundleContext;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.parser.ISourceParserFactory;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.Log.LogLevel;
import net.certiv.stdt.core.parser.STSourceParserFactory;

public class STCore extends DslCore {

	private static final String[] EXTENSIONS = new String[] { "st", "stg" };

	private STSourceParserFactory factory;

	public static STCore plugin;

	/**
	 * The constructor
	 */
	public STCore() {
		super();
		Log.defLevel(LogLevel.Debug);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static STCore getDefault() {
		return plugin;
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	public void start(BundleContext context) throws Exception {
		plugin = this;
		super.start(context);
	}

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
	public ISourceParserFactory getSourceParserFactory() {
		if (factory == null) {
			factory = new STSourceParserFactory();
		}
		return factory;
	}
}
