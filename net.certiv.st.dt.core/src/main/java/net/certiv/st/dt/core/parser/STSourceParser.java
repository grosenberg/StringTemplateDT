package net.certiv.st.dt.core.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.log.Log;
import net.certiv.dsl.core.log.Log.LogLevel;
import net.certiv.dsl.core.model.builder.ModelBuilder;
import net.certiv.dsl.core.parser.DslParseRecord;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.st.dt.core.STCore;
import net.certiv.st.dt.core.parser.gen.STGLexer;
import net.certiv.st.dt.core.parser.gen.STGParser;
import net.certiv.st.dt.core.parser.gen.StructureVisitor;

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
	public boolean doAnalysis() {
		return true;
	}

	@Override
	public Throwable parse() {
		try {
			record.cs = CharStreams.fromString(getContent(), record.unit.getFile().getName());
			STGLexer lexer = new STGLexer(record.cs);
			lexer.setTokenFactory(TokenFactory);
			lexer.removeErrorListeners();
			lexer.addErrorListener(getErrorListener());

			record.ts = new CommonTokenStream(lexer);
			record.parser = new STGParser(record.ts);
			record.parser.setTokenFactory(TokenFactory);
			record.parser.removeErrorListeners();
			record.parser.addErrorListener(getErrorListener());
			record.tree = ((STGParser) record.parser).group();
			return null;

		} catch (Exception | Error e) {
			getErrorListener().generalError(ERR_ANALYSIS, e);
			return e;
		}
	}

	@Override
	public Throwable analyze(ModelBuilder builder) {
		try {
			StructureVisitor visitor = new StructureVisitor(record.tree);
			visitor.setSourceName(record.unit.getPackageName());
			visitor.setBuilder(builder);
			builder.beginAnalysis();
			visitor.findAll();
			builder.endAnalysis();
			return null;

		} catch (Exception | Error e) {
			getErrorListener().generalError(ERR_ANALYSIS, e);
			return e;
		}
	}
}
