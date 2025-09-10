<img src="https://github.com/lhartman2/INFO1541-Assignment1/blob/master/assets/images/AssignmentHeader.PNG" alt="">
<h2><img src="https://github.com/lhartman2/INFO1541-Assignment1/blob/master/assets/images/Assignment.png" width="72" height="72" alt="">Assignment</h2>
<p>For this assignment we are going to practice using Git for version control by forking a repository and making a branch to allow us to write some tests and custom annotations for our code. The provided project code that you get from GitHub is a simple console based program that involves objects to interact with a simple SQLite database.</p>
<p>As you are writing your tests, fixing the code and making our own annotations you should be doing different commits in your repository. It is part of the grade to see several different commits for each part of this assignment.</p>

<h2><img src="https://github.com/lhartman2/INFO1541-Assignment1/blob/master/assets/images/Directions.png" width="72" height="72" alt="">Directions</h2>
<h3>To Get Started:</h3>
<ol style="list-style-type: decimal;">
    <li>Make sure that you have Git and optionally GitHub Desktop installed on your computer
        <ul style="list-style-type: disc;">
            <li><a href="https://git-scm.com/downloads" target="_blank" rel="noopener">Git Download</a></li>
            <li><a href="https://desktop.github.com/download/" target="_blank" rel="noopener">GitHub Desktop App</a></li>
        </ul>
    </li>
    <li>Create your own GitHub account
        <ul style="list-style-type: disc;">
            <li>You may use either your MCC email or a personal one.</li>
        </ul>
    </li>
    <li>Sign in to IntelliJ's Git and GitHub Desktop as needed
    </li>
</ol>
<h3>Part 1 - Setting up a fork and branch</h3>
<p>First up you need to get a copy of the project file from GitHub and fork it:&nbsp; [GitHub Repo Link]</p>
<ul>
    <li>Your repository should be private. Once you create it you should also invite your instructor to the repository so that they can grade it. You will need to use your instructor's GitHub account listed in Module 1 on Canvas. See submission instructions for more information below.</li>
</ul>
<p>Once you do that you can then download the project and open it up in IntelliJ</p>
<ul>
    <li>You can then log in to GitHub Desktop App and tell it to clone a local copy, then select to open it up in IntelliJ.
        <ul>
            <li><img src="https://github.com/lhartman2/INFO1541-Assignment1/blob/master/assets/images/OpenWithGitHubDesktop.png" width="288" height="264" alt=""> </li>
        </ul>
    </li>
</ul>
<p>Now that the project is open in IntelliJ we want to alter the code. While the altering of our code isn't too major we still want to leave the main branch alone and create our own.&nbsp; Make a NEW branch in this project called "A1-Testing"</p>
<p>Once the new branch is created, make sure that you are working in that branch (either automatically done or if you need to "check out" the branch.</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<h3>Part 2 - Unit Testing our code</h3>
<p>We now can write some JUnit tests to make sure the code in our three employee objects are coded correctly. The project is using the maven build system and you should make sure that the JUnit dependencies are included in the pom.xml file. You can do this manually or let IntelliJ create the test file for you.</p>
<p><span style="background-color: #fbeeb8;">Make sure that each method you create has a super specific name to what you are testing.</span></p>
<p>&nbsp;</p>
<h4>Hourly Employee</h4>
<p>Create a new java file inside the <strong>test-&gt;java</strong> folder called <strong>HourlyEmployeeTest.java</strong></p>
<p>Use the following code to set up a fake employee to then test with:</p>
<pre><span style="font-size: 12pt;">HourlyEmployee emp = new HourlyEmployee("Tony", "Stark", 5749, "Service", "Lead Service Manager", 32.85);</span></pre>
<p>&nbsp;</p>
<p>Create methods to test the following:</p>
<ul>
    <li><strong>increaseHours(double h)</strong>
        <ul>
            <li>This method makes sure the value sent ('h') is a positive number. We don't want to add negative numbers. Do several method calls adding hours both positive and negative then use the getter for testing the hoursWorked value.</li>
        </ul>
    </li>
    <li><strong>annualRaise()</strong>
        <ul>
            <li>This method increases the wage of the employee by 5%. It does this by taking wage * .05, rounding to two decimals and adding to the wage of the employee. Check to make sure that it gives the correct raise. The employee should be paid <strong>34.49</strong> after the annual raise method is called.</li>
        </ul>
    </li>
    <li><strong>calculateWeeklyPay()</strong>
        <ul>
            <li>This method calculates the weekly pay for the employee. BUT if the hours they have worked is over 40 then they get overtime.</li>
            <li>Write a method that sends 35 hours to the test employee above. Then when you call the calculateWeeklyPay it should return: <strong>1149.75</strong></li>
            <li>Write a method that sends 45 hours to the test employee above. Then when you call the calculateWeeklyPay it shoudl return: <strong>1560.38</strong>
                <ul>
                    <li>If a number is off, check the caluclation. It should take 40 * pay + (pay *1.5 * (hours-40)). Fix if needed and commit changes.</li>
                </ul>
            </li>
        </ul>
    </li>
</ul>
<h4>Salary Employee</h4>
<p>Create a new java file inside the <strong>test-&gt;java</strong> folder called <strong>SalaryEmployeeTest.java</strong></p>
<p>Use the following code to set up a fake employee to then test with:</p>
<pre><span style="font-size: 12pt;">SalaryEmployee emp = new SalaryEmployee("Steve", "Rodgers", 3781, "Sales", "Manager", 64325);</span></pre>
<p>&nbsp;</p>
<p>Create methods to test the following:</p>
<ul>
    <li><strong>calculateWeekleyPay()</strong>
        <ul>
            <li>This method calculates the weekly pay of a salary employee by taking their salary dividing it by 52. Create a testing method that calls this and makes sure that the amount the Steve get's paid should be: 1237.02 - This is a rounded amount that should be exact, if not fix the code as part of your commits. Use Math.round() to help.</li>
        </ul>
    </li>
    <li><strong>holidayBonus()</strong>
        <ul>
            <li>This method calculates the holiday bonus by giving the employee 3.365% of their salary. Create a test method that calls this and makes sure that the amount for the bonus is: 2164.54 exactly. This is a rounded amount that should be exact, if not fix the code as part of your commits. Use Math.round() to help.</li>
        </ul>
    </li>
</ul>
<p>&nbsp;</p>
<h4>Commission Employee</h4>
<p>Create a new java file inside the <strong>test-&gt;java</strong> folder called <strong>CommissionEmployeeTest.java</strong></p>
<p>Use the following code to set up a fake employee to then test with:</p>
<pre><span style="font-size: 12pt;">CommissionEmployee emp = new CommissionEmployee("Clint", "Barton", 6847, "Sales", "Customer Representative", .0265);</span></pre>
<p>&nbsp;</p>
<p>Create methods to test the following:</p>
<ul>
    <li><strong>increaseSales(double s)</strong>
        <ul>
            <li>This method makes sure that the value sent ('s') is a positive number before adding to the employee's sales. Write a test method that adds positive and negative amounts to an employee. Then see what the sales values are. It should be what positive values were sent.
                <ul>
                    <li>If the test fails then take a look at this method in the CommissionEmployee.java and add some conditions to make sure 's' is positive before adding to the sales field. Commit any fixes.</li>
                </ul>
            </li>
        </ul>
    </li>
    <li><strong>holidayBonus()</strong>
        <ul>
            <li>This method for commission employees should return 0. These employees don't get a holiday bonus, make sure that value is correct.</li>
        </ul>
    </li>
    <li><strong>annualRaise()</strong>
        <ul>
            <li>This method adds .002 to the rate field of the employee. Call this method <strong>twice</strong> and then use the getter method to check that the rate for the test employee is: .0305</li>
        </ul>
    </li>
</ul>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>When you are done writing your tests and fixing some code make sure to commit the code in Git. You should have several commits, say one for each employee test you are doing. You should also have different commit text for your test files vs. editing the object code that I provide to you.</p>
<p>&nbsp;</p>
<h3>Part 3 - Writing Annotations</h3>
<p>Let's create some annotations to better mark our code for different files that we have. You can write these annotations in the same folder as the employee object's themselves. We won't worry about more organization than is already there.</p>
<p>&nbsp;</p>
<h4>Class Annotation</h4>
<p>First create a new annotation called EmployeeObjects.EmployeeType. This will have a String variable called "type". You can have the retention policy to be runtime.</p>
<p>Then annotate the three employee classes and send the value "Hourly" "Salary" or "Commission" to their respectable files.</p>
<p>&nbsp;</p>
<h4>Method Annotation</h4>
<p>Next create a method level annotation called EmployeeObjects.WeeklyPayCalculator. This won't have any variables. You can have the retention policy to be runtime.</p>
<p>Then annotate the three employee classes method that is calculateWeeklyPay().&nbsp;</p>
<p>&nbsp;</p>
<h4>Field Annotation</h4>
<p>Lastly, create a field level annotation called EmployeeObjects.PayRate. This will also have a String variable called "type". You can have the retention policy to be runtime as well.</p>
<p>Then annotate the pay variable on each Hourly, Salary and Commission employee. You will need to send the "Hourly", "Salary", or "Commission" in the annotation as well.&nbsp;</p>
<p>Note that the pay variable is named differently on each employee class but should be self explanatory which one it is.</p>
<p>&nbsp;</p>
<h4>Processing Annotations with Reflection</h4>
<p>Now that we our annotations written let's write a "test" file to make sure that these annotations exist.</p>
<p>First create a new file for "TestingAnnoations" that you will write a main method in that we can run. You can put this folder with the employee objects. Some more work would have to be done for having them in the Unit test folder.</p>
<p>Next copy the code above from Part 2 that creates an hourly, salary and commission employee.</p>
<p>Then using if statements check that each class is annotated with the Employee type. Simply have a variable that counts how many employee objects. If the annotation is present then ++ to the tracking variable. Print out a message like "You have # employee types"&nbsp; which should be 3.</p>
<p>Lastly loop through each employee of hourly, salary and commission and check both the methods and fields. Once you find the field that is marked with EmployeeObjects.PayRate print the value out in a format like :&nbsp; "Employee pay rate: $####".&nbsp; Then loop through the methods of the same class and find the method marked with EmployeeObjects.WeeklyPayCalculator, invoke the method and print out the results that the method returns.</p>
<p>When you are done writing your annotations and adding them to our code files you should make sure to "commit" those changes.</p>
<p></p>
<h2><img src="https://github.com/lhartman2/INFO1541-Assignment1/blob/master/assets/images/ckeck.png" width="72" height="72" alt="">Submission</h2>
<p>Throughout the different parts of the assignment above you should have made several varying commits in your code. Some for testing each employee, fixing the code in your employees, and writing the annotations/adding them in. This should not be seen as one big commit. This is part of the rubric with seeing a commit for each along with the actual fix of the code.</p>
<p>Once you are complete you need to push your updates up to your GitHub repository. You should have your final code for turn in on the repository.</p>
<p>Your repository on GitHub should be private and you will need to invite your instructor to see the repo. Please see the information text on Module 1 for your instructor's GitHub username. While yes your instructor is able to view your repository after accepting and invite you still need to submit the URL of it here to this assignment.</p>
<ul style="list-style-type: disc;">
    <li>Please note that sometimes your instructor may not accept your invite to the repository in time and the invitation can expire. If this is done your instructor may reach out to send it again. Please do so ASAP.</li>
</ul>
