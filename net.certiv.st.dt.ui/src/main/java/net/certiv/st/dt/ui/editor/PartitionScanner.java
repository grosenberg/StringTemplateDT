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
package net.certiv.st.dt.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

import net.certiv.dsl.ui.editor.scanners.DslRuleBasedPartitionScanner;

public class PartitionScanner extends DslRuleBasedPartitionScanner {

	public PartitionScanner() {

		IToken slcToken = new Token(Partitions.COMMENT_SL);
		IToken mlcToken = new Token(Partitions.COMMENT_ML);
		IToken docToken = new Token(Partitions.COMMENT_JD);

		List<IRule> rules = new ArrayList<>();

		rules.add(new EndOfLineRule("//", slcToken));
		rules.add(new MultiLineRule("/**", "*/", docToken));
		rules.add(new MultiLineRule("/*", "*/", mlcToken));
		IPredicateRule[] rule = new IPredicateRule[rules.size()];
		setPredicateRules(rules.toArray(rule));
	}
}
