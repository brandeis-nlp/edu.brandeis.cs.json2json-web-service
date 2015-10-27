package edu.brandeis.cs.json;

/**
 * Created by 310201833 on 2015/10/26.
 */
public interface IJsonProxy {
    IJsonArr newArr();
    IJsonObj newObj();
    Object wrap(Object obj);
    Object unwrap(Object obj);
}
