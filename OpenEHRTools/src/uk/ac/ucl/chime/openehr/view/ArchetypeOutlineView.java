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
package uk.ac.ucl.chime.openehr.view;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CComplexObject;

import se.acode.openehr.parser.ADLParser;
import uk.ac.ucl.chime.main.MainOne;
import uk.ac.ucl.chime.openehr.Activator;
import uk.ac.ucl.chime.openehr.data.Child;
import uk.ac.ucl.chime.openehr.data.Parent;
import uk.ac.ucl.chime.openehr.help.OpenEHRContextProvider;

import uk.ac.ucl.chime.utilities.ArchetypeWrapperFactory;
import uk.ac.ucl.chime.wrappers.ActivityWrapper;
import uk.ac.ucl.chime.wrappers.ClusterWrapper;
import uk.ac.ucl.chime.wrappers.CodedTextValueWrapper;
import uk.ac.ucl.chime.wrappers.DataValueWrapper;
import uk.ac.ucl.chime.wrappers.EHRStructureWrapper;
import uk.ac.ucl.chime.wrappers.ElementWrapper;
import uk.ac.ucl.chime.wrappers.EvaluationWrapper;
import uk.ac.ucl.chime.wrappers.EventWrapper;
import uk.ac.ucl.chime.wrappers.HistoryWrapper;
import uk.ac.ucl.chime.wrappers.IWrapperNavigator;
import uk.ac.ucl.chime.wrappers.InstructionWrapper;
import uk.ac.ucl.chime.wrappers.ItemTreeWrapper;
import uk.ac.ucl.chime.wrappers.ObservationWrapper;
import uk.ac.ucl.chime.wrappers.QuantityWrapper;
import uk.ac.ucl.chime.wrappers.RIMWrapper;
import uk.ac.ucl.chime.wrappers.RMClassName;
import uk.ac.ucl.chime.wrappers.TextValueWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ArchetypeWrapper;
import uk.ac.ucl.chime.wrappers.archetypewrappers.ClusterArchetypeWrapper;


public class ArchetypeOutlineView extends ViewPart implements ISelectionListener{

	protected String archetypeFile = null;
	TreeViewer viewer = null;
	private Map imageCache = new HashMap();
	
	public static ImageDescriptor getImageDescriptor(String name) {
		String iconPath = "/icons/";
		try {
			URL installURL = Activator.getDefault().getBundle().getEntry("/");
			URL url = new URL(installURL, iconPath + name);
			return ImageDescriptor.createFromURL(url);
		} catch (Exception ex) {
			// should not happen
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}
	
	public ArchetypeOutlineView()  {
		// TODO Auto-generated constructor stub	
		
	}

	@Override
	public void createPartControl(Composite parent) {
		getViewSite().getPage().addSelectionListener(this);
		viewer = new TreeViewer(parent);
		getSite().setSelectionProvider(viewer);
		if (archetypeFile == null)
			return;		
		updateTreeView();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		getViewSite().getPage().removeSelectionListener(this);
		System.out.println(" I am unregistering me as listener");
	}
	
	protected void updateTreeView(){
		viewer.setLabelProvider(new LabelProvider(){
			@Override
			public Image getImage(Object element) {				
				ImageDescriptor descriptor = null;
//				if(element instanceof ClusterWrapper)
//					descriptor = getImageDescriptor("16_button_blue.png");
//				else if (element instanceof ElementWrapper)
//					descriptor = getImageDescriptor("16_button_green.png");
//				else
//					descriptor = getImageDescriptor("16_button_red.png");
				
				if(element instanceof TextValueWrapper){
					descriptor = getImageDescriptor("text.gif");
				}
				if(element instanceof CodedTextValueWrapper){
					descriptor = getImageDescriptor("text.gif");
				}
				else if (element instanceof QuantityWrapper){
					descriptor = getImageDescriptor("quantity.gif"); 
				}
				else if (element instanceof ItemTreeWrapper){
					descriptor = getImageDescriptor("structure.png"); 
				}
				else if (element instanceof ElementWrapper){
					descriptor = getImageDescriptor("element.png");
				}
				else if (element instanceof ClusterWrapper){
					descriptor = getImageDescriptor("cluster.png");
				}
				else if (element instanceof ObservationWrapper){
					descriptor = getImageDescriptor("observation.png");
				}
				else if (element instanceof EvaluationWrapper){
					descriptor = getImageDescriptor("evaluation.png"); 
				}
				else if (element instanceof InstructionWrapper){
					descriptor = getImageDescriptor("instruction.png");
				}
				else if (element instanceof ActivityWrapper){
					descriptor = getImageDescriptor("activity.gif");
				}
				else if (element instanceof HistoryWrapper){
					descriptor = getImageDescriptor("history.png");
				}
				else if (element instanceof EventWrapper){
					descriptor = getImageDescriptor("folder_blue.gif");
				}
				
				Image image = (Image)imageCache.get(descriptor);
				if (image == null) {
					image = descriptor.createImage();
					
					imageCache.put(descriptor, image);
				}
				return image;				
			}
			@Override
			public String getText(Object element) {
				try {
					if (element instanceof RIMWrapper)
						return ((RIMWrapper)element).getName();					
					else if (element != null)
						return element.toString();
					else
						return null;
				} catch (Exception ex) {
					System.out.println(ex.toString());
					return null;
				}
			}
		});
		viewer.setContentProvider(new ITreeContentProvider(){

			public Object[] getChildren(Object parentElement) {
				if (parentElement instanceof RIMWrapper){
					try {
						return ((RIMWrapper)parentElement).getChildren().toArray();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				}
				else 
					return null;
			}

			public Object getParent(Object element) {
				//TODO: this will have to change as archetype slots are introduced
				if (element instanceof ArchetypeWrapper)
					return null;
				else if (element instanceof RIMWrapper)
					try {
						return ((IWrapperNavigator)element).getParent();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				else
					return null;
			}

			public boolean hasChildren(Object element) {
				if (element instanceof RIMWrapper){
					try {
						return ( ((RIMWrapper)element).getChildren() != null && ((RIMWrapper)element).getChildren().size() > 0 );
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					} 
				}
				else
					return false;
			}

			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof ArchetypeWrapper){
					Object[] singleMemberArr = new Object[]{((ArchetypeWrapper)inputElement).getRootWrapper()};
					return singleMemberArr;
				}
				else 
					return null;
				
			}

			public void dispose() {
				//dispose images in cache
				for (Iterator i = imageCache.values().iterator(); i.hasNext();) {
					((Image) i.next()).dispose();
				}
				imageCache.clear();	
			}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
				// TODO Auto-generated method stub				
			}			
		});		
		viewer.setInput(getAW());
	}

	protected ArchetypeWrapper getAW(){
		try {
			if (archetypeFile == null)
				return null;
			File f = new File(archetypeFile);
			if (!f.exists()){
				System.out.println("Archetype file not found, quitting");
				System.out.println(f.getAbsolutePath());
				return null;
			}
			ArchetypeWrapperFactory factory = new ArchetypeWrapperFactory();
			ArchetypeWrapper aw  = factory.loadFromFile(archetypeFile);
			return aw;
		} catch (Exception e) {			
			System.out.println(e.toString());
			return null;
		}	
	}
	@Override
	public void setFocus() {
		//this.getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (IContextProvider.class.equals(adapter)) {
			System.out.println("sending out the new context provider...");
    		return new OpenEHRContextProvider(Activator.PLUGIN_ID + ".openehr_plugin_help", (IStructuredSelection)viewer.getSelection());
    	}
		return null;
	}

	

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof StructuredSelection && selection instanceof TreeSelection){
//			System.out.println(((TreeSelection)selection).getFirstElement().getClass().toString());
//			org.eclipse.core.internal.resources.File
			if (((TreeSelection)selection).getFirstElement() instanceof IFile){
				String extension = ((IFile)((TreeSelection)selection).getFirstElement()).getFileExtension();
				if (extension.equals("adl")){
					//if selection change is fired due to right click etc, 
					//in other words, the archetypeFile has not changed, simply return
					if (archetypeFile != null && archetypeFile.equals(((IFile)((TreeSelection)selection).getFirstElement()).getLocation().toOSString()))
						return;
					archetypeFile = ((IFile)((TreeSelection)selection).getFirstElement()).getLocation().toOSString();
					updateTreeView();
					return;
				}
			}
		}
//		archetypeFile = null;
//		updateTreeView();
	}
}
