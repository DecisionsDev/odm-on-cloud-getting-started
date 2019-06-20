# Tutorial scenario and prerequisites

This tutorial shows you how to update, test, and deploy a decision service in the cloud portal. Review the following information before starting.

## Scenario

In this tutorial, you work on the Miniloan Service decision service, which is used by a fictitious loan company to determine whether borrowers are eligible for loans. When the company updates its loan policies, you reflect the changes in the business rules in the decision service.

The rules in the decision service are kept in two folders:

-   Eligibility: Contains the rules that determine whether a loan can be approved.
-   Validation: Contains the rules that make preliminary checks to determine whether data is rejected immediately.

You update the decision service in the cloud portal, which has three different work environments. You modify and test the decision service in the development environment, and deploy the rules from the decision service to execution servers in the development, test, and production environments. Finally, you make the rules available to a sample client application, which uses the rules to process data.

## Prerequisites

You need the following items to do this tutorial:

-   Access to an instance of the Operational Decision Manager on Cloud portal
-   Access to ODMDEV on GitHub: [https://github.com/ODMDev](https://github.com/ODMDev)

## More information

-   Technical documentation on the product: [IBM Operational Decision Manager on Cloud Knowledge Center](https://www.ibm.com/support/knowledgecenter/SS7J8H/welcome/kc_welcome_cloud.html)
-   Tutorials on the functionality of the product: [Tutorials](https://www.ibm.com/support/knowledgecenter/SS7J8H/com.ibm.odm.cloud.tutorial/topics/con_tutorials_intro.html)

[**Next** ![""](../tut_cloud_getstart_ghimages/next.jpg)](../tut_cloud_getstart_ghtopics/tut_cloud_getstart_prep_lsn.md)

[![""](../tut_cloud_getstart_ghimages/home.jpg) **Back to table of contents**](../README.md)

© Copyright IBM Corporation 2019

