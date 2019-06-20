# Getting started in IBM® Operational Decision Manager on Cloud

This tutorial shows you how to work on a decision service in the Operational Decision Manager on Cloud portal.

Operational Decision Manager on Cloud is a complete system for creating and using rule applications. It enables you to express your organization's decisions and policies in business rules, and to make the rules available to your production applications. Users can work together in the portal to develop and maintain the rules in decision services.

In this tutorial, you work in the cloud portal. You update the rules in a decision service, and then test the decision service. After the decision service passes the test, you deploy the rules from the decision service to different execution servers. Finally, you look at a sample application that uses the rules that were deployed from the decision service.

**Important:** 

-   You can do this tutorial in Operational Decision Manager on Cloud Express. However, only the parts of the tutorial on the development environment apply to Express. The parts that refer to the test and production environments only work in the full version of Operational Decision Manager on Cloud.
-   The miniloan.zip file in this GitHub repository can be used in place of the compressed file that is imported in step 4 of the preparation task in the same tutorial in the IBM Knowledge Center: [Task 1: Preparing to do the tutorial](https://www.ibm.com/support/knowledgecenter/SS7J8H/com.ibm.odm.cloud.tutorials/tut_cloud_getstart_kctopics/tut_cloud_getstart_prep_lsn.html)
-   You do not create a decision service in this tutorial. To learn how to create a decision service, see [Creating a decision service in Rule Designer.](https://www.ibm.com/support/knowledgecenter/SS7J8H/com.ibm.odm.cloud.tutorials/tut_cloud_ds_topics/odm_cloud_dservice_tut.html)
-   This tutorial does not cover the decision governance framework, the system for coordinating work on projects in Decision Center. For more information about the framework, see [Governance principles](https://www.ibm.com/support/knowledgecenter/SS7J8H/com.ibm.odm.dcenter.bu.bconsole/mng_changes/con_cmg_governance.html) and the video [Using the Decision Governance Framework in the Decision Center Business Console](https://https://www.youtube.com/embed/h2q3Age5-o4).

## Audience

The tutorial is for users who want to work on decision services in the cloud portal of Operational Decision Manager on Cloud.

## Time required

1 hour

## Learning objectives

You learn how to do the following tasks:

-   Import a decision service
-   Create a branch in a decision service
-   Edit a decision table and create an action rule
-   Test a decision service
-   Deploy a RuleApp from a decision service
-   Explore the contents of a RuleApp in an execution server console
-   Call a ruleset from a client application by using REST

## Table of contents

-   [Tutorial scenario and prerequisites](tut_cloud_getstart_ghtopics/tut_cloud_getstart_intro.md)
-   [Task 1: Preparing to do the tutorial](tut_cloud_getstart_ghtopics/tut_cloud_getstart_prep_lsn.md)
-   [Task 2: Touring the decision service](tut_cloud_getstart_ghtopics/tut_cloud_getstart_start_lsn.md)
-   [Task 3: Creating and editing rules](tut_cloud_getstart_ghtopics/tut_cloud_getstart_rules_lsn.md)
-   [Task 4: Testing and deploying the decision service](tut_cloud_getstart_ghtopics/tut_cloud_getstart_test_lsn.md)
-   [Task 5: Testing in the execution server](tut_cloud_getstart_ghtopics/tut_cloud_getstart_res_lsn.md)
-   [Task 6: Calling the rules from a client application](tut_cloud_getstart_ghtopics/tut_cloud_getstart_app_lsn.md)

## Licensing information

Copyright IBM Corp. 1987, 2019. Licensed to the Apache Software Foundation \(ASF\) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 \(the "License"\); you may not use this file except in compliance with the License. You may obtain a copy of the License at [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0). Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

[**Next** ![""](tut_cloud_getstart_ghimages/next.jpg)](tut_cloud_getstart_ghtopics/tut_cloud_getstart_intro.md)
