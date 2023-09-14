#include <stdio.h>
#include <stdlib.h>

#define MAX 10

struct process {
    int pid;
    int arrival_time;
    int burst_time;
    int waiting_time;
    int completion_time;
    int turnaround_time;
};

void swap(struct process *a, struct process *b) {
    struct process temp = *a;
    *a = *b;
    *b = temp;
}

void sort_by_arrival_time(struct process *p, int n) {
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (p[j].arrival_time > p[j + 1].arrival_time) {
                swap(&p[j], &p[j + 1]);
            }
        }
    }
}

void calculate_waiting_time(struct process *p, int n) {
    p[0].waiting_time = 0;
    for (int i = 1; i < n; i++) {
        p[i].waiting_time = p[i - 1].completion_time - p[i].arrival_time;
        if (p[i].waiting_time < 0) {
            p[i].waiting_time = 0;
        }
    }
}

void calculate_turnaround_time(struct process *p, int n) {
    for (int i = 0; i < n; i++) {
        p[i].turnaround_time = p[i].burst_time + p[i].waiting_time;
    }
}

void calculate_completion_time(struct process *p, int n) {
    p[0].completion_time = p[0].burst_time;
    for (int i = 1; i < n; i++) {
        p[i].completion_time = p[i - 1].completion_time + p[i].burst_time;
    }
}

void sjf(struct process *p, int n) {
    sort_by_arrival_time(p, n);
    
    int current_time = p[0].arrival_time;
    
    for (int i = 0; i < n; i++) {
        int min_burst_time_idx = i;
        for (int j = i + 1; j < n && p[j].arrival_time <= current_time; j++) {
            if (p[j].burst_time < p[min_burst_time_idx].burst_time) {
                min_burst_time_idx = j;
            }
        }
        swap(&p[i], &p[min_burst_time_idx]);
        
        current_time += p[i].burst_time;
        p[i].completion_time = current_time;
        
        calculate_waiting_time(p, n);
        calculate_turnaround_time(p, n);
    }
}

void print_table(struct process *p, int n) {
    printf("PID\tAT\tBT\tWT\tCT\tTAT\n");
    float awt = 0.0, atat = 0.0;
    for (int i = 0; i < n; i++) {
        printf("%d\t%d\t%d\t%d\t%d\t%d\n", p[i].pid, p[i].arrival_time, p[i].burst_time,p[i].waiting_time, p[i].completion_time, p[i].turnaround_time);
        awt += p[i].waiting_time;
        atat += p[i].turnaround_time;
    }
    printf("Average Waiting Time: %.2f\n", awt/n);
    printf("Average Turnaround Time: %.2f\n", atat/n);
}

int main() {
    struct process p[MAX];
    int n;

    printf("Enter the number of processes: ");
    scanf("%d", &n);

    printf("Enter the arrival time and burst time for each process:\n");
    for (int i = 0; i < n; i++) {
        printf("Process %d: ", i + 1);
        scanf("%d %d", &p[i].arrival_time, &p[i].burst_time);
        p[i].pid = i + 1;
    }

    sjf(p, n);

    print_table(p, n);

    return 0;
}
