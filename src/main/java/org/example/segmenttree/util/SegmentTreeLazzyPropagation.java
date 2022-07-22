package org.example.segmenttree.util;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SegmentTreeLazzyPropagation<T, Type> extends SegementTree<T, Type> {
    ArrayList<T> lazzy;

    public SegmentTreeLazzyPropagation(int size, Function getSegmentTreeValue, BiFunction joinValueOfSegment, BiFunction updateValueOfSegment) {
        super(size, getSegmentTreeValue, joinValueOfSegment, updateValueOfSegment, null);
        lazzy = new ArrayList<>(2 * size + 1);
        for(int i = 0; i < 2 * size + 1; i ++)
            lazzy.add(null);
    }

    @Override
    public T executeQuery(int index, int start, int end, int queryStart, int queryEnd) {

        //Propagate previous updates
        propagate(index, start, end);

        // segement overlap
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
     *Method to update perticular value at index
     */
    @Override
    public void updateQuery(int index, int start, int end, int updateIndex, Type val){
        //Propagate previous updates
        propagate(index, start, end);

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
    public void updateSegement1(int index, int start, int end, int startUpdateIndex, int endUpdateIndex, Type val){
        // segement overlap
        if(start >= startUpdateIndex && end <= endUpdateIndex) {
            lazzy.set(index, getSegmentTreeValue.apply(val));
            propagate(index, start, end);
            return;
        }
        propagate(index, start, end);
        int mid = (start + end) / 2;
        if(endUpdateIndex <= mid){
            updateSegement(2 * index + 1, start,  mid, startUpdateIndex, endUpdateIndex, val);
            return ;
        }
        if(startUpdateIndex > mid) {
            updateSegement(2 * index + 2, mid + 1, end, startUpdateIndex, endUpdateIndex, val);
            return ;
        }
        updateSegement(2 * index + 1, start,  mid, startUpdateIndex, mid, val);
        updateSegement(2 * index + 2, mid + 1, end, mid + 1, endUpdateIndex, val);
        seg.set(index, joinValueOfSegment.apply(seg.get(2 * index + 1), seg.get(2 * index + 2)));
    }

    private void propagate(int index, int start, int end){
            if(lazzy.get(index) != null) {
                if(start != end) { // Ellimate leaf node from propagation
                    lazzy.set(2 * index + 1, updateValueOfSegment.apply(lazzy.get(2 * index + 1),  lazzy.get(index)));
                    lazzy.set(2 * index + 2, updateValueOfSegment.apply(lazzy.get(2 * index + 2),  lazzy.get(index)));
                }

                // update segement tree
                seg.set(index, updateValueOfSegment.apply(seg.get(index), lazzy.get(index)));
                lazzy.set(index, null);
            }
    }
}
