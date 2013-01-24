// Copyright 2007-2010 Google, Inc.

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at

//     http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.acre.util.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletContext;

public class ServletResourceSource implements ResourceSource {

    private ServletContext _servletContext;

    public ServletResourceSource(ServletContext servletContext) {
        _servletContext = servletContext;
    }

    public InputStream getResourceAsStream(String path) throws IOException {
        return _servletContext.getResourceAsStream(path);
    }

    public long getLastModifiedTime(String path) throws IOException {
        URL resource = _servletContext.getResource(path);
        if (resource != null) {
            return resource.openConnection().getLastModified();
        } else {
            throw new RuntimeException("Resource " + path + " could not be found");
        }
    }

}
