package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.parser.DslParseErrorListener;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.util.Log;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.core.parser.gen.CodeAssistProcessor;
import net.certiv.stdt.core.parser.gen.OutlineProcessor;
import net.certiv.stdt.core.parser.gen.STGLexer;
import net.certiv.stdt.core.parser.gen.STGParser;
import net.certiv.stdt.core.parser.gen.STGParser.GroupContext;

public class STSourceParser extends DslSourceParser {

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	/**
	 * Builds a ParseTree for the given content representing the source of a corresponding module
	 * (file).
	 */
	@Override
	public ParseTree parse(String name, char[] content, DslParseErrorListener errListener) throws RecognitionException {
		Log.debug(this, "Parse [name=" + name + "]");

		input = new ANTLRInputStream(content, content.length);
		STGLexer lexer = new STGLexer(input);

		// lexer.setLexerHelper(new LexerHelper());
		// STGTokenFactory factory = new STGTokenFactory(input);
		// lexer.setTokenFactory(factory);
		tokens = new CommonTokenStream(lexer);

		parser = new STGParser(tokens);
		// parser.setTokenFactory(factory);
		parser.removeErrorListeners(); // remove ConsoleErrorListener to reduce the noise
		parser.addErrorListener(errListener); // add internal error listener to feed error markers
		GroupContext parseTree = ((STGParser) parser).group();

		return parseTree;
	}

	/**
	 * Build the internal minimal model used as the structure basis for the outline view, etc.
	 */
	@Override
	public void buildModel() {
		Log.debug(this, "Model [root=" + (parseTree != null ? "not null" : "null") + "]");

		try {
			OutlineProcessor oProcessor = new OutlineProcessor(parseTree);
			oProcessor.setHelper(this);
			oProcessor.findAll();
		} catch (IllegalArgumentException e) {
			Log.error(this, "Model - Outline processing error", e);
		}
	}

	/**
	 * Tree pattern matcher used to identify the code elements that may be signficant in CodeAssist
	 * operations
	 */
	@Override
	public void buildCodeAssist() {
		Log.debug(this, "CodeAssist [root=" + (parseTree != null ? "not null" : "null") + "]");

		try {
			CodeAssistProcessor caProcessor = new CodeAssistProcessor(parseTree);
			caProcessor.setHelper(this);
			caProcessor.findAll();
		} catch (Exception e) {
			Log.error(this, "CodeAssist - Tree walk error", e);
		}
	}
}
