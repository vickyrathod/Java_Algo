<h2>Async execution in code</h2>
Use DAG to represent all async task in a system and there messaging.
Every node should represent single Thread with amount of time it take.
Longest path in this graph is the longest time after parallism.\
The ratio of ideal parallism is found by following formula\
`(total work) / (Longest path span)`\
This ratio always greater than or equal to one.

<h3>Thread Pool</h3>
submit task --> Task Queue --> Thread Pool
<h3> Amdalhs Law</h3>
`1/((1 - P)+ P/S)`
P = Portion of program that can be parallized.\
S = speed up for parallized portion of program.\
For Example:
P=0.95 (Means 95% of code is paralalize.\
S=2 (Means 2 parallel processor)
<h3>Factor to consider</h3>
<h4>Partitioning</h4>
1) Functional decomposition
    1) scan all fuctions.
    2) look for data dependacy.
2) Data decomposition: decomposing data in such a way that no threads are accessing same data at a time.
    1) Block decomposing.
    2) Cyclic decomposing.

<h3>Communication</h3>
1) Async comunication.(Non Blocking)
2) Sync communication.(Blocking)

<h3>Standard Multithreading problem steps</h3>
`Problem ---> multiple subproblems ---> communication between subproblem`

<h3>Aglomeration</h3>
It may possible, after dividing big task to many subproblem can cause increase the compute to communication ratio.
to avoid this just only devide limited subtask and make it dynamic on processors.
