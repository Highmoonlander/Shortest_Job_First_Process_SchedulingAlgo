# SJF (Shortest Job First) Scheduling Algorithm

This is a Java implementation of the SJF scheduling algorithm, which is a non-preemptive algorithm that schedules processes based on their burst time. The process with the shortest burst time is executed first.

## How to Run

1. Clone or download the repository.
2. Open the project in an IDE (e.g. Eclipse, IntelliJ IDEA).
3. Run the `SJF.java` file.

## Algorithm Details

The SJF algorithm schedules processes based on their burst time, with the shortest job being executed first. It is a non-preemptive algorithm, meaning that once a process starts executing, it continues until completion.

The algorithm works by first sorting the processes by their arrival time. Then, it selects the process with the shortest burst time that has arrived, and executes it until completion. This process is then removed from the list of processes, and the algorithm repeats until all processes have been executed.

## Input

The input to the algorithm is an array of `Process` objects, where each object represents a process and contains the following fields:

- `pid`: process ID
- `arrival_time`: arrival time of the process
- `burst_time`: burst time of the process

## Output

The output of the algorithm is an array of `Process` objects, where each object represents a process and contains the following fields:

- `pid`: process ID
- `arrival_time`: arrival time of the process
- `burst_time`: burst time of the process
- `completion_time`: completion time of the process
- `waiting_time`: waiting time of the process
- `turnaround_time`: turnaround time of the process

## Example

Suppose we have the following input:

```java
Process[] processes = {
    new Process(1, 0, 3),
    new Process(2, 1, 2),
    new Process(3, 2, 1),
    new Process(4, 3, 4),
    new Process(5, 4, 6)
};
```

After running the algorithm, we get the following output:

```
Process    Arrival Time    Burst Time      Completion Time Waiting Time    Turnaround Time
1          0               3               3               0               3
2          1               2               5               2               4
3          2               1               6               3               4
4          3               4               10              6               7
5          4               6               16              6               10
```

## Limitations

- The algorithm assumes that all processes arrive at different times.
- The algorithm does not handle preemption.
