/*
 * Copyright (C) 2011-2014 Volker Bergmann (volker.bergmann@bergmann-it.de).
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.databene.script.expression;

import static org.junit.Assert.*;

import org.databene.script.DefaultScriptContext;
import org.databene.script.QNExpression;
import org.databene.script.ScriptContext;
import org.databene.script.ScriptTestUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the {@link QNExpression}.<br/><br/>
 * Created: 18.05.2011 16:17:22
 * @since 0.6.6
 * @author Volker Bergmann
 */
public class QNExpressionTest {
	
	ScriptContext context;
	
	@Before
	public void stUpContext() {
		context = new DefaultScriptContext();
	}

	@Test
	public void testClass() {
		check(ScriptTestUtil.class, "org", "databene", "script", "ScriptTestUtil");
	}

	@Test
	public void testImportedClass() {
		context.importClass("org.databene.script.*");
		check(ScriptTestUtil.class, context, "ScriptTestUtil");
	}

	@Test
	public void testStaticField() {
		check("pubVarContent", "org", "databene", "script", "ScriptTestUtil", "pubvar");
	}

	@Test
	public void testStaticFieldOfImportedClass() {
		ScriptContext context = new DefaultScriptContext();
		context.importClass("org.databene.script.*");
		check("pubVarContent", context, "ScriptTestUtil", "pubvar");
	}
	
	// helpers ---------------------------------------------------------------------------------------------------------

	private void check(Object expected, String... parts) {
		check(expected, context, parts);
	}
	
	private static void check(Object expected, ScriptContext context, String... parts) {
		assertEquals(expected, new QNExpression(parts).evaluate(context));
	}
	
}
