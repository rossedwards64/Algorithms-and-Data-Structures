import java.util.Arrays;

class PriorityQueues {
    static int[] priority = new int[7];
    static String[] data = new String[7];
    static int[] link = new int[8];
    static int[] storage = new int[7];

    static int front;
    static int rear;
    static int max = storage.length;

    // if element in list is null, do not display it
    public static void main(String[] args) {
        createQueue();
        init(8);
        System.out.println("Storage before filling queue: " + Arrays.toString(storage));
        insert("EEE", 4);
        insert("GGG", 5);
        insert("CCC", 2);
        insert("DDD", 4);
        insert("BBB", 2);
        insert("FFF", 4);
        insert("AAA", 1);
        printJobs();
//        removeFrom(0);
//        removeFrom(1);
//        removeFrom(2);
//        printJobs();
    }

    static void createQueue() {
        front = -1;
        rear = -1;
    }

    static void init(int n) {
        for(int i = 0; i < n; i++) {
            if(i != max) {
                storage[i] = i + 1;
            }
            link[i] = -1;
        }
    }

    static void printJobs() {
        int currentPtr = front;
        System.out.println();
        do {
            System.out.print(priority[currentPtr] + ": " + data[currentPtr] + " -> ");
            currentPtr = link[currentPtr];
        } while (link[currentPtr] != -1);
        System.out.print(priority[rear] + ": " + data[rear]);
    }

    // value from getNode is the location where the node will be
    // stored in the arrays
    static int getNode() {
        // get the node from the first index
        int node = storage[0];
        for(int i = 0; i + 1 < max; i++) {
            // copy the next index to the current index
            // to shift the indexes left
            storage[i] = storage[i + 1];
        }
        // because an index has been removed, set the final
        // element in storage to 0
        storage[max - 1] = -1;

        // subtract one to account for indexes counting
        // from zero
        return node;
    }

    static void retNode(int node) {
        // shift all elements to the right
        for(int i = max - 2; i >= 0; i--) {
            storage[i + 1] = storage[i];
        }
        // put node in first element
        storage[0] = node;
    }

//                // set pointer to the position that the node will
//                // be inserted into
//                pointer = getNode();
//
//                // insert node into all arrays at the
//                // specified position
//                link[pointer - 1] = pointer;
//                link[pointer] = pointer + 1;
//                data[pointer - 1] = description;
//                priority[pointer - 1] = priorityLvl;

    // before adding element to queue, check if the priority level
    // is higher or lower than the element that is already there
    // if it is higher, then shift all elements after it one to the right
    // if it lower, move onto the next element and do the check again

    // 2 checks: 1 to find if it's empty
    // traverse to

    // 1. check if full
    // 2. if not, check if empty, if so then fill front
    // 3. if not empty, then create a temporary pointer equal to front
    // 4. check if priority is greater than or equal to front of priority
    // 5. create a temporary pointer equal to front
    // 6. create a current pointer equal to front of link
    // 7. while front of link is not -1, and priority is greater than or equal to current pointer
    // 8. set temporary pointer to front of link in loop
    // 9. if front of link is -1, then set front of link to getnode - 1, then the link after the front to -1, and set rear to value after front
    // 10. if not, set index after front of link to front of link, and front of link to front after link
    // 11. if priority is not greater than or equal to front of priority, set the index after front to front
    // 12. set front to index after front

    // pointer = getnode() - 1
    // tempptr = front
    static void insert(String description, int priorityLvl) {
        if(isFull()) {
            System.out.println("Queue is full.");
        } else {
            int pointer = getNode() - 1;
            data[pointer] = description;
            priority[pointer] = priorityLvl;
            int currentPtr = front;

            if(isEmpty()) {
                front = pointer;
                link[front] = -1;
                rear = pointer;
            } else {
                int nextPtr = link[currentPtr];

                if (priorityLvl >= priority[currentPtr]) {
                    while (nextPtr != -1 && priorityLvl >= priority[nextPtr]) {
                        currentPtr = link[currentPtr];
                        nextPtr = link[currentPtr];
                    }

                    if (nextPtr == -1) {
                        link[currentPtr] = pointer;
                        link[pointer] = -1;
                        rear = pointer;
                    } else {
                        link[pointer] = nextPtr;
                        link[currentPtr] = pointer;
                    }

                } else {
                    link[pointer] = front;
                    front = pointer;
                }
            }
        }
    }

    // data and priority need to be set to null
    // then any elements after need to be shifted
    // to the left so there is no null
    static void removeFrom(int position) {
        int index = 1;

        // add one to account for indexes counting from zero
        // this is to avoid returning 0 to storage instead
        // of 1 when removing the first element, for example
        int pointer = front + 1;

        if(position == 0) {
            front = link[front];
            retNode(pointer);
        } else {
            while(index != position) {
                rear = pointer;
                pointer = link[pointer];
                index++;
            }
            retNode(pointer);
            if(pointer == 0) {
                link[rear] = -1;
            } else {
                link[rear] = link[pointer];
            }
        }
    }

    static void peek() {
        System.out.println(priority[front] + ": " + data[front]);
    }

    static boolean isEmpty() {
        return front == -1;
    }

    static boolean isFull() {
        return rear == max;
    }
}
