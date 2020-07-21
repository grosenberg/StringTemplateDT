/*******************************************************************************
 * Copyright (c) 2012, 2020 Certiv Analytics.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package net.certiv.st.dt.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 *
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class CleanHandler extends AbstractHandler {

	/**
	 * The constructor.
	 */
	public CleanHandler() {}

	/**
	 * the command has been executed, so extract the needed information from the application
	 * context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection != null && selection instanceof IStructuredSelection) {
			for (Object element : ((IStructuredSelection) selection).toArray()) {
				if (element instanceof IResource) {
					IResource res = (IResource) element;
					try {
						res.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
					} catch (CoreException e) {
						throw new ExecutionException(e.getMessage(), e.getCause());
					}
				}
			}
		}
		return null;
	}
}
