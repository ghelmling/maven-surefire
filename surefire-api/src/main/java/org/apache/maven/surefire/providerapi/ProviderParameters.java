package org.apache.maven.surefire.providerapi;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.Properties;
import org.apache.maven.surefire.report.ConsoleLogger;
import org.apache.maven.surefire.report.ReporterConfiguration;
import org.apache.maven.surefire.report.ReporterFactory;
import org.apache.maven.surefire.testset.DirectoryScannerParameters;
import org.apache.maven.surefire.testset.TestArtifactInfo;
import org.apache.maven.surefire.testset.TestRequest;
import org.apache.maven.surefire.util.DirectoryScanner;

/**
 * Injected into the providers upon provider construction. Allows the provider to request services and data it needs.
 * <p/>
 * NOTE: This class is part of the proposed public api for surefire providers from 2.7 and up. It may
 * still be subject to changes, even for minor revisions.
 * <p/>
 * The api covers this interface and all the types reachable from it. And nothing else.
 *
 * @author Kristian Rosenvold
 */
public interface ProviderParameters
{
    /**
     * Provides a directory scanner that enforces the includes/excludes parameters that were passed to surefire.
     * See #getDirectoryScannerParameters for details
     *
     * @return The directory scanner
     */
    DirectoryScanner getDirectoryScanner();

    /**
     * Provides features for creating reporting objects
     *
     * @return A ReporterFactory that allows the creation of one or more ReporterManagers
     */
    ReporterFactory getReporterFactory();

    /**
     * Gets a logger intended for console output.
     * <p/>
     * This output is inteded for provider-oriented messages that are not attached to a single test-set
     * and will normally be written to something console-like immediately.
     *
     * @return A console logger
     */
    ConsoleLogger getConsoleLogger();

    /**
     * The raw parameters used in creating the directory scanner
     *
     * @return The parameters
     */
    DirectoryScannerParameters getDirectoryScannerParameters();

    /**
     * The raw parameters used in creating the ReporterManagerFactory
     *
     * @return The reporter configuration
     */
    ReporterConfiguration getReporterConfiguration();

    /**
     * Contains information about requested test suites or individual tests from the command line.
     *
     * @return The testRequest
     */

    TestRequest getTestRequest();

    /**
     * The class loader for the tests
     *
     * @return the classloader
     */
    ClassLoader getTestClassLoader();

    /**
     * The per-provider specific properties that may come all the way from the plugin's properties setting.
     *
     * @return the provider specific properties
     */
    Properties getProviderProperties();

    /**
     * Artifact info about the artifact used to autodetect provider
     *
     * @return The artifactinfo, or null if autodetect was not used.
     */
    TestArtifactInfo getTestArtifactInfo();

}
