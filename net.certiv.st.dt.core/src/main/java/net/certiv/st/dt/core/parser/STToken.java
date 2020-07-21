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
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

import net.certiv.dsl.core.parser.IDslToken;

public class STToken extends CommonToken implements IDslToken {

	private int _mode;
	protected int charIndex = -1;

	public STToken(int type, String text) {
		super(type, text);
	}

	public STToken(Pair<TokenSource, CharStream> source, int type, int channel, int start, int stop) {
		super(source, type, channel, start, stop);
	}

	@Override
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
