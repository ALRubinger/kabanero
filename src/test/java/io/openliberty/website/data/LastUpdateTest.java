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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import io.openliberty.website.Constants;

public class LastUpdateTest {

	@Test
	public void ctor() {
		LastUpdate lastUpdate = new LastUpdate();

		assertEquals(Constants.NEVER_ATTEMPTED, lastUpdate.getLastUpdateAttempt());
		assertEquals(Constants.NEVER_UPDATED, lastUpdate.getLastSuccessfulUpdate());
		assertTrue(lastUpdate.hasNeverSuccessfullyUpdated());
		assertEquals("{\"last_update_attempt\":\"never_attempted\",\"last_successful_update\":\"never_updated\"}",
				lastUpdate.asJsonObject().toString());
	}

	@Test
	public void setLastUpdateAttempt() {
		LastUpdate lastUpdate = new LastUpdate();
		lastUpdate.setLastUpdateAttempt(new Date(1000));

		assertEquals("Thu Jan 01 00:00:01 UTC 1970", lastUpdate.getLastUpdateAttempt());
		assertEquals(Constants.NEVER_UPDATED, lastUpdate.getLastSuccessfulUpdate());
		assertTrue(lastUpdate.hasNeverSuccessfullyUpdated());
		assertEquals("{\"last_update_attempt\":\"Thu Jan 01 00:00:01 UTC 1970\",\"last_successful_update\":\"never_updated\"}",
				lastUpdate.asJsonObject().toString());
	}

	@Test
	public void markUpdateAttempt() {
		LastUpdate lastUpdate = new LastUpdate();
		lastUpdate.markUpdateAttempt();

		@SuppressWarnings("deprecation")
		Date updated = new Date(lastUpdate.getLastUpdateAttempt());

		assertTrue(updated.getTime() <= new Date().getTime());
	}

	@Test
	public void markSuccessfulUpdate() {
		LastUpdate lastUpdate = new LastUpdate();
		lastUpdate.setLastUpdateAttempt(new Date(2000));
		lastUpdate.markSuccessfulUpdate();

		assertEquals("Thu Jan 01 00:00:02 UTC 1970", lastUpdate.getLastUpdateAttempt());
		assertEquals("Thu Jan 01 00:00:02 UTC 1970", lastUpdate.getLastSuccessfulUpdate());
		assertFalse(lastUpdate.hasNeverSuccessfullyUpdated());
		assertEquals("{\"last_update_attempt\":\"Thu Jan 01 00:00:02 UTC 1970\",\"last_successful_update\":\"Thu Jan 01 00:00:02 UTC 1970\"}",
				lastUpdate.asJsonObject().toString());
	}

	@Test
	public void isUpdateNeeded_never_attempted() {
		LastUpdate lastUpdate = new LastUpdate();
		assertTrue(lastUpdate.isUpdateNeeded());
	}

	@Test
	public void isUpdateNeeded_never_updated() {
		LastUpdate lastUpdate = new LastUpdate();
		lastUpdate.markUpdateAttempt();
		assertTrue(lastUpdate.isUpdateNeeded());
	}

	@Test
	public void isUpdateNeeded_successfully_updated() {
		LastUpdate lastUpdate = new LastUpdate();
		lastUpdate.markUpdateAttempt();
		lastUpdate.markSuccessfulUpdate();
		assertFalse(lastUpdate.isUpdateNeeded());
	}

	@Test
	public void isUpdateNeeded_updated_over_an_hour_ago() {
		LastUpdate lastUpdate = new LastUpdate();
		lastUpdate.setLastUpdateAttempt(new Date(2000));
		lastUpdate.markSuccessfulUpdate();
		assertTrue(lastUpdate.isUpdateNeeded());
	}

}
