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

import java.lang.reflect.Field;
import java.sql.Types;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class rappresenting a useful representation of java.sql.Types
 * @author Piero Ottuzzi <piero.ottuzzi@brucalipto.org>
 */
public class SQLUtilTypes
{
	private final static Log log = LogFactory.getLog(SQLUtilTypes.class);
	
	public final static Map SQL_TYPES = Collections.unmodifiableMap(fillJavaSQLTypes());
	
	private final static Map fillJavaSQLTypes()
    {
		Map sqlTypes = new Hashtable();
        try
        {
            Field[] sqlTypeFields = Types.class.getFields();
            if (sqlTypeFields==null)
            {
                sqlTypeFields = new Field[0];
            }
            for (int i=0; i<sqlTypeFields.length; i++)
            {
                final Field sqlType = sqlTypeFields[i];
                final String sqlTypeName = sqlType.getName();
                final int value = sqlType.getInt(sqlTypeName);
                log.debug("TypeName: '"+sqlTypeName+"'; TypeValue: '"+value+"'");
                sqlTypes.put(Integer.valueOf(""+value), sqlTypeName);
            }
        }
        catch (Exception e)
        {
            log.error("Error adding java.sql.Types to SQLTypes map");
        }
        return sqlTypes;
    }
    
    public static void main(String[] argv) throws Throwable
    {
    	System.out.println("Starting...");
    }
}
