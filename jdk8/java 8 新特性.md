[toc]

# lambda
- 方法重写 : 这个接口必须是函数式接口,有且只有一个为实现的抽象方法  Runnable r = ()->{};
- 方法调用 : 函数推导出来用哪个方法即可,这个类可以有多个方法,我推导其中一个使用  System.out::printly



## Function
- T 入参类型 , R 返回值类型
- 接受一个参数,转换成另一个类型返回
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```
```java
// 传入一个int类型的值,返回一个R类型的值
@FunctionalInterface
public interface IntFunction<R> {
    R apply(int value);
}
```
```java
// 传入一个T类型和U类型2个值,返回一个R类型的值
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
```

## Predicate<T>
- T 入参类型
- 接受一个参数,返回一个布尔值
```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```
```java
//只接受long类型的参数,返回一个布尔值
@FunctionalInterface
public interface LongPredicate {
    boolean test(long value);
}
```
```java
//接受T类型和U类型的2个参数,返回一个布尔值
@FunctionalInterface
public interface BiPredicate<T, U> {
    boolean test(T t, U u);
}
```

## Consumer<T>
- T 入参类型
- 接受一个参数,无返回值
```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```
```java
//只接受 Double 类型的参数,无返回值
@FunctionalInterface
public interface DoubleConsumer {
    void accept(double value);
}
```
```java
//接受T类型和U类型的2个参数,无返回值
@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
}
```


## Supplier<T>
- T 返回值类型
- 无入参,返回 T 类型的值
```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```
```java
//无入参,返回固定 long 类型
@FunctionalInterface
public interface LongSupplier {
    long getAsLong();
}
```

## BinaryOperator
- 继承自 BiFunction<T,T,T>,他把参数都固定成一个类型了,2个T类型的入参,一个T类型的返回值
```java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T,T,T> {
    T apply(T t, T u);
}
```
```java
//固定3个类型都是Double
@FunctionalInterface
public interface DoubleBinaryOperator {
    double applyAsDouble(double left, double right);
}
```

## UnaryOperator
- 继承自 Function<T, T> , 固定入参和出参是一个类型
```java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
    T apply(T t);
}
```
```java
//固定入参出参类型都是int
@FunctionalInterface
public interface IntUnaryOperator {
    int applyAsInt(int operand);
```


# Stream
- 依赖于lambda表达式
## 常用API
    - stream() : 将当前集合变为Stream对象,单线程方式的stream,用的就是当前调用线程,之后调用方法都是单线程的
    ```java
    default Stream<E> stream() {
        //spliterator() 是个分割器,可以将任务分割便于多线程执行
        //false表示非并行
        return StreamSupport.stream(spliterator(), false); 
    }
    ```
    - parallelStream() : 将当前集合变为Stream对象,多线程方式的stream,默认使用ForkJoinPool.commonPool线程池中的线程和当前调用线程,之后调用方法都是并行的
    ```java
    default Stream<E> parallelStream() {
        //spliterator() 是个分割器,可以将任务分割便于多线程执行
        //false表示非并行
        return StreamSupport.stream(spliterator(), true);
    }
    ```
    - parallel() : 并行
    - sequential() : 串行
    ```java
        //在同一个stream链式调用中,多次调用parallel和sequential,意图改变执行的过程,这种做法是无效的,stream只认最后一次指定的串行还是并行
        //所以这里是串行
        list.stream().parallel().sequential().parallel();
    ```
    
    - filter(Predicate<? super T> predicate) : 传入一个 断言 对象,true保留,false剔除
    - distinct() : 对集合去重
    
    - Stream<T> sorted() : 无参数,顺序排列集合
    - Stream<T> sorted(Comparator<? super T> comparator) : 传入一个Comparator lambda 表达式
    ```java
    //传入两个 T 类型的对象,返回一个 int 类型的结果
    @FunctionalInterface
    public interface Comparator<T> {
        int compare(T o1, T o2);
    }
    ```
   
    - <R> Stream<R> map(Function<? super T, ? extends R> mapper) : 传入一个Function lambda表达式,将传入的T类型转为R类型并返回一个Stream<R>对象
    - <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) : 将多个集合合成一个stream
        - map会将每一条输入映射为一个新对象。{苹果，梨子}.map(去皮） = {去皮苹果，去皮梨子} 其中： “去皮”函数的类型为：A => B
        - flatMap包含两个操作：会将每一个输入对象输入映射为一个新集合，然后把这些新集合连成一个大集合。 {苹果，梨子}.flatMap(切碎) = {苹果碎片1，苹果碎片2，梨子碎片1，梨子碎片2} 其中： “切碎”函数的类型为： A => Stream<B>
        - flatMap传入的参数是一个集合对象,在flatmap方法中,将这个集合对象转换成stream对象即可
    ```java
        List<String> list = new ArrayList<>();
        list.add("55");
        list.add("22");
        List<String> list2 = new ArrayList<>();
        list.add("11");
        list.add("33");
        list.add("44");
        Stream<String> stringStream = Stream.of(list, list2).flatMap(Collection::stream);
        stringStream.forEach(System.out::println);
    ```
    ```java
        List<String> list = new ArrayList<>();
        list.add("55");
        list.add("22");
        List<String> list2 = new ArrayList<>();
        list.add("11");
        list.add("33");
        list.add("44");
        Stream<Integer> integerStream = Stream.of(list, list2).flatMap((l) -> {
            int[] ints = new int[l.size()];
            for (int i = 0; i < l.size(); i++) {
                ints[i] = Integer.valueOf(l.get(i));
            }
            return Arrays.stream(ints).boxed();
        });

        integerStream.forEach(System.out::println);
    ```
    
    - Stream<T> skip(long n) : 跳过指定个数个数据,返回后面所有数据，如果超过集合最大长度，则返回空集合
    - Stream<T> limit(long maxSize) : 限制返回个数
   
    - boolean allMatch(Predicate<? super T> predicate) : 终端方法,如果stream中所有数据都符合条件,返回true,否则返回false
    - boolean anyMatch(Predicate<? super T> predicate) : 终端方法,如果stream中有一个以上符合条件的数据,返回true,否则返回false
    - boolean noneMatch(Predicate<? super T> predicate) : 终端方法,如果stream中所有数据都不符合条件,返回true,否则返回false
    ```java
        List<String> list = new ArrayList<>();
        list.add("55");
        list.add("22");
        List<String> list2 = new ArrayList<>();
        list.add("11");
        list.add("33");
        list.add("44");
        Stream<Integer> integerStream = Stream.of(list, list2).flatMap((l) -> {
            int[] ints = new int[l.size()];
            for (int i = 0; i < l.size(); i++) {
                ints[i] = Integer.valueOf(l.get(i));
            }
            return Arrays.stream(new int[]{1, 2}).boxed();
        });
        boolean b = integerStream.noneMatch(i -> i == 3);
        System.out.println(b);
    ```
    
    - Optional<T> findFirst() : 终端方法,始终返回第一个数据
    - Optional<T> findAny() : 终端方法,随机返回一个数据,大概率是第一个
        - findAny()操作，返回的元素是不确定的，对于同一个列表多次调用findAny()有可能会返回不同的值。使用findAny()是为了更高效的性能。如果是数据较少，串行地情况下，一般会返回第一个结果，如果是并行的情况，那就不能确保是第一个.
    
    - T reduce(T identity, BinaryOperator<T> accumulator) : 终端方法,一个聚合函数,用来将stream中的集合数据组成一个数据
        - 传入一个初始值,和一个BinaryOperator组成一个跟初始值类型相同的对象返回
        - 注意串行和并行区别,并行每个线程都会获取到初始值,所以拼出来的结果都会多好几个初始值,所以如果串行就用不带初始值的方法
        ```java
        //会拼成一个大字符串返回
        String reduce = stringStream.reduce("789", (str1, str2) -> str1 + str2);
        ```
    - Optional<T> reduce(BinaryOperator<T> accumulator) : 终端方法,没有初始值,一个聚合函数,用来将stream中的集合数据组成一个数据
        - 返回Optional对象
        ```java
        stringStream.reduce((str1, str2) -> str1 + str2).ifPresent(System.out::println);
        ```

    - <R, A> R collect(Collector<? super T, A, R> collector) : 将Stream对象转为集合,这个是个终端方法,表示之后无法再调用Stream的链式方法
    ```java
    //常用方法
    .collect(Collectors.toList())  //将stream转为list
    .collect(Collectors.toSet()) //将stream转为set
    .collect(Collectors.toCollection(TreeSet::new))  //将stream转为Collection
    ```
- 流这种概念是将数据作为stream内部的一部分,stream对其操作**只能操作一次**
> 终端方法不会再产生新的stream对象,所以这些方法(foreach,collet)只能进行一次,map,sorted能多次执行是因为他们返回了新的stream对象,所以stream操作还是只能进行一次
```java
    Stream<String> stringStream = list.parallelStream();
    stringStream.forEach(System.out::println);
    //抛出异常 java.lang.IllegalStateException: stream has already been operated upon or closed
    stringStream.forEach(System.out::println); //不能再次执行
```

## Stream 的创建
- 通过集合创建Stream
```java
    list.stream();
    list.parallelStream();
```
- 通过多个值创建Stream
```java
    Stream.of("11","bb","22");
```
- 通过数组创建Stream
```java
    Arrays.stream(new String[]{"111","222"});
```
- 通过文件流生成Stream
```java
    //Paths.get 方法 需要传入一个文件全路径,不能是文件夹,否则抛出AccessDeniedException
    Path path = Paths.get("E:/360驱动大师目录/main-en.txt");
    try(Stream stream = Files.lines(path)) {
        stream.forEach(System.out::println);
    }catch (Exception e){
        e.printStackTrace();
    }
```

- 通过Stream的迭代器,生成一个无限序列,需要配合limit使用
```java
    Stream.iterate(1,n-> {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2+n;}).limit(10).forEach(System.out::println);
```
```java
    Stream.generate(Math::random).limit(10).forEach(System.out::println);
```


## Numberic Stream : IntStream,DoubleStream,LongStream 
- 能用则用
- Stream -> IntStream : mapToInt()
> 转换的好处是,可以直接使用int类型,从而减少大量拆箱装箱的操作,还有自定义好的max() min() 等
    - mapToInt
    - mapToDouble
    - mapToLong
    ```java
        //Stream带泛型,所以这里的1,2是integer类型,所以可以调用intValue方法,转成int类型
        IntStream intStream = Stream.of(1, 2).mapToInt(i -> i.intValue());
    ```
- IntStream -> Stream  : boxed()
> 基本类型节约空间,但是包装类型拥有更多的丰富的功能
```java
    Stream<Integer> boxed = IntStream.range(1, 10).boxed();
```
- mapToObj() : 可以不装箱,但是需要手动实现功能

- Numberic Stream 相关的方法
- sum
```java
    Stream.of(1, 2).mapToInt(i -> i.intValue()).sum();
```
- reduce
```java
    //建议可以转成基本类型的stream,都使用 Numberic Stream 的 reduce 方法
    Stream.of(1, 2).mapToInt(i -> i.intValue()).reduce((i, j) -> i + j)
        .ifPresent(System.out::println);
```

# Optional
- 构造
    - Optional<Object> empty = Optional.empty();
    - Optional<Object> empty = Optional.of(new Object());
    - Optional<Object> empty = Optional.ofNullable(null);
    ```java
        Optional<Object> empty = Optional.ofNullable(null);
        System.out.println(empty.orElseGet(()->{
            return "啥也没有";
        }));
    ```
    - Object o = Optional.ofNullable(null).orElse(new Object());
    - Object o = Optional.ofNullable(null).orElseThrow(RuntimeException::new);  //为null , 创建时这句代码就直接报错
- 常用方法
    - public Optional<T> filter(Predicate<? super T> predicate) : 有就返回原值,没有就返回空,空不能调用get方法
    ```java
        Optional<Integer> integer = Optional.ofNullable(1);
        Optional<Integer> integer1 = integer.filter(i -> i == 2);
        System.out.println(integer1.get()); //抛出异常
    ```
    - public boolean isPresent() : 是否存在
    
    - public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) : 可以链式嵌套防止空指针,唯一需要注意的就是和map的区别,flatMap需要返回值为Optional对象,以下功能用map也能实现,代码不一样
    ```java
        //比如A内有B,B内有C,C内有D,现在要获取D的值,我们得保证A不为空,B不为空,C不为空,最后获取D 
        //使用Optional#flatMap就可以写成这样,以下代码不会抛出空指针异常,任何一阶段为null,都会返回"啥也没有"
        String r = Optional.ofNullable(A)
                .flatMap(a -> Optional.ofNullable(a.getB()))
                .flatMap(b -> Optional.ofNullable(b.getC()))
                .flatMap(c -> Optional.ofNullable(c.getD()))
                .orElse("啥也没有");
        System.out.println(r);
    ```
    - public<U> Optional<U> map(Function<? super T, ? extends U> mapper) : 可以链式嵌套防止空指针
    ```java
        String r = Optional.ofNullable(A)
                .map(a -> a.getB())
                .map(b -> b.getC())
                .map(c -> c.getD())
                .orElse("啥也没有");
        System.out.println(r);
    ```

# Collector
- 调用对象 : Collectors
- 常用方法
    - averagingDouble
    - averagingInt
    - averagingLong
    ```java
        Double collect = transactions.stream().collect(Collectors.averagingDouble(Transaction::getValue));
        Double collect1 = transactions.stream().collect(Collectors.averagingInt(Transaction::getValue));
        Double collect2 = transactions.stream().collect(Collectors.averagingLong(Transaction::getValue));
    ```
    - collectingAndThen 2个参数,第一个是计算过程,第二个是得到结果后干的任务
    ```java
        Object collect2 = transactions.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.averagingDouble(Transaction::getValue),
                x->"最后结果是"+x
                ));
        System.out.println(collect2);
    ```
    - 可以利用 Collections#unmodifiableSet方法得到一个不能修改的集合  注意 : Collections != Collectors
    ```java
        Set<Integer> collect = transactions.stream().map(t -> t.getValue())
                .collect(Collectors.collectingAndThen(
                        Collectors.toSet(),
                        Collections::unmodifiableSet
                ));
        System.out.println(collect);
        collect.add(1);//抛出异常
    ```
    - counting 计数
    ```java
        Long collect = transactions.stream().collect(Collectors.counting());
        System.out.println(collect);
    ```
    - groupingBy(Function<? super T, ? extends K> classifier)  单个参数 -> 分组
    ```java
        Map<Integer, List<Transaction>> collect = transactions.stream()
            .collect(Collectors.groupingBy(Transaction::getValue));
        System.out.println(collect);
    ```
    - groupingBy(Function<? super T, ? extends K> classifier,Collector<? super T, A, D> downstream)    2个参数 -> 分组并计数
    ```java
        Map<Integer, Long> collect = transactions.stream()
            .collect(Collectors.groupingBy(Transaction::getValue, Collectors.counting()));
        System.out.println(collect);
    ```
    - groupingBy(Function<? super T, ? extends K> classifier,  Supplier<M> mapFactory, Collector<? super T, A, D> downstream)    3个参数 -> 分组并计数,并且设定返回的map类型
    > 上面2个参数的方法返回的map是hashmap,如果需要自定义其他map的实现,可以使用这个方法,可选的map看方法的返回值,子类皆可
    ```java
        Map<Integer, Long> collect = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getValue, TreeMap::new,Collectors.counting()));
        System.out.println(collect);
        System.out.println(collect.getClass());
    ```
    - groupingByConcurrent(Function<? super T, ? extends K> classifier)  跟上面单参数的功能一样,只是返回的是ConcurrentHashMap
    ```java
        ConcurrentMap<Integer, List<Transaction>> collect = transactions.stream()
                .collect(Collectors.groupingByConcurrent(Transaction::getValue));
        System.out.println(collect);
        System.out.println(collect.getClass());
    ```
    - groupingByConcurrent(Function<? super T, ? extends K> classifier,Collector<? super T, A, D> downstream)  跟上面2参数的功能一样,只是返回的是ConcurrentHashMap
    ```java
        Map<Integer, Long> collect = transactions.stream()
                .collect(Collectors.groupingByConcurrent(Transaction::getValue, Collectors.counting()));
        System.out.println(collect);
        System.out.println(collect.getClass());
    ```
    - groupingByConcurrent(Function<? super T, ? extends K> classifier,Collector<? super T, A, D> downstream)  跟上面3参数的功能一样,只是返回的是ConcurrentHashMap的子类,自己可以设定,比如:ConcurrentSkipListMap 跳表
    ```java
        Map<Integer, Long> collect = transactions.stream()
                .collect(Collectors.groupingByConcurrent(Transaction::getValue, ConcurrentSkipListMap::new,Collectors.counting()));
        System.out.println(collect);
        System.out.println(collect.getClass());
    ```
    - joining 拼接数据成为一个字符串 
    > 这个方法只能处理 CharSequence 子类的数据,所以需要提前将数据准备好
    ```java
        String collect = transactions.stream().map(t -> t.getTrader().getName())
                .collect(Collectors.joining());
        System.out.println(collect);
    ```
    ```java
        //joining(";") 可以设置分隔符
        String collect = transactions.stream().map(t -> t.getTrader().getName())
                .collect(Collectors.joining(";"));//CharSequence
        System.out.println(collect);
    ```
    ```java
        //joining(";","prefix","suffix") 可以设置分隔符,前缀,后缀
        String collect = transactions.stream().map(t -> t.getTrader().getName())
                .collect(Collectors.joining(";","prefix","suffix"));//CharSequence
        System.out.println(collect);
    ```
    - mapping 2个参数,第一个是map的功能,第二个是下一步的任务
    ```java
        String collect = transactions.stream().map(t -> t.getTrader().getName())
                .collect(Collectors.joining(";","prefix","suffix"));//CharSequence
        System.out.println(collect);
        // 等价于 ==>
        String collect2 = transactions.stream()
                .collect(
                        Collectors.mapping(t -> t.getTrader().getName(),
                                Collectors.joining(";","prefix","suffix"))
                );
        System.out.println(collect2);
    ```
    - maxBy(Comparator<? super T> comparator)  获取一个最大对象,传入比较器
    - minBy(Comparator<? super T> comparator)  获取一个最小对象,传入比较器
    ```java
        Optional<Transaction> collect = transactions.stream()
                .collect(Collectors.minBy(Comparator.comparingInt(Transaction::getValue)));
        System.out.println(collect);
    ```
    - partitioningBy(Predicate<? super T> predicate)   按照条件分组
    ```java
        Map<Boolean, List<Transaction>> milan = transactions.stream()
                .collect(Collectors.partitioningBy((t) -> t.getTrader().getCity().equals("Milan")));
        System.out.println(milan);
        //true 一组,false 一组
        //{false=[{Trader:aaa in Cambridge, year: 2011, value:300}, {Trader:Raoul in Cambridge, year: 2012, value:1000}, {Trader:Raoul in Cambridge, year: 2011, value:400}, {Trader:Alan in Cambridge, year: 2012, value:950}], 
        // true=[{Trader:Mario in Milan, year: 2012, value:710}, {Trader:Mario in Milan, year: 2012, value:700}]}
    ```
    - partitioningBy(Predicate<? super T> predicate,Collector<? super T, A, D> downstream)   第一步分组,第二步处理
    ```java
        Map<Boolean, Long> milan = transactions.stream()
                .collect(Collectors.partitioningBy(
                        (t) -> t.getTrader().getCity().equals("Milan"), Collectors.counting()
                ));
        System.out.println(milan);
    ```
    - reducing(T identity, BinaryOperator<T> op)   : 可以设定一个初始值,一定要注意,前面的类型决定了后面参数的类型,同时这个方法传入的类型,也决定了之前stream产生的类型得能让这个方法服务
    ```java
        Object collect = transactions.stream()
                .collect(Collectors.reducing(new Transaction(brian, 2011, 300), (t1, t2) -> {
                    return t2;
                }));
        System.out.println(collect);
    ```
    - summarizingDouble(ToDoubleFunction<? super T> mapper)  统计一个 double 类型的数据,并返回统计值
    - summarizingLong(ToLongFunction<? super T> mapper)  统计一个 long 类型的数据,并返回统计值
    - summarizingInt(ToIntFunction<? super T> mapper)  统计一个 int 类型的数据,并返回统计值
    ```java
        //DoubleSummaryStatistics 统计类型
        DoubleSummaryStatistics collect = transactions.stream()
                .collect(Collectors.summarizingDouble(Transaction::getValue));
        System.out.println(collect);
    ```

    - toCollectiontoCollection(Supplier<C> collectionFactory)  可以指定一个你希望的集合类型(必须是Collection的子类)
    ```java
        ArrayList<Transaction> collect = transactions.stream()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect);
    ```


    - toConcurrentMap(Function<? super T, ? extends K> keyMapper,Function<? super T, ? extends U> valueMapper)  根据指定的key和value生成一个新的 ConcurrentMap ,如果key重复,会抛出异常:java.lang.IllegalStateException: Duplicate key xxxx
    ```java
        ConcurrentMap<Integer, Integer> collect = transactions.stream()
                .collect(Collectors.toConcurrentMap(Transaction::getValue, Transaction::getYear));
        System.out.println(collect);
    ```
    - toConcurrentMap(Function<? super T, ? extends K> keyMapper,Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction)
    > 两个参数的方法如果key相同就会抛出异常,这里第三个参数就是告诉程序当两个key相同的时候,应该如何处理,这样就不会抛出异常了
    ```java
        ConcurrentMap<Trader, Integer> collect = transactions.stream()
                .collect(Collectors.toConcurrentMap(Transaction::getTrader,
                        Transaction::getYear,
                        (t1,t2)->{
                            return t1;
                        }));
    ```
    - toConcurrentMap(Function<? super T, ? extends K> keyMapper,Function<? super T, ? extends U> valueMapper,BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier)
    > 四个参数,在上边的基础上增加了可以自定义map类型,只能是ConcurrentMap的子类,目前两个选择方案: ConcurrentSkipListMap(需要键实现java.lang.Comparable接口),或者不写,默认就是ConcurrentHashMap
    ```java
        ConcurrentSkipListMap<Integer, Integer> collect = transactions.stream()
                .collect(Collectors.toConcurrentMap(
                        Transaction::getValue,
                        Transaction::getYear,
                        (t1, t2) -> {
                            return t1;
                        },
                        ConcurrentSkipListMap::new)
                );
    ```

    - toList : 只有arraylist实现
    - toSet : 只有HashSet实现

    - toMap : 三个方法和toConcurrentMap一一对应,功能一致,只不过这个方法返回的是map

- 调用流程
    - Collector 的操作交于其子类实现 CollectorImpl 
    - Collector 内部有一套调用逻辑,调用方法实质上是将参数传入 CollectorImpl 实现类中,再由 CollectorImpl 来执行操作
    - 逻辑在 ReferencePipeline 代码 public final <R, A> R collect(Collector<? super P_OUT, A, R> collector) 方法中定义的 

- 自定义 Collector 

# stream 并行对于arraylist和 Numberic Stream 所做的性能提升非常大,但是象map或者是链表所做的提升非常小,所以要看情况选择是否要用stream

# Spliterator
- 基于fork/join框架
- 从Spliterators中获取
- stream创建时会作为参数传入,用来分割stream
- 自定义 Spliterator
```java
public class MySpliteratorText {
    private final String[] data;

    public MySpliteratorText(String data) {
        this.data = data.split("\n");
    }

    public Stream<String> stream(){
        return StreamSupport.stream(new MySpliterator(),false);
    }
    public Stream<String> parallelStream(){
        return StreamSupport.stream(new MySpliterator(),true);
    }

    private class MySpliterator implements Spliterator<String>{
        private int start;
        private int end;

        public MySpliterator() {
            this.start = 0;
            this.end =MySpliteratorText.this.data.length - 1 ;
        }
        public MySpliterator(int start, int end) {
            this.start = start;
            this.end = end;
        }
        /**
         * @author Mr.BULLET
         * fork/join 中对应的实现功能
         * 但具体实现是通过用户重写accept方法实现的
         */
        @Override
        public boolean tryAdvance(Consumer<? super String> action) {
            System.out.println("MySpliterator.tryAdvance");
            //相当于循环调用,每次处理其中一部分
            if(start<=end){
                action.accept(MySpliteratorText.this.data[start++]);
                return true;
            }
            return false;
        }

        /**
         * @author Mr.BULLET
         * fork/join  中对应的拆分功能
         * 并行时才调用,否则只调用上面的方法
         */
        @Override
        public Spliterator<String> trySplit() {
            System.out.println("MySpliterator.trySplit");
            int mid = (end - start) / 2;
            if (mid <= 1){
                return null;
            }
            int left = start ;
            int right = start + mid ;
            this.start = start + mid + 1;
            return new MySpliterator(left,right);
        }

        @Override
        public long estimateSize() {
            return end - start;
        }
        /**
         * @author Mr.BULLET
         * 特征值
         */
        @Override
        public int characteristics() {
            //IMMUTABLE : 结构不可变的,无法添加或者减少
            //SIZED : 有限个数据

            return Spliterator.IMMUTABLE|Spliterator.SIZED|Spliterator.SUBSIZED;
        }
    }


    public static void main(String[] args) {
        String str = "SELECT customer_id\n" +
                "FROM fact_pbc_zx_repayment\n" +
                "WHERE repayment_status = 3 AND fact_repay > 0 AND customer_id IN (SELECT DISTINCT customer_id \n" +
                "FROM `fact_pbc_zx_repayment`\n" +
                "WHERE is_out_of_date = 1)";
        MySpliteratorText my = new MySpliteratorText(str);
//        long count = my.stream().count();
//        System.out.println(count);
        Optional<String> reduce = my.stream().map(s -> s.split(" "))
                .flatMap(array -> Arrays.stream(array))
                .reduce((s1, s2) -> s1 + s2);
        System.out.println(reduce.get());

    }
}
``` 

# LocalDate
```java
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2019,5,4);
        int dayOfYear = localDate.getDayOfYear();
        int i = localDate.get(ChronoField.DAY_OF_YEAR);
        assert dayOfYear == i;
    }
```
- withXXX : 用來设置时间
```java
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2019,5,4);
        //重置時間
        localDate = localDate.withYear(2020).withMonth(4);
        System.out.println(localDate);
    }
```
- 使用LocalDateTime 结合 LocalDate 和 LocalTime
```java
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2019,5,4);
        LocalTime localTime = LocalTime.now();
        LocalDateTime of = LocalDateTime.of(localDate, localTime);
        System.out.println(of);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }
```
- Duration 和 Instant 和 Period
    - Instant 时间点
    - Duration 时间段,区间  时分秒
    - Period 大的时间段 年月天
    - between : 用来计算两个时间点之间的时间差,后面的 - 前面的 ,可以算出负数
    - toXXXX : 转换单位
    - getXXXX : 获取某个单位上的值
    ```java
        public static void main(String[] args) {
            Instant start = Instant.now();
            try {
                TimeUnit.MILLISECONDS.sleep(1001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Instant end = Instant.now();
            System.out.println(Duration.between(start,end).toNanos()); //将时间段的单位转成某个单位
            System.out.println(Duration.between(start,end).getSeconds()); //只获取时间段的秒部分数据
            System.out.println(Duration.between(start,end).getNano()); //只获取时间段的纳秒部分数
            System.out.println(Duration.between(start,end).getUnits());

            Period between = Period.between(
                    LocalDate.of(2011, 1, 1),
                    LocalDate.of(2012, 1, 1)
            );
            System.out.println(between.getDays());//只获取天部分数据
            System.out.println(between.getYears());//只获取年部分数据
            System.out.println(between.getUnits());
        }
    ```
- format
```java
    public static void main(String[] args) {
        LocalDate of = LocalDate.of(2011, 1, 1);
        System.out.println(of.format(DateTimeFormatter.ISO_LOCAL_DATE)); //2011-01-01
        /*------ 等价于 --------*/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(of.format(dtf));
    }
```
- parse
```java
    public static void main(String[] args) {
        String string = "2020-05-06";
        LocalDate parse = LocalDate.parse(string, DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(parse);
        /*------- 等价于 ------------*/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(LocalDate.parse(string, dtf));
    }
```



