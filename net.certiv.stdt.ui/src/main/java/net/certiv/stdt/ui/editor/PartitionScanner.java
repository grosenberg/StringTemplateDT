package net.certiv.stdt.ui.editor;

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

		IToken slComment = new Token(Partitions.COMMENT_SL);
		IToken mlComment = new Token(Partitions.COMMENT_ML);
		IToken jdComment = new Token(Partitions.COMMENT_JD);

		List<IRule> rules = new ArrayList<IRule>();

		rules.add(new EndOfLineRule("//", slComment));
		rules.add(new MultiLineRule("/**", "*/", jdComment));
		rules.add(new MultiLineRule("/*", "*/", mlComment));

		IPredicateRule[] rule = new IPredicateRule[rules.size()];
		setPredicateRules(rules.toArray(rule));
	}
}
