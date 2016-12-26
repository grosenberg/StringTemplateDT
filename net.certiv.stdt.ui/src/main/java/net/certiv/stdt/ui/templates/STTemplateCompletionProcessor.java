package net.certiv.stdt.ui.templates;

import net.certiv.dsl.ui.templates.DslTemplateCompletionProcessor;
import net.certiv.dsl.ui.text.completion.DslContentAssistInvocationContext;
import net.certiv.stdt.ui.STUI;
import net.certiv.stdt.ui.editor.Partitions;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;

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

	@Override
	protected STTemplateAccess getTemplateAccess() {
		return STTemplateAccess.getInstance();
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

	@Override
	protected TemplateContextType getContextType(ITextViewer viewer, IRegion region) {
		IDocumentExtension3 doc = (IDocumentExtension3) viewer.getDocument();
		ITypedRegion typedRegion = null;
		try {
			typedRegion = doc.getPartition(Partitions.ST_PARTITIONING, region.getOffset(), true);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (BadPartitioningException e) {
			e.printStackTrace();
		}
		if (typedRegion == null) return null;
		String type = typedRegion.getType();

		if (type.equals(IDocument.DEFAULT_CONTENT_TYPE)) {
			return getTemplateAccess().getContextTypeRegistry()
					.getContextType(STTemplateContextType.ST_CONTEXT_TYPE_ID);
		}

		// return null;
		return super.getContextType(viewer, region);
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
			return getTemplateAccess().getTemplateStore().getTemplates(contextTypeId);
		}
		return new Template[0];
	}
}
