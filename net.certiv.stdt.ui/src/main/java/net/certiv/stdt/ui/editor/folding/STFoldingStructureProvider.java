package net.certiv.stdt.ui.editor.folding;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.folding.AbstractDslFoldingStructureProvider;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.editor.Partitions;

public class STFoldingStructureProvider extends AbstractDslFoldingStructureProvider {

	public STFoldingStructureProvider() {
		super();
	}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}

	@Override
	protected String getPartitioning() {
		return Partitions.ST_PARTITIONING;
	}

	@Override
	protected String getMultiLineCommentPartition() {
		return Partitions.COMMENT_ML;
	}

	@Override
	protected String getSingleLineCommentPartition() {
		return Partitions.COMMENT_SL;
	}
}
