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
package net.certiv.st.dt.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;

import net.certiv.common.util.Strings;
import net.certiv.dsl.ui.wizard.DslFileWizard;
import net.certiv.dsl.ui.wizard.DslFileWizardPage;

public class STNewWizardPage extends DslFileWizardPage {

	public STNewWizardPage(DslFileWizard wizard, IStructuredSelection selection) {
		super("STNewWizardPage", wizard, selection);

		setTitle("Group Template");
		setDescription("Create new StringTemplate group template file");
		setFilename("group");
		setFileExtension("stg");
	}

	@Override
	protected void createCustomGroup(Composite topLevel) {}

	@Override
	protected String getInitialContents() {
		String name = getFilename();

		StringBuilder sb = new StringBuilder();
		sb.append("/**" + Strings.EOL);
		sb.append(" * New " + name + " group file" + Strings.EOL);
		sb.append(" */" + Strings.EOL + Strings.EOL);

		sb.append("decl(type, name, value) ::= <<" + Strings.EOL);
		sb.append("\t<type> <name><if(value)> = <value><endif>" + Strings.EOL);
		sb.append(">>" + Strings.EOL);
		return sb.toString();
	}
}
