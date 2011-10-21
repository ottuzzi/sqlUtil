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

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;

/**
 * Class defining parameters needed to call a STORED PROCEDURE
 * @author Piero Ottuzzi <piero.ottuzzi@brucalipto.org>
 */
public class SPInputBean
{
    public final String spName;
    public final SPParameter[] inputParams;
    public final int[] outputParams;

    /**
     * Constructor
     * @param spName The STORED PROCEDURE name
     * @param inputValuesLenght Number of input parameters
     * @param outputValuesLenght Number of output parameters
     */
    public SPInputBean(String spName, int inputValuesLenght, int outputValuesLenght)
    {
        this.spName = spName;
        this.inputParams = new SPParameter[inputValuesLenght];
        this.outputParams = new int[outputValuesLenght];
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A String value
     */
    public void addInputParameter(final int pos, final String value)
    {
        final SPParameter spParam = new SPParameter(Types.VARCHAR, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value An Integer value
     */
    public void addInputParameter(final int pos, final Integer value)
    {
        final SPParameter spParam = new SPParameter(Types.INTEGER, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Long value
     */
    public void addInputParameter(final int pos, final Long value)
    {
        final SPParameter spParam = new SPParameter(Types.INTEGER, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Date value
     */
    public void addInputParameter(final int pos, final Date value)
    {
        final SPParameter spParam = new SPParameter(Types.DATE, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Boolean value
     */
    public void addInputParameter(final int pos, final Boolean value)
    {
        final SPParameter spParam = new SPParameter(Types.BOOLEAN, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Character value
     */
    public void addInputParameter(final int pos, final Character value)
    {
        final SPParameter spParam = new SPParameter(Types.CHAR, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Double value
     */
    public void addInputParameter(final int pos, final Double value)
    {
        final SPParameter spParam = new SPParameter(Types.DOUBLE, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Float value
     */
    public void addInputParameter(final int pos, final Float value)
    {
        final SPParameter spParam = new SPParameter(Types.FLOAT, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value A Timestamp value
     */
    public void addInputParameter(final int pos, final Timestamp value)
    {
        final SPParameter spParam = new SPParameter(Types.TIMESTAMP, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an input parameter
     * @param pos The position in which you want to insert the value
     * @param value An Object value
     */
    public void addInputParameter(final int pos, final Object value)
    {
        final SPParameter spParam = new SPParameter(Types.OTHER, value);
        this.inputParams[pos] = spParam;
    }

    /**
     * Method to add an output parameter
     * @param pos The position in which you define output value
     * @param sqlType A java.sql.Types type of return value
     */
    public void addOutputParameter(final int pos, final int sqlType)
    {
        this.outputParams[pos] = sqlType;
    }
}
