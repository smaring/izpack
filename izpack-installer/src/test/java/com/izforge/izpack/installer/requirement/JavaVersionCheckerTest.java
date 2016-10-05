/*
 * IzPack - Copyright 2001-2012 Julien Ponge, All Rights Reserved.
 *
 * http://izpack.org/
 * http://izpack.codehaus.org/
 *
 * Copyright 2012 Tim Anderson
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
 */

package com.izforge.izpack.installer.requirement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the {@link JavaVersionChecker} class.
 *
 * @author Tim Anderson
 */
public class JavaVersionCheckerTest extends AbstractRequirementCheckerTest
{
    /**
     * Tests the {@link JavaVersionChecker}.
     */
    @Test
    public void testJavaVersion()
    {
        JavaVersionChecker checker = new JavaVersionChecker(installData, prompt);

        installData.getInfo().setJavaVersion(null);
        assertTrue(checker.check());

        String currentVersion = System.getProperty("java.version");
        installData.getInfo().setJavaVersion("9" + currentVersion);
        assertFalse(checker.check());

        installData.getInfo().setJavaVersion(currentVersion);
        assertTrue(checker.check());
        
        installData.getInfo().setJavaVersion(currentVersion + "9");
        assertTrue(checker.check());
        
        installData.getInfo().setJavaVersionStrict(true);
        assertFalse(checker.check());
    }
}
