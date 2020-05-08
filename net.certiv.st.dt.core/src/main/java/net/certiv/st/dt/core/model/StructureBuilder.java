package net.certiv.st.dt.core.model;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.dsl.core.model.ModelType;
import net.certiv.dsl.core.model.builder.ModelBuilder;
import net.certiv.dsl.core.util.Strings;
import net.certiv.st.dt.core.parser.gen.STGParser;
import net.certiv.st.dt.core.parser.gen.STGParser.DelimitersContext;
import net.certiv.st.dt.core.parser.gen.STGParser.DictContext;
import net.certiv.st.dt.core.parser.gen.STGParser.GroupContext;
import net.certiv.st.dt.core.parser.gen.STGParser.ImportSpecContext;
import net.certiv.st.dt.core.parser.gen.STGParser.TemplateContext;

/** Implementing functions for the model tree walker. */
public abstract class StructureBuilder extends Processor {

	protected ModelBuilder builder;
	private String name = ModelBuilder.UNKNOWN;

	public StructureBuilder(ParseTree tree) {
		super(tree);
	}

	public void setBuilder(ModelBuilder builder) {
		this.builder = builder;
	}

	public void setSourceName(String name) {
		if (name != null && !name.isEmpty()) {
			this.name = name;
		}
	}

	public void createGroup() {
		GroupContext ctx = (GroupContext) lastPathNode();
		if (!name.equals(ModelBuilder.UNKNOWN)) {
			IPath path = Path.fromOSString(name);
			name = path.removeFileExtension().lastSegment();
		}
		name += " Group";

		Specialization data = new Specialization(SpecializedType.Group, ruleName(ctx), ctx, name);
		builder.module(ctx, name, data);
	}

	public void createTemplateStatement() {
		TemplateContext ctx = (TemplateContext) lastPathNode();
		Specialization data;
		if (ctx.AT() != null) {
			data = new Specialization(SpecializedType.Region, ruleName(ctx), ctx, Strings.join(Strings.DOT, ctx.ID()));
		} else {
			data = new Specialization(SpecializedType.Template, ruleName(ctx), ctx, ctx.name.getText());
		}
		builder.statement(ModelType.EXPRESSION, ctx, ctx.name, data);
	}

	public void createDelimStatement() {
		DelimitersContext ctx = (DelimitersContext) lastPathNode();
		String name = "Delimiters: ";
		name += ctx.STRING(0) != null ? ctx.STRING(0) : Strings.EMPTY;
		name += ctx.STRING(1) != null ? ", " + ctx.STRING(1) : Strings.EMPTY;
		Specialization data = new Specialization(SpecializedType.Delims, ruleName(ctx), ctx, name);
		builder.statement(ModelType.DECLARATION, ctx, ctx, data);
	}

	public void createImportStatement() {
		ImportSpecContext ctx = (ImportSpecContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.Imports, ruleName(ctx), ctx, ctx.STRING().getText());
		builder.importStmt(ctx, ctx, data);
	}

	public void createDictStatement() {
		DictContext ctx = (DictContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.Dict, ruleName(ctx), ctx, ctx.ID().getText());
		builder.statement(ModelType.EXPRESSION, ctx, ctx.ID(), data);
	}

	public void blockEnd() {}

	private String ruleName(ParserRuleContext ctx) {
		return STGParser.ruleNames[ctx.getRuleIndex()];
	}
}
