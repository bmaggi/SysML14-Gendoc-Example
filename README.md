# SysML14-Gendoc-Example
Example of [Gendoc][2] template for a [Papyrus][1] [SysML 1.4][3] model.

### Status ###

[![License](https://img.shields.io/badge/license-EPL2-blue.svg)](https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html)

:warning: Built/Hand tested against Eclipse Oxygen | Papyrus 3.0.0 | SysML 1.4 1.1.0 | Gendoc 0.7.0

### Repository structure ###

This repository is organized around logical software components:

* `com.github.bmaggi.sysml14.gendoc.queries`: plugin with acceleo querries
* `com.github.bmaggi.sysml14.gendoc.queries.tests`: test for querries plugin
* `com.github.bmaggi.sysml14.gendoc.template`: gendoc template and papyrus model
* `com.github.bmaggi.sysml14.gendoc.feature`: feature for querries and template
* `com.github.bmaggi.sysml14.gendoc.repository`: update site for querries and template

### How to build ###

Components in this project are built using Maven and its Tycho plugin for the build of Eclipse artifacts.
To build locally, simply execute the command line:

```
mvn clean install
```

### How to use ###

Install the latest stable version from [TODO]

Or once the projects are build you need:
 - add the update site in "Available Software Site"  (it has been created here repository\target\repository\plugins\content.jar)
 - install the plugins

[1]:http://www.eclipse.org/papyrus/
[2]:http://www.eclipse.org/gendoc/
[3]:http://www.eclipse.org/papyrus/SysML14
