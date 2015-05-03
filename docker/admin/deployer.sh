#!/usr/bin/perl

deployer deploy base

deployer build java

deployer shell tomcat

deployer start validation qa


pre-built images could exist for every environement as a version..

magex9/jquery:ut2
magex9/jquery:utps
magex9/jquery:dev