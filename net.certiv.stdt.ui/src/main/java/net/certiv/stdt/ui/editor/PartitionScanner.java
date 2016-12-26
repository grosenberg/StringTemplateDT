package net.certiv.stdt.ui.editor;

import java.util.ArrayList;
import java.util.List;

import net.certiv.dsl.ui.text.AbstractRuleBasedPartitionScanner;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class PartitionScanner extends AbstractRuleBasedPartitionScanner {

	public PartitionScanner() {

		IToken slComment = new Token(Partitions.COMMENT_SL);
		IToken mlComment = new Token(Partitions.COMMENT_ML);
		IToken jdComment = new Token(Partitions.COMMENT_JD);
		IToken gptemplate = new Token(Partitions.TEMPLATE_GP);
		IToken slTemplate = new Token(Partitions.TEMPLATE_SL);

		List<IRule> rules = new ArrayList<IRule>();

		rules.add(new EndOfLineRule("//", slComment));
		rules.add(new MultiLineRule("/**", "*/", jdComment));
		rules.add(new MultiLineRule("/*", "*/", mlComment));
		rules.add(new MultiLineRule("<<", ">>", gptemplate));
		rules.add(new SingleLineRule("\"", "\"", slTemplate));

		IPredicateRule[] rule = new IPredicateRule[rules.size()];
		setPredicateRules(rules.toArray(rule));
	}
}
