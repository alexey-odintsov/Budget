package com.alekso.budget.model;

/**
 * Created by alekso on 09/05/2017.
 */

public class Account {
    private long mId;
    private String mName;
    private int mType;

    /**
     * @param id
     * @param name
     * @param type
     */
    public Account(long id, String name, int type) {
        setId(id);
        setName(name);
        setTyoe(type);
    }

    /**
     * @param name
     * @param type
     */
    public Account(String name, int type) {
        setName(name);
        setTyoe(type);
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

    public void setTyoe(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }

    @Override
    public String toString() {
        return String.format("Account { id: %d; type: %d; name: %s }", mId, mType, mName);
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
