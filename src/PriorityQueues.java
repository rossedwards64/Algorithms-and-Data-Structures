import java.util.Arrays;

public class PriorityQueues {

    // holds data
    static int[] data = new int[10];
    // holds pointer to the next data element
    static int[] link = new int[10];
    // holds priority of data
    static int[] priority = new int[10];

    // data could be a two-dimensional array that contains both data and priority

    static int front;
    static int rear;
    static int pointer;
    static int max = data.length;

    public static void displayQueue() {
        init(10);
        addQueue(1);
        System.out.println(Arrays.toString(link));
        System.out.println(Arrays.toString(data));
        removeQueue(link);
        System.out.println(Arrays.toString(link));
    }

    static void init(int n) {
        for(int i = 0; i < n - 1; i++) {
            link[i] = i + 1;
        }
        link[n - 1] = -1;
        System.out.println(Arrays.toString(link));
        pointer = 0;
    }

    static void addQueue(int item) {
        if(isFull(rear)) {
            System.out.println("Queue is full.");
        } else {
            getNode(item);
            data[pointer] = item;
            link[pointer] = 0;

            if(isEmpty(front)) {
                front = pointer;
            } else {
                link[rear] = pointer;
            }
            rear = pointer;
        }
    }

    static void removeQueue(int[] link) {
        if(isEmpty(front)) {
            System.out.println("Queue is empty.");
        } else {
            if(front == rear) {
                int item = data[front];
                pointer = front;
                retNode(pointer);
                front = link[front];
            }
        }
    }

    static boolean isFull(int rear) {
        return rear == max;
    }

    // if the front is null, then the queue is empty
    static boolean isEmpty(int front) {
        return front == 0;
    }

    // gets a node from the storage pool
    static void getNode(int x) {
        if(pointer == 0) {
            System.out.println("There are no more nodes.");
        }
        x = pointer;
        pointer = link[pointer];
    }

    // returns node to storage pool
    static void retNode(int x) {
        link[x] = pointer;
        pointer = x;
    }
}
