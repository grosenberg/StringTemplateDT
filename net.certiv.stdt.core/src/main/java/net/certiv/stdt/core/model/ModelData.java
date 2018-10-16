package net.certiv.stdt.core.model;

import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.dsl.core.model.IDescriptionData;
import net.certiv.dsl.core.model.IStatement.Form;
import net.certiv.dsl.core.model.IStatement.Realm;
import net.certiv.dsl.core.model.IStatement.Type;

public class ModelData implements IDescriptionData {

	// overlay type
	public static final int COMBINED = 1 << 0;

	public int decoration = 0;
	public Type type = Type.VARIABLE;
	public Form form = Form.DECLARATION;
	public Realm realm = Realm.LOCAL;

	public ModelType mType;
	public ParseTree rootNode;
	public String key;

	public ModelData(ModelType mType, ParseTree rootNode, String key) {
		this.mType = mType;
		this.rootNode = rootNode;
		this.key = key;
	}
}
