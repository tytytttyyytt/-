using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Http;
using 网上租房系统;

namespace HouseRetetalSystemV2.Controller
{
    public class RoomController : ApiController
    {
        /// <summary>
        /// 新增房间
        /// </summary>
        /// <param name="id"></param>
        /// <param name="password"></param>
        /// <param name="ownerName"></param>
        /// <param name="address"></param>
        /// <param name="level"></param>
        /// <param name="price"></param>
        /// <param name="status"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<string> AddNewHouse(string id, string password, string ownerName, string address, string level, string price, string status)
        {
            var result = new BaseDataPackage<string>();
            string s = string.Format("insert into 租房房间表 values('{0}','{1}','{2}','{3}','{4}','{5}')", id, ownerName, address, level, price, status);
            string ss = string.Format("insert into 密码表 values('{0}','{1}')", id, password);
            //插入到数据库之前先判断是否已存在相同的账号
            string str = string.Format("select * from 租房房间表 where 账号='{0}'", id);
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
                    SqlHelper.ExecuteNonQuery(ss); //执行密码表插入操作
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
        /// 查询房源
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<DataTable> GetHouse()
        {
            var result = new BaseDataPackage<DataTable>();
            DataTable dataTable = SqlHelper.ExecuteDataTable("select 租房房间表.账号,密码,房主昵称,房屋所在地,房屋星级,[租价 元/时],状态 from 租房房间表,密码表 where 租房房间表.账号=密码表.账号");
            result.Data = dataTable;
            result.Status = ApiStatusCode.OK1;
            result.Message = "查询成功！";
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
            string str = string.Format("select * from 租房房间表 where 账号='{0}'", id);
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
        /// 修改房间信息
        /// </summary>
        /// <param name="id"></param>
        /// <param name="account"></param>
        /// <param name="password"></param>
        /// <param name="ownerName"></param>
        /// <param name="address"></param>
        /// <param name="level"></param>
        /// <param name="price"></param>
        /// <param name="status"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<string> UpdateHouse(string id, string account, string password, string ownerName, string address, string level, string price, string status)
        {
            var result = new BaseDataPackage<string>();

            string s = string.Format("update 租房房间表 set 账号='{0}',房主昵称='{1}',房屋所在地='{2}',房屋星级='{3}',[租价 元/时]='{4}',状态='{5}' where 账号='{6}'", id, ownerName, address, level, price, status, account);
            string ss = string.Format("update 密码表 set 账号='{0}',密码='{1}' where 账号='{2}'", id, password, account);
            SqlHelper.ExecuteNonQuery(ss);
            if (SqlHelper.ExecuteNonQuery(s) > 0)
            {
                result.Data = "UPDATEOK";
                result.Status = ApiStatusCode.OK2;
                result.Message = "修改成功！";
                return result;
            }
            result.Data = "EXCEPTION";
            result.Status = ApiStatusCode.EXCEPTION;
            result.Message = "抱歉，网络异常";
            return result;
        }

        /// <summary>
        /// 删除房间
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<string> DeleteHouse(string id)
        {
            var result = new BaseDataPackage<string>();

            string s = string.Format("delete from 租房房间表 where 账号='{0}'", id);
            string ss = string.Format("delete from 密码表 where 账号='{0}'", id);
            if (SqlHelper.ExecuteNonQuery(s) > 0)
            {
                SqlHelper.ExecuteNonQuery(ss);
                result.Data = "DELETEOK";
                result.Status = ApiStatusCode.OK2;
                result.Message = "删除成功！";
                return result;
            }
            result.Data = "EXCEPTION";
            result.Status = ApiStatusCode.EXCEPTION;
            result.Message = "抱歉，网络异常";
            return result;
        }

    }
}