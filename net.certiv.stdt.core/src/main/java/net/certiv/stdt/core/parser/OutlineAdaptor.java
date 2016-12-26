package net.certiv.stdt.core.parser;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.dsl.core.parser.IModelConstruction;
import net.certiv.stdt.core.parser.gen.STGParser.DelimitersContext;
import net.certiv.stdt.core.parser.gen.STGParser.DictContext;
import net.certiv.stdt.core.parser.gen.STGParser.ImportsContext;
import net.certiv.stdt.core.parser.gen.STGParser.TemplateContext;

/**
 * Implementing functions for contructing the outline view.
 * 
 * @author Gerald B. Rosenberg
 * @since 2010.03.10
 */
public abstract class OutlineAdaptor extends Processor {

	protected IModelConstruction helper;

	public OutlineAdaptor(ParseTree tree) {
		super(tree);
	}

	public void setHelper(IModelConstruction helper) {
		this.helper = helper;
	}

	public void createModule(ParseTree root, String name) {
		ModelData customData = new ModelData(name, ModelData.COMBINED);
		helper.module(root, customData);
	}

	public void createTemplateStatement(String name, boolean at) {
		TemplateContext ctx = (TemplateContext) lastPathNode();
		ModelType mType = at ? ModelType.Region : ModelType.Template;
		ModelData data = new ModelData(mType, ctx, name);
		helper.statement(ctx, ctx.ID().get(0), data);
	}

	public void createDelimStatement() {
		DelimitersContext ctx = (DelimitersContext) lastPathNode();
		String name = "Delimiters: ";
		name += ctx.STRING(0) != null ? ctx.STRING(0) : "";
		name += ctx.STRING(1) != null ? ", " + ctx.STRING(1) : "";
		ModelData data = new ModelData(ModelType.Delims, ctx, name);
		helper.statement(ctx, ctx, data);
	}

	public void createImportStatement() {
		ImportsContext ctx = (ImportsContext) lastPathNode();
		for (TerminalNode node : ctx.STRING()) {
			ModelData data = new ModelData(ModelType.Import, ctx, node.getText());
			helper.statement(ctx, ctx, data);
		}
	}

	public void createDictStatement(String id) {
		DictContext ctx = (DictContext) lastPathNode();
		ModelData data = new ModelData(ModelType.Dict, ctx, id);
		helper.statement(ctx, ctx, data);
	}

	public void blockEnd() {
		helper.blockEnd();
	}

	@SuppressWarnings("unused")
	private String join(List<TerminalNode> nodes, char c) {
		StringBuilder sb = new StringBuilder();
		for (TerminalNode node : nodes) {
			sb.append(node.getSymbol().getText() + c);
		}
		if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
