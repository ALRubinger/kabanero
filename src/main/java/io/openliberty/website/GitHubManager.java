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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class GitHubManager {

    private Client client = null;

    public GitHubManager() {
        client = ClientBuilder.newClient();
    }

    public String getIssues() {
        WebTarget target = client.target(Constants.GITHUB_ISSUES_URL);
        Builder builder = target.request("application/json");
        builder.header("Authorization", "Basic " + System.getenv(Constants.PAT_ENV_VARIABLE_NAME));
        Response response = builder.get();
        return response.readEntity(String.class);
    }

}
