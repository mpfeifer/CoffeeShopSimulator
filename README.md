From Google Gemini (Prompt: java programming exercise for the topic "Concurrency")

Concurrency in Java can be a bit of a "mind-bender" because it requires thinking about multiple things happening at the exact same time.
To help you master this, I’ve designed a practical exercise that moves from basic thread creation to using modern concurrency utilities.

---

## The Challenge: The Multi-Threaded Order Processor

**Scenario:** You are building a backend for a busy coffee shop. Orders are coming in fast, and you need to process them in parallel to keep customers happy. Each order takes a specific amount of time to "brew."

### Phase 1: The Basics (`Thread` or `Runnable`)

Create a class `CoffeeOrder` that implements `Runnable`.

* It should take an `orderName` and `brewTime` (in milliseconds) as constructor arguments.
* In the `run()` method, print "Brewing: [orderName]", sleep for the `brewTime`, and then print "Finished: [orderName]".
* **Task:** In your `main` method, create and start three different coffee orders manually.

### Phase 2: The Thread Pool (`ExecutorService`)

Manually managing threads is inefficient. Let's upgrade to an `ExecutorService`.

* **Task:** Create a `FixedThreadPool` with 2 threads.
* Submit 5 different orders to the pool.
* Observe how only two orders are processed at a time while the others wait in the queue.

### Phase 3: Coordination (`CountDownLatch`)

Now, imagine the shop can't open until all 5 initial "Pre-opening" tasks (grinding beans, warming the machine, etc.) are done.

* **Task:** Use a `CountDownLatch` initialized to 5.
* Start 5 threads that each perform a setup task and call `latch.countDown()`.
* The `main` thread should use `latch.await()` and only print "Shop is Open!" once all setup tasks are finished.

---

## Key Concepts to Remember

When working through these exercises, keep these three pillars in mind:

| Concept | Description | Java Tool |
| --- | --- | --- |
| **Atomicity** | Operations that happen all at once or not at all. | `AtomicInteger`, `synchronized` |
| **Visibility** | Ensuring one thread's changes are visible to others. | `volatile` keyword |
| **Ordering** | Preventing the CPU from reordering your instructions. | `final`, `Locks` |

---

## Troubleshooting Tips

* **InterruptedException:** Always handle this when using `Thread.sleep()`. It’s the thread's way of saying someone tried to stop it.
* **Shutdown:** Always call `executorService.shutdown()` when you are done, otherwise your Java program might never exit!

**Would you like me to provide a starter code template for Phase 1, or should we jump straight into a solution for the Thread Pool phase?**
