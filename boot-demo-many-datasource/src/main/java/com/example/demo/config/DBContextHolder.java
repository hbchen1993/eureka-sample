package com.example.demo.config;

public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void write() {
        set(DBTypeEnum.WRITE);
    }

    public static void reader() {
        set(DBTypeEnum.READER);
    }

}