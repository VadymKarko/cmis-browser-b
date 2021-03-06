package com.engagepoint.university.ep2013b.browser.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrowserItem implements Serializable
{

    public enum TYPE {FILE, FOLDER}

    private String id = "";
    private TYPE type = TYPE.FILE;
    private String name = "";
    private BrowserItem parent = null;
    private List<BrowserItem> children = new ArrayList<BrowserItem>();
    private int totalPages = 0;

    public BrowserItem()
    {
    }

    public BrowserItem(String name)
    {
        this.name = name;
    }

    public BrowserItem(String name, TYPE type)
    {
        this.name = name;
        this.type = type;
    }

    public BrowserItem(String name, TYPE type, BrowserItem parent)
    {
        this.name = name;
        this.type = type;
        this.parent = parent;
    }

    public BrowserItem(String id, String name, TYPE folder) {
        this.id = id;
        this.name = name;
        this.type = folder;
    }

    public BrowserItem(String name, TYPE type, BrowserItem parent, List<BrowserItem> children) {
        this.name = name;
        this.type = type;
        this.parent = parent;
        this.children = children;
    }

    public BrowserItem(String name, TYPE type, BrowserItem parent, List<BrowserItem> children, int totalPages) {
        this.name = name;
        this.type = type;
        this.parent = parent;
        this.children = children;
        this.totalPages = totalPages;
    }


    public BrowserItem(String id, String name, TYPE type, BrowserItem parent, List<BrowserItem> children) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parent = parent;
        this.children = children;
    }


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public TYPE getType()
    {
        return type;
    }

    public void setType(TYPE type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BrowserItem getParent()
    {
        return parent;
    }

    public void setParent(BrowserItem parent)
    {
        this.parent = parent;
    }

    public List<BrowserItem> getChildren()
    {
        return children;
    }

    public void setChildren(List<BrowserItem> children)
    {
        this.children.addAll(children);
    }

    public void setChild(BrowserItem child)
    {
        children.add(child);
    }

    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof BrowserItem)) return false;

        return (this.getId().equals(((BrowserItem)other).getId()));
    }

    @Override
    public String toString()
    {
        String result = "BrowserItem ("+getId()+", "+getName()+", "+getType().name()+", ";

        result += (parent == null) ? "null" : parent.getId();
        result += ", ";

        if (children.isEmpty()) result += "empty";
        else
        {
            result += "[";
            for (BrowserItem i : children)
            {
                result += i.getId() + ", ";
            }
            result += "]";
        }

        result += ")";

        return result;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}