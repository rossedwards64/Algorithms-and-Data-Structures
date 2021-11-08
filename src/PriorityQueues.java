public class PriorityQueues {

    public static void displayQueue() {
        PriorityQueues pq = new PriorityQueues();
        PriorityQueues.Node node;

        node = pq.createNode("EEE", 4);
        node = pq.push(node, "GGG", 5);
        node = pq.push(node, "CCC", 2);
        node = pq.push(node, "DDD", 4);
        node = pq.push(node, "BBB", 2);
        node = pq.push(node, "FFF", 4);
        node = pq.push(node, "AAA", 1);

        System.out.print("\n");
        while(!pq.isEmpty(node)) {
            System.out.print("Priority " + node.nodePriority + ": " + pq.peek(node) + " \n");
            node = pq.pop(node);
        }
    }

    // initialise data members of node
    static class Node {
        String nodeData;
        int nodePriority;
        Node nextNode;
    }

    // next node is null because this is the first node
    Node createNode(String initData, int initPriority) {
        Node tempNode = new Node();
        tempNode.nodeData = initData;
        tempNode.nodePriority = initPriority;
        tempNode.nextNode = null;
        return tempNode;
    }

    // get value of the head of the list without removing it
    String peek(Node nodeHead) {
        return nodeHead.nodeData;
    }

    Node push(Node nodeHead, String initData, int initPriority) {
        Node startNode = nodeHead;
        Node tempNode = createNode(initData, initPriority);

        // if the priority of the node head is greater than the priority passed in, then the temp node being pushed becomes the new head
        if(nodeHead.nodePriority > initPriority) {
            tempNode.nextNode = nodeHead;
            nodeHead = tempNode;
        } else {

            /* if the priority of the node head is less than the priority passed in, then the current head stays as is
                and the temp node being pushed becomes the next node
               if the priority passed in is more than the next node's priority, then it goes after the next node */

            while(startNode.nextNode != null && startNode.nextNode.nodePriority < initPriority) {
                startNode = startNode.nextNode;
            }
            tempNode.nextNode = startNode.nextNode;
            startNode.nextNode = tempNode;
        }
        return nodeHead;
    }

    // return the head node from the list
    Node pop(Node nodeHead) {
        nodeHead = nodeHead.nextNode;
        return nodeHead;
    }

    boolean isEmpty(Node nodeHead) {
        return nodeHead == null;
    }
}
