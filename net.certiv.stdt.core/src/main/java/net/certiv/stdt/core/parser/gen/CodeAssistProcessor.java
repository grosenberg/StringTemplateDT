//
// Generated from CodeAssist.g4 by ANTLR-XVisitor 4.4
//
package net.certiv.stdt.core.parser.gen;

	import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.antlr.runtime.xvisitor.xpath.EType;
import net.certiv.stdt.core.parser.STSourceParser;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CodeAssistProcessor extends Processor {

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

	public CodeAssistProcessor(ParseTree tree) {
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
		mainRule("codeAssist");

					createPathSpec("templateId");
						addElement(EType.Rule, true, false, "template", 3); 
					completePathSpec(); 
	}


	@Override
	public void executeActionBlock(String name) {
		switch (name) {
					case "templateId":
						templateId();
						break;
		}
	}

	private void templateId() {
		if (entering()) { helper.codeAssist(getToken(STGParser.ID, 0)); }
	}



private STSourceParser helper;

	public void setHelper(STSourceParser helper) {
		this.helper = helper;
	}
}