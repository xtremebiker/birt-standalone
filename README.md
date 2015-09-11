# Birt Standalone for microservice

A simple web servlet which serves reports powered by [BIRT engine](http://www.eclipse.org/birt/).

## How to

- Clone/Download the project. Build it with Maven and the necessary war file with dependencies will be created. You can download the deployable file from releases too.

## What does this project offer comparing with the standard BIRT Standalone server?

The BIRT Standalone Server or Runtime is great just for displaying the reports, but it's not usable in production environments. This project implements a RESTful web service layer, that way you can access your reports with a plain HTTP request and you'll get the report itself. It's the ideal way of [having a centralized report server](http://stackoverflow.com/questions/19051144/taking-advantage-of-birt-standalone-runtime). 

Forget about including the BIRT runtime (~50MB with dependencies) in each of your deploys, just call the service with the proper parameters!

## First steps

1. The default template path is set to /home/birtserver/templates/. The server will try to copy the test template to that folder.

2. Navigate to *{deployUrl}* in your browser. You'll see the test report displayed.

## Project details

- This project is open source and everyone is invited to colaborate in it. New commiters will be welcomed.
