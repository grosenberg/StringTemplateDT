package net.certiv.stdt.core.model;

import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.dsl.core.model.builder.ISpecializedType;
import net.certiv.dsl.core.model.builder.ISpecialization;

public class Specialization implements ISpecialization, Cloneable {

	// overlay type
	public static final int COMBINED = 1 << 0;

	public int decoration = 0;
	public SpecializedType specializedType;

	public ParseTree stmtNode;
	public String name;

	public Specialization(SpecializedType specializedType, ParseTree rootNode, String name) {
		this.specializedType = specializedType;
		this.stmtNode = rootNode;
		this.name = name;
	}

	/** Sets the display icon decoration type. */
	public void setDecoration(int decoration) {
		this.decoration = decoration & 0xFF;
	}

	@Override
	public ISpecializedType getSpecializedType() {
		return specializedType;
	}

	@Override
	public void setSpecializedType(ISpecializedType specializedType) {
		this.specializedType = (SpecializedType) specializedType;
	}

	@Override
	public Specialization copy() {
		try {
			return (Specialization) this.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Shoud never occur", e);
		}
	}

	@Override
	public String toString() {
		return String.format("ModelData [name=%s]", name);
	}
}
