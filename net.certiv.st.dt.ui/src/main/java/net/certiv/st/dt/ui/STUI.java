/*******************************************************************************
 * Copyright (c) 2012, 2020 Certiv Analytics.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package net.certiv.st.dt.ui;

import org.apache.logging.log4j.Level;
import org.osgi.framework.BundleContext;

import net.certiv.common.log.Log;
import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslImageManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.dsl.ui.templates.CompletionManager;
import net.certiv.st.dt.core.STCore;
import net.certiv.st.dt.ui.editor.STCompletionManager;
import net.certiv.st.dt.ui.editor.STEditor;
import net.certiv.st.dt.ui.editor.STTextTools;

public class STUI extends DslUI {

	private static STUI plugin;
	private DslTextTools textTools;
	private DslImageManager imgMgr;
	private STCompletionManager compMgr;

	public STUI() {
		super();
		Log.defLevel(Level.DEBUG);
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
	public DslTextTools getTextTools() {
		if (textTools == null) {
			textTools = new STTextTools(true);
		}
		return textTools;
	}

	@Override
	public DslImageManager getImageManager() {
		if (imgMgr == null) {
			imgMgr = new STImageManager();
		}
		return imgMgr;
	}

	@Override
	public String getEditorId() {
		return STEditor.EDITOR_ID;
	}

	@Override
	public CompletionManager getCompletionMgr() {
		if (compMgr == null) {
			compMgr = new STCompletionManager(this, getEditorId());
		}
		return compMgr;
	}
}
