package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.dsl.core.model.ModuleStmt;
import net.certiv.dsl.core.model.builder.ModelBuilder;
import net.certiv.dsl.core.util.antlr.AntlrUtil;
import net.certiv.stdt.core.model.Specialization;
import net.certiv.stdt.core.model.SpecializedType;
import net.certiv.stdt.core.parser.gen.STGParser.DelimitersContext;
import net.certiv.stdt.core.parser.gen.STGParser.DictContext;
import net.certiv.stdt.core.parser.gen.STGParser.GroupContext;
import net.certiv.stdt.core.parser.gen.STGParser.ImportSpecContext;
import net.certiv.stdt.core.parser.gen.STGParser.TemplateContext;

/** Implementing functions for the model tree walker. */
public abstract class StructureBuilder extends Processor {

	protected ModelBuilder builder;

	public StructureBuilder(ParseTree tree) {
		super(tree);
	}

	public void setBuilder(ModelBuilder builder) {
		this.builder = builder;
	}

	public void createGroup() {
		GroupContext ctx = (GroupContext) lastPathNode();
		String name = groupName(ctx);
		Specialization data = new Specialization(SpecializedType.Group, ctx, name);
		ModuleStmt module = builder.module(ctx, name, data);
		builder.pushParent(module);
	}

	public void createTemplateStatement() {
		TemplateContext ctx = (TemplateContext) lastPathNode();
		if (ctx.AT() != null) {
			Specialization data = new Specialization(SpecializedType.Region, ctx, AntlrUtil.join(".", ctx.ID()));
			builder.statement(ctx, ctx.name, data);
		} else {
			Specialization data = new Specialization(SpecializedType.Template, ctx, ctx.name.getText());
			builder.statement(ctx, ctx.name, data);
		}
	}

	public void createDelimStatement() {
		DelimitersContext ctx = (DelimitersContext) lastPathNode();
		String name = "Delimiters: ";
		name += ctx.STRING(0) != null ? ctx.STRING(0) : "";
		name += ctx.STRING(1) != null ? ", " + ctx.STRING(1) : "";
		Specialization data = new Specialization(SpecializedType.Delims, ctx, name);
		builder.statement(ctx, ctx, data);
	}

	public void createImportStatement() {
		ImportSpecContext ctx = (ImportSpecContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.Imports, ctx, ctx.STRING().getText());
		builder.statement(ctx, ctx, data);
	}

	public void createDictStatement() {
		DictContext ctx = (DictContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.Dict, ctx, ctx.ID().getText());
		builder.statement(ctx, ctx.ID(), data);
	}

	public void blockEnd() {
		// ParserRuleContext ctx = (ParserRuleContext) lastPathNode();
		// if (ctx instanceof GroupContext) {
		// GroupContext cty = (GroupContext) ctx;
		// builder.block(IDslElement.END_BLOCK, cty.getChild(0), cty.getChild(cty.getChildCount() - 1), null);
		// }
	}

	private String groupName(GroupContext ctx) {
		String name = ctx.start.getInputStream().getSourceName();
		if (name == null || name.isEmpty()) {
			return "StringTemplate Group";
		}

		IPath path = Path.fromOSString(name);
		name = path.removeFileExtension().lastSegment();
		return name + " Group";
	}
}
