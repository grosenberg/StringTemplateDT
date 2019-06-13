package net.certiv.stdt.ui.templates;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;

import net.certiv.dsl.ui.editor.text.completion.DslContentAssistInvocationContext;
import net.certiv.dsl.ui.editor.text.completion.tmpl.DslTemplateCompletionProcessor;
import net.certiv.dsl.ui.templates.CompletionManager;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.editor.Partitions;

public class STTemplateCompletionProcessor extends DslTemplateCompletionProcessor {

	private static char[] IGNORE = new char[] { '.' };

	public STTemplateCompletionProcessor(DslContentAssistInvocationContext context) {
		super(STUI.getDefault(), context);
	}

	@Override
	protected String getContextTypeId() {
		return STTemplateContextType.ST_CONTEXT_TYPE_ID;
	}

	@Override
	protected char[] getIgnore() {
		return IGNORE;
	}

	// NOTE: this handles empty prefixes
	// TODO: implement for globally enabling blank line template completions
	protected boolean isValid(ITextViewer viewer, Region region, String prefix) {
		if (prefix == null) return false;
		if (prefix.length() == 0) {
			TemplateContextType contextType = getContextType(viewer, region);
			String cId = contextType.getId();
			if (STTemplateContextType.ST_CONTEXT_TYPE_ID.equals(cId)) {
				return true;
			}
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected TemplateContextType getContextType(ITextViewer viewer, IRegion region) {
		IDocumentExtension3 doc = (IDocumentExtension3) viewer.getDocument();
		ITypedRegion typedRegion = null;
		try {
			typedRegion = doc.getPartition(Partitions.PARTITIONING, region.getOffset(), true);
		} catch (BadLocationException | BadPartitioningException e) {
			e.printStackTrace();
		}
		if (typedRegion == null) return null;
		String type = typedRegion.getType();

		if (type.equals(IDocument.DEFAULT_CONTENT_TYPE)) {
			return getCompletionMgr().getTemplateContextTypeRegistry()
					.getContextType(STTemplateContextType.ST_CONTEXT_TYPE_ID);
		}

		return super.getContextType(viewer, region);
	}

	private CompletionManager getCompletionMgr() {
		return STUI.getDefault().getCompletionMgr();
	}

	// private boolean isValidPartition(String type) {
	// for (String partition : Partitions.getContentTypes()) {
	// if (type.equals(partition)) return true;
	// }
	// return false;
	// }

	@Override
	protected Template[] getTemplates(String contextTypeId) {
		if (contextTypeId.equals(STTemplateContextType.ST_CONTEXT_TYPE_ID)) {
			return getCompletionMgr().getTemplates();
		}
		return new Template[0];
	}
}
