camel-standalone-boot
=======================

[![Build Status](https://travis-ci.org/oneHandedDev/camel-standalone-boot.svg?branch=master)](https://travis-ci.org/oneHandedDev/camel-standalone-boot) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/2d44181afdc74290bfda6d5f32bfa7b5)](https://www.codacy.com/app/stefan-haupt/camel-standalone-boot?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=oneHandedDev/camel-standalone-boot&amp;utm_campaign=Badge_Grade)

JAVA 8 REQUIRED

**Run as Dev:**

Builds and starts with Spring Boot Maven Plugin:
`mvn spring-boot:run`

=======================

**Packaging and Installation:**

Create package with Maven:
`mvn package`

Manage Camel Routes from config file named 'routes.xml'
Create your own or advance from the predefined `./routes.xml`
The 'routes.xml'-file should be located in your working directory

For information on Camel's Spring-XML-DSL see also [Apache Camel](http://camel.apache.org/) for configuration-details

afterwards run as java app:
`java -jar camel-standalone-boot-[version]-full.jar`

or run via included bash-script

=======================

**TODOs:**
- [x] Expose simple JMX interface
- [x] Shell-Skript for starting the application
- [ ] Gracefully reload config (for configuration without downtime)
- [x] Log4j fileappender
- [x] Error Processor
- [x] Logging Processor
- [ ] Gradle Support
- [ ] Camel Watch?
