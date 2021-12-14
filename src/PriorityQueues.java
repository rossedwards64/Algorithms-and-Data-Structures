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
        insert("EEE", 4);
        System.out.println();
        printJobs();
        insert("GGG", 5);
        System.out.println();
        printJobs();
        insert("CCC", 2);
        System.out.println();
        printJobs();
        insert("DDD", 4);
        System.out.println();
        printJobs();
        insert("BBB", 2);
        System.out.println();
        printJobs();
        insert("FFF", 4);
        System.out.println();
        printJobs();
        insert("AAA", 1);
        System.out.println();
        printJobs();

        remove();
        System.out.println();
        printJobs();
        remove();
        System.out.println();
        printJobs();
        remove();
        System.out.println();
        printJobs();
        remove();
        System.out.println();
        printJobs();
        remove();
        System.out.println();
        printJobs();
        remove();
        System.out.println();
        printJobs();
    }

    static void createQueue() {
        front = -1;
        rear = -1;
    }

    static void init(int n) {
        for(int i = 0; i < n; i++) {
            if(i != max) {
                storage[i] = i;
            }
            link[i] = -1;
        }
    }

    static void printJobs() {
        int currentPtr = front;
        while (link[currentPtr] != -1) {
            System.out.print(priority[currentPtr] + ": " + data[currentPtr] + " -> ");
            currentPtr = link[currentPtr];
        }
        System.out.print(priority[rear] + ": " + data[rear]);
    }

    static int getNode() {
        if(storage[0] == -1) {
            System.out.println("No more nodes!");
            return -1;
        } else {
            int node = storage[0];
            for (int i = 0; i + 1 < max; i++) {
                storage[i] = storage[i + 1];
            }
            storage[max - 1] = -1;

            return node;
        }
    }

    static void retNode(int node) {
        if(storage[max - 1] != -1) {
            System.out.println(" \nStorage pool is full!.");
        } else {
            storage[0] = node;

            for (int i = max - 2; i >= 0; i--) {
                storage[i + 1] = storage[i];
            }
        }
    }


    static void insert(String description, int priorityLvl) {
        if(isFull()) {
            System.out.println("Queue is full.");
        } else {
            int pointer = getNode();
            data[pointer] = description;
            priority[pointer] = priorityLvl;
            int currentPtr = front;

            if(isEmpty()) {
                front = pointer;
                link[front] = -1;
                rear = pointer;
            } else {
                int start = link[currentPtr];

                if (priorityLvl >= priority[currentPtr]) {
                    while (start != -1 && priorityLvl >= priority[start]) {
                        currentPtr = link[currentPtr];
                        start = link[currentPtr];
                    }

                    if (start == -1) {
                        link[currentPtr] = pointer;
                        link[pointer] = -1;
                        rear = pointer;
                    } else {
                        link[pointer] = start;
                        link[currentPtr] = pointer;
                    }

                } else {
                    link[pointer] = front;
                    front = pointer;
                }
            }
        }
    }

    static void remove() {
        if(isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            if(link[front] != -1) {
                front = link[front];
                retNode(front);
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
