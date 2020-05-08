package net.certiv.st.dt.ui.editor.text;

import net.certiv.dsl.core.util.Chars;
import net.certiv.dsl.ui.editor.text.rules.IWordDetector2;

public class WordDetector implements IWordDetector2 {

	@Override
	public boolean isWordStart(char c) {
		return Character.isUnicodeIdentifierStart(c);
	}

	@Override
	public boolean isWordPart(char c) {
		return Character.isUnicodeIdentifierPart(c);
	}

	@Override
	public boolean isPriorCharValid(char c) {
		return Character.isWhitespace(c) || c == Chars.LANGLE;
	}
}
