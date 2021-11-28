public class PriorityQueues {

    public static void displayQueue() {
        PriorityQueues pq = new PriorityQueues();
        PriorityQueues.Node node;

        // data set given in the assignment brief
        /*
        Nodes should be printed in descending order of priority
         */
        {
            node = pq.createNode("EEE", 4);
            node = pq.push(node, "GGG", 5);
            node = pq.push(node, "CCC", 2);
            node = pq.push(node, "DDD", 4);
            node = pq.push(node, "BBB", 2);
            node = pq.push(node, "FFF", 4);
            node = pq.push(node, "AAA", 1);

            System.out.print("\n");
            while (!pq.isEmpty(node)) {
                // prints node before popping off the list
                System.out.print("Priority " + node.nodePriority + ": " + pq.peek(node) + " \n");
                node = pq.pop(node);
            }

            /*
            Once all nodes are printed, output will look like this:
            Priority 1: AAA
            Priority 2: CCC
            Priority 2: BBB
            Priority 4: FFF
            Priority 4: DDD
            Priority 4: EEE
            Priority 5: GGG
             */
        }

        // alternate data set to test algorithm effectiveness
        // new data added and some data of existing nodes swapped around
        {
            node = pq.createNode("CCC", 4);
            node = pq.push(node, "BBB", 5);
            node = pq.push(node, "DDD", 2);
            node = pq.push(node, "EEE", 4);
            node = pq.push(node, "GGG", 2);
            node = pq.push(node, "AAA", 4);
            node = pq.push(node, "FFF", 1);
            node = pq.push(node, "HHH", 3);
            node = pq.push(node, "III", 5);
            node = pq.push(node, "JJJ", 3);
            node = pq.push(node, "KKK", 1);

            System.out.print("\n");
            while (!pq.isEmpty(node)) {
                // prints node before popping off the list
                System.out.print("Priority " + node.nodePriority + ": " + pq.peek(node) + " \n");
                node = pq.pop(node);
            }

            /*
            Once all nodes are printed, output should look like this:
            Priority 1: FFF
            Priority 1: KKK
            Priority 2: DDD
            Priority 2: GGG
            Priority 3: JJJ
            Priority 3: HHH
            Priority 4: AAA
            Priority 4: EEE
            Priority 4: CCC
            Priority 5: III
            Priority 5: BBB
             */
        }
    }

    // declare data members of node
    static class Node {
        // value of the node
        String nodeData;
        // what the node's position in the list will be
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
