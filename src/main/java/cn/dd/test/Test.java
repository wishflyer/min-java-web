package cn.dd.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wishflyer on 2016/6/28.
 */
public class Test {
    public static void main(String[] args){
        HashMap<String,Set<String>> m = new HashMap<>();
        String key = "test";
        String value = "test";
        if(m.containsKey(key)){
            m.get(key).add(value);
        }else{
            Set<String> valueSet = new HashSet<String>();
            valueSet.add(value);
            m.put(key,valueSet);

        }
        m.get(key).add(value);
    }
}
