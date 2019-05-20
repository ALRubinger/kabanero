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

var latest_release_url = '/api/builds/latest';

$(document).ready(function() {

    $.ajax({
        url: latest_release_url
    }).done(function(data) {

        $('#download_link').attr('href', data.runtime.driver_location);
        $('#download_link_size_label').text('(' + Math.floor(data.runtime.size_in_bytes / 1048576) + ' MB)');

    });
});
