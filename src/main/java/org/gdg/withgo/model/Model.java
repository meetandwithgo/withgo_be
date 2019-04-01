package org.gdg.withgo.model;

import com.sun.tools.javac.util.Assert;

public abstract class Model {

    public abstract void assertFields() throws IllegalArgumentException;

    protected void valid(String str, String msg){
        Assert.checkNonNull(str, msg);
        Assert.check(!str.isEmpty(), msg);
    }

}
