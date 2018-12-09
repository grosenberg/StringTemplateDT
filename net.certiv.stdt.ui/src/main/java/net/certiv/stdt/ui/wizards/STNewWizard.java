package net.certiv.stdt.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.util.CoreUtil;
import net.certiv.dsl.ui.DslImageManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.wizards.DslBaseWizard;
import net.certiv.stdt.core.STCore;
import net.certiv.stdt.ui.STUI;

/**
 * New file wizard. The wizard creates one file with the extension "st".
 */
public class STNewWizard extends DslBaseWizard {

	/** Platform dependent end-of-line marker */
	public static final String eol = System.lineSeparator();

	private STNewWizardPage page;

	public STNewWizard() {
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
	public String getWindowShellTitle() {
		return "New StringTemplate group";
	}

	@Override
	public ImageDescriptor getPageImageDescriptor() {
		DslImageManager imgMgr = getDslUI().getImageManager();
		return imgMgr.getDescriptor(imgMgr.IMG_WIZBAN_NEW_FILE);
	}

	@Override
	public void addPages() {
		page = new STNewWizardPage(getSelection());
		page.setTitle("Template");
		page.setDescription("Create new StringTemplate group template file");
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		final String filename = page.getFileName();
		final IPath container = page.getContainerFullPath();

		IRunnableWithProgress op = new IRunnableWithProgress() {

			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(filename, container, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * The worker method. It will find the container, create the file if missing or just replace its
	 * contents, and open the editor on the newly created file.
	 */

	private void doFinish(String filename, IPath containerPath, IProgressMonitor monitor) throws CoreException {

		monitor.beginTask("Creating " + filename, 2);
		IWorkspaceRoot root = CoreUtil.getWorkspaceRoot();
		IContainer container = (IContainer) root.findMember(containerPath);
		if (!container.exists() || !(container instanceof IContainer)) {
			throwCoreException("Container '" + containerPath + "' does not exist.");
			return;
		}

		int dot = filename.lastIndexOf('.');
		final String name = (dot != -1) ? filename.substring(0, dot) : filename;

		final IFile file = container.getFile(new Path(filename));
		try {
			InputStream stream = openContentStream(name);
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {}
		monitor.worked(1);

		monitor.setTaskName("Opening file for editing...");
		openEditor(file);
		monitor.worked(1);
	}

	private InputStream openContentStream(String name) {
		StringBuilder sb = new StringBuilder();
		sb.append("/**" + eol);
		sb.append(" * New " + name + " group file" + eol);
		sb.append(" */" + eol + eol);

		sb.append("decl(type, name, value) ::= <<" + eol);
		sb.append("\t<type> <name><if(value)> = <value><endif>" + eol);
		sb.append(">>" + eol);
		return new ByteArrayInputStream(sb.toString().getBytes());
	}
}
