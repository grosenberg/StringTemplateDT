package net.certiv.stdt.core.model;

import net.certiv.dsl.core.model.builder.ISpecializedType;
import net.certiv.dsl.core.util.Strings;

public enum SpecializedType implements ISpecializedType {
	
	Group("Grammar Type", Strings.EMPTY),
	Delims("Grammar Type", Strings.EMPTY),
	Imports("Grammar Type", Strings.EMPTY),
	Template("Grammar Type", Strings.EMPTY),
	Region("Grammar Type", Strings.EMPTY),
	Dict("Grammar Type", Strings.EMPTY),

	Block("Grammar Type", Strings.EMPTY),
	Key("Grammar Type", Strings.EMPTY),
	Value("Grammar Type", Strings.EMPTY),

	Unknown("Unknown", Strings.EMPTY),

	;

	public final String name;
	public final String css;

	SpecializedType(String name, String css) {
		this.name = name;
		this.css = css;
	}

	@Override
	public String getStyle() {
		return css;
	}

	@Override
	public String toString() {
		return name;
	}
}
