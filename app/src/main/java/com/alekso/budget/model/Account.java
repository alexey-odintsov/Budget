package com.alekso.budget.model;

/**
 * Created by alekso on 09/05/2017.
 */

public class Account {
    private long mId;
    private long mCurrencyId;
    private String mName;
    private int mType;

    /**
     * Initialization constructor
     *
     * @param id
     * @param currencyId
     * @param name
     * @param type
     */
    public Account(long id, long currencyId, String name, int type) {
        setId(id);
        setCurrencyId(currencyId);
        setName(name);
        setType(type);
    }

    /**
     * New instance constructor
     *
     * @param currencyId
     * @param name
     * @param type
     */
    public Account(long currencyId, String name, int type) {
        setCurrencyId(currencyId);
        setName(name);
        setType(type);
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

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public long getCurrencyId() {
        return mCurrencyId;
    }

    public void setCurrencyId(long currencyId) {
        mCurrencyId = currencyId;
    }

    @Override
    public String toString() {
        return String.format("Account { id: %d; currency: %d; type: %d; name: %s }", mId, mCurrencyId, mType, mName);
    }


    public enum TYPE {
        CASH(0),
        CREDIT_CARD(1),
        DEBIT_CARD(2),
        E_MONEY(3);

        private int id;

        TYPE(int id) {
            this.id = id;
        }

        public static TYPE get(int id) {
            for (TYPE t : values()) {
                if (id == t.id) return t;
            }

            return TYPE.CASH;
        }
    }
}
