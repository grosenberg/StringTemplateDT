package net.certiv.stdt.ui.preferences.formatter;

import java.net.URL;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.formatter.DslFormatterFactory;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialog;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialogOwner;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

public class FormatterFactory extends DslFormatterFactory {

	public FormatterFactory() {
		super();
	}

	@Override
	public String[] getFormatterKeys() {
		String[] keys = new String[getPrefsManager().getFormatterKeys().size()];
		getPrefsManager().getFormatterKeys().toArray(keys);
		return keys;
	}

	@Override
	public URL getPreviewContent() {
		return getClass().getResource("FormatPreview.stg");
	}

	@Override
	public IFormatterModifyDialog createDialog(IFormatterModifyDialogOwner dialogOwner) {
		return new ModifyDialog(dialogOwner, this);
	}

	@Override
	public DslUI getDslUI() {
		return STUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return STCore.getDefault();
	}
}
