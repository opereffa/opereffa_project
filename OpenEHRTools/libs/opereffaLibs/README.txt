This folder contains the libraries used by all projects related to Opereffa.
Some of the libraries are not referenced at all, by any of the other Eclipse
projects, but don't let this fool you. They are loaded by other libraries during
runtime. 
Some of these libraries are referenced by other projects, but all of them end up
being copied to lib sub-folder of web-inf folder in the web application. So all 
jars, including the ones in the sub directories like liboutputs and openehrrefimp
end up in the lib sub-folder of web-inf.