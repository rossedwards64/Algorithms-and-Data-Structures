import java.util.Arrays;

class PriorityQueues {
    static int[] priority = new int[7];
    static String[] data = new String[7];
    static int[] link = new int[8];
    static int[] storage = new int[7];

    static int front;
    static int rear;
    static int max = storage.length;

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
        peek();
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

    static int getNode() {
        int node = storage[0];
        for(int i = 0; i + 1 < max; i++) {
            storage[i] = storage[i + 1];
        }
        storage[max - 1] = -1;

        return node;
    }

    static void retNode(int node) {
        for(int i = max - 2; i >= 0; i--) {
            storage[i + 1] = storage[i];
        }
        storage[0] = node;
    }


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

    static void removeFrom(int position) {
        int index = 1;

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
        System.out.println();
        System.out.println(priority[front] + ": " + data[front]);
    }

    static boolean isEmpty() {
        return front == -1;
    }

    static boolean isFull() {
        return rear == max;
    }
}
