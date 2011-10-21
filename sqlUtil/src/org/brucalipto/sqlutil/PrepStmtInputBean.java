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

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

/**
 * Utility class defining parameters needed to call a SQL statement
 * @author Piero Ottuzzi <piero.ottuzzi@brucalipto.org>
 */
public class PrepStmtInputBean
{
    private final transient SQLParameter[] inputParams;

    /** Creates a new instance of SQLInputBean */
    public PrepStmtInputBean(int inputValuesLenght)
    {
        this.inputParams = new SQLParameter[inputValuesLenght];
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A String value
     */
    public void addInputParameter(final int pos, final String value)
    {
        final SQLParameter spParam = new SQLParameter(Types.VARCHAR, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value An Integer value
     */
    public void addInputParameter(final int pos, final Integer value)
    {
        final SQLParameter spParam = new SQLParameter(Types.INTEGER, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Long value
     */
    public void addInputParameter(final int pos, final Long value)
    {
        final SQLParameter spParam = new SQLParameter(Types.INTEGER, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Date value
     */
    public void addInputParameter(final int pos, final Date value)
    {
        final SQLParameter spParam = new SQLParameter(Types.DATE, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Boolean value
     */
    public void addInputParameter(final int pos, final Boolean value)
    {
        final SQLParameter spParam = new SQLParameter(Types.BOOLEAN, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Character value
     */
    public void addInputParameter(final int pos, final Character value)
    {
        final SQLParameter spParam = new SQLParameter(Types.CHAR, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Double value
     */
    public void addInputParameter(final int pos, final Double value)
    {
        final SQLParameter spParam = new SQLParameter(Types.DOUBLE, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Float value
     */
    public void addInputParameter(final int pos, final Float value)
    {
        final SQLParameter spParam = new SQLParameter(Types.FLOAT, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Timestamp value
     */
    public void addInputParameter(final int pos, final Timestamp value)
    {
        final SQLParameter spParam = new SQLParameter(Types.TIMESTAMP, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value An Object value
     */
    public void addInputParameter(final int pos, final Serializable value)
    {
        final SQLParameter spParam = new SQLParameter(Types.OTHER, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A SQLParameter value
     */
    public void addInputParameter(final int pos, final SQLParameter value)
    {
        this.inputParams[pos] = value;
    }

    SQLParameter[] getInputParams()
    {
        return (SQLParameter[])this.inputParams.clone();
    }
}
