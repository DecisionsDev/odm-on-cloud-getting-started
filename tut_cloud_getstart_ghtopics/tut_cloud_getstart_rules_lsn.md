# Task 3: Creating and editing rules

You update a decision table, and create an action rule to implement policy changes.

The loan company wants the following changes to be implemented in the decision service:

-   Increase the minimum credit score from 200 to 300 for all new loan requests.
-   Reject loans that are shorter than six months.

To implement the changes, you use the search function to locate the table that applies the minimum credit score. After you change the score, you create an action rule to implement the minimum loan duration.

## Step 1: Searching for rules

To make the first change, you use the search function to find all the rules that change the credit score.

**Procedure**
1.   In your branch, click inside the search field to be able to enter a search string: 

![Image shows the search field.](../tut_cloud_getstart_ghimages/scrn_gs_rules_search.jpg)

2.   Type "score" and press Enter. The results show all the action rules and decision tables in which the word score occurs. You look at the rules, and conclude that you must edit the repayment and score decision table.
3.   Click **repayment and score** in the list of search results. The decision table opens in the preview window. 

## Step 2: Modifying the decision table

You update the decision table. For more information, see [Decision table editor](https://www.ibm.com/support/knowledgecenter/SS7J8H/com.ibm.odm.dcenter.bu.bconsole/dtables/con_bc_dteditor_overview.html).

**Procedure**
1.   Click **Edit**. The decision table editor opens.
2.   Double-click 200 in row 1 of the **credit score** column, and replace it with 300. Do the same in row 2.
3.   Click **Save**. 
4.   Enter the following comment, and then click **Create New Version**. 

    Changed credit score to 300


## Step 3: Creating an action rule

You introduce the second policy change by creating an action rule. For more information, see [Building rules using the Intellirule editor](https://www.ibm.com/support/knowledgecenter/SS7J8H/com.ibm.odm.dcenter.bu.bconsole/shared_intellirule/tpc_intelli_build_rules.html).

1.   Click **My Branch** in the navigation breadcrumbs. 
2.   Click **Decision Artifacts**, and then click **validation** to open the folder. 
3.   Click the **Create** button ![""](../tut_cloud_getstart_ghimages/icon_gs_releases_plus.jpg), and click **New Rule**. 
4.   Enter check duration as the name of the rule, and click **Create**. 
5.   Enter the following rule statement in the rule editor. If your web browser supports it, you can copy the rule to the rule editor. Otherwise, type in the rule or build it with the completion menu.


    if
       the duration of 'the loan' is less than 6
    then
       add "The duration of the loan is too short" to the messages of 'the loan';
       reject 'the loan';
    

The rule checks the duration of a loan. If the duration is shorter than six months, the rule adds a rejection message to the loan and rejects the loan.

6.   Click **Save**. 
7.   Enter the following message, and then click **Create New Version**. 

    Added rule for summer policy


In the next task, you test and deploy the decision service.

[**Next** ![""](../tut_cloud_getstart_ghimages/next.jpg)](../tut_cloud_getstart_ghtopics/tut_cloud_getstart_test_lsn.md)

[![""](../tut_cloud_getstart_ghimages/home.jpg) **Back to table of contents**](../README.md)

© Copyright IBM Corporation 2018

