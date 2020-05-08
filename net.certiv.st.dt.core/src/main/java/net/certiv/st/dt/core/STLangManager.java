package net.certiv.st.dt.core;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.lang.LanguageManager;
import net.certiv.dsl.core.lang.RootEntry;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.util.Chars;
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
