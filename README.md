# java-native-memory-leak-reproducer

* Install jemalloc as follows:

```
cd ~/workspaces/
wget https://github.com/jemalloc/jemalloc/releases/download/5.2.1/jemalloc-5.2.1.tar.bz2
tar xvf jemalloc-5.2.1.tar.bz2
cd jemalloc-5.2.1/
./configure --enable-prof --prefix=/home/izeye/workspaces/jemalloc
make
make install
```

* Add the following to `~/.bash_profile`: 

```
export LD_PRELOAD="/home/izeye/workspaces/jemalloc/lib/libjemalloc.so"
export MALLOC_CONF="prof:true,lg_prof_interval:30,lg_prof_sample:17,prof_leak:true"
```

* Run the application as follows:

```
cd ~/workspaces/
git clone https://github.com/izeye/java-native-memory-leak-reproducer.git
cd java-native-memory-leak-reproducer/src/main/java/
javac com/izeye/sample/Application.java
java -cp . com.izeye.sample.Application
```

* Analyze the results as follows:

```
$ cd ~/workspaces/java-native-memory-leak-reproducer/src/main/java
$ ~/workspaces/jemalloc/bin/jeprof --show_bytes *.heap
Using local file jeprof.36471.0.i0.heap.
Using local file jeprof.36471.2.i2.heap.
Using local file jeprof.36471.1.i1.heap.
Fetching 2 profiles, Be patient...
Using local file jeprof.36471.2.i2.heap.
Using local file jeprof.36471.1.i1.heap.
addr2line: /home/izeye/workspaces/java-native-memory-leak-reproducer/src/main/java/jeprof.36471.0.i0.heap: File format not recognized
Welcome to jeprof!  For help, type 'help'.
(jeprof) top
Total: 5335112116 B
5333407452 100.0% 100.0% 5333407452 100.0% je_prof_backtrace
 1704664   0.0% 100.0% 5311029734  99.5% Java_java_util_zip_Deflater_init
       0   0.0% 100.0% 205618089   3.9% 00007f5270a1f62f
       0   0.0% 100.0%   270421   0.0% 00007f5270a2d079
       0   0.0% 100.0%   270421   0.0% 00007f5270a2e1cf
       0   0.0% 100.0% 5106198893  95.7% 00007f52784dcfff
       0   0.0% 100.0%  1264300   0.0% AllocateHeap@42c3e0
       0   0.0% 100.0% 19445540   0.4% AllocateHeap@42c440
       0   0.0% 100.0%   592551   0.0% Arena::Arealloc
       0   0.0% 100.0%   827637   0.0% Arena::Arena
(jeprof) 
```
