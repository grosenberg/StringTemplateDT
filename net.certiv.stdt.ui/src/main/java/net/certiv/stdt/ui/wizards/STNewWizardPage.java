package net.certiv.stdt.ui.wizards;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.wizards.DslContainerWizardPage;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

public class STNewWizardPage extends DslContainerWizardPage {

	public STNewWizardPage(IStructuredSelection selection) {
		super("wizardPage", selection);
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
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(container);
		GridLayoutFactory.fillDefaults().spacing(6, 9).margins(6, 6).applyTo(container);

		setFileName("template");
		setFileExtension("stg");
		createContainerControl(container);

		validatePage();
		setErrorMessage(null);
		setMessage(null);
		setControl(container);
	}
}
