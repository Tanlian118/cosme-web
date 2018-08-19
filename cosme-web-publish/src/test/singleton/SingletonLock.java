package singleton;

/**
 * 双重校验锁
 * @author Tanlian
 * @create 2018-08-17 11:09
 **/

/*
第一次校验：单例模式只需要创建一次实例，如果后面再次调用getInstance方法时，则直接返回之前创建的实例，
因此大部分时间不需要执行同步方法里面的代码，大大提高了性能。如果不加第一次校验的话，和基本的懒汉模式没什么区别，每次都要去竞争锁。
第二次校验：如果没有第二次校验，假设线程t1执行了第一次校验后，判断为null，这时t2也获取了CPU执行权，
也执行了第一次校验，判断也为null。接下来t2获得锁，创建实例。这时t1又获得CPU执行权，由于之前已经进行了第一次校验，
结果为null（不会再次判断），获得锁后，直接创建实例。结果就会导致创建多个实例。所以需要在同步代码里面进行第二次校验，
如果实例为空，则进行创建。
 */

public class SingletonLock {

    private volatile static SingletonLock instance;

    private SingletonLock(){}

    public static SingletonLock getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new SingletonLock();
                }
            }
        }
        return instance;
    }
}
