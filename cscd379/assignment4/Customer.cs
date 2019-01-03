using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Assignment4
{
    public class Customer
    {
        private int orderID;
        private String fName;
        private String lName;
        private String street;
        private String city;
        private String state;
        private int ZIP;
        private String email;

        public Customer(int orderID, String f, String l, String street, String city, String state, int zip, String email)
        {
            this.orderID = orderID;
            this.fName = f;
            this.lName = l;
            this.street = city;
            this.state = state;
            this.ZIP = zip;
            this.email = email;
        }

        public String GetFirstName()
        {
            return this.fName;
        }

        public String GetLastName()
        {
            return this.lName;
        }

        public int GetOrderID()
        {
            return this.orderID;
        }

        public String GetAddressStreet()
        {
            return this.street;
        }

        public String GetAddressCity()
        {
            return this.city;
        }

        public String GetAddressState()
        {
            return this.state;
        }

        public int GetAddressZIP()
        {
            return this.ZIP;
        }

        public String GetEmail()
        {
            return this.email;
        }
    }
}