using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Assignment4
{
    public class CartItem
    {
        private int ID;
        private string name;
        private int quantity;
        private double price;
        private int weight;
        private double shipping;
        private double totalPrice;

        private const double SHIPPING_COST = 0.46;

        public CartItem(int ID, string name, int quantity, double cost, int weight)
        {
            this.ID = ID;
            this.name = name;
            this.quantity = quantity;
            this.price = cost;
            this.weight = weight * quantity;
            this.shipping = SHIPPING_COST * weight * quantity;
            this.totalPrice = (cost * quantity) + this.shipping;
        }

        public int GetID()
        {
            return this.ID;
        }

        public string GetName()
        {
            return this.name;
        }

        public int GetQauntity()
        {
            return this.quantity;
        }

        public void SetQuantity(int quantity)
        {
            this.quantity = quantity;
        }

        public double GetPrice()
        {
            return this.price;
        }

        public int GetWeight()
        {
            return this.weight;
        }

        public double GetShipping()
        {
            return this.shipping;
        }

        public double GetTotalPrice()
        {
            return this.totalPrice;
        }
    }
}