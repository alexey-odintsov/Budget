package com.alekso.budget.model;

/**
 * Created by alekso on 09/05/2017.
 */

public class Currency {
    private long mId;
    private String mName;
    private String mSymbol;

    /**
     * @param id
     * @param name
     * @param symbol
     */
    public Currency(long id, String name, String symbol) {
        setId(id);
        setName(name);
        setSymbol(symbol);
    }

    /**
     * @param name
     * @param symbol
     */
    public Currency(String name, String symbol) {
        setName(name);
        setSymbol(symbol);
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    @Override
    public String toString() {
        return String.format("Currency { id: %d; name: %s; symbol: %s }", mId, mName, mSymbol);
    }

}
