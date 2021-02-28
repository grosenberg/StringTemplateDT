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
package net.certiv.st.dt.core;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import net.certiv.common.util.Chars;
import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.lang.LanguageManager;
import net.certiv.dsl.core.lang.RootEntry;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.st.dt.core.parser.STSourceParser;

public class STLangManager extends LanguageManager {

	public static final String DSL_NAME = "stg";
	public static final List<String> EXTENSIONS = Arrays.asList("stg");

	// maven standard source root paths - project relative
	private static final String SRC = "src/main/antlr4";
	private static final String EXC = "src/main/antlr4/import";
	private static final String OUT = "target/classes";

	public STLangManager(DslCore core) {
		super(core);
	}

	@Override
	public String getDslName() {
		return DSL_NAME;
	}

	@Override
	public DslSourceParser createSourceParser(ICodeUnit unit, String langId) {
		return new STSourceParser(unit.getParseRecord(langId));
	}

	@Override
	public List<String> getDslFileExtensions() {
		return EXTENSIONS;
	}

	@Override
	public List<RootEntry> getLangSourceRoots() {
		return Arrays.asList(RootEntry.source(SRC).exclude(EXC).out(OUT));
	}

	@Override
	public List<IPath> importNameToPathname(String name) {
		IPath path = new Path(name.replace(Chars.DOT, Chars.SLASH));
		path.append(Chars.DOT + EXTENSIONS.get(0));
		return Arrays.asList(path);
	}
}
