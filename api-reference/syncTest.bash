#!/bin/bash
PROJECT_ID_HEADER='Project-Id: 167348'
CONTENT_TYPE_HEADER='Content-Type: application/json'
SYNC_SEARCH_API_PROD='https://doc-interface-test.netease.im/apiArticle/syncSearch'

CI_COMMIT_SHA=$(git rev-parse HEAD)

CHANGED_FILES=`git diff-tree --no-commit-id --name-status -r -m $CI_COMMIT_SHA`

HTML_FILES=`echo ${CHANGED_FILES} | grep -Eo "[ADM]\s[a-z0-9-]+\/[0-9a-zA-Z\/]*Latest\/[0-9a-zA-Z\/_.-]+\.html" `

HTML_STRING=`echo ${HTML_FILES} | sed 's/ /--/g' | sed 's/\.html/@@html/g'`

REQUEST_DATA_STRING='{ "changes":"'$HTML_STRING'" }'

echo $REQUEST_DATA_STRING | curl  --request POST --header "Content-Type: application/json" --header "$PROJECT_ID_HEADER" -d @- "$SYNC_SEARCH_API_PROD"
