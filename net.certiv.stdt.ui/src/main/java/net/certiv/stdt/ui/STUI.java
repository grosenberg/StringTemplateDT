package net.certiv.stdt.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.Log.LogLevel;
import net.certiv.dsl.ui.DslImages;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.dsl.ui.formatter.IDslFormatterFactory;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.editor.STTextTools;
import net.certiv.stdt.ui.preferences.formatter.FormatterFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class STUI extends DslUI {

	// Should be unique, lower case, single word
	private static final String DSL_NAME = "string_template";

	private static STUI plugin;
	private DslTextTools textTools;
	private IDslFormatterFactory factory;
	private DslImages imageProvider;

	/**
	 * The constructor
	 */
	public STUI() {
		super();
		Log.defLevel(LogLevel.Debug);
	}

	/**
	 * Returns the shared instance of the plugin.
	 * 
	 * @return the shared instance
	 */
	public static STUI getDefault() {
		return plugin;
	}

	@Override
	public DslUI getDslUI() {
		return getDefault();
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
	public String getDslLanguageName() {
		return DSL_NAME;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in relative path
	 * 
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * Returns the text tools
	 */
	@Override
	public DslTextTools getTextTools() {
		if (textTools == null) {
			textTools = new STTextTools(true);
		}
		return textTools;
	}

	@Override
	public DslImages getImageProvider() {
		if (imageProvider == null) {
			imageProvider = new STImages();
		}
		return imageProvider;
	}

	@Override
	public IDslFormatterFactory getFormatterFactory() {
		if (factory == null) {
			factory = new FormatterFactory();
		}
		return factory;
	}
}
