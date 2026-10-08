package com.sauter001.demo.spike.minihb.naive;

public class NaiveTranslator {
    public String translate(String jpql) {
        return jpql
                .replace("Reservation", "reservation")
                .replaceAll("\\br\\.(\\w+)", "r.$1")
                .replaceAll("SELECT (\\w+) FROM", "SELECT $1.* FROM");
    }

    public String translate(String jpql, int limit) {
        return translate(jpql) + " limit " + limit;
    }
}
