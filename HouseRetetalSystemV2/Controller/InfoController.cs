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
    public class InfoController : ApiController
    {
        /// <summary>
        /// 查询个人信息
        /// </summary>
        /// <param name="account"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<UserData> GetInfo(string account)
        {
            //使用account和password验证用户

            UserData userData = new UserData();
            userData.UserGuid = Guid.NewGuid().ToString();
            userData.UserName = "测试用户";

            var result = new BaseDataPackage<UserData>();

            string s = string.Format("select * from 会员表 where 账号='{0}'", account);
            DataTable dt = SqlHelper.ExecuteDataTable(s);
            if (dt.Rows.Count > 0)
            {
                userData.UserAccount = dt.Rows[0][0].ToString();
                userData.Name = dt.Rows[0][2].ToString();
                userData.Phone = dt.Rows[0][3].ToString();
                userData.Cost = Convert.ToInt32(dt.Rows[0][4]);
                result.Data = userData;
                result.Status = ApiStatusCode.OK1;
                result.Message = "查找成功";
                return result;
            }
            else
            {
                //注册失败！
                result.Data = null;
                result.Status = ApiStatusCode.FAIL;
                result.Message = "网络问题，查找失败！";
                return result;

            }
        }

        /// <summary>
        /// 修改个人信息
        /// </summary>
        /// <param name="account"></param>
        /// <param name="password"></param>
        /// <param name="username"></param>
        /// <param name="userphone"></param>
        /// <param name="oldaccount"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<string> UpdateInfo(string account, string password, string username, string userphone,string oldaccount)
        {

            var result = new BaseDataPackage<string>();
            string id = account;
            string pwd = password;
            string name = username;
            string phone = userphone;
            string oldUserId = oldaccount;
            //个人信息修改后，得更新 会员表、租房登记表、已完成订单表 里的该会员信息
            string s1 = string.Format(@"update 会员表 set 账号='{0}',密码='{1}',姓名='{2}',电话='{3}'
where 账号='{4}'", id, pwd, name, phone, oldUserId);
            string s2 = string.Format(@"update 租房登记表 set 租借人账号='{0}',租借人姓名='{1}',租借人电话='{2}'
where 租借人账号='{3}'", id, pwd, name, oldUserId);
            string s3 = string.Format(@"update 交易成功订单表 set 租借人账号='{0}',租借人姓名='{1}',租借人电话='{2}'
where 租借人账号='{3}'", id, pwd, name, oldUserId);

            int r1 = SqlHelper.ExecuteNonQuery(s1);
            int r2 = SqlHelper.ExecuteNonQuery(s2);
            int r3 = SqlHelper.ExecuteNonQuery(s3);
            if (r1 > 0 && r2 >= 0 && r3 >= 0)
            {
                result.Data = "UPDATEOK";
                result.Status = ApiStatusCode.OK1;
                result.Message = "更新成功！";
                return result;
            }
            result.Data = "EXCEPTION";
            result.Status = ApiStatusCode.EXCEPTION;
            result.Message = "网络问题，更新失败！";
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
            string s = string.Format("select * from 租房房间表 where 状态='{0}'", "空闲");
            DataTable dataTable = SqlHelper.ExecuteDataTable(s);
            result.Data = dataTable;
            result.Status = ApiStatusCode.OK1;
            result.Message = "查询成功";
            return result;
        }

        /// <summary>
        /// 查询交易记录
        /// </summary>
        /// <param name="account"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<DataTable> GetRecord(string account)
        {
            var result = new BaseDataPackage<DataTable>();
            string s = string.Format("select * from 交易成功订单表 where 租借人账号='{0}' ", account);
            DataTable dataTable = SqlHelper.ExecuteDataTable(s);
            result.Data = dataTable;
            result.Status = ApiStatusCode.OK1;
            result.Message = "查询成功";
            return result;
        }

        /// <summary>
        /// 退房
        /// </summary>
        /// <param name="account"></param>
        /// <param name="uaccount"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<string> RetentHouse(string account,string uaccount)
        {
            int rowIndex = 0;
            var result = new BaseDataPackage<string>();
            string number = account;
            string sql = string.Format("select * from 租房登记表 where 账号='{0}'", number);
            DataTable dt = SqlHelper.ExecuteDataTable(sql);
            //从 租房登记表里 移除该条租房记录，并往 已完成订单表  里录入该条租房记录,
            //并在 租房房间表 里设置 该账号的状态为空闲
            string s1 = string.Format("delete  from 租房登记表 where 账号='{0}'", number);
            int r1 = SqlHelper.ExecuteNonQuery(s1);

            string orderId = dt.Rows[rowIndex]["订单号"].ToString().Trim();
            string houseNumber = dt.Rows[rowIndex]["账号"].ToString().Trim();
            string price = dt.Rows[rowIndex]["租价"].ToString().Trim();
            string id = dt.Rows[rowIndex]["租借人账号"].ToString().Trim(); //租借人账号
            string name = dt.Rows[rowIndex]["租借人姓名"].ToString().Trim();
            string phone = dt.Rows[rowIndex]["租借人电话"].ToString().Trim();
            DateTime time1;
            DateTime.TryParse(dt.Rows[rowIndex]["租出时间"].ToString(), out time1);
            DateTime time2 = DateTime.Now;//归还时间
            int hours = time2.Subtract(time1).Hours; //共租借的小时
            string moneyIn = (int.Parse(price) * hours).ToString();//应收款额
            string s2 = string.Format(@"insert into 交易成功订单表(订单号,账号,租价,租借人账号,租借人姓名,租借人电话,
租出时间,归还时间,共借小时数,应收金额) values('{0}','{1}','{2}','{3}','{4}','{5}',
'{6}','{7}','{8}','{9}')", orderId, houseNumber, price, id, name, phone, time1,
                                   time2, hours, moneyIn);
            int r2 = SqlHelper.ExecuteNonQuery(s2);
            string s3 = string.Format("update 租房房间表 set 状态='{0}' where 账号='{1}'", "空闲", number);
            int r3 = SqlHelper.ExecuteNonQuery(s3);
            if (r1 > 0 && r2 > 0 && r3 > 0)
            {
                result.Data = "RETURNOK";
                result.Status = ApiStatusCode.OK1;
                result.Message = "归还成功，扣除账户余额" + moneyIn + "元";

                string sql2 = string.Format("select * from 会员表 where 账号='{0}'", uaccount);
                DataTable dt2 = SqlHelper.ExecuteDataTable(sql2);
                string yue = dt2.Rows[0]["余额"].ToString().Trim();
                string allmoney = (int.Parse(yue) - int.Parse(moneyIn)).ToString();
                //更新账户余额
                string s4 = string.Format("update 会员表 set 余额='{0}' where 账号='{1}'", allmoney, uaccount);
                int r4 = SqlHelper.ExecuteNonQuery(s4);
                return result;
            }


            result.Data = "EXCEPTION";
            result.Status = ApiStatusCode.EXCEPTION;
            result.Message = "网络故障";
            return result;
        }
        
        
        /// <summary>
        /// 查询正在租房
        /// </summary>
        /// <param name="account"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<DataTable> GetRetentingRecord(string account)
        {
            var result = new BaseDataPackage<DataTable>();
            string s = string.Format("select * from 租房登记表 where 租借人账号='{0}' ", account);
            DataTable dataTable = SqlHelper.ExecuteDataTable(s);
            result.Data = dataTable;
            result.Status = ApiStatusCode.OK1;
            result.Message = "查询成功";
            return result;
        }

        /// <summary>
        /// 租房
        /// </summary>
        /// <param name="Haccount"></param>
        /// <param name="Uaccount"></param>
        /// <returns></returns>
        [HttpGet]
        public BaseDataPackage<string> RetenalHouse(string Haccount, string Uaccount)
        {

            string houseId = Haccount; //租借的账号
            string userId = Uaccount;//租借人的会员账号
            var result = new BaseDataPackage<string>();

            string s1 = string.Format("select * from 租房房间表 where 账号='{0}'", houseId);
            DataTable dt1 = SqlHelper.ExecuteDataTable(s1);//要租借的账号的信息
            string s2 = string.Format("select * from 会员表 where 账号='{0}'", userId);
            DataTable dt2 = SqlHelper.ExecuteDataTable(s2);//租借人 的 会员信息
            if (int.Parse(dt2.Rows[0]["余额"].ToString().Trim()) <= 0)
            {
                result.Data = "PAYFAILED";
                result.Status = ApiStatusCode.PAYFAIL;
                result.Message = "抱歉，余额不足！";
                return result;
            }
            string s5 = string.Format("select * from 密码表 where 账号='{0}'", houseId);
            DataTable dt5 = SqlHelper.ExecuteDataTable(s5);//租借账号的密码信息
            if (dt1.Rows.Count == 0 || dt2.Rows.Count == 0)
            {
                result.Data = "NOTEXSIT";
                result.Status = ApiStatusCode.FAIL;
                result.Message = "不存在该账号或会员!";
                return result;
            }
            else
            {
                //先确定订单号，订单号格式：日期_时间_序号，每天的序号都是从1开始
                string orderNum;//订单号
                DataTable d = SqlHelper.ExecuteDataTable("select * from 租房登记表");
                if (d.Rows.Count == 0)//登记表里还没有任何订单记录
                {
                    orderNum = DateTime.Now.ToShortDateString() + "_" + DateTime.Now.ToLongTimeString() + "_" + 1.ToString();
                }
                else
                {
                    string str = d.Rows[d.Rows.Count - 1]["订单号"].ToString();
                    string[] nums = str.Split(new char[] { '_' });
                    if (nums[0].Trim() == DateTime.Now.ToShortDateString().Trim())
                    {
                        //租房登记表里最后一个订单的日期等于当前日期,序号就紧接着排下去
                        int n = int.Parse(nums[nums.Length - 1].Trim());
                        orderNum = DateTime.Now.ToShortDateString() + "_" + DateTime.Now.ToLongTimeString() + "_" + (n + 1).ToString();
                    }
                    else//租房登记表里最后一个订单的日期等于当前日期,序号就从1开始
                    {
                        orderNum = DateTime.Now.ToShortDateString() + "_" + DateTime.Now.ToLongTimeString() + "_" + 1.ToString();
                    }
                }
                //在租房之前，先确定该房间号没有被出租
                string sqlBike = string.Format("select * from 租房房间表 where 账号='{0}'", houseId);
                DataTable dtBike = SqlHelper.ExecuteDataTable(sqlBike);
                if (dtBike.Rows[0]["状态"].ToString().Trim() != "空闲")
                {
                    result.Data = "HASRETANAL";
                    result.Status = ApiStatusCode.FAIL;
                    result.Message = "该账号户主的房间已出租，请选择其他房间!";
                    return result;
                }
                else
                {
                    string number = dt1.Rows[0]["账号"].ToString().Trim();
                    string price = dt1.Rows[0]["租价 元/时"].ToString().Trim();
                    string id = dt2.Rows[0]["账号"].ToString().Trim(); //租借人账号
                    string name = dt2.Rows[0]["姓名"].ToString().Trim();
                    string phone = dt2.Rows[0]["电话"].ToString().Trim();
                    DateTime time = DateTime.Now;//租借时间
                    string password = dt5.Rows[0]["密码"].ToString().Trim();
                    string sql1 = string.Format(@"insert into 租房登记表
(订单号,账号,密码,租价,租借人账号,租借人姓名,租借人电话,租出时间) values ('{0}','{1}','{2}','{3}','{4}','{5}','{6}','{7}')"
, orderNum, number, password, price, id, name, phone, time);
                    //往 租房登记表里录入 一条租房信息
                    int r1 = SqlHelper.ExecuteNonQuery(sql1);
                    //设置 租房房间表 里该账号的状态为 已出租
                    string sql2 = string.Format("update 租房房间表 set 状态='{0}' where 账号='{1}'", "已出租", houseId);
                    int r2 = SqlHelper.ExecuteNonQuery(sql2);
                    if (r1 > 0 && r2 > 0)
                    {
                        result.Data = "RETENTALSUCC";
                        result.Status = ApiStatusCode.OK1;
                        result.Message = "租赁成功";
                        return result;
                    }
                }

            }
            result.Data = "EXCEPTION";
            result.Status = ApiStatusCode.EXCEPTION;
            result.Message = "查询异常";
            return result;
        }
    }
}