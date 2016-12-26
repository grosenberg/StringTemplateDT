package net.certiv.stdt.ui.editor;

import net.certiv.dsl.ui.editor.DslDocumentSetupParticipant;
import net.certiv.stdt.ui.STUI;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;

/**
 * Reference in the extension point is used to associate the contextTypeId with a file extension
 */
public class STDocumentSetupParticipant extends DslDocumentSetupParticipant implements
		IDocumentSetupParticipant {

	public STDocumentSetupParticipant() {
		super(STUI.getDefault().getTextTools());
	}
}
