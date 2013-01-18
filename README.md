opereffa
========

This is the public repository for Opereffa Project
The master branch contains directories which are Eclipse projects. 
The projects do not include the libraries they depend on. This is due to ongoing process 
of checking the open source licenses of libraries used by these projects, so that rules regarding the deployment of 
these libraries are followed.

For this reason, and other conveniences that Maven provides, the plan is to switch to use of Maven for Opereffa, so that 
required projects are downloaded automatically. 
Until then, required libraries must be manually downloaded from their official web pages. There are readme files
under directories where required libraries should be placed, and dependencies in the Eclipse project settings 
also help trace which libraries are needed. 
