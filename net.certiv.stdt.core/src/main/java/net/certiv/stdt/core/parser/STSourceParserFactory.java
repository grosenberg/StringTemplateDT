package net.certiv.stdt.core.parser;

import net.certiv.dsl.core.formatter.IDslCodeFormatter;
import net.certiv.dsl.core.parser.DslSourceParserFactory;

public class STSourceParserFactory extends DslSourceParserFactory {

	public STSourceParserFactory() {
		super();
	}

	@Override
	public IDslCodeFormatter createCodeFormatter() {
		return new STSourceFormatter();
	}

	@Override
	public STSourceParser createSourceParser() {
		return new STSourceParser();
	}
}
