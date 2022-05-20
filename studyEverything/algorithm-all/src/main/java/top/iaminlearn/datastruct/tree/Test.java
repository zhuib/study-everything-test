package top.iaminlearn.datastruct.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		new ArrayDeque<>();


		ArrayList list = new ArrayList();
		list.sort(new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return 0;
			}
		});

		new PriorityQueue<>();

	}

}
