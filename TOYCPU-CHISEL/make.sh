if [ "$1" = "simulator" ]; then 
	sbt run
	verilator --cc --exe main.cpp Top.v
	cd obj_dir
	make -f VTop.mk
	cd -
fi

if [ "$1" = "clean" ]; then  
	rm *.v *.json *.fir *.f
	rm -rf obj_dir
fi

if [ "$1" = "run" ]; then  
    ./obj_dir/VTop $2
fi
