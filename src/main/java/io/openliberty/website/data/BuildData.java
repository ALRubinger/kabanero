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

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import io.openliberty.website.Constants;

public class BuildData {
	private LatestReleases latestReleases;
	private BuildLists builds;

	public BuildData(LatestReleases latestReleases, BuildLists builds) {
		this.latestReleases = latestReleases;
		this.builds = builds;
	}

	public JsonObject asJsonObject() {
		JsonObjectBuilder data = Json.createObjectBuilder();
		data.add(Constants.LATEST_RELEASES, latestReleases != null ? latestReleases.asJsonObject() : Json.createObjectBuilder().build());
		data.add(Constants.BUILDS, builds != null ? builds.asJsonObject() : Json.createObjectBuilder().build());
		return data.build();
	}

	public LatestReleases getLatestReleases() {
		return latestReleases;
	}

	public BuildLists getBuilds() {
		return builds;
	}

}
