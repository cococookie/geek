**类加载生命周期:**
1、加载[(加载class文件)
链接[验证、准备(加载静态字段、方法表)、解析(符号解析为引用)]
初始化(构造器、静态变量赋值、静态代码块)
使用(new等)]
2、卸载

**类加载时机**
1、显式调用(main方法所在的类、new对象、调用静态方法字段所在的类)
2、隐式调用(初始化子类时,父类先被初始化、接口实现类的初始化、反射调用时初始化、methodHandle调用)

不回触发初始化的情况
1、通过子类调用父类的静态字段,不会初始化子类
2、定义对象数组,不会初始化该对象(因为是一个空引用)
3、调用某类的常量,不会初始化该类,因为常量在编译期就存入常量池中
4、类.class不会初始化该类
5、Class.forName如果参数initialize是false,不会触发初始化
6、通过classLoader默认的loadClass方法

**加载器类型**
1、启动类加载器(代码由jdk底层实现,加载jre和jre/lib目录下的核心库)
2、扩展类加载器(通过url加载,父类是classLoader,加载jre/lib/ext目录下的扩展包)
3、应用类加载器(通过url加载,父类是classLoader,加载自己写的类、以及这些类所依赖的包)

**加载器特点:**
1、双亲委托(为了避免重复加载,当某加载器需要加载类时,首先请求父加载器加载,如果父加载器有加载则返回引用,否则由自己来加载. 父子关系 启动类加载器>扩展类加载器>应用类加载器>自定义加载器)
2、负责依赖(如果加载器加载了某类,则与其相关联需要加载的类也会被加载)
3、缓存加载(一个类只会被加载一次,当需要使用类时,会先去内存查找是否有加载,有则直接拿)

**jvm内存结构**
1、由堆栈组成,原生类型、局部变量以及对象的引用在栈上存储,栈由每个线程独有.
对象和类成员变量、静态变量存储在堆中,堆可被所有线程共享.
由于多个线程访问同一对象的方法,堆中的对象是共用的,线程从堆中拿到值的副本后在栈中操作,在这个过程中可能会产生并发问题
2、栈结构:
a)


3、堆内存结构
a)

4、JMM规范
定义了规范,控制线程之间的交互操作,让线程之间可以协调调度.对于栈中局部变量是不在jmm范围内的


**jvm启动参数**
- :所有jvm都要实现的参数
-D: 设置系统属性
-X:  非标准参数 不是所有jvm都要实现 java -X查看当前jvm支持的参数
-XX: 与jvm实现有关,也不是每个jvm都有的参数
赋值方式: -XX: +-Flags 是开关的设置形式  -XX: key=value 是设置值的方式
 1、系统属性参数 : 如 -Dfile.encoding=UTF-8 在当前进程生效 
2、运行模式参数 : -server 启动速度慢 但是运行性能和内存管理效率高
-client 启动速度快,但是运行和内存管理效率低
-Xint:解释模式下运行
-Xcomp: 编译模式执行 
-Xmixed: 混合模式,热点代码编译模式,冷代码解释模式
3、堆内存设置参数: -Xms: 堆内存空间初始化大小 -Xmx: 堆内存最大大小  简易xms与					xmx一致; -Xmn: 新生代大小 建议堆内存最大的1/2-1/4
4、GC设置参数: -XX: +UseG1GC 设置用G1回收器
问题: 各个jvm版本默认的GC是什么?
5、分析诊断参数:
6、JavaAgent参数:通过无入侵的方法注入AOP代码、执行统计等等


**java命令行工具**
1、查看java进程: jps 
2、查看gc相关信息: jstat
a)jstat -gx 
执行: jstat -gc 10403 1000 1000   
解释: 10403 java进程, 1000 打印1000次 每1000ms打印一次

解释: s0c: s0区总字节数  
s1c:s1区总字节数; 
s0u: s0区使用的字节数; 
s1u:s1区使用的字节数; 
EC: 年轻代总字节数
Eu:年轻代使用字节数
 OC:老年代总字节数
 OU: 老年代使用字节数
MC: 元数据区总字节数
MU:元数据区使用字节数
CCSC:ccs区总
 CCSU:ccs区使用
 YGC:yuongGC次数
YGCTL:youngGC时间
 FGC:fullGC次数
 FGCT: fullGC时间
GCT:GC总时间
执行: jstat -gcutil 10403 1000 1000

解释: 各个区使用百分比 上面gc 使用/总的结果
3、查看堆占用空间统计:jmap
a)jmap -histo 15167 查看进程类占用字节数

b)jmap -heap 查看堆使用空间统计 是静态的
4、查看线程信息: jstack
a)jstack -l  15167
b)kill -3 15167  执行该命令 可以在java进程当中打印线程信息
5、jvm相关分析命令: jcmd
a)jcmd 15167 help 查看可提供的指令 (可替代以上的指令)
b)jcmd 15167 VM.flags 查看所有启动命令
c)
[root@smartgo-man-gz01a-blue-797dfbf486-bgm2t /]# jcmd 1 VM.flags

-XX:CICompilerCount=2 -XX:+CMSClassUnloadingEnabled -XX:CMSInitiatingOccupancyFraction=70 -XX:+CMSParallelRemarkEnabled -XX:CompressedClassSpaceSize=260046848 -XX:ConcGCThreads=0 -XX:ErrorFile=/web/logs/jvm/smartgo-man/10.13.98.156_d6e7354842/jvm_error.log -XX:GCLogFileSize=20971520 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/web/logs/jvm/smartgo-man/10.13.98.156_d6e7354842/jvm_dump.hprof -XX:InitialHeapSize=1610612736 -XX:MaxDirectMemorySize=536870912 -XX:MaxHeapSize=1610612736 -XX:MaxMetaspaceSize=268435456 -XX:MaxNewSize=603979776 -XX:MinHeapDeltaBytes=196608 -XX:NewSize=603979776 -XX:NumberOfGCLogFiles=10 -XX:OldPLABSize=16 -XX:OldSize=1006632960 -XX:ParallelGCThreads=2 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:ReservedCodeCacheSize=268435456 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:SurvivorRatio=8 -XX:ThreadStackSize=256 -XX:+UseCMSCompactAtFullCollection -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:+UseGCLogFileRotation -XX:+UseParNewGC 

jcmd 1 VM.command_line

1:
VM Arguments:
jvm_args: -Djava.util.logging.config.file=/web/servers/tomcat/conf/logging.properties -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -javaagent:/jacocoagent.jar=includes=*,address=0.0.0.0,port=8081,output=tcpserver,append=true -javaagent:/web/servers/jdbc-agent/jdbc-agent.jar -Djdbc.server.url=http://testpulsar.api.inner-dmall.com/pulsar/getid -javaagent:/web/servers/dsw-agent/dsw-agent.jar -Xms1536M -Xmx1536M -Xmn576M -Xss256K -Dfile.encoding=UTF8 -XX:MaxDirectMemorySize=512M -XX:ReservedCodeCacheSize=256M -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:CMSInitiatingOccupancyFraction=70 -XX:+CMSParallelRemarkEnabled -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+CMSClassUnloadingEnabled -XX:ParallelGCThreads=2 -XX:ConcGCThreads=1 -XX:SurvivorRatio=8 -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dsun.net.client.defaultConnectTimeout=60000 -Dsun.net.client.defaultReadTimeout=60000 -Ddubbo.protocol.port=20880 -Ddubbo.application.flag=blue -Ddubbo.application.shared=true -Ddubbo.application.region=gz01a -Ddmall.traffic.group.code=blue -Ddmall.zone.code=gz01a -Dcom.dmall.zone=gz01 -Dcom.dmall.group=blue -Dcom.dmall.physical.dc=a -XX:MaxMetaspaceSize=256M -Dsun.net.inetaddr.ttl=30 -Dnetworkaddress.cache.ttl=30 -Ddump.directory=/web/logs/jvm/smartgo-man/10.13.98.156_d6e7354842 -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=20M -XX:+PrintGCDateStamps -Xloggc:/web/logs/jvm/smartgo-man/10.13.98.156_d6e7354842/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:ErrorFile=/web/logs/jvm/smartgo-man/10.13.98.156_d6e7354842/jvm_error.log -XX:HeapDumpPath=/web/logs/jvm/smartgo-man/10.13.98.156_d6e7354842/jvm_dump.hprof -Djdk.tls.ephemeralDHKeySize=2048 -Djava.protocol.handler.pkgs=org.apache.catalina.webresources -Dorg.apache.catalina.security.SecurityListener.UMASK=0027 -Dignore.endorsed.dirs= -Dcatalina.base=/web/servers/tomcat -Dcatalina.home=/web/servers/tomcat -Djava.io.tmpdir=/web/servers/tomcat/temp 
java_command: org.apache.catalina.startup.Bootstrap start
java_class_path (initial): /web/servers/tomcat/bin/bootstrap.jar:/web/servers/tomcat/bin/tomcat-juli.jar:/jacocoagent.jar:/web/servers/jdbc-agent/jdbc-agent.jar:/web/servers/dsw-agent/dsw-agent.jar
Launcher Type: SUN_STANDARD

**可视化工具**
a)jconsole

b) jvisualvm
c) visualGC: idea 插件
d) jmc: jdk 8 2.6.1之前版本自带

**GC算法**
a)引用计数法: 有引用计数就加一 否则就减一 缺点 不能解决循环引用的问题
b)标记清除法: 将堆空间中可达的的对象做标记,然后将剩余的对象清除.可解决循环引用的问题 .  在做标记和清除的时候,为了避免产生新的对象和改变引用关系,所以此时stop the world 即STW,让程序停下来,不会做任何修改对象的操作,以确保标记正确性
如何提升gc的效率?
采用分代算法,因为根据分析发现新创建的对象总是很快无用,而存活较长的对象也往往能活得更长.所以根据分区采用不通的策略.
复制算法:
年轻代分新生代区和s0,s1, 当有新对象创建时会在新生代分配,新生代快满了之后,触发一次GC,将可达对象做标记,然后将可达对象复制到s1或s0区域中,然后清空新生代数据. 程序继续运行,当eden区快要满的时候,触发GC,这时将eden区和有数据的s区的可达对象进行标记,复制到空的S区中,再将原来的数据清空. 如此反复,所以年轻代中总有一个s区是空的.(两个存活区有数据的叫from 空的叫to , from和to总是互换角色).当存活区的对象存活了超过指定次数的GC,就会被移动到老年代,默认是15次GC. 年轻代的GC过程成为MinorGC
老年代使用标记清除整理算法,老年代发生的GC过程称为Major GC
年轻代与老年代一起GC称为FullGC
可以作为GC Roots的对象
当前正在执行的方法的局部变量和输入参数、活动线程本身、静态字段、JNI引用
GC暂停时间的长短,与存活的对象数量有关系,与堆内存和对象总数没有直接关系
c)整理算法: 标记清除算法后会产生大量的碎片空间,使用该算法可以整理内存空间.

**串行GC/并行GC**
a)串行GC,只适用于单核CPU,当对内存过大,可能或导致卡死的情况.
b)并行gc: 适用于多核CPU,可以增加吞吐量,暂停的时间更短.JDK678默认并行GC.

**GC策略**
a)CMS GC  最大可能性并发的标记清除垃圾回收算法
对年轻代采用并行STW方式的mark-copy算法,对老年代适用并发mark-sweep算法
CMS优点: 目的是为了降低老年代GC的STW时间,使用与业务线程并发的方式处理GC过程,默认处理GC的并发线程数是CPU核心数的1/4.即3/4的线程处理业务,同时1/4的线程用于GC处理,这样可以避免业务系统暂停. 为了提升效率,CMS策略对老年代避免使用了标记清除整理算法,而是采用标记清除算法,避免了整理带来的效率影响,而是使用空闲列表的方式管理内存空间的回收

