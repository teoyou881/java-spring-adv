package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

    @Test
    void begin_end() {
        ThreadLocalLogTrace trace = new ThreadLocalLogTrace();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        ThreadLocalLogTrace trace = new ThreadLocalLogTrace();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

    @Test
    void multithreaded() throws InterruptedException {
        final ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

        // Thread 1
        Thread thread1 = new Thread(() -> {
            TraceStatus status1 = trace.begin("thread1-hello1");
            TraceStatus status2 = trace.begin("thread1-hello2");
            trace.end(status2);
            trace.end(status1);
        });

        // Thread 2
        Thread thread2 = new Thread(() -> {
            TraceStatus status1 = trace.begin("thread2-hello1");
            TraceStatus status2 = trace.begin("thread2-hello2");
            trace.end(status2);
            trace.end(status1);
        });

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait for both threads to complete
        thread1.join();
        thread2.join();
    }
}