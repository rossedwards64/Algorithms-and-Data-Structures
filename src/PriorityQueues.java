import java.util.Arrays;

class PriorityQueues {
    static int[] priority = new int[8];
    static String[] data = new String[8];
    static int[] link = new int[8];
    static int[] storage = new int[8];

    static int front;
    static int rear;
    static int max = storage.length;

    // if element in list is null, do not display it
    public static void main(String[] args) {
        createQueue();
        init(8);
        insertAt(0, "EEE", 4);
        insertAt(1, "GGG", 5);
        insertAt(2, "CCC", 2);
        insertAt(3, "DDD", 4);
        insertAt(4, "BBB", 2);
        insertAt(5, "FFF", 4);
        insertAt(6, "AAA", 1);
        printJobs();
    }

    static void printJobs() {
        for(int i = 0; i < data.length; i++) {
            if(data[i] != null && priority[i] != 0) {
                System.out.print(priority[i] + ":" + data[i] + " -> ");
            }
        }
    }

    static void createQueue() {
        front = -1;
        rear = -1;
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
        storage[max - 1] = 0;
        if(isEmpty()) {
            return 0;
        }
        return node;
    }

    static void init(int n) {
        for(int i = 0; i < n; i++) {
            storage[i] = i + 1;
        }
    }

    static void insertAt(int position, String description, int priorityLvl) {
        int pointer;
        int index = 0;
        String tempData;
        int tempPriority, tempPointer;
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

            tempData = data[pointer];
            tempPriority = priority[pointer];
            tempPointer = link[pointer];

            data[pointer] = description;
            priority[pointer] = priorityLvl;
            newPointer = getNode();
            link[pointer] = newPointer;

            data[newPointer] = tempData;
            priority[newPointer] = tempPriority;
            link[newPointer] = tempPointer;
        }
        rear = newPointer;
    }

    // put retNode() somewhere in here
    static void removeFrom(int position) {
        int counter = 1;
        int pointer = front;

        if(position == 1) {
            front = link[front];
        } else {
            while(counter != position) {
                rear = pointer;
                pointer = link[pointer];
                counter++;
            }
            if(pointer == 0) {
                link[rear] = 0;
            } else {
                link[rear] = link[pointer];
            }
        }
    }

    static boolean isEmpty() {
        return front == -1;
    }

    static boolean isFull() {
        return rear == max - 1;
    }
}
