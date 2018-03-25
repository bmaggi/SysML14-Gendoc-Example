# How to make a release of X.Y.Z and setup X.Y.Z+1 working state

1. Check that the target platform is correctly configured
1. Set the version for the release
```
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=X.Y.Z
```
Manual modification for category.xml and target in main pom.xml
1. Check & Create the update site
```
mvn clean install
```
1. Tag the release
```
git add *
git commit -s -m "Set version to X.Y.Z"
git tag -a vX.Y.Z -m "Release X.Y.Z"
```
1. copy/paste repository in gh_pages branch
1. rename the directory to X.Y.Z
1. set up the composite update site
1. push the repo in gh_pages

```
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=X.Y.Z+1-SNAPSHOT 
```
Check that you are using latest versions
```
mvn versions:display-plugin-updates
```
Manual modification for category.xml and target in main pom.xml

(Check that it's still working)
```
mvn clean install 
```
```
git add *
git commit -s -m "Set version to X.Y.Z+1-SNAPSHOT"
git push 
git push origin vX.Y.Z
```

Check that the version is available using these update sites
https://bmaggi.github.io/SysML14-Gendoc-Example/vX.Y.Z/ and https://bmaggi.github.io/SysML14-Gendoc-Example