package net.certiv.st.dt.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

import net.certiv.dsl.ui.editor.scanners.AbstractRuleBasedPartitionScanner;

public class PartitionScanner extends AbstractRuleBasedPartitionScanner {

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
