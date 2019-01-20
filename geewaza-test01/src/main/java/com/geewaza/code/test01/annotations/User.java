package com.geewaza.code.test01.annotations;

import com.geewaza.code.test01.annotations.ReqMapping.ReqMethod;

/**
 * Created by wangheng on 2019/1/20.
 *
 * @author wangheng
 * @date 2019/1/20
 */
@ReqMapping(method = ReqMethod.POST,val = "类")
public class User  {

    @ReqValue(value1 = "张三")
    private String userName;

    @ReqValue(value2 = "密码")
    private String pswd;


    @ReqMapping(method = ReqMethod.GET)
    public void get(){

    }

    @ReqMapping(method = ReqMethod.POST)
    public void post(){

    }

    @ReqMapping(val={"a","b"})
    public void other(){

    }

}