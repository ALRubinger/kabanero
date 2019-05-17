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

import java.util.logging.Level;
import java.util.logging.Logger;

public class LatestReleases {

	private static final Logger logger = Logger.getLogger(LatestReleases.class.getName());

	private BuildInfo runtime;
	private BuildInfo tools;
	

	public LatestReleases() {
	}

	public LatestReleases(BuildInfo runtime, BuildInfo tools) {
		this.runtime = runtime;
		this.tools = tools;
	}

	public JsonObject asJsonObject() {
		JsonObjectBuilder json = Json.createObjectBuilder();
		if (runtime != null) {
			json.add(Constants.RUNTIME, runtime.asJsonObject());
		}
		if (tools != null) {
			json.add(Constants.TOOLS, tools.asJsonObject());
		}
		return json.build();
	}

	public BuildInfo getRuntimeRelease() {
		if (logger.isLoggable(Level.FINER)) {
			logger.log(Level.FINE, "getRuntimeRelease()", runtime);
		}
		return runtime;
	}
}
