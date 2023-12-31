// MyAidlInterface.aidl
package com.example.week11;

// Declare any non-default types here with import statements

interface MyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    int add(int a, int b);
}