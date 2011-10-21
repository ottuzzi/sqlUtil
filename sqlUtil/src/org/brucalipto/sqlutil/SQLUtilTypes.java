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
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is a superset of java.sql.Types containing constants from
 * others providers (eg. oracle.jdbc.driver.OracleTypes)
 * @author Piero Ottuzzi <piero.ottuzzi@brucalipto.org>
 */
public class SQLUtilTypes
{
	private final static Log log = LogFactory.getLog(SQLUtilTypes.class);
	
	public final static Map SQL_TYPES = Collections.unmodifiableMap(fillJavaSQLTypes());
	
	/* java.sql.Types Constants */
	public final static int BOOLEAN = 16;
	public final static int DATALINK = 70;
	public final static int REF = 2006;
	public final static int CLOB = 2005;
	public final static int BLOB = 2004;
	public final static int ARRAY = 2003;
	public final static int STRUCT = 2002;
	public final static int DISTINCT = 2001;
	public final static int JAVA_OBJECT = 2000;
	public final static int OTHER = 1111;
	public final static int NULL = 0;
	public final static int LONGVARBINARY = -4;
	public final static int VARBINARY = -3;
	public final static int BINARY = -2;
	public final static int TIMESTAMP = 93;
	public final static int TIME = 92;
	public final static int DATE = 91;
	public final static int LONGVARCHAR = -1;
	public final static int VARCHAR = 12;
	public final static int CHAR = 1;
	public final static int DECIMAL = 3;
	public final static int NUMERIC = 2;
	public final static int DOUBLE = 8;
	public final static int REAL = 7;
	public final static int FLOAT = 6;
	public final static int BIGINT = -5;
	public final static int INTEGER = 4;
	public final static int SMALLINT = 5;
	public final static int TINYINT = -6;
	
	/* oracle.jdbc.driver.OracleTypes Constants */
	public final static int FIXED_CHAR = 999;
	public final static int PLSQL_INDEX_TABLE = -14;
	public final static int JAVA_STRUCT = 2008;
	public final static int OPAQUE = 2007;
	public final static int BFILE = -13;
	public final static int CURSOR = -10;
	public final static int ROWID = -8;
	public final static int INTERVALDS = -104;
	public final static int INTERVALYM = -103;
	public final static int TIMESTAMPLTZ = -102;
	public final static int TIMESTAMPTZ = -101;
	public final static int TIMESTAMPNS = -100;
	
	private final static Map fillJavaSQLTypes()
    {
		Map sqlTypes = new Hashtable();
        try
        {
            Field[] sqlTypeFields = SQLUtilTypes.class.getFields();
            if (sqlTypeFields==null)
            {
                sqlTypeFields = new Field[0];
            }
            for (int i=0; i<sqlTypeFields.length; i++)
            {
                final Field sqlType = sqlTypeFields[i];
                final String sqlTypeName = sqlType.getName();
                if (!sqlTypeName.equals("SQL_TYPES"))
                {
	                final int value = sqlType.getInt(sqlTypeName);
	                log.debug("TypeName: '"+sqlTypeName+"'; TypeValue: '"+value+"'");
	                sqlTypes.put(Integer.valueOf(""+value), sqlTypeName);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error adding java.sql.Types to SQLTypes map", e);
        }
        return sqlTypes;
    }
    
    public static void main(String[] argv) throws Throwable
    {
    	System.out.println("Starting...");
    }
}
