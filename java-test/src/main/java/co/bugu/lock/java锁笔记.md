java中常用的锁分析总结:
ReentrantLock, ReentrantReadWriteLock, Synchronized

类继承结构：
Lock(interface)
|\\
DummyConcurrentLock MonReentrantLock ReadLock ReentrantLock

ReentrantLock类继承结构：
ReadWriteLock（interface）
|\
ReentrantReadWriteLock

概念：
1 什么是可重入锁：可重入所的概念是自己可以再次获取自己的内部锁。例如：一条线程获得了某个对象的锁，
此时这个对象锁还没有释放，当其再次想要获取这个对象的锁的时候还是可以获取的（如果不可重入的话，此刻
或造成死锁）。说的高深点，可重入锁是一种递归无阻塞的同步机制。

2 什么叫读写锁：读写锁拆成读锁和写锁来理解。读锁可以共享，多个线程可以同时拥有读锁，但是写锁却只能
只有一个线程拥有，而且获取写锁的时候其他线程都已经释放了读锁，而且该线程获取写锁之后，其他线程不能
再获取读锁。简单的说就是写锁是排他锁，读锁是共享锁。
  
3 获取锁涉及到的两个概念即 公平和非公平：公平表示线程获取锁的顺序是按照线程加锁的顺序来分配的，
即先来先得的FIFO顺序。而非公平就是一种获取锁的抢占机制，和公平相对就是先来不一定先得，这个方式可能
造成某些线程饥饿（一直拿不到锁）。


(c)  ReentrantLock，ReentrantReadWriteLock，Sychronized用法即作用

ReentrantLock： 类ReentrantLock实现了Lock，它拥有与Sychronized相同的并发性和内存语义，
但是添加了类似锁投票、定时锁等候和可中断等候的一些特性。此外，它还提供了在与激烈争用情况下更佳的
性能（说白了就是ReentrantLock和Sychronized差不多，线程间都是完全互斥的，一个时刻只能有一个
线程获取到锁，执行被锁住的代码，但ReentrantLock相对于Sychronized提供了更加丰富的功能并且在
线程调度上做了优化，JVM调度使用ReentrantLock的线程会更快）

ReentrantReadWriteLock：类ReentrantReadWriteLock实现了ReadWirteLock接口。它和
ReentrantLock是不同的两套实现，在类继承结构上并无关联。和ReentrantLock定义的互斥锁不同的是，
ReentrantReadWriteLock定义了两把锁即读锁和写锁。读锁可以共享，即同一个资源可以让多个线程获取
读锁。这个和ReentrantLock（或者sychronized）相比大大提高了读的性能。在需要对资源进行写入的
时候在会加写锁达到互斥的目的。



公平锁与非公平锁
先阻塞先获取到锁就是公平锁，随机获取就叫做非公平锁。