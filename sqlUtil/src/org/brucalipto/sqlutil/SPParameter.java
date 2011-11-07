/*
 * Copyright 2011 Piero Ottuzzi <piero.ottuzzi@brucalipto.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package org.brucalipto.sqlutil;

/**
 * Class rappresenting a parameter useful for calling a STORED PROCEDURE
 * @author Piero Ottuzzi <piero.ottuzzi@brucalipto.org>
 */
public class SPParameter
{
    public final int sqlType;
    public final Object value;
    public final String name;
    /**
     * Constructor
     * @param sqlType An int rappresenting a java.sql.Types of this object
     * @param value An Object
     */
    public SPParameter(int sqlType, Object value)
    {
        this.sqlType = sqlType;
        this.value = value;
        this.name = "";
    }

    /**
     * Constructor
     * @param sqlType An int rappresenting a java.sql.Types of this object
     * @param value An Object
     * @param name The parameter name in the SP
     */
    public SPParameter(int sqlType, String name, Object value)
    {
        this.sqlType = sqlType;
        this.value = value;
        this.name = name;
    }

    /**
     * Overrides Object's toString()
     * @return A String rappresenting a SPParameter
     */
    public String toString()
    {
    	if (this.name.length()>0)
    	{
    		return "'"+this.name+"': 'Types."+SQLUtilTypes.SQL_TYPES.get(Integer.valueOf(""+this.sqlType))+"'->'"+this.value+"'";
    	}
    	else
    	{
    		return "'Types."+SQLUtilTypes.SQL_TYPES.get(Integer.valueOf(""+this.sqlType))+"'->'"+this.value+"'";
    	}
    }
}
