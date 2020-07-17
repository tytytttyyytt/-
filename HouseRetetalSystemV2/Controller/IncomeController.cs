using HouseRetetalSystemV2.Model;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Http;
using 网上租房系统;

namespace HouseRetetalSystemV2.Controller
{
    public class IncomeController : ApiController
    {
        /// <summary>
        /// 查询收入明细
        /// </summary>
        /// <param name="time"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<DataTable> GetIncome(string time)
        {
            var result = new BaseDataPackage<DataTable>();

            if(time == null || time == "null")
            {
                result.Data = null;
                result.Status = ApiStatusCode.LOADING;
                result.Message = "初始化中……";
                return result;
            }
            string sql = string.Format("select * from 交易成功订单表 where DateDiff(dd,归还时间,'{0}')=0", time);
            DataTable dt = SqlHelper.ExecuteDataTable(sql);
            result.Data = dt;
            result.Status = ApiStatusCode.OK2;
            result.Message = time + "记录查询成功！";
            return result;
        }
    }
}