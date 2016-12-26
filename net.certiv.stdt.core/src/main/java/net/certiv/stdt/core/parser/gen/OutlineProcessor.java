//
// Generated from Outline.g4 by ANTLR-XVisitor 4.4
//
package net.certiv.stdt.core.parser.gen;

	import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.antlr.runtime.xvisitor.xpath.EType;
import net.certiv.stdt.core.parser.OutlineAdaptor;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OutlineProcessor extends OutlineAdaptor {

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

	public OutlineProcessor(ParseTree tree) {
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
		mainRule("outline");

					createPathSpec("group");
						addElement(EType.Rule, false, false, "group", 0); 
					completePathSpec(); 

					createPathSpec("delims");
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

					createPathSpec("dict");
						addElement(EType.Rule, true, false, "dict", 6); 
					completePathSpec(); 
	}


	@Override
	public void executeActionBlock(String name) {
		switch (name) {
					case "template":
						template();
						break;
					case "delims":
						delims();
						break;
					case "imports":
						imports();
						break;
					case "dict":
						dict();
						break;
					case "group":
						group();
						break;
		}
	}

	private void template() {
		if (entering()) { createTemplateStatement(getToken(STGParser.ID, 0).getText(), getToken(STGParser.AT, 0) != null);	}
	}

	private void delims() {
		if (entering()) { createDelimStatement();	}
	}

	private void imports() {
		if (entering()) { createImportStatement();	}
	}

	private void dict() {
		if (entering()) { createDictStatement(getToken(STGParser.ID, 0).getText());	}
	}

	private void group() {
		if (entering()) {	createModule(getNode(STGParser.RULE_group, 0), "ST Group Outline");	}
		if (exiting()) {	blockEnd(); }
	}



}