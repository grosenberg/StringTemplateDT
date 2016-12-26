//
// Generated from Format.g4 by ANTLR-XVisitor 4.4
//
package net.certiv.stdt.core.parser.gen;

	import net.certiv.dsl.core.formatter.DslCodeFormatter;

import static net.certiv.stdt.core.preferences.PrefsKey.*;

import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.antlr.runtime.xvisitor.xpath.EType;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormatProcessor extends Processor {

	public static final String[] tokenNames = {
		"<INVALID>", "DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", "TMPL_COMMENT", 
		"HORZ_WS", "VERT_WS", "ID", "STRING", "BIGSTRING", "BIGSTRING_NO_NL", 
		"ANON_TEMPLATE", "TMPL_ASSIGN", "ASSIGN", "DOT", "COMMA", "COLON", "LPAREN", 
		"RPAREN", "LBRACK", "RBRACK", "AT", "TRUE", "FALSE", "ELLIPSIS", "'delimiters'", 
		"'import'", "'default'", "'key'", "'value'", "'first'", "'last'", "'rest'", 
		"'trunc'", "'strip'", "'trim'", "'length'", "'strlen'", "'reverse'", "'group'", 
		"'wrap'", "'anchor'", "'separator'"
	};

	public static final String[] ruleNames = {
		"group", "delimiters", "imports", "template", "formalArgs", "formalArg", 
		"dict", "dictPairs", "keyValuePair", "defaultValuePair", "keyValue"
	};

	public FormatProcessor(ParseTree tree) {
		super(tree);
		init();
	}

	/**
	 * Entry point for finding all matches of the defined XPaths in the parse tree
	 */
	@Override
	public void findAll() {
		super.findAll();
	}

	/**
	 * Entry point for finding all matches of a set of one or more named XPaths in the parse tree. The name of an XPath
	 * is the rulename used in the tree grammar to define the XPath.
	 */
	@Override
	public void find(String... names) {
		super.find(names);
	}

	/**
	 * Change the parse tree to match against. Implicitly performs a reset.
	 */
	@Override
	public void setNewParseTree(ParseTree tree) {
		super.setNewParseTree(tree);
	}

	/**
	 * Clears state information developed in a prior find operation.
	 */
	@Override
	public void reset() {
		super.reset();
	}

	protected String[] getTokenNames() {
		return tokenNames;
	}

	protected String[] getRuleNames() {
		return ruleNames;
	}

	private void init() {
		mainRule("format");

					createPathSpec("delimiters");
						addElement(EType.Rule, false, false, "group", 0); 
						addElement(EType.Rule, false, false, "delimiters", 1); 
					completePathSpec(); 

					createPathSpec("imports");
						addElement(EType.Rule, false, false, "group", 0); 
						addElement(EType.Rule, false, false, "imports", 2); 
					completePathSpec(); 

					createPathSpec("template");
						addElement(EType.Rule, true, false, "template", 3); 
					completePathSpec(); 

					createPathSpec("args");
						addElement(EType.Rule, true, false, "formalArgs", 4); 
					completePathSpec(); 

					createPathSpec("arg");
						addElement(EType.Rule, true, false, "formalArg", 5); 
					completePathSpec(); 

					createPathSpec("dict");
						addElement(EType.Rule, true, false, "dict", 6); 
					completePathSpec(); 

					createPathSpec("dpairs");
						addElement(EType.Rule, true, false, "dictPairs", 7); 
					completePathSpec(); 

					createPathSpec("kpairs");
						addElement(EType.Rule, true, false, "keyValuePair", 8); 
					completePathSpec(); 

					createPathSpec("vpairs");
						addElement(EType.Rule, true, false, "defaultValuePair", 9); 
					completePathSpec(); 
	}


	@Override
	public void executeActionBlock(String name) {
		switch (name) {
					case "template":
						template();
						break;
					case "args":
						args();
						break;
					case "kpairs":
						kpairs();
						break;
					case "vpairs":
						vpairs();
						break;
					case "imports":
						imports();
						break;
					case "delimiters":
						delimiters();
						break;
					case "arg":
						arg();
						break;
					case "dict":
						dict();
						break;
					case "dpairs":
						dpairs();
						break;
		}
	}

	private void template() {
		if (entering()) {	helper.locate(getToken(STGParser.ID, 0)).indent().splitBefore();
					helper.locate(getToken(STGParser.LPAREN, 0)).space(FORMAT_SPACE_PAREN);
					helper.locate(getToken(STGParser.RPAREN, 0)).space(FORMAT_SPACE_PAREN);
					helper.locate(getToken(STGParser.TMPL_ASSIGN, 0)).space(FORMAT_SPACE_TMPL_ASSIGN);
				}
	}

	private void args() {
		if (entering()) {	helper.locate(getToken(STGParser.COMMA, 0)).space(FORMAT_SPACE_COMMA);	}
	}

	private void kpairs() {
		if (entering()) {	helper.locate(getToken(STGParser.COLON, 0)).space(FORMAT_SPACE_COLON);	}
	}

	private void vpairs() {
		if (entering()) {	helper.locate(getToken(STGParser.COLON, 0)).space(FORMAT_SPACE_COLON);	}
	}

	private void imports() {
		if (entering()) {	helper.locate(getToken(STGParser.IMPORT, 0)).indent().spaceAfter();
					helper.locate(getToken(STGParser.STRING, 0)).splitAfter();
				}
	}

	private void delimiters() {
		if (entering()) {	helper.locate(getToken(STGParser.DELIMITERS, 0)).indent().spaceAfter();
					helper.locate(getToken(STGParser.COMMA, 0)).space(FORMAT_SPACE_COMMA);
				}
	}

	private void arg() {
		if (entering()) {	helper.locate(getToken(STGParser.ASSIGN, 0)).space(FORMAT_SPACE_ASSIGN);	}
	}

	private void dict() {
		if (entering()) {	helper.locate(getToken(STGParser.ID, 0)).indent();
					helper.locate(getToken(STGParser.TMPL_ASSIGN, 0)).space(FORMAT_SPACE_TMPL_ASSIGN);
					helper.locate(getToken(STGParser.LBRACK, 0)).space(FORMAT_SPACE_LBRACKET);
					helper.locate(getToken(STGParser.RBRACK, 0)).space(FORMAT_SPACE_RBRACKET);
				}
	}

	private void dpairs() {
		if (entering()) {	helper.locate(getToken(STGParser.COMMA, 0)).space(FORMAT_SPACE_COMMA);	}
	}



private DslCodeFormatter helper;

	public void setHelper(DslCodeFormatter helper) {
		this.helper = helper;
	}
}