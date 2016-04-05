## Description

Milliways is the test service for Marvin Bot. It facilitates our integration
testing: Slack <-> Marvin <-> Milliways.

## Setup

To run the integration test, you will need to do the following:

- Ensure you have configured the endpoints correctly in `application.properties`.
- Build the latest copy of Marvin and copy the jar file to Milliways' `lib` folder.
- Run the test by typing: `./gradlew test`

For more information, you can refer to the [Wiki page]
(https://github.com/neo/milliways/wiki/Blackbox-testing-between-two-servers).

## TODO

- Research how we can automate the process of transfering the jar from Marvin
- Configure CI to run the integration test