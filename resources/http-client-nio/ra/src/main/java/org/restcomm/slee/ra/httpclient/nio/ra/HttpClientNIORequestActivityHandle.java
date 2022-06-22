/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2012, TeleStax and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for
 * a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.restcomm.slee.ra.httpclient.nio.ra;

import javax.slee.resource.ActivityHandle;

/**
 * 
 * @author martins
 *
 */
public class HttpClientNIORequestActivityHandle implements ActivityHandle {
	
	private final String id;
	
	/**
	 * 
	 * @param id
	 */
    public HttpClientNIORequestActivityHandle(String id){
        this.id = id;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
    	if (o != null && o.getClass() == this.getClass()) {
			return ((HttpClientNIORequestActivityHandle)o).id.equals(this.id);
		}
		else {
			return false;
		}
    }    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
    	return id.hashCode();
    }	

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HttpClientNIOActivityHandle:" + id;
    }
}
