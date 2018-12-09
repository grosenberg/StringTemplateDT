package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.log.Log;
import net.certiv.dsl.core.log.Log.LogLevel;
import net.certiv.dsl.core.model.builder.DslModelMaker;
import net.certiv.dsl.core.parser.DslParseRecord;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.core.parser.gen.STGLexer;
import net.certiv.stdt.core.parser.gen.STGParser;
import net.certiv.stdt.core.parser.gen.StructureVisitor;

public class STSourceParser extends DslSourceParser {

	private static final STTokenFactory TokenFactory = new STTokenFactory();

	public STSourceParser(DslParseRecord record) {
		super(record);
		Log.setLevel(this, LogLevel.Info);
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	public void parse() {
		record.cs = CharStreams.fromString(record.doc.get());
		STGLexer lexer = new STGLexer(record.cs);
		lexer.setTokenFactory(TokenFactory);
		lexer.removeErrorListeners();
		lexer.addErrorListener(getDslErrorListener());

		record.ts = new CommonTokenStream(lexer);
		record.parser = new STGParser(record.ts);
		record.parser.setTokenFactory(TokenFactory);
		record.parser.removeErrorListeners();
		record.parser.addErrorListener(getDslErrorListener());
		record.tree = ((STGParser) record.parser).group();
	}

	@Override
	public void analyzeStructure(DslModelMaker maker) {
		try {
			StructureVisitor visitor = new StructureVisitor(record.tree);
			visitor.setMaker(maker);
			visitor.findAll();
		} catch (IllegalArgumentException e) {
			getDslErrorListener().generalError("Model analysis: %s @%s:%s", e);
		}
	}

	@Override
	public boolean modelContributor() {
		return true;
	}
}
