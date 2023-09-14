import java.util.Arrays;

class Process {
    int pid;
    int arrival_time;
    int burst_time;
    int waiting_time;
    int completion_time;
    int turnaround_time;

    public Process(int pid, int arrival_time, int burst_time) {
        this.pid = pid;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
    }
}

public class SJF {
    public static void swap(Process a, Process b) {
        Process temp = new Process(a.pid, a.arrival_time, a.burst_time);
        a.pid = b.pid;
        a.arrival_time = b.arrival_time;
        a.burst_time = b.burst_time;
        b.pid = temp.pid;
        b.arrival_time = temp.arrival_time;
        b.burst_time = temp.burst_time;
    }

    public static void sortByArrivalTime(Process[] p, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (p[j].arrival_time > p[j + 1].arrival_time) {
                    swap(p[j], p[j + 1]);
                }
            }
        }
    }

    public static void calculateWaitingTime(Process[] p, int n) {
        p[0].waiting_time = 0;
        for (int i = 1; i < n; i++) {
            p[i].waiting_time = p[i - 1].completion_time - p[i].arrival_time;
            if (p[i].waiting_time < 0) {
                p[i].waiting_time = 0;
            }
        }
    }

    public static void calculateTurnaroundTime(Process[] p, int n) {
        for (int i = 0; i < n; i++) {
            p[i].turnaround_time = p[i].burst_time + p[i].waiting_time;
        }
    }

    public static void calculateCompletionTime(Process[] p, int n) {
        p[0].completion_time = p[0].burst_time;
        for (int i = 1; i < n; i++) {
            p[i].completion_time = p[i - 1].completion_time + p[i].burst_time;
        }
    }

    public static void sjf(Process[] p, int n) {
        sortByArrivalTime(p, n);

        int currentTime = p[0].arrival_time;

        for (int i = 0; i < n; i++) {
            int minBurstTimeIdx = i;
            for (int j = i + 1; j < n && p[j].arrival_time <= currentTime; j++) {
                if (p[j].burst_time < p[minBurstTimeIdx].burst_time) {
                    minBurstTimeIdx = j;
                }
            }
            swap(p[i], p[minBurstTimeIdx]);
            currentTime += p[i].burst_time;
            p[i].completion_time = currentTime;
        }

        calculateWaitingTime(p, n);
        calculateTurnaroundTime(p, n);
    }

    public static void main(String[] args) {
        Process[] processes = {
                new Process(1, 0, 3),
                new Process(2, 1, 2),
                new Process(3, 2, 1),
                new Process(4, 3, 4),
                new Process(5, 4, 6)
        };

        sjf(processes, processes.length);

        System.out.printf("%-10s %-15s %-15s %-15s %-15s %-15s\n", "Process", "Arrival Time", "Burst Time", "Completion Time", "Waiting Time", "Turnaround Time");
        for (Process process : processes) {
            System.out.printf("%-10d %-15d %-15d %-15d %-15d %-15d\n", process.pid, process.arrival_time, process.burst_time, process.completion_time, process.waiting_time, process.turnaround_time);
        }
    }
}
