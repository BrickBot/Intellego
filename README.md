# Intellego
by Graham Ritchie, University of St Andrews

[Archive of Original Website](https://web.archive.org/web/20090930092943/http://homepages.inf.ed.ac.uk/s0237680/intellego/intellego.html)

The release contains all of the submission for the project:

### Common
* Javadoc generated documentation is in the "javadocs" folder
* Java2HTML generated code is in the HTMLcode folder

### Windows
* To use the software in Windows you must first have java and lejos installed (see below for download sites), go to the `setup.bat` file in the `util` directory and set the JAVA_HOME, LEJOS_HOME and INTELLEGO_HOME variables to the correct paths to java, lejos and intellego respectively
* To compile the software, from this directory run `clean.bat`, to delete all previous class files, and then run `build.bat` to compile.
* To run the system, run `Intellego.bat`
* To generate the javadoc run `makeJavadoc.bat`
* To generate the html of the code run `makeHTMLcode.bat`

### Linux
* To use the software in linux, make sure you have java installed correctly, then edit `./Intellego.sh` and 
`util/setup.sh` to contain the correct paths and classpaths to the Intellego software and to lejos.
* You may also wish to add the intellego home directory to your `~/.bashrc` or similar PATH variable.
* To compile the software, run `make`
* To run the system, run `Intellego.sh` 
* To generate the javadoc run `make javadoc
* To genereate the html of the code run `make html`
* Run `make zip` to build a zip file if you have the Infozip "zip" program installed
* Run `make tar` to build a tar.gz file for distribution. 

Note that jdk1.3 has problems in it's implementation of Swing, and you should use jdk1.4 under linux for best performance and looks.
