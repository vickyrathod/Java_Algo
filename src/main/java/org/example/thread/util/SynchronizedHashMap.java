package org.example.thread.util;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;
import java.util.function.Function;

/*
This class for multiple read and single Write at a instance.
It is always reccomonded to use concurrent hashmap than this
 */
public class SynchronizedHashMap<K, V>  extends HashMap<K, V> {

    //Shared Read/WriteLock
    ReentrantReadWriteLock RWL = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = RWL.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = RWL.writeLock();

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        readLock.lock();
        // To avoid abandant of lock
        // Always keep the creatical section in try block
        V v = null;
        try{
            v = super.getOrDefault(key, defaultValue);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        readLock.unlock();
        return v;
    }

    @Override
    public V get(Object key) {
        readLock.lock();
        V v = null;
        // To avoid abandant of lock
        // Always keep the creatical section in try block
        try{
            v = super.get(key);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        readLock.unlock();
        return v;
    }

    @Override
    public V put(K key, V value) {
        writeLock.lock();
        V v = null;
        // To avoid abandant of lock
        // Always keep the creatical section in try block
        try{
            v = super.put(key, value);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        writeLock.unlock();

        return v;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        writeLock.lock();
        V v = null;
        // To avoid abandant of lock
        // Always keep the creatical section in try block
        try{
            v = super.putIfAbsent(key, value);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        writeLock.unlock();
        return v;
    }

    @Override
    public boolean remove(Object key, Object value) {
        writeLock.lock();
        boolean b = false;
        // To avoid abandant of lock
        // Always keep the creatical section in try block
        try{
            b = super.remove(key, value);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        writeLock.unlock();
        return b;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        writeLock.lock();
        boolean b = false;
        // To avoid abandant of lock
        // Always keep the creatical section in try block
        try{
            b = super.replace(key, oldValue, newValue);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        writeLock.unlock();
        return b;
    }

    @Override
    public V replace(K key, V value) {
        writeLock.lock();
        V v = null;
        // To avoid abandant of lock
        // Always keep the creatical section in try block
        try{
            v = super.replace(key, value);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        writeLock.unlock();
        return v;
    }

    @Override
    public V computeIfAbsent(K key,
                             Function<? super K, ? extends V> mappingFunction) {
        writeLock.lock();
        V v = null;
        // To avoid abandant of lock
        // Always keep the creatical section in try block
        try{
            v = super.computeIfAbsent(key, mappingFunction);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        writeLock.unlock();
        return v;
    }

    @Override
    public V computeIfPresent(K key,
                              BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        writeLock.lock();
        V v = null;
            // To avoid abandant of lock
            // Always keep the creatical section in try block
            try{
                v = super.computeIfPresent(key, remappingFunction);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        writeLock.unlock();
        return v;
    }

    @Override
    public V compute(K key,
                     BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        writeLock.lock();
        V v = null;
                // To avoid abandant of lock
                // Always keep the creatical section in try block
                try{
                    v = super.compute(key, remappingFunction);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
        writeLock.unlock();
        return v;
    }
}
