package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.dsl.core.model.IDslElement;
import net.certiv.dsl.core.parser.IModelMaker;
import net.certiv.stdt.core.parser.gen.STGParser.DelimitersContext;
import net.certiv.stdt.core.parser.gen.STGParser.DictContext;
import net.certiv.stdt.core.parser.gen.STGParser.GroupContext;
import net.certiv.stdt.core.parser.gen.STGParser.ImportSpecContext;
import net.certiv.stdt.core.parser.gen.STGParser.TemplateContext;

/** Implementing functions for the model tree walker. */
public abstract class StructureAdaptor extends Processor {

	protected IModelMaker maker;

	public StructureAdaptor(ParseTree tree) {
		super(tree);
	}

	public void setHelper(IModelMaker maker) {
		this.maker = maker;
	}

	public void createGroup() {
		GroupContext ctx = (GroupContext) lastPathNode();
		String name = groupName(ctx);
		ModelData data = new ModelData(ModelType.Group, ctx, name);
		maker.module(ctx, name, data);
	}

	public void createTemplateStatement() {
		TemplateContext ctx = (TemplateContext) lastPathNode();
		ModelType mType = ctx.AT() != null ? ModelType.Region : ModelType.Template;
		for (TerminalNode id : ctx.ID()) {
			ModelData data = new ModelData(mType, ctx, id.getText());
			maker.statement(ctx, id, data);
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
		ParserRuleContext ctx = (ParserRuleContext) lastPathNode();
		if (ctx instanceof GroupContext) {
			GroupContext cty = (GroupContext) ctx;
			maker.block(IDslElement.END_BLOCK, cty.getChild(0), cty.getChild(cty.getChildCount() - 1), null);
		}
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
