APPFILE=

0rvcpu:
	sbt run
	verilator --cc --exe main.cpp Top.v
	cd obj_dir && make -f VTop.mk
	cd ..

1binary:
	cd src/c/ && make $(APPFILE)

2run:
	./obj_dir/VTop ./src/hex/$(APPFILE).hex 

clean:
	rm *.v *.json *.fir *.f
	rm -rf obj_dir

