package com.veontomo;

import java.util.LinkedList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        final int size = 100000;
        final int pivot = size + 1;
        List<Integer> list1 = createList(size);
        List<Integer> list2 = createList(size + 1);
        System.out.println("" + pivot + " is " + (list1.contains(pivot) ? "" : "not") + " present in the list.");
        System.out.println("List2 minus list1 contains " + minus(list2, list1).size() + " elements." );
    }

    static private List<Integer> createList(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            result.add(i);
        }
        return result;
    }

    static private List<Integer> minus(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new LinkedList<>();
        for (int i : list1){
            if (!list2.contains(i)){
                result.add(i);
            }
        }
        return result;
    }


}
