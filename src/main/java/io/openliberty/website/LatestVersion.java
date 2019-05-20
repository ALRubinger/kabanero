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

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.openliberty.website.data.LatestReleases;

@WebServlet("/latestVersion.js")
public class LatestVersion extends HttpServlet {

  @Inject
  private BuildsManager manager;

  private String response;
  private String version = "0.0.0.0";
  private String template = "var latestReleasedVersion = {\r\n" +
                            "    version: '0.0.0.0',\r\n" +
                            "    productName : 'Open Liberty',\r\n" +
                            "    availableFrom : 'https://openliberty.io/downloads?welcome'\r\n" +
                            "};";

  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("application/javascript");
    resp.getWriter().println(response);
  }

  public void init() {
    LatestReleases releases = manager.getLatestReleases();
    String v = releases.getRuntimeRelease().getVersion();
    if (!v.equals(version)) {
      response = template.replaceAll("0\\.0\\.0\\.0", v);
      version = v;
    }
  }
}
