# Birt Standalone

A simple web servlet which serves reports powered by [BIRT engine](http://www.eclipse.org/birt/).

## How to

- Clone/Download the project. Build it with Maven and the necessary war file with 
dependencies will be created.

## First steps

1. The default template path is set to /home/birtserver/templates/. There's a simple 
test template included in the project (in WEB-INF), just copy it to that folder.

2. Type *{deployUrl}/service/report?template=test_report&param1=Hello world* in your
browser. You'll see the report displayed.

## Project details

- This project is open source and everyone is invited to colaborate in it. New 
commiters will be welcomed.
