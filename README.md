# toyrobot
ToyRobot is a controller application for handling a robot on a 5*5 sized table.

To implement the operations to control the robot the command design pattern is used and they can be found in the 
**org.zonedigital.vikhor.toyrobot.commands** package.    
A factory class (**CommandFactory**) can be also found in the package which is responsible for creating the command classes 
from text instructions arriving in text files (see **journey.txt**). If the text instruction cannot be interpreted 
then a **DummyCommand** will be created.

The robot can be managed by sending list of commands to **ToyRobotSimulation** class which is the main interface of 
the toy robot.  
The **PuppetMaster** class is a bridge between the **ToyRobotSimulation** and the text file that contains the text
instructions.

## Testing
The unit tests are in the **src/test** folder, the integration test are in the **src/int-test** folder.
Both the unit tests and integrations tests can be executed with 

    gradlew check

Only the integration tests can be run with 

    gradlew integrationTest

## Building
The built JAR will be placed to **build\libs\**.
The full build (which will execute the tests too) can be started with

    gradlew build

## Application run
The application can be started with
* gradle
* jar

### Gradle

    gradlew bootRun

By default the embedded **journey.txt** will be used where the text instructions will be read from.
However an external file can be used too

    gradlew bootRun --args='d:/downloads/j.txt'

### JAR

    java -jar toyrobot-0.0.1-SNAPSHOT.jar

With external file:

    java -jar toyrobot-0.0.1-SNAPSHOT.jar d:/downloads/j.txt

## What is missing
* I wrote unit tests only for the important classes
* I didn't test any possible scenario
* I wrote integration tests for only 2 main possible cases