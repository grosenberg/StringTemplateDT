package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.formatter.DslCodeFormatter;
import net.certiv.dsl.core.parser.DslParseErrorListener;
import net.certiv.dsl.core.util.Log;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.core.parser.gen.FormatProcessor;
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
	public ParseTree parse(String name, char[] content, DslParseErrorListener errListener) throws RecognitionException {
		Log.debug(this, "Parse [name=" + name + "]");

		CharStream input = new ANTLRInputStream(content, content.length);
		STGLexer lexer = new STGLexer(input);

		// lexer.setLexerHelper(new LexerHelper());
		// STGTokenFactory factory = new STGTokenFactory(input);
		// lexer.setTokenFactory(factory);
		tokens = new CommonTokenStream(lexer);

		parser = new STGParser(tokens);
		// parser.setTokenFactory(factory);
		parser.removeErrorListeners(); // remove ConsoleErrorListener to reduce the noise
		parser.addErrorListener(errListener); // have to add the internal error listener
												 // that feeds the error markers

		GroupContext parseTree = ((STGParser) parser).group();
		return parseTree;
	}

	@Override
	public void formatContent() {
		Log.debug(this, "AST [root=" + (parseTree != null ? "not null" : "null") + "]");
		if (parseTree == null) return;

		// one pass: format
		try {
			FormatProcessor formatter = new FormatProcessor(parseTree);
			formatter.setHelper(this);
			formatter.findAll();
		} catch (Exception e) {
			Log.error(this, "Formatter error", e);
		}
	}
}
