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

import net.certiv.common.util.Strings;
import net.certiv.dsl.core.model.builder.ISpecializedType;

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
