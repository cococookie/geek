**IO模型**

1、阻塞IO模型：服务端线程一直等待客户端请求，直到内核给服务端应用程序请求信息，再处理请求

2、非阻塞IO模型：服务端向内核询问请求结果，内核如果还没有准备好数据，则返回
未准备好数据，此时线程可以做别的事情。直到内核通知服务端数据准备好，服务端再去拿回数据处理。

3、IO复用模型：和非阻塞IO模型类似，使用少数线程监听内核中多个请求的监听(在Linux中，网络请求由fd文件描述符标识，
因此至少少数线程调用select监控fd，当发现有fd的数据准备好了之后，就会通知处理数据的线程开始工作)，如果有准备好的数据，就发起一个
线程去处理这些数据（处理数据的线程利用recvfrom方法调用数据）.复用模型的好处就是通过select监听多个fd，而不必为
每一个fd创建一个线程，减少了资源的浪费

4、信号驱动的IO模型：IO复用模型中，监听线程也是通过不断的轮训调用select接口获取数据状态信息的，信号驱动模型
解决了这个问题。进程直接调用sigaction方法建立SIGIO的信号联系，之后进程就继续做其他的事情，当有数据状态发生改变的时候，
SIGIO会给进程发送一个数据准备好的信号，这是线程再调用recvfrom方法读取数据

5、异步IO：服务端进程直接通知内核监听，内核直接将数据处理好包括执行IO操作.



**同步和异步、阻塞和非阻塞的区别**


1、阻塞和非阻塞的区别：当进程向内核请求读取数据的时候，如果内核直接返回是否准备好数据，就是非阻塞的，
如果需要等待内核准备好数据才返回，就是阻塞的。

2、同步和异步的区别：当进程发起读取数据的请求时，还需要等待内核准备好数据再读取数据处理的就是同步，
如果进程只发起请求之后不在参与数据的处理和后续的流程，就是异步。