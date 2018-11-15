package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

public class STTokenFactory implements TokenFactory<STToken> {

	@Override
	public STToken create(int type, String text) {
		return new STToken(type, text);
	}

	@Override
	public STToken create(Pair<TokenSource, CharStream> source, int type, String text, int channel, int start, int stop,
			int line, int charPos) {

		STToken token = new STToken(source, type, channel, start, stop);
		token.setText(text);
		token.setLine(line);
		token.setCharPositionInLine(charPos);
		token.setMode(((Lexer) source.a)._mode);
		return token;
	}
}
