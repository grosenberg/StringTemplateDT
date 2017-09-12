package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.parser.DslParseErrorListener;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.Log.LogLevel;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.core.parser.gen.CodeAssistVisitor;
import net.certiv.stdt.core.parser.gen.STGLexer;
import net.certiv.stdt.core.parser.gen.STGParser;
import net.certiv.stdt.core.parser.gen.STGParser.GroupContext;
import net.certiv.stdt.core.parser.gen.StructureVisitor;

public class STSourceParser extends DslSourceParser {

	public STSourceParser() {
		super();
		Log.setLevel(this, LogLevel.Info);
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	/** Builds a ParseTree for the given content representing the source of a corresponding unit. */
	@Override
	public ParseTree parse(String name, String content, DslParseErrorListener errListener) throws RecognitionException {
		Log.debug(this, "Parse [name=" + name + "]");

		input = CharStreams.fromString(content);
		STGLexer lexer = new STGLexer(input);

		// lexer.setLexerHelper(new LexerHelper());
		// STGTokenFactory factory = new STGTokenFactory(input);
		// lexer.setTokenFactory(factory);
		tokens = new CommonTokenStream(lexer);

		parser = new STGParser(tokens);
		// parser.setTokenFactory(factory);
		parser.removeErrorListeners();
		parser.addErrorListener(errListener);
		GroupContext parseTree = ((STGParser) parser).group();

		return parseTree;
	}

	/**
	 * Build the internal minimal model used as the structure basis for the outline view, etc.
	 */
	@Override
	public void buildStructure() {
		Log.debug(this, "Model [root=" + (tree != null ? "not null" : "null") + "]");

		try {
			StructureVisitor walker = new StructureVisitor(tree);
			walker.setHelper(this);
			walker.findAll();
		} catch (IllegalArgumentException e) {
			Log.error(this, "Model - Outline processing error", e);
		}
	}

	/**
	 * Tree walker used to identify the code elements that may be signficant in CodeAssist operations
	 */
	@Override
	public void buildCodeAssist() {
		Log.debug(this, "CodeAssist [root=" + (tree != null ? "not null" : "null") + "]");

		try {
			CodeAssistVisitor walker = new CodeAssistVisitor(tree);
			walker.setHelper(this);
			walker.findAll();
		} catch (Exception e) {
			Log.error(this, "CodeAssist - Tree walk error", e);
		}
	}
}
