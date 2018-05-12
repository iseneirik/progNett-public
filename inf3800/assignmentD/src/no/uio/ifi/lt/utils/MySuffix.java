// =================================================================                                                                   
// Copyright (C) 2011-2013 Pierre Lison (plison@ifi.uio.no)                                                                            
//                                                                                                                                     
// This library is free software; you can redistribute it and/or                                                                       
// modify it under the terms of the GNU Lesser General Public License                                                                  
// as published by the Free Software Foundation; either version 2.1 of                                                                 
// the License, or (at your option) any later version.                                                                                 
//                                                                                                                                     
// This library is distributed in the hope that it will be useful, but                                                                 
// WITHOUT ANY WARRANTY; without even the implied warranty of                                                                          
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU                                                                    
// Lesser General Public License for more details.                                                                                     
//                                                                                                                                     
// You should have received a copy of the GNU Lesser General Public                                                                    
// License along with this program; if not, write to the Free Software                                                                 
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA                                                                           
// 02111-1307, USA.                                                                                                                    
// =================================================================                                                                   

package no.uio.ifi.lt.utils;

import no.uio.ifi.lt.storage.IDocumentStore;
import no.uio.ifi.lt.storage.InMemoryDocumentStore;

/**
 * 
 *
 * @author  Pierre Lison (plison@ifi.uio.no)
 * @version $Date::                      $
 *
 */
public class MySuffix implements Comparable<Object> {
	
	int docId;
	int startIndex;
	IDocumentStore store;
	
	public MySuffix(int docId, int startIndex, IDocumentStore store) {
		this.docId = docId;
		this.startIndex = startIndex;
		this.store = store;
	}
	
	public String getString() {
		return store.getDocument(docId).getOriginalData().substring(startIndex);
	}

	/**
	 *
	 * @param arg0
	 * @return
	 */
	public int compareTo(Object arg0) {
		if (arg0 instanceof MySuffix) {
			return getString().compareTo(((MySuffix)arg0).getString());
		}
		else if (arg0 instanceof String) {
			return getString().compareTo((String)arg0);
		}
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public int getDocId() {
		return docId;
	}
	
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * 
	 * @return
	 */
	public IDocumentStore getDictionary() {
		return store;
	}

	

}
