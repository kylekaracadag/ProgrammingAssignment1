import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.io.File;  
import java.io.PrintWriter;  

class Counter extends Thread {
    AtomicInteger numPrimes;
    AtomicLong sum;
    boolean[] numbers;
    List<Integer> list;

    public Counter(boolean[] numbers) {
        numPrimes = new AtomicInteger();
        sum = new AtomicLong();
        this.numbers = numbers;
        list = new ArrayList<Integer>();
    }

    public void run() {
        int lowerBound = 0;
        int upperBound = 0;
        boolean flag = false;

        if (Thread.currentThread().getName().equals("One")) {
            lowerBound = 1;
            upperBound = 12500000;
        }
        if (Thread.currentThread().getName().equals("Two")) {
            lowerBound = 12500000;
            upperBound = 25000000;
        }
        if (Thread.currentThread().getName().equals("Three")) {
            lowerBound = 25000000;
            upperBound = 37500000;
        }
        if (Thread.currentThread().getName().equals("Four")) {
            lowerBound = 37500000;
            upperBound = 50000000;
        }
        if (Thread.currentThread().getName().equals("Five")) {
            lowerBound = 50000000;
            upperBound = 62500000;
        }
        if (Thread.currentThread().getName().equals("Six")) {
            lowerBound = 62500000;
            upperBound = 75000000;
        }
        if (Thread.currentThread().getName().equals("Seven")) {
            lowerBound = 75000000;
            upperBound = 87500000;
        }
        if (Thread.currentThread().getName().equals("Eight")) {
            lowerBound = 87500000;
            upperBound = 100000000;
            flag = true;
        }
        
        for (int i = lowerBound; i < upperBound; i++) {
            if (numbers[i]) {
                if (flag)
                    list.add(i);

                sum.getAndAdd(i);
                numPrimes.getAndIncrement(); 
            }
        }
    }
}


public class Threading {
    static int num = 100000000;

    public static void main(String [] args) throws InterruptedException, IOException {
        long startTime = System.nanoTime();
        boolean[] numbers = new boolean[num + 1];
        Arrays.fill(numbers, true);
        
        for (int i = 2; i * i <= num; i++) {
            if (numbers[i]) {
                for (int j = i * i; j <= num; j += i)
                    numbers[j] = false;
            }
        }

        Counter c = new Counter(numbers);
        Thread threadOne = new Thread(c, "One");
        Thread threadTwo = new Thread(c, "Two");
        Thread threadThree = new Thread(c, "Three");
        Thread threadFour = new Thread(c, "Four");
        Thread threadFive = new Thread(c, "Five");
        Thread threadSix = new Thread(c, "Six");
        Thread threadSeven = new Thread(c, "Seven");
        Thread threadEight = new Thread(c, "Eight");

        threadOne.start();
        threadTwo.start();
        threadThree.start();
        threadFour.start();
        threadFive.start();
        threadSix.start();
        threadSeven.start();
        threadEight.start();

        threadOne.join();
        threadTwo.join();
        threadThree.join();
        threadFour.join();
        threadFive.join();
        threadSix.join();
        threadSeven.join();
        threadEight.join();

        int numPrimes = c.numPrimes.intValue();
        long sum = c.sum.longValue();

        // Output the results
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        double totalTimeSeconds = (double) totalTime / 1_000_000_000;
        
        PrintWriter writer = new PrintWriter("primes.txt", "UTF-8");
        writer.println("Execution Time (seconds): " + totalTimeSeconds + 
            " Total Number: " + (numPrimes-1) +
            " Sum: " + (sum-1));
        
        writer.print("Top Ten Maximum Primes: ");
        int startIndex = c.list.size() - 10;
        for (int i = startIndex; i < c.list.size(); i++)
            writer.print(c.list.get(i) + " ");
        writer.close();
    }
}