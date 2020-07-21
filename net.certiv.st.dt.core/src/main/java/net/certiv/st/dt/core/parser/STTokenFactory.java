/*******************************************************************************
 * Copyright (c) 2012, 2020 Certiv Analytics.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package net.certiv.st.dt.core.parser;

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
