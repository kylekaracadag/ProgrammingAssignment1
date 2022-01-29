# Kyle Karacadag COP4520 Programming Assignment 1

## Instructions for running the program
 - Download all the files in this repository and put them in a folder
 - Open your command prompt and change the directory to the folder you created
 - Type "javac Threading.java" in the command prompt
 - Type "java Threading" in the command prompt
 - The output will be displayed in the "primes.txt" text file

## Documentation:
 - To get all the primes in the range I used the prime of sieve algorithm. The algorithm creates a boolean array filled with false values initially between 2 to n. Then we slowly mark each composite in the array and the remaining unmarked numbers are the prime numbers. We mark the composites by getting a number and marking all the multiples of that number. Then we move onto the next unmarked number and get the multiples of it and repeat the process. This algorithm is very efficient so I decided not to use threads on marking the numbers.
 - I used threads to go through the marked boolean array and extract the prime numbers. I created 8 blocks of ranges that split the boolean array equally into 8 parts. Then I put the ranges into an object and passed each object into a thread to iterate through the boolean array in a given range. Each thread increments an atomic type integer to get the count of primes, atomic type of long for the total of the primes. The 8th thread performs an extra function where the primes are added to an arraylist so I can extract the 10 largest primes at the end. The values gotten by each thread for the prime count and the sum are added into their respective variables (primes, sum) so we can get a final result.
 - The algorithm without using threads took 1.37 seconds on average to execute. With using 8 threads it took 0.98 seconds which shows about 28.5% decrease in execution time.

### References:
Prime of Sieve: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes <br/>
Java Threading: https://www.tutorialspoint.com/java/java_multithreading.htm
