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

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import io.openliberty.website.Constants;

public class LastUpdate {
	private static final int ONE_HOUR_MILLIS = 3600000;

	private Date lastAttempt;
	private Date lastSuccess;

	private static final Logger logger = Logger.getLogger(LastUpdate.class.getName());

	public String getLastUpdateAttempt() {
		String result = lastAttempt == null ? Constants.NEVER_ATTEMPTED : DateUtil.asUTCString(lastAttempt);
		if (logger.isLoggable(Level.FINER)) {
			logger.log(Level.FINE, "getLastUpdateAttempt()", result);
		}
		return result;
	}

	void setLastUpdateAttempt(Date date) {
		lastAttempt = date;
	}

	public String getLastSuccessfulUpdate() {
		String result = lastSuccess == null ? Constants.NEVER_UPDATED : DateUtil.asUTCString(lastSuccess);
		if (logger.isLoggable(Level.FINER)) {
			logger.fine("getLastSuccessfulUpdate() " + result);
		}
		return result;
	}

	public void markUpdateAttempt() {	
		lastAttempt = new Date();
		if (logger.isLoggable(Level.FINER)) {
			logger.fine("markUpdateAttempt() " + lastAttempt);
		}
	}

	public void markSuccessfulUpdate() {	
		lastSuccess = lastAttempt;
		if (logger.isLoggable(Level.FINER)) {
			logger.fine("markSuccessfulUpdate() " + lastSuccess);
		}
	}

	public JsonObject asJsonObject() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add(Constants.LAST_UPDATE_ATTEMPT, getLastUpdateAttempt());
		builder.add(Constants.LAST_SUCCESSFULL_UPDATE, getLastSuccessfulUpdate());
		if (logger.isLoggable(Level.FINER)) {
			logger.fine("asJsonObject() builder=" + builder);
		}
		return builder.build();
	}

	public boolean hasNeverSuccessfullyUpdated() {
		if (logger.isLoggable(Level.FINER)) {
			logger.fine("hasNeverSuccessfullyUpdated() " + (lastSuccess == null));
		}
		return lastSuccess == null;
	}

	/**
	 * Compare the current time with the time the last successful update completed.
	 * An update is needed if the last successful update was more than an hour ago.
	 */
	public boolean isUpdateNeeded() {	
		boolean isBuildUpdateAllowed = true;

		if (lastSuccess != null) {
			long currentTime = new Date().getTime();
			long lastUpdateTime = lastSuccess.getTime();
			if ((currentTime - lastUpdateTime) < ONE_HOUR_MILLIS) {
				isBuildUpdateAllowed = false;
			}
		}
		if (logger.isLoggable(Level.FINER)) {
			logger.fine("isUpdateNeeded() " + isBuildUpdateAllowed);
		}

		return isBuildUpdateAllowed;
	}
}
