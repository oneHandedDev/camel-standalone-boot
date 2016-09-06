#!/usr/bin/env bash
# Checking for routes.xml
ROUTESFILE=$(find -name 'routes.xml')
if [[ ! -f ${ROUTESFILE} ]]; then
    echo "routes.xml not found"
    exit 2
fi

# looking up jar-file
JARFILE=$(find -name 'camel-standalone-*full.jar')
if [[ -f ${JARFILE} ]]; then
    echo "starting camel-standalone-boot"
    java -jar ${JARFILE} -Dcamel.incoming.port=8580
else
    echo "jar-file not found!"
    exit 1
fi