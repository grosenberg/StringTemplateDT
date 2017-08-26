package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.formatter.DslCodeFormatter;
import net.certiv.dsl.core.parser.DslParseErrorListener;
import net.certiv.dsl.core.util.Log;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.core.parser.gen.FormatVisitor;
import net.certiv.stdt.core.parser.gen.STGLexer;
import net.certiv.stdt.core.parser.gen.STGParser;
import net.certiv.stdt.core.parser.gen.STGParser.GroupContext;

public class STSourceFormatter extends DslCodeFormatter {

	private static STGParser parser;

	public STSourceFormatter() {
		super();
		setCommentTokens(STGParser.DOC_COMMENT, STGParser.LINE_COMMENT);
		setLineCommentTokens(STGParser.LINE_COMMENT);
		setVertSpacingTokens(STGParser.VERT_WS);
		setHorzSpacingTokens(STGParser.HORZ_WS);
		setListSeparatorTokens(STGParser.COMMA);
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	public ParseTree parse(String name, String content, DslParseErrorListener errListener) throws RecognitionException {
		Log.debug(this, "Parse [name=" + name + "]");

		CharStream input = CharStreams.fromString(content);
		STGLexer lexer = new STGLexer(input);
		tokens = new CommonTokenStream(lexer);
		parser = new STGParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(errListener);

		GroupContext parseTree = ((STGParser) parser).group();
		return parseTree;
	}

	@Override
	public void formatContent() {
		Log.debug(this, "Parse tree [root=" + (parseTree != null ? "not null" : "null") + "]");
		if (parseTree == null) return;

		// one pass: format
		try {
			FormatVisitor visitor = new FormatVisitor(parseTree);
			visitor.setHelper(this);
			visitor.findAll();
		} catch (Exception e) {
			Log.error(this, "Formatter error", e);
		}
	}
}
