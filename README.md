## publish_subscribe

#什么是观察者模式
>观察者模式： 发布-订阅(Publish/Subscribe)模式，由于Rxjava是基于观察者模式的，所以大家很有必要了解下什么是观察者模式。

> 观察者模式是对象的行为模式，又叫发布-订阅(Publish/Subscribe)模式、模型-视图(Model/View)模式、源-监听器(Source/Listener)模式或从属者(Dependents)模式。

> 观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。这个主题对象在状态上发生变化时，会通知所有观察者对象，使它们能够自动更新自己。

* 目的： 定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。

* 主要解决： 一个对象状态改变给其他对象通知的问题，而且要考虑到易用和低耦合，保证高度的协作。

* 何时使用： 一个对象（目标对象）的状态发生改变，所有的依赖对象（观察者对象）都将得到通知，进行广播通知。

* 解决方案： 使用面向对象技术，可以将这种依赖关系弱化。

* 关键代码： 在抽象类里有一个 ArrayList 存放观察者们。

* 应用实例： 
	* 1、拍卖的时候，拍卖师观察最高标价，然后通知给其他竞价者竞价。 
	* 2、西游记里面悟空请求菩萨降服红孩儿，菩萨洒了一地水招来一个老乌龟，这个乌龟就是观察者，他观察菩萨洒水这个动作。
<p>	
* 优点： 1、观察者和被观察者是抽象耦合的。 2、建立一套触发机制。

* 缺点： 
	* 1、如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。 
	* 2、如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。 
	* 3、观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。
<p>	
* 使用场景： 
* 1、有多个子类共有的方法，且逻辑相同。 
* 2、重要的、复杂的方法，可以考虑作为模板方法。
<p>	
* 注意事项： 
* 1、JAVA 中已经有了对观察者模式的支持类。 
* 2、避免循环引用。  
* 3、如果顺序执行，某一观察者错误会导致系统卡壳，一般采用异步方式。


## Rx概念
> Rx是一个使用可观察数据流进行异步编程的编程接口，ReactiveX结合了观察者模式、迭代器模式和函数式编程的精华


* 迭代器模式

		为了更好的理解什么是Rx，有必要了解下迭代器模式:
		
		提供一种方法顺序访问一个聚合对象中的各种元素，而又不暴露该对象的内部表示
	
![迭代器模式UML类图](http://img.my.csdn.net/uploads/201302/06/1360131342_4539.png)

## Schedulers调度

> * 在RxJava 中，Scheduler ——调度器，相当于线程控制器，RxJava 通过它来指定每一段代码应该运行在什么样的线程。RxJava 已经内置了几个 Scheduler ，它们已经适合大多数的使用场景：

> * Schedulers.immediate():直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。

> * Schedulers.newThread():总是启用新线程，并在新线程执行操作。

> * Schedulers.io():I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。

> * Schedulers.computation():计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。

> * 另外， Android 还有一个专用的 AndroidSchedulers.mainThread() ，它指定的操作将在 Android 主线程运行。

> * 另外使用AndroidSchedulers.mainThread()需要添加

> * compile ‘io.reactivex:rxandroid:1.1.0’的依赖有了这几个 Scheduler ，就可以使用 subscribeOn() 和 observeOn() 两个方法来对线程进行控制了。

> * subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。 
> * observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。

## RxJava中的操作符

在Rxjava当中最重要的就是操作符，RxJava当中有着庞大的操作符

* 创建操作符：负责创建Observable对象

		just( ) — 将一个或多个对象转换成发射这个或这些对象的一个Observable

		from( ) — 将一个Iterable, 一个Future, 或者一个数组转换成一个Observable
 
		repeat( ) — 创建一个重复发射指定数据或数据序列的Observable
 
		repeatWhen( ) — 创建一个重复发射指定数据或数据序列的Observable，它依赖于另一个Observable发射的数据

		create( ) — 使用一个函数从头创建一个Observable

		defer( ) — 只有当订阅者订阅才创建Observable；为每个订阅创建一个新的Observable

		range( ) — 创建一个发射指定范围的整数序列的Observable

		interval( ) — 创建一个按照给定的时间间隔发射整数序列的Observable

		timer( ) — 创建一个在给定的延时之后发射单个数据的Observable

		empty( ) — 创建一个什么都不做直接通知完成的Observable

		error( ) — 创建一个什么都不做直接通知错误的Observable

		never( ) — 创建一个不发射任何数据的Observable

* 变换操作符：对Observable发射的数据执行变换操作的各种操作符
		map( ) — 对序列的每一项都应用一个函数来变换Observable发射的数据序列

		flatMap( ), concatMap( ), and flatMapIterable( ) 
			— 将Observable发射的数据集合变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable

		switchMap( ) — 将Observable发射的数据集合变换为Observables集合，然后只发射这些Observables最近发射的数据
		
		scan( ) — 对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值

		groupBy( ) — 将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据

		buffer( ) — 它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个

		window( ) — 定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项

		cast( ) — 在发射之前强制将Observable发射的所有数据转换为指定类型

* 过滤操作符：用于过滤和选择Observable发射的数据序列

		filter( ) — 过滤数据

		takeLast( ) — 只发射最后的N项数据

		last( ) — 只发射最后的一项数据

		lastOrDefault( ) — 只发射最后的一项数据，如果Observable为空就发射默认值

		takeLastBuffer( ) — 将最后的N项数据当做单个数据发射

		skip( ) — 跳过开始的N项数据

		skipLast( ) — 跳过最后的N项数据

		take( ) — 只发射开始的N项数据

		first( ) and takeFirst( ) — 只发射第一项数据，或者满足某种条件的第一项数据

		firstOrDefault( ) — 只发射第一项数据，如果Observable为空就发射默认值

		elementAt( ) — 发射第N项数据

		elementAtOrDefault( ) — 发射第N项数据，如果Observable数据少于N项就发射默认值

		sample( ) or throttleLast( ) — 定期发射Observable最近的数据

		throttleFirst( ) — 定期发射Observable发射的第一项数据

		throttleWithTimeout( ) or debounce( ) — 只有当Observable在指定的时间后还没有发射数据时，才发射一个数据

		timeout( ) — 如果在一个指定的时间段后还没发射数据，就发射一个异常

		distinct( ) — 过滤掉重复数据

		distinctUntilChanged( ) — 过滤掉连续重复的数据

		ofType( ) — 只发射指定类型的数据

		ignoreElements( ) — 丢弃所有的正常数据，只发射错误或完成通知

* 结合操作符：用于组合多个Observables的组合使用
* 
		startWith( ) — 在数据序列的开头增加一项数据

		merge( ) — 将多个Observable合并为一个

		mergeDelayError( ) — 合并多个Observables，让没有错误的Observable都完成后再发射错误通知

		zip( ) — 使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果

		and( ), then( ), and when( ) — (rxjava-joins) 通过模式和计划组合多个Observables发射的数据集合

		combineLatest( ) — 当两个Observables中的任何一个发射了一个数据时，
			通过一个指定的函数组合每个Observable发射的最新数据（一共两个数据），然后发射这个函数的结果

		join( ) and groupJoin( ) — 无论何时，如果一个Observable发射了一个数据项，
			只要在另一个Observable发射的数据项定义的时间窗口内，就将两个Observable发射的数据合并发射

		switchOnNext( ) — 将一个发射Observables的Observable转换成另一个Observable，
			后者发射这些Observables最近发射的数据

* 辅助操作符：用于Observable的辅助操作符
* 
		materialize( ) — 将Observable转换成一个通知列表convert an Observable into a list of Notifications

		dematerialize( ) — 将上面的结果逆转回一个Observable

		timestamp( ) — 给Observable发射的每个数据项添加一个时间戳

		serialize( ) — 强制Observable按次序发射数据并且要求功能是完好的

		cache( ) — 记住Observable发射的数据序列并发射相同的数据序列给后续的订阅者

		observeOn( ) — 指定观察者观察Observable的调度器

		subscribeOn( ) — 指定Observable执行任务的调度器

		doOnEach( ) — 注册一个动作，对Observable发射的每个数据项使用

		doOnCompleted( ) — 注册一个动作，对正常完成的Observable使用

		doOnError( ) — 注册一个动作，对发生错误的Observable使用

		doOnTerminate( ) — 注册一个动作，对完成的Observable使用，无论是否发生错误

		doOnSubscribe( ) — 注册一个动作，在观察者订阅时使用

		doOnUnsubscribe( ) — 注册一个动作，在观察者取消订阅时使用

		finallyDo( ) — 注册一个动作，在Observable完成时使用

		delay( ) — 延时发射Observable的结果

		delaySubscription( ) — 延时处理订阅请求

		timeInterval( ) — 定期发射数据

		using( ) — 创建一个只在Observable生命周期存在的资源

		single( ) — 强制返回单个数据，否则抛出异常

		singleOrDefault( ) — 如果Observable完成时返回了单个数据，就返回它，否则返回默认数据

		toFuture( ), toIterable( ), toList( ) — 将Observable转换为其它对象或数据结构

## 关于操作符的一些详细信息： 
* [ReactiveX/RxJava文档中文版](https://mcxiaoke.gitbooks.io/rxdocs/content/  )
