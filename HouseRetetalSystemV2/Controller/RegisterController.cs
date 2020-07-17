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
    public class RegisterController : ApiController
    {
        /// <summary>
        /// 注册
        /// </summary>
        /// <param name="account"></param>
        /// <param name="password"></param>
        /// <param name="name"></param>
        /// <param name="phone"></param>
        /// <param name=""></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<UserData> Register(string account, string password, string name, string phone)
        {
            //使用account和password验证用户

            UserData userData = new UserData();
            userData.UserGuid = Guid.NewGuid().ToString();
            userData.UserName = "测试用户";

            var result = new BaseDataPackage<UserData>();

            string s = string.Format("insert into 会员表(账号,密码,姓名,电话,余额) values('{0}','{1}','{2}','{3}',0)", account, password, name, phone);
            if (SqlHelper.ExecuteNonQuery(s) > 0)
            {
                userData.UserName = name;
                result.Data = userData;
                result.Status = ApiStatusCode.OK1;
                result.Message = "注册成功";
                return result;
            }
            else
            {
                //注册失败！
                result.Data = null;
                result.Status = ApiStatusCode.FAIL;
                result.Message = "网络问题，注册失败！";
                return result;

            }
        }
    }
}