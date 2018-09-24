
Implemented Test Details

## Tools/Lib Details
#### 
Maven,TestNg, gson, apache.commons-lang(for StringUtil and RandomUtil)
slf4j-log4j12(logging)


## run
#### 
navigate to project directory ./qa-test-hf-seleniumtest

java1.8 is used for compilation

run with mvn commands
mvn clean install
mvn test
after run verify emailable-report.html  target/surefire-reports to check which tests exec



## Package Info
#### com.hellofresh.selenium.base
contains base classes from which test classes would inherit
for all test classes in package(com.hellofresh.selenium.tests) base class is BaseSeleniumSetup.java
for all pages class in package (com.hellofresh.selenium.pages) base class is BaseSeleniumWebPageAction.java
BaseSeleniumSetup: purpose is to Initialize driver, navigation to pages methods, failedtestcase method,close driver

BaseSeleniumWebPageAction: It has all selenium webpage action which is useful in pages class to interact with selenium elements.


####com.hellofresh.selenium.driverbase 
contains all driver manager code and called in BaseSeleniumSetup.java setup().
It initializes driver based on browser and underlying os.

#### com.hellofresh.selenium.tests
contains test classes. Each test class represents single test.
Each test class has corresponding page in pages package where all interaction logic is kept.
for ex: LogInTest uses com.hellofresh.selenium.pages.LoginPageWidget
each test is supplied with testdatafile for ex: src/test/resources/configurableData.json in setup to read testdata input or expected


#### com.hellofresh.selenium.pages
Depending upon the web pages interaction here page classes are created
each page class represents a web page or a widget on web page
It has locators to find element and extend to BaseWebPageAction.java to interact with elements
It has assertions logic.

####com.hellofresh.data.common: 
This package has RandomDataGenerator to generate random registration data.
TestDataGson.java to process configurableData.json so that data is read from file
common.java has readTestData  that could be used beyond selenium tests also

BaseSeleniumSetup.java->DriverManagerFactory->common


####com.hellofresh.testng.listners: 

A custom listener is created to execute takescreenshot on failed test cases.




