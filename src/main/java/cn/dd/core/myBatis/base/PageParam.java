package cn.dd.core.myBatis.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wishflyer on 2016/5/24.
 */
public class PageParam {

    //从第多少个数据开始
    private int ddPageParmStart;
    //每页的大小
    private int ddPageParmSize;
    //第几页
    private int ddPageParmNo;

    public PageParam(int pageNo,int pageSize){
        this.ddPageParmNo = pageNo;
        this.ddPageParmSize = pageSize;
        this.ddPageParmStart = pageSize * (pageNo - 1);
    }

    public Map getPageParamMap(){
        Map<String,Object> pageParamMap = new HashMap<String,Object>();
        pageParamMap.put("ddPageParmStart",ddPageParmStart);
        pageParamMap.put("ddPageParmSize",ddPageParmSize);
        return pageParamMap;
    }

    public int getPageStart() {
        return ddPageParmStart;
    }


    public void setPageStart(int ddPageParmStart) {
        this.ddPageParmStart = ddPageParmStart;
    }

    public int getPageSize() {
        return ddPageParmSize;
    }

    public void setPageSize(int ddPageParmSize) {
        this.ddPageParmSize = ddPageParmSize;
    }

    public int getPageNo() {
        return ddPageParmNo;
    }

    public void setPageNo(int ddPageParmNo) {
        this.ddPageParmNo = ddPageParmNo;
    }
}
