package net.certiv.st.dt.core;

import org.osgi.framework.BundleContext;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.lang.LanguageManager;
import net.certiv.dsl.core.log.Log;
import net.certiv.dsl.core.log.Log.LogLevel;

public class STCore extends DslCore {

	public static STCore plugin;

	private STLangManager langMgr;

	public STCore() {
		super();
		Log.defLevel(LogLevel.Info);
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
	public LanguageManager getLangManager() {
		if (langMgr == null) {
			langMgr = new STLangManager(this);
		}
		return langMgr;
	}
}