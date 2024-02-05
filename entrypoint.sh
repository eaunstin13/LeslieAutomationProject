#!/bin/bash
echo 'inside the entrypoint'
mvn clean test
if [[ "$?" -ne 0 ]] ; then
echo 'could not perform tests'; exit 1
fi
exec "$@"
