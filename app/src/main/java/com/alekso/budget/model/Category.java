package com.alekso.budget.model;

/**
 * Created by alekso on 09/05/2017.
 */

public class Category {
    private long mId;
    private long mParentId;
    private String mName;

    /**
     * @param id
     * @param parentId
     * @param name
     */
    public Category(long id, long parentId, String name) {
        setId(id);
        setParentId(parentId);
        setName(name);
    }

    /**
     * @param parentId
     * @param name
     */
    public Category(long parentId, String name) {
        setParentId(parentId);
        setName(name);
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getParentId() {
        return mParentId;
    }

    public void setParentId(long parentId) {
        mParentId = parentId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return String.format("Category { id: %d; name: %s }", mId, mName);
    }
}
