using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Http;
using 网上租房系统;

namespace HouseRetetalSystemV2.Controller
{
    public class MemberController : ApiController
    {

        /// <summary>
        /// 新增会员
        /// </summary>
        /// <param name="id"></param>
        /// <param name="password"></param>
        /// <param name="ownerName"></param>
        /// <param name="phone"></param>
        /// <param name="rest"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<string> AddNewMember(string id, string password, string ownerName, string phone, string rest)
        {

            var result = new BaseDataPackage<string>();
            string s = string.Format("insert into 会员表 values('{0}','{1}','{2}','{3}','{4}')", id, password, ownerName, phone, rest);
            //插入到数据库之前先判断是否已存在相同的账号
            string str = string.Format("select * from 会员表 where 账号='{0}'", id);
            DataTable dt = SqlHelper.ExecuteDataTable(str);
            if (dt.Rows.Count > 0)
            {
                // 查找成功，返回失败
                result.Data = "USERREPEAT";
                result.Status = ApiStatusCode.REPEAT;
                result.Message = "账号已存在！";
                return result;
            }
            else
            {
                if (SqlHelper.ExecuteNonQuery(s) > 0)
                {
                    // 新增成功
                    result.Data = "ADDOK";
                    result.Status = ApiStatusCode.OK2;
                    result.Message = "新增房间成功！";
                    return result;
                }
            }
            // 其他情况！
            result.Data = "EXCEPTION";
            result.Status = ApiStatusCode.EXCEPTION;
            result.Message = "网络问题，新增失败！";
            return result;

        }

        /// <summary>
        /// 查是否有重名者
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<string> CheckUser(string id)
        {
            var result = new BaseDataPackage<string>();
            //插入到数据库之前先判断是否已存在相同的账号
            string str = string.Format("select * from 会员表 where 账号='{0}'", id);
            DataTable dt = SqlHelper.ExecuteDataTable(str);
            if (dt.Rows.Count > 0)
            {
                // 查找成功，返回失败
                result.Data = "USERREPEAT";
                result.Status = ApiStatusCode.REPEAT;
                result.Message = "账号已存在！";
                return result;
            }
            else
            {
                // 查找失败，返回成功
                result.Data = "USEROK";
                result.Status = ApiStatusCode.OK2;
                result.Message = "账号可用！";
                return result;
            }
        }


        /// <summary>
        /// 充值
        /// </summary>
        /// <param name="id"></param>
        /// <param name="money"></param>
        /// <param name="rest"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<string> Recharge(string id, string money, string rest)
        {
            var result = new BaseDataPackage<string>();

            int sum = Convert.ToInt32(rest) + Convert.ToInt32(money);
            string moneyAll = Convert.ToString(sum);
            string sql = string.Format("update 会员表 set 余额='{0}' where 账号='{1}'", moneyAll, id);
            if (SqlHelper.ExecuteNonQuery(sql) > 0)
            {
                // 充值成功
                result.Data = "RECHARGEOK";
                result.Status = ApiStatusCode.OK2;
                result.Message = "充值成功！目前账户余额：" + moneyAll + "元";
                return result;
            }
            result.Data = "EXCEPTION";
            result.Status = ApiStatusCode.EXCEPTION;
            result.Message = "抱歉，网络异常";
            return result;
        }

        /// <summary>
        /// 查询所有会员
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<DataTable> GetMember()
        {
            var result = new BaseDataPackage<DataTable>();
            DataTable dataTable = SqlHelper.ExecuteDataTable("select * from 会员表"); ;
            result.Data = dataTable;
            result.Status = ApiStatusCode.OK1;
            result.Message = "查询成功！";
            return result;
        }

        /// <summary>
        /// 删除用户的所有系统信息
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<string> DeleteMember(string id)
        {
            var result = new BaseDataPackage<string>();

            string s0 = string.Format("select 账号 from 租房登记表 where 租借人账号='{0}'", id);
            DataTable dt0 = SqlHelper.ExecuteDataTable(s0);
            string s = string.Format("delete from 会员表 where 账号='{0}'", id);
            int r1 = SqlHelper.ExecuteNonQuery(s);

            string s3 = string.Format("delete from 租房登记表 where 租借人账号='{0}'", id);
            int r3 = SqlHelper.ExecuteNonQuery(s3);

            if (r1 > 0)
            {
                string s4;
                int temp;
                for(int i = 0; i < dt0.Rows.Count; i++)
                {
                    s4 = string.Format("update 租房房间表 set 状态='空闲' where 账号='{0}'",dt0.Rows[i][0].ToString());
                    temp = SqlHelper.ExecuteNonQuery(s4);
                }
                result.Data = "DELETEOK";
                result.Status = ApiStatusCode.OK2;
                result.Message = "抹去用户信息成功！";
                return result;
            }
            result.Data = "EXCEPTION";
            result.Status = ApiStatusCode.EXCEPTION;
            result.Message = "抱歉，网络异常";
            return result;
        }
    }
}