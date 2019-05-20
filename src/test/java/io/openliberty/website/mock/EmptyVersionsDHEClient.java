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

package io.openliberty.website.mock;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import io.openliberty.website.Constants;
import io.openliberty.website.dheclient.DHEClient;

public class EmptyVersionsDHEClient extends DHEClient {

	@Override
	public JsonObject retrieveJSON(String url) {
		if (url.equals("https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/runtime/release/info.json")) {
			JsonObjectBuilder runtimeReleaseVersions = Json.createObjectBuilder();
			runtimeReleaseVersions.add(Constants.VERSIONS, Json.createArrayBuilder().build());
			return runtimeReleaseVersions.build();
		}
		if (url.equals("https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/runtime/nightly/info.json")) {
			JsonObjectBuilder runtimeReleaseVersions = Json.createObjectBuilder();
			runtimeReleaseVersions.add(Constants.VERSIONS, Json.createArrayBuilder().build());
			return runtimeReleaseVersions.build();
		}
		if (url.equals("https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/tools/release/info.json")) {
			JsonObjectBuilder runtimeReleaseVersions = Json.createObjectBuilder();
			runtimeReleaseVersions.add(Constants.VERSIONS, Json.createArrayBuilder().build());
			return runtimeReleaseVersions.build();
		}
		if (url.equals("https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/tools/nightly/info.json")) {
			JsonObjectBuilder runtimeReleaseVersions = Json.createObjectBuilder();
			runtimeReleaseVersions.add(Constants.VERSIONS, Json.createArrayBuilder().build());
			return runtimeReleaseVersions.build();
		}
		return null;
	}

	@Override
	public String retrieveSize(String url) {
		return "1234";
	}
}
