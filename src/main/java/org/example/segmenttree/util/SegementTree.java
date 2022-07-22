package org.example.segmenttree.util;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;
/*
 * The code is for bulding a segement tree without any update query
 *
 */
public class SegementTree<T, Type> {
    protected ArrayList<T> seg;
    private int size;
    Function<Type,T> getSegmentTreeValue;
    BiFunction<T, T, T> joinValueOfSegment;

    // First argument is segement and second is new value to be added in segement
    BiFunction<T, T, T> updateValueOfSegment;

    //To return function for range update first argument is initial value of range, 2nd is value by it is going to updated
    BiFunction<T, T, T> returnUpdatedValueOfSegment;

    /*
     * Build segment tree accept getSegmentTreeValue function for converting arrayValue to segmentTree Value and
     * joinValueOfSegment function for joining two value of segment tree.
     * joinValueOfSegment is main operation of segement tree.
     */
    public void bulidSegementTree(int index, int start, int end, Type[] arr) {
        if(start == end){
            seg.set(index, getSegmentTreeValue.apply(arr[start]));
            return;
        }
        int mid = (start + end) / 2;
        bulidSegementTree( 2 * index + 1, start, mid, arr);
        bulidSegementTree( 2 * index + 2, mid + 1, end, arr);

        seg.set(index, joinValueOfSegment.apply(seg.get(2 * index + 1), seg.get(2 * index + 2)));
    }
    /*
     * Get the value of a range
     */
    public T executeQuery(int index, int start, int end, int queryStart, int queryEnd){
            if(start >= queryStart && end <= queryEnd)
                return seg.get(index);
            int mid = (start + end) / 2;
            if(queryEnd <= mid)
                return executeQuery(2 * index + 1, start, mid, queryStart, queryEnd);
            if(queryStart > mid)
                return executeQuery(2 * index + 2, mid + 1, end, queryStart, queryEnd);

            return joinValueOfSegment.apply(executeQuery(2 * index + 1, start, mid, queryStart, mid),
                    executeQuery(2 * index + 2, mid + 1, end, mid + 1, queryEnd));
    }

    /*
    Method to update perticular value at index
     */
    public void updateQuery(int index, int start, int end, int updateIndex, Type val){
        if(start == updateIndex && end == updateIndex) {
            seg.set(index, getSegmentTreeValue.apply(val));
            return;
        }
        int mid = (start + end) / 2;
        if(start <= updateIndex && updateIndex <= mid) {
            updateQuery(2 * index + 1, start, mid, updateIndex, val);
            seg.set(index, updateValueOfSegment.apply(seg.get(index), seg.get(2 * index + 1)));
        }

        updateQuery(2 * index + 2, mid + 1, end, updateIndex, val);
        seg.set(index, updateValueOfSegment.apply(seg.get(index), seg.get(2 * index + 2)));
    }
    /*
     * Update perticular range of segementTree
     * As it is without lazzy propagation, The complexity will be O(endUpdateIndex - startUpdateIndex)
     */
    public T updateSegement(int index, int start, int end, int startUpdateIndex, int endUpdateIndex, Type val){
        if(start == end) {
            seg.set(index, updateValueOfSegment.apply(seg.get(index), getSegmentTreeValue.apply(val)));
            return returnUpdatedValueOfSegment.apply(null, getSegmentTreeValue.apply(val));
        }

        int mid = (start + end) / 2;
        if(endUpdateIndex <= mid){
            T t = updateSegement(2 * index + 1, start,  mid, startUpdateIndex, endUpdateIndex, val);
            seg.set(index, updateValueOfSegment.apply(seg.get(index), t));
            return returnUpdatedValueOfSegment.apply(null, t);
        }
        if(startUpdateIndex > mid) {
            T t = updateSegement(2 * index + 2, mid + 1, end, startUpdateIndex, endUpdateIndex, val);
            seg.set(index, updateValueOfSegment.apply(seg.get(index), t));
            return returnUpdatedValueOfSegment.apply(null, t);
        }
        T t1 = updateSegement(2 * index + 1, start,  mid, startUpdateIndex, mid, val);
        T t2 = updateSegement(2 * index + 2, mid + 1, end, mid + 1, endUpdateIndex, val);
        seg.set(index, joinValueOfSegment.apply(seg.get(2 * index + 1), seg.get(2 * index + 2)));
        return returnUpdatedValueOfSegment.apply(t1, t2);
    }

    //Constructor of segment Tree
    public SegementTree(int size, Function<Type,T> getSegmentTreeValue, BiFunction<T, T, T> joinValueOfSegment, BiFunction<T, T, T> updateValueOfSegment,
                        BiFunction<T, T, T> returnUpdatedValueOfSegment){
        this.size = 2 * size + 1;
        seg = new ArrayList<T>();
        for(int i = 0 ; i < this.size; i ++)
            seg.add(null);
        this.getSegmentTreeValue = getSegmentTreeValue;
        this.joinValueOfSegment = joinValueOfSegment;
        this.updateValueOfSegment = updateValueOfSegment;
        this.returnUpdatedValueOfSegment = returnUpdatedValueOfSegment;
    }
}