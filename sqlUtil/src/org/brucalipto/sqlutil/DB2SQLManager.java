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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class implementation specific for DB2
 * @author Piero Ottuzzi <piero.ottuzzi@brucalipto.org>
 */
public class DB2SQLManager extends SQLManager 
{
	private final static Map instances = new HashMap();
	private final static Log log = LogFactory.getLog(DB2SQLManager.class);
	
	private DB2SQLManager()
	{
		super((DataSource)null);
		throw new IllegalStateException("Do not call this contructor!");
	}
	
	public static DB2SQLManager getInstance(String dataSourceName)
	{
		DB2SQLManager instance = (DB2SQLManager)instances.get(dataSourceName);
		if (instance == null)
		{
			instance = new DB2SQLManager(dataSourceName);
			instances.put(dataSourceName, instance);
		}
		return instance;
	}
	
	private DB2SQLManager(DataSource dataSource)
	{
		super(dataSource);
	}
	
    private DB2SQLManager(String dataSourceName)
	{
		super(dataSourceName);
	}
	
	public DB2SQLManager(Connection conn)
	{
		super(conn);
	}
	
    /**
     * Method useful for using STORED PROCEDURE
     * @param spib The {@link SPInputBean} bean containing data to execute the stored procedure
     * @return The {@link SPOutputBean} containing returned values
     */
    public SPOutputBean executeSP(final SPInputBean spib) throws SQLException
    {
        Connection conn = null;
        CallableStatement call = null;
        ResultSet resultSet = null;
        final String procedureName = spib.spName;
        
        SPParameter[] inputParameters = spib.inputParams;
        int[] outputParameters = spib.outputParams;
        
        final int inputParametersSize = inputParameters.length;
        final int outputParametersSize = outputParameters.length;
        
        final StringBuffer spName = new StringBuffer("call ").append(procedureName).append('(');
        int totalParameters = inputParametersSize+outputParametersSize;
        for(int i=0; i<totalParameters; i++)
        {
            if(i!=totalParameters-1)
            {
                spName.append("?,");
            }
            else
            {
                spName.append('?');
            }
        }
        spName.append(")");
        
        try
        {
            if (this.dataSource!=null)
        	{
            	conn = this.dataSource.getConnection();
        	}
        	else
        	{
        		conn = this.connection;
        	}
            call = conn.prepareCall(spName.toString());
            for (int i=0; i<inputParametersSize; i++)
            {
                final SPParameter inputParam = inputParameters[i];
                final int sqlType = inputParam.sqlType;
                final Object inputParamValue = inputParam.value;
                log.debug((i+1)+") Setting input value 'Types."+SQLUtilTypes.SQL_TYPES.get(Integer.valueOf(""+sqlType))+"'-'"+inputParamValue+"'");
                if (inputParamValue==null)
                {
                    call.setNull(i+1, sqlType);
                    continue;
                }
                switch(sqlType)
                {
                    case Types.VARCHAR:
                        call.setString(i+1, (String)inputParamValue);
                        break;
                    case Types.INTEGER:
                        if (inputParamValue instanceof Integer)
                        {
                            call.setInt(i+1, ((Integer)inputParamValue).intValue());
                        }
                        else if (inputParamValue instanceof Long)
                        {
                            call.setLong(i+1, ((Long)inputParamValue).longValue());
                        }
                        break;
                    case Types.DATE:
                        call.setDate(i+1, (Date)inputParamValue);
                        break;
                    case Types.BOOLEAN:
                        call.setBoolean(i+1, ((Boolean)inputParamValue).booleanValue());
                        break;
                    case Types.CHAR:
                        call.setString(i+1, ((Character)inputParamValue).toString());
                        break;
                    case Types.DOUBLE:
                        call.setDouble(i+1, ((Double)inputParamValue).doubleValue());
                        break;
                    case Types.FLOAT:
                        call.setFloat(i+1, ((Float)inputParamValue).floatValue());
                        break;
                    case Types.TIMESTAMP:
                        call.setTimestamp(i+1, (Timestamp)inputParamValue);
                        break;
                    default:
                        call.setObject(i+1, inputParamValue);
                        break;
                }
            }

            for (int i=0; i<outputParametersSize; i++)
            {
                int sqlType = outputParameters[i];
                log.debug((i+1)+") Registering output type 'Types."+SQLUtilTypes.SQL_TYPES.get(Integer.valueOf(""+sqlType))+"'");
                call.registerOutParameter(inputParametersSize+i+1, sqlType);
            }
			
			log.debug("Going to call: '"+procedureName+"'");
			long elapsedTime = System.currentTimeMillis();
            boolean hasResultSet = call.execute();
            log.debug("SP '"+procedureName+"' executed in "+(System.currentTimeMillis()-elapsedTime)+"millis");
            if (hasResultSet)
            {
            	log.debug("This SP is going to return also a resultSet");
            }
            
            final SPOutputBean output = new SPOutputBean();
            for(int i=0; i<outputParametersSize; i++)
            {
                int sqlType = outputParameters[i];
                log.debug((i+1)+") Getting output type 'Types."+SQLUtilTypes.SQL_TYPES.get(Integer.valueOf(""+sqlType))+"'");
                final Object spResult = call.getObject(inputParametersSize+i+1);
                SPParameter outParam = new SPParameter(sqlType, spResult);
                output.addResult(outParam);
            }
            if (hasResultSet)
            {
            	RowSetDynaClass rowSetDynaClass = new RowSetDynaClass(call.getResultSet());
            	SPParameter outParam = new SPParameter(Types.JAVA_OBJECT, rowSetDynaClass);
            	output.addResult(outParam);
            }
            return output;
        }
        finally
        {
            closeResources(resultSet, call, conn);
        }
    }
}
