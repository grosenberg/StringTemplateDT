package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

/**
 * @author Gerald B. Rosenberg
 * @since 2010.03.10
 */
public class STToken extends CommonToken implements Token {

	private static final long serialVersionUID = 6127179419658566188L;

	protected int charIndex = -1;

	public STToken(Pair<TokenSource, CharStream> source, int type, int channel, int start, int stop) {
		super(source, type, channel, start, stop);
	}

	public STToken(int type, String text) {
		super(type, text);
	}

	public STToken(int type) {
		super(type);
	}

	public STToken(Token oldToken) {
		super(oldToken);
	}

	public int getCharIndex() {
		return charIndex;
	}

	public void setCharIndex(int charIndex) {
		this.charIndex = charIndex;
	}
}
