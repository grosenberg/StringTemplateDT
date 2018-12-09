package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.dsl.core.model.ModuleStmt;
import net.certiv.dsl.core.model.builder.DslModelMaker;
import net.certiv.dsl.core.util.antlr.AntlrUtil;
import net.certiv.stdt.core.model.ModelData;
import net.certiv.stdt.core.model.ModelType;
import net.certiv.stdt.core.parser.gen.STGParser.DelimitersContext;
import net.certiv.stdt.core.parser.gen.STGParser.DictContext;
import net.certiv.stdt.core.parser.gen.STGParser.GroupContext;
import net.certiv.stdt.core.parser.gen.STGParser.ImportSpecContext;
import net.certiv.stdt.core.parser.gen.STGParser.TemplateContext;

/** Implementing functions for the model tree walker. */
public abstract class StructureBuilder extends Processor {

	protected DslModelMaker maker;

	public StructureBuilder(ParseTree tree) {
		super(tree);
	}

	public void setMaker(DslModelMaker maker) {
		this.maker = maker;
	}

	public void createGroup() {
		GroupContext ctx = (GroupContext) lastPathNode();
		String name = groupName(ctx);
		ModelData data = new ModelData(ModelType.Group, ctx, name);
		ModuleStmt module = maker.module(ctx, name, data);
		maker.pushParent(module);
	}

	public void createTemplateStatement() {
		TemplateContext ctx = (TemplateContext) lastPathNode();
		if (ctx.AT() != null) {
			ModelData data = new ModelData(ModelType.Region, ctx, AntlrUtil.join(".", ctx.ID()));
			maker.statement(ctx, ctx.name, data);
		} else {
			ModelData data = new ModelData(ModelType.Template, ctx, ctx.name.getText());
			maker.statement(ctx, ctx.name, data);
		}
	}

	public void createDelimStatement() {
		DelimitersContext ctx = (DelimitersContext) lastPathNode();
		String name = "Delimiters: ";
		name += ctx.STRING(0) != null ? ctx.STRING(0) : "";
		name += ctx.STRING(1) != null ? ", " + ctx.STRING(1) : "";
		ModelData data = new ModelData(ModelType.Delims, ctx, name);
		maker.statement(ctx, ctx, data);
	}

	public void createImportStatement() {
		ImportSpecContext ctx = (ImportSpecContext) lastPathNode();
		ModelData data = new ModelData(ModelType.Imports, ctx, ctx.STRING().getText());
		maker.statement(ctx, ctx, data);
	}

	public void createDictStatement() {
		DictContext ctx = (DictContext) lastPathNode();
		ModelData data = new ModelData(ModelType.Dict, ctx, ctx.ID().getText());
		maker.statement(ctx, ctx.ID(), data);
	}

	public void blockEnd() {
		// ParserRuleContext ctx = (ParserRuleContext) lastPathNode();
		// if (ctx instanceof GroupContext) {
		// GroupContext cty = (GroupContext) ctx;
		// maker.block(IDslElement.END_BLOCK, cty.getChild(0), cty.getChild(cty.getChildCount() - 1), null);
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
