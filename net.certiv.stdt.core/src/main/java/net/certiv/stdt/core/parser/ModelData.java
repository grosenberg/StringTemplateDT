package net.certiv.stdt.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;

public class ModelData {

	// overlay type
	public static final int COMBINED = 1 << 0;

	public String name = "";
	public int decoration = 0;

	public ModelData(String name, int decoration) {
		this.name = name;
		this.decoration = decoration;
	}

	public ModelData(String name) {
		this.name = name;
	}

	public ModelData(ModelType object, ParseTree ctx, String text) {}
}
