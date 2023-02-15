/*******************************************************************************
 * Copyright (c) Contributors to the Eclipse Foundation
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
 *
 * SPDX-License-Identifier: Apache-2.0 
 *******************************************************************************/
package org.osgi.test.cases.framework.weaving.tb1;

/**
 * This class is used as a basic weavable entity that
 * adds a new package dependency. The TCK changes the 
 * value of the constant passed to {@link #doDynamicImport(String)}
 * to be the name of a class which is then dynamically loaded.
 * 
 * @author IBM
 */
public class TestDynamicImport {

	/**
	 * Return the dynamic import result
	 * N.B. without weaving this method will throw {@link ClassNotFoundException}
	 */
	public String toString() {
		return doDynamicImport(
				"f31aa0ab-f572-4c3a-b564-4e47f5935603_a5d56cb7-8987-416e-9212-26631f2924cd");
	}

	/**
	 * Dynamically load the supplied class name and get a String from it
	 * 
	 * @param name The name of the class to load
	 * @return
	 */
	private String doDynamicImport(String name) {
		// Trim any added spaces
		name = name.trim();
		try {
			//If the class name is the SymbolicNameVersion class then we want the object's
			//toString, otherwise the Class's toString is fine, and allows us to load interfaces
			if("org.osgi.test.cases.framework.weaving.tb2.SymbolicNameVersion".equals(name))
				return Class.forName(name)
						.getConstructor()
						.newInstance()
						.toString();
			else
				return Class.forName(name).toString();
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}
}
