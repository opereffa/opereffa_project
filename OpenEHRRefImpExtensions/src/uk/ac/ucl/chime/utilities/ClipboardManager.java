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
package uk.ac.ucl.chime.utilities;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public final class ClipboardManager implements ClipboardOwner {

  
  
   public void lostOwnership(Clipboard pClipboard, Transferable pTransferable) {    
   }
  
  public void setClipboardContents(String pContent){
    StringSelection stringSel = new StringSelection(pContent);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSel, this);
  }

  public String getClipboardContents() {
    String content = "";
    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();    
    Transferable clipboardContents = clpbrd.getContents(null);
    boolean hasText = false;
    if ((clipboardContents != null) && clipboardContents.isDataFlavorSupported(DataFlavor.stringFlavor))
    	hasText = true;
    if (hasText) {
      try {
        content = (String)clipboardContents.getTransferData(DataFlavor.stringFlavor);
      }
      catch (Exception ex){        
        System.out.println(ex);
        ex.printStackTrace();
      }      
    }
    return content;
  }
} 
