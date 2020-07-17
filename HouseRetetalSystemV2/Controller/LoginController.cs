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
    public class LoginController : ApiController
    {
        /// <summary>
        /// 登录
        /// </summary>
        /// <param name="account"></param>
        /// <param name="password"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<UserData> Login(string account, string password, string role)
        {
            //使用account和password验证用户

            UserData userData = new UserData();
            userData.UserGuid = Guid.NewGuid().ToString();
            userData.UserName = "测试用户";

            var result = new BaseDataPackage<UserData>();

            if (role == "1")//会员登录
            {
                string s = string.Format("select * from 会员表 where 账号='{0}' and 密码='{1}'", account, password);
                DataTable dt = SqlHelper.ExecuteDataTable(s);
                if (dt.Rows.Count > 0)
                {
                    //登录成功！
                    userData.UserName = dt.Rows[0][2].ToString();
                    userData.UserAccount = dt.Rows[0][0].ToString();
                    userData.Name = dt.Rows[0][2].ToString();
                    userData.Phone = dt.Rows[0][3].ToString();
                    userData.Cost = Convert.ToInt32(dt.Rows[0][4]);
                    result.Data = userData;
                    result.Status = ApiStatusCode.OK1;
                    result.Message = "登录成功，正在跳转……";
                    return result;
                }
                else
                {
                    //登录失败！
                    result.Data = null;
                    result.Status = ApiStatusCode.FAIL;
                    result.Message = "登录失败,用户名或密码错误！";
                    return result;
                }
            }
            else if (role == "2")//管理员登录
            {
                string s = string.Format("select * from 管理员表 where 登录账号='{0}' and 登录密码='{1}'", account, password);
                DataTable dt = SqlHelper.ExecuteDataTable(s);
                if (dt.Rows.Count > 0)
                {
                    //登录成功！
                    userData.UserAccount = dt.Rows[0][0].ToString();
                    userData.Name = dt.Rows[0][2].ToString();
                    userData.Phone = dt.Rows[0][3].ToString();
                    result.Data = userData;
                    result.Status = ApiStatusCode.OK2;
                    result.Message = "登录成功，正在跳转……";
                    return result;
                }
                else
                {
                    //登录失败！

                    result.Data = null;
                    result.Status = ApiStatusCode.FAIL;
                    result.Message = "登录失败，用户名或密码错误！";
                    return result;
                }
            }
            result.Data = null;
            result.Status = ApiStatusCode.EXCEPTION;
            result.Message = "登录异常！";
            return result;

        }
    }

    /// <summary>
    /// 状态码
    /// </summary>
    public class ApiStatusCode
    {
        /// <summary>
        /// OK 用户登录成功
        /// </summary>
        public const int OK1 = 1;

        /// <summary>
        /// OK 管理员登录成功
        /// </summary>
        public const int OK2 = 2;

        /// <summary>
        /// 失败
        /// </summary>
        public const int FAIL = 0;

        /// <summary>
        /// 余额不足
        /// </summary>
        public const int PAYFAIL = 3;

        /// <summary>
        /// 账号重复
        /// </summary>
        public const int REPEAT = 4;
        
        /// 加载中
        /// </summary>
        public const int LOADING = 4;

        /// <summary>
        /// 异常
        /// </summary>
        public const int EXCEPTION = -1;
    }

    public class BaseDataPackage<T>
    {
        public int Status { get; set; }
        public string Message { get; set; }
        public T Data { get; set; }
    }

}