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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class defining an output from a STORED PROCEDURE
 * @author Piero Ottuzzi <piero.ottuzzi@brucalipto.org>
 */
public class SPOutputBean implements Serializable
{
    private static final long serialVersionUID = 5698716775941303599L;
	private final List results = new ArrayList();

    /**
     * Method to get the List of SPParameters
     * @return A List of {@link SPParameter}
     */
    public List getResults()
    {
        return Collections.unmodifiableList(this.results);
    }

    /**
     * Method to add a {@link SPParameter} to the list
     * @param result An utility method to add a {@link SPParameter} to the list
     */
    void addResult(final Object result)
    {
        this.results.add(result);
    }
}
