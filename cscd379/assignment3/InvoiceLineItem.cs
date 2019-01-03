using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Assignment3
{
    [Serializable]
    public class InvoiceLineItem
    {
        private int mCusID;
        private int mOrdID;
        private string mDescription;
        private int mQty;
        private double mPrice;
        private double mWeight;

        private const double SHIP_RATE = 1.19;

        public InvoiceLineItem(int cusID, int ordID, string description, int qty, double price, double weight)
        {
            mCusID = cusID;
            mOrdID = ordID;
            mDescription = description;
            mQty = qty;
            mPrice = price;
            mWeight = weight;
        }

        public int OrderID
        {
            get
            {
                return mOrdID;
            }
        }

        public string Description
        {
            get
            {
                return mDescription;
            }
        }

        public int Quantity
        {
            get
            {
                return mQty;
            }
        }

        public double Price
        {
            get
            {
                return mPrice;
            }
        }

        public double Weight
        {
            get
            {
                return mWeight;
            }
        }

        public double Shipping
        {
            get
            {
                return mWeight * SHIP_RATE;
            }
        }
    }
}