package com.scm.myblog.manager.ServiceCommonManager;

import com.scm.myblog.entity.CORE.StatusMes;
import com.scm.myblog.entity.VO.Result;

public class DeleteManager {

    public static  Result  deleteBatchIds(boolean isRemoveSuccess,Integer[] ids){
        Result result = new Result();
        if (isRemoveSuccess){
            result.setData(ids);
            result.setCode(StatusMes.DELETE_OK.getCode());
            result.setMessage(StatusMes.DELETE_OK.getMessage());
        }
        else {
            result.setData(null);
            result.setCode(StatusMes.DELETE_ERR.getCode());
            result.setMessage(StatusMes.DELETE_ERR.getMessage());
        }
        return result;
    }

    public static  Result  deleteBatchIds(boolean isRemoveSuccess,Long[] ids){
        Result result = new Result();
        if (isRemoveSuccess){
            result.setData(ids);
            result.setCode(StatusMes.DELETE_OK.getCode());
            result.setMessage(StatusMes.DELETE_OK.getMessage());
        }
        else {
            result.setData(null);
            result.setCode(StatusMes.DELETE_ERR.getCode());
            result.setMessage(StatusMes.DELETE_ERR.getMessage());
        }
        return result;
    }
}
