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

package io.openliberty.website;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.openliberty.website.data.BuildData;
import io.openliberty.website.data.BuildLists;
import io.openliberty.website.data.LastUpdate;
import io.openliberty.website.data.LatestReleases;
import io.openliberty.website.dheclient.DHEBuildParser;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class BuildsManager {

	private static final Logger logger = Logger.getLogger(BuildsManager.class.getName());

	@Inject
	private DHEBuildParser dheBuilds;

	/** Defined default constructor */
	public BuildsManager() {
	}

	/** Allow for unittest injection */
	BuildsManager(DHEBuildParser dheBuilds) {
		if (logger.isLoggable(Level.FINER)) {
			logger.log(Level.FINE, "BuildsManager() ", dheBuilds);
		}
		this.dheBuilds = dheBuilds;
	}

	public BuildData getData() {
		return dheBuilds.getBuildData();
	}

	public BuildLists getBuilds() {
		return dheBuilds.getBuildData().getBuilds();
	}

	public LatestReleases getLatestReleases() {
		return dheBuilds.getBuildData().getLatestReleases();
	}

	public LastUpdate getStatus() {
		return dheBuilds.getLastUpdate();
	}

	public LastUpdate updateBuilds() {
		return dheBuilds.blockingUpdate();
	}

}
