/*******************************************************************************
 * Copyright 2012 Sevket Seref Arikan, David Ingram
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package uk.ac.ucl.chime.openehr.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.index.MemoryIndex;
import org.eclipse.jdt.internal.core.util.MementoTokenizer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.part.FileEditorInput;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CComplexObject;

import se.acode.openehr.parser.ADLParser;
import uk.ac.ucl.chime.gui.Generator;
import uk.ac.ucl.chime.gui.PanelFieldInfo;
import uk.ac.ucl.chime.main.MainOne;

import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.wrappers.RMClassName;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ClusterArchetypeWrapper;

public class TestActionDelegate implements IWorkbenchWindowActionDelegate, ISelectionListener {

	protected String beginXHTML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
		+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
		+ "\r\n"
		+ "<html xmlns=\"http://www.w3.org/1999/xhtml\"\r\n"
		+ "	xmlns:ui=\"http://java.sun.com/jsf/facelets\"\r\n"
		+ "	xmlns:h=\"http://java.sun.com/jsf/html\"\r\n"
		+ "	xmlns:f=\"http://java.sun.com/jsf/core\" xml:lang=\"en\" lang=\"en\">\r\n"
		+ "	<ui:composition template=\"archetypeFormTemplate.xhtml\">\r\n"
		+ "		<ui:define name=\"archetypeFormContent\">\n";
	protected String endXHTML = "</ui:define>		\r\n" + "	</ui:composition>\r\n"
		+ "</html>";
	private ISelection _selection;
	protected IFile archetypeFile = null;
	private IWorkbenchWindow _window;
	
	public void dispose() {
		_window.getSelectionService().removeSelectionListener(this);

	}

	public void init(IWorkbenchWindow window) {
		_window = window;
		window.getSelectionService().addSelectionListener(this);
		

	}

	public void run(IAction action) {
		if (archetypeFile != null){
			ContainerSelectionDialog dlg = new ContainerSelectionDialog(_window.getShell(), null, true, "Select parent for new JSF artifact");			
			dlg.open();
			Object[] res = dlg.getResult();
			if (res != null && res.length > 0){
				String folderPath = res[0].toString();
				createCompilationUnits(folderPath);
			}
			System.out.println(res.length);
			
		}
//		if (archetypeFile != null)
//			createCompilationUnits(archetypeFile.getProject());

	}

	public void selectionChanged(IAction action, ISelection selection) {
		System.out.println("selection changed from wb window action delegate");
		System.out.println(selection.toString());
		if (selection instanceof StructuredSelection && selection instanceof TreeSelection && ((TreeSelection)selection).getFirstElement() instanceof IFile){						
			String extension = ((IFile)((TreeSelection)selection).getFirstElement()).getFileExtension();
			if (extension.equals("adl")){
				action.setEnabled(true);									
			}
		}
		else
			action.setEnabled(false);
		
	}
	
	private ClusterArchetypeWrapper readSelectedArchetype(){
		try {
			File f = new File(archetypeFile.getRawLocation().toOSString());
			if (!f.exists()){
				System.out.println("Archetype file not found, quitting");
				return null;
			}
			ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
			
			ADLParser parser = new ADLParser(f);
			Archetype arc = parser.parse();
			
//			process definition subsection of archetype
			CComplexObject definition =  arc.getDefinition();
			String definitionTypeName = definition.getRmTypeName();
			if (RMClassName.valueOf(definitionTypeName) == RMClassName.CLUSTER){
				ClusterArchetypeWrapper aw  = (ClusterArchetypeWrapper) factory.loadFromFile(archetypeFile.getRawLocation().toOSString());
//				ClusterWrapper cw = new ClusterWrapper(definition,aw);
//					ClusterWrapper processed = cw.processCluster(null);
					return aw;
			}	
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	private void createCompilationUnits(String pFolderName){
		try {
			String filePath = archetypeFile.getLocation().toOSString();
			ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
			ArchetypeWrapper wrapper = factory.loadFromFile(filePath);
			String formText = beginXHTML + wrapper.getJSFForm() + endXHTML;
			formText = formText.replaceAll("&", "&amp;");			
			
			IFolder jsfFolder = archetypeFile.getProject().getFolder(new Path(pFolderName));			
//			IPackageFragmentRoot srcFolder = (IPackageFragmentRoot) JavaCore.create(topSrc);
//			IPackageFragment newFolder = srcFolder.createPackageFragment("uk.ac.ucl.chime.generated", true, null);
//			ICompilationUnit javaUnit = jsfFolder.createCompilationUnit(getJSFArtifactName(), javaContent, true, null);
//			IPath path =  newFolder.getPath().makeRelative();
			IFile xmlFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(pFolderName  + "/"+ getJSFArtifactName()));
			if (xmlFile.exists()){
				xmlFile.delete(true, null);
			}
			InputStream is = new ByteArrayInputStream(formText.getBytes("UTF-8"));
			xmlFile.create(is, true, null);
			IEditorDescriptor desc = PlatformUI.getWorkbench().
	        getEditorRegistry().getDefaultEditor(xmlFile.getName());
			IWorkbenchPage page = Workbench.getInstance().getActiveWorkbenchWindow().getActivePage();
			if (desc == null){
				MessageDialog.openWarning(_window.getShell(), "Warning", "It appears you do not have an editor assigned to open the xhtml file. Please configure your Eclipse settings.");
				return;
			}else
				page.openEditor(new FileEditorInput(xmlFile), desc.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openError(_window.getShell(), "Error", "Error during file generation: " + e.toString());
		} 
		
	}

	//obselete: it was used for dekstop client source generation
//	private String getJavaClassName() {
//		return archetypeFile.getName().replaceAll("-", "_").replaceAll("\\.", "_") ;
//	}
	
	private String getJSFArtifactName() {
		String artifactName = archetypeFile.getName().replaceAll(".adl", ".xhtml"); 
		return artifactName;
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof StructuredSelection && selection instanceof TreeSelection){
			System.out.println("selection changed from selection listener");
			//System.out.println(((TreeSelection)selection).getFirstElement().getClass().toString());
//			org.eclipse.core.internal.resources.File
			if (((TreeSelection)selection).getFirstElement() instanceof IFile){
				String extension = ((IFile)((TreeSelection)selection).getFirstElement()).getFileExtension();
				if (extension.equals("adl")){
					archetypeFile = ((IFile)((TreeSelection)selection).getFirstElement());
					return;
				}
			}
		}
		archetypeFile = null;
		
	}

	

}
