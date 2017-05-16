# Query of Death

From Google Distributed Jam 2017 [Round 1](https://code.google.com/codejam/contest/8314486/dashboard#s=p4)



Let's consider a different version of it. Given an array of $n$ numbers, with exactly one `qod`. How many machines do we need to guarantee to read correctly every number with **100%** probability? 

This question seems impossible, but it is doable! For example, it is easy to see that $n$ machines can solve the problem, i.e., the $i$-th machine just reads the $i$-th element. But in general, can we use fewer machines? The answer is yes! We will gradually present a sequence of solutions with fewer and fewer machines requied.

##### $3n/4$ machines

All machines read correctly the first $3n/4$ numbers. For the remaining $n/4$ numbers, each one is read by three machines. Specifically, there can be at most one broken machines, so we can go ahead and pick the number with mode no less than 2.



##### $O(n / \log n)$ machines



##### $O(\log n)$ machines





We can show that $\Omega(\log n)$ is the lower bound if every number can be read at most once.



It is surprising to see that if we do not limit the number of times each number can accessed, only **3** machines will be sufficient.