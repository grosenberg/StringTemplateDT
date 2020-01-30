package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerNoViableAltException;

import net.certiv.stdt.core.parser.gen.STLexer;

/**
 * Base class for the Lexer. Combines functionality for <br>
 * <ul>
 * <li>lexer error strategy</li>
 * <li>various builder routines</li>
 * <ul>
 * 
 * @author Gbr
 * 
 */
public class STLexerErrorStrategy extends STLexer {

	// ///// Error strategy /////////////////////////////
	public STLexerErrorStrategy(CharStream input) {
		super(input);
	}

	public void recover(LexerNoViableAltException e) {
		// throw new RuntimeException(e); // Bail out
		super.recover(e);
	}

	// ///// Parse stream management ////////////////////

}
