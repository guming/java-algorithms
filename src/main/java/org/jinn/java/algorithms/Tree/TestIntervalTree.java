package org.jinn.java.algorithms.Tree;

import java.util.ArrayList;


import com.jinn.algorithms.Tree.IntervalTree;
import com.jinn.algorithms.Tree.IntervalTree.IntervalData;

public class TestIntervalTree {
	public static void main(String[] args) {
		ArrayList<IntervalData<String>> list=new ArrayList<IntervalData<String>>();
		IntervalData<String> id=new IntervalData<String>(30,"30");
		IntervalData<String> id2=new IntervalData<String>(35,"35");
		IntervalData<String> id3=new IntervalData<String>(50,"50");
		IntervalData<String> id4=new IntervalData<String>(130,"130");
		list.add(id);
		list.add(id2);
		list.add(id3);
		list.add(id4);
		IntervalTree it=new IntervalTree(list);
		IntervalData it1= it.query(20, 60);
		System.out.println(it1.toString());
	}
}
