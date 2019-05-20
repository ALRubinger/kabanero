/******************************************************************************
 *
 * Copyright 2019 IBM Corporation and others.
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
 ******************************************************************************/

package io.openliberty.website.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class BuildDataTest {

	@Test
	public void null_data() {
		BuildData data = new BuildData(null, null);

		assertEquals("{\"latest_releases\":{},\"builds\":{}}", data.asJsonObject().toString());

		assertNull(data.getLatestReleases());
		assertNull(data.getBuilds());
	}

	@Test
	public void empty_data() {
		LatestReleases latest = new LatestReleases();
		BuildLists builds = new BuildLists();
		BuildData data = new BuildData(latest, builds);

		assertEquals("{\"latest_releases\":{},\"builds\":{}}", data.asJsonObject().toString());

		assertEquals(latest, data.getLatestReleases());
		assertEquals(builds, data.getBuilds());
	}

}
