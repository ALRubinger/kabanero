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

public interface Constants {

    // URL
    String DHE_URL = "https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/";
    String DHE_RUNTIME_PATH_SEGMENT = "runtime/";
    String DHE_TOOLS_PATH_SEGMENT = "tools/";
    String DHE_RELEASE_PATH_SEGMENT = "release/";
    String DHE_NIGHTLY_PATH_SEGMENT = "nightly/";
    String DHE_INFO_JSON_FILE_NAME = "info.json";

    // JSON
    String LAST_UPDATE_ATTEMPT = "last_update_attempt";
    String LAST_SUCCESSFULL_UPDATE = "last_successful_update";
    String NEVER_ATTEMPTED = "never_attempted";
    String NEVER_UPDATED = "never_updated";
    String TESTS_PASSED = "test_passed";
    String TOTAL_TESTS = "total_tests";
    String BUILD_LOG = "build_log";
    String TESTS_LOG = "tests_log";
    String DRIVER_LOCATION = "driver_location";
    String PACKAGE_LOCATIONS = "package_locations";
    String RUNTIME_RELEASES = "runtime_releases";
    String RUNTIME_NIGHTLY_BUILDS = "runtime_nightly_builds";
    String TOOLS_RELEASES = "tools_releases";
    String TOOLS_NIGHTLY_BUILDS = "tools_nightly_builds";
    String DATE = "date_time";
    String VERSION = "version";
    String VERSIONS = "versions";
    String SIZE_IN_BYTES = "size_in_bytes";
    String RUNTIME = "runtime";
    String TOOLS = "tools";
    String BUILDS = "builds";
    String LATEST_RELEASES = "latest_releases";

    // HTTP
    String CONTENT_LENGTH = "Content-Length";
    String API_SERVLET_PATH = "/api";

    // GITHUB
    String GITHUB_ISSUES_URL = "https://api.github.com/repos/OpenLiberty/open-liberty/issues?sort=update";
    String PAT_ENV_VARIABLE_NAME = "PAT";
    
    // BLUEMIX
    String OPEN_LIBERTY_GREEN_APP_HOST = "openliberty-green.mybluemix.net";
    String OPEN_LIBERTY_BLUE_APP_HOST = "openliberty-blue.mybluemix.net";

}
