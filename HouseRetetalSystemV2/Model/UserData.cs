using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HouseRetetalSystemV2.Model
{
    public class UserData
    {
        private string userGuid;
        private string userName;
        private string userAccount;
        private string name;
        private string phone;
        private int cost;

        public string UserGuid
        {
            get;set;
        }
        public string UserName
        {
            get;set;
        }
        public string UserAccount
        {
            get; set;
        } 
        public string Name
        {
            get; set;
        } 
        public string Phone
        {
            get; set;
        } 
        public int Cost
        {
            get; set;
        } 
    }
}