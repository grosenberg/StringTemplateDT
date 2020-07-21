/*******************************************************************************
 * Copyright (c) 2012, 2020 Certiv Analytics.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package net.certiv.st.dt.core.model;

import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.dsl.core.model.builder.ISpecializedType;
import net.certiv.dsl.core.model.builder.ModelSpecialization;

public class Specialization extends ModelSpecialization {

	// overlay type
	public static final int COMBINED = 1 << 0;

	public SpecializedType specializedType;		// specialized model type

	/** Represents a simple statement; name contains the display text. */
	public Specialization(SpecializedType specializedType, String ruleName, ParseTree stmtNode, String name) {
		this(specializedType, ruleName, stmtNode, name, null);
	}

	/** Represents some key/value pair-like statement. */
	public Specialization(SpecializedType specializedType, String ruleName, ParseTree stmtNode, String name,
			ParseTree value) {

		super(ruleName, stmtNode, name, value);
		this.specializedType = specializedType;
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
			return (Specialization) clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Shoud never occur", e);
		}
	}
}
