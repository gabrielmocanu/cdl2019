build: kevin

kevin: Cdl.class 
	javac Cdl.java

clean:
	rm -rf *.o kevin

run: kevin
	java Cdl $(var)
