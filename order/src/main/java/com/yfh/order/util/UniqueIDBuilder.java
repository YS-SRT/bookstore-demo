package com.yfh.order.util;

public class UniqueIDBuilder {
    private final long twepoch = 1652424300000L;
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final long sequenceBits = 12L;
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
 
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
 
    public UniqueIDBuilder(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
           * Get the next ID (this method is thread safe)
     * @return SnowflakeId
     */
    public synchronized long buildId() {
        long timestamp = timeGen();
        //If the current time is smaller than the timestamp generated by the last ID, the system clock must be thrown out at this time.
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //If it is generated at the same time, in millisecond sequence
        if (lastTimestamp == timestamp) {
            //If milliseconds, increment generation serial numbers from 0
            sequence = (sequence + 1) & sequenceMask;
            //Square sequence in millisecond overflow
            if (sequence == 0) {
                //Block to the next milliseconds, get a new timestamp
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //Timestamp change, in millisecond sequence reset
        else {
            sequence = 0L;
        }

        //Time to generate id times
        lastTimestamp = timestamp;

        //Shift and pass or operate to form a 64-bit ID together
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }
 
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
 
    protected long timeGen() {
        return System.currentTimeMillis();
        //return Clock.systemUTC().millis();
    }
 
    
    // public void testGeneration() {
    //     UniqueIDBuilder builder = new UniqueIDBuilder(0, 0);
    //     for (int i = 0; i < 100; i++) {
    //         long id = builder.buildId();
    //         System.out.println(id);
    //     }
    // }
}
