/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.dmn.feel.impl.scala;

import org.camunda.bpm.engine.variable.context.VariableContext;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.camunda.feel.context.JavaVariableProvider;

import java.util.Optional;

public class ContextVariableWrapper extends JavaVariableProvider {

  protected VariableContext context;

  public ContextVariableWrapper(VariableContext context) {
    this.context = context;
  }

  @Override
  public Optional<Object> getVariableAsOptional(String name) {
    if (context.containsVariable(name)) {
      TypedValue typedValue = context.resolve(name);
      Object value = typedValue.getValue();
      return Optional.ofNullable(value);

    } else {
      return Optional.empty();

    }
  }

  @Override
  public Iterable<String> keysAsIterator() {
    return context.keySet();
  }

}
