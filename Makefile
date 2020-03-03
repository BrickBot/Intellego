default: clean all

clean:	
	util/clean.sh

all:	
	util/setup.sh
	javac intellego/Intellego.java 
	cd controllers; make default; cd ..
	cd simworlds; make default; cd ..

tar:	
	make clean; make all; tar -cz . > ./Intellego.tar.gz

zip:
	make clean; make all; zip -r Intellego.zip .

javadoc: 
	make clean; make all ; cd util; ./build_javadoc.sh

html:	
	make clean; make all ; cd util; ./build_html.sh
