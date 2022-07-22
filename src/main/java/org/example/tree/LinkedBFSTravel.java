package org.example.tree;

import org.example.tree.util.LinkedTreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedBFSTravel {

        public void connect(LinkedTreeNode root) {
            Queue<LinkedTreeNode> queue = new LinkedList<LinkedTreeNode>();

            queue.add(root);
            queue.add(null);
            while(!queue.isEmpty()) {
                LinkedTreeNode temp = queue.peek();
                queue.poll();

                // Adding level flag and end condition
                if(temp == null && queue.isEmpty())
                    continue;
                if(temp == null && !queue.isEmpty()){
                    queue.add(null);
                    continue;
                }

                temp.setNext(queue.peek());

                if(temp.getLeft() != null)
                    queue.add(temp.getLeft());
                if(temp.getRight() != null)
                    queue.add(temp.getRight());
            }
        }
}
