package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

public class STToken extends CommonToken implements Token {

	private int _mode;
	protected int charIndex = -1;

	public STToken(int type, String text) {
		super(type, text);
	}

	public STToken(Pair<TokenSource, CharStream> source, int type, int channel, int start, int stop) {
		super(source, type, channel, start, stop);
	}

	public int getMode() {
		return _mode;
	}

	public void setMode(int mode) {
		_mode = mode;
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
