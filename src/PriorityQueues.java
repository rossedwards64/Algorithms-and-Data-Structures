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
        System.out.println("Link: " + Arrays.toString(link));
        insertAt(0, "EEE", 4);
        System.out.println("Link: " + Arrays.toString(link));
        insertAt(1, "GGG", 5);
        System.out.println("Link: " + Arrays.toString(link));
        insertAt(2, "CCC", 2);
        System.out.println("Link: " + Arrays.toString(link));
        insertAt(3, "DDD", 4);
        System.out.println("Link: " + Arrays.toString(link));
        insertAt(4, "BBB", 2);
        System.out.println("Link: " + Arrays.toString(link));
        insertAt(5, "FFF", 4);
        System.out.println("Link: " + Arrays.toString(link));
        insertAt(6, "AAA", 1);
        System.out.println("Link: " + Arrays.toString(link));
        printJobs();
        System.out.println("Storage after filling queue: " + Arrays.toString(storage));
        sortJobs();
        printJobs();
        System.out.println("Link: " + Arrays.toString(link));
//        System.out.println("Link: " + Arrays.toString(link));
//        removeFrom(0);
//        printJobs();
//        System.out.println("Storage after removal: " + Arrays.toString(storage));
//        System.out.println("Link: " + Arrays.toString(link));
//        removeFrom(1);
//        printJobs();
//        System.out.println("Storage after removal: " + Arrays.toString(storage));
//        System.out.println("Link: " + Arrays.toString(link));
//        removeFrom(2);
//        printJobs();
//        System.out.println("Storage after removal: " + Arrays.toString(storage));
//        System.out.println("Link: " + Arrays.toString(link));
    }

    static void createQueue() {
        front = -1;
        rear = -1;
    }

    static void printJobs() {
        for(int i = 0; i < data.length; i++) {
            if(data[i] != null && priority[i] != 0) {
                if(i != data.length - 1) {
                    System.out.print(priority[i] + ": " + data[i] + " -> ");
                } else {
                    System.out.print(priority[i] + ": " + data[i]);
                }
            }
        }
        System.out.println("\n");
    }

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
        if(isEmpty()) {
            return 0;
        }

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

    static void init(int n) {
        for(int i = 0; i < n; i++) {
            if(i != max) {
                storage[i] = i + 1;
            }
            link[i] = -1;
        }
    }

    // before adding element to queue, check if the priority level
    // is higher or lower than the element that is already there
    // if it is higher, then shift all elements after it one to the right
    // if it lower, move onto the next element and do the check again
    //
    static void insertAt(int position, String description, int priorityLvl) {
        int pointer;
        int index = 0;
        int newPointer = -1;

        if(isEmpty()) {
            front = getNode();
            data[front] = description;
            priority[front] = priorityLvl;
            link[front] = 1;
        } else if(isFull()) {
            System.out.println("Queue is full.");
        } else {
            pointer = front;
            while(index != position) {
                pointer = link[pointer];
                index++;
            }
            data[pointer] = description;
            priority[pointer] = priorityLvl;
            newPointer = getNode();
            link[pointer] = newPointer;
        }
        rear = newPointer;
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

    // sort data and link arrays to correspond
    // to priority array
    static void sortJobs() {
        int tempPriority;
        String tempData;
        int tempLink;

        for (int i = 0; i < priority.length; i++) {
            for (int j = i + 1; j < priority.length; j++) {
                if(priority[i] > priority[j]) {
                    tempPriority = priority[i];
                    tempData = data[i];
                    tempLink = link[i];

                    priority[i] = priority[j];
                    data[i] = data[j];
                    link[i] = link[j];

                    priority[j] = tempPriority;
                    data[j] = tempData;
                    link[j] = tempLink;
                }
            }
        }
    }

    static boolean isEmpty() {
        return front == -1;
    }

    static boolean isFull() {
        return rear == max;
    }
}
