package net.certiv.stdt.ui;

import org.osgi.framework.BundleContext;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.Log.LogLevel;
import net.certiv.dsl.ui.DslImages;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.editor.STEditor;
import net.certiv.stdt.ui.editor.STTextTools;
import net.certiv.stdt.ui.templates.STTemplateContextType;

public class STUI extends DslUI {

	// Should be unique, lower case, single word
	private static final String DSL_NAME = "st";

	private static STUI plugin;
	private DslTextTools textTools;
	private DslImages imageProvider;

	public STUI() {
		super();
		Log.defLevel(LogLevel.Debug);
	}

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
	public String getDslLanguageName() {
		return DSL_NAME;
	}

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
	protected String[] getDslContextTypes() {
		return new String[] { STTemplateContextType.ST_CONTEXT_TYPE_ID };
	}

	@Override
	protected String getEditorId() {
		return STEditor.EDITOR_ID;
	}
}
