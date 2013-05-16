package ru.rstyle.so.domain;

public final class Semaphore {

    private Semaphore() {

    }

    private static String vendor;

    public static String getVendor() {
        return vendor;
    }

    public static void setVendor(String vendor) {
        Semaphore.vendor = vendor;
    }

}
