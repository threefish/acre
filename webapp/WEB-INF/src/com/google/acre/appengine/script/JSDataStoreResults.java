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

package com.google.acre.appengine.script;

import java.util.Iterator;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import com.google.acre.javascript.JSObject;
import com.google.acre.script.exceptions.JSConvertableException;
import com.google.appengine.api.datastore.PreparedQuery;

public class JSDataStoreResults extends JSObject {
        
    private static final long serialVersionUID = 529101157247378231L;

    public static Scriptable jsConstructor(Context cx, Object[] args, Function ctorObj, boolean inNewExpr) {
        Scriptable scope = ScriptableObject.getTopLevelScope(ctorObj);
        return new JSDataStoreResults(((JSDataStoreResults) args[0]).getWrapped(), scope);
    }
    
    public JSDataStoreResults() { }

    private Object _result;
    
    public JSDataStoreResults(Object result, Scriptable scope) {
        _result = result;
        _scope = scope;
    }
    
    public Object getWrapped() {
        return _result;
    }
    
    public String getClassName() {
        return "DataStoreResults";
    }
        
    // -------------------------------------------------------------
    
    public Object jsFunction_as_iterator() {
        try {
            @SuppressWarnings("rawtypes")
            Iterator iterator = ((PreparedQuery) _result).asIterator();
            JSDataStoreResultsIterator resultIterator = new JSDataStoreResultsIterator(iterator,_scope);
            return resultIterator.makeJSInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new JSConvertableException("" + e.getMessage()).newJSException(_scope);
        }
    }
        
}
