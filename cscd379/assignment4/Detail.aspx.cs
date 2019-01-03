using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment4
{
    public partial class Detail : System.Web.UI.Page
    {
        private int itemID;
        private string itemName;
        private int itemQuantity;
        private double itemPrice;
        private int itemWeight;

        private List<CartItem> cart;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Cart"] == null)
                this.cart = new List<CartItem>();
            else
                this.cart = (List<CartItem>)Session["Cart"];

            SqlConnection conn;
            SqlCommand cmd;
            SqlDataReader itemReader;

            conn = new SqlConnection("Data Source=SQL7001.site4now.net;Initial Catalog=DB_A38E92_nhamilton;User Id=DB_A38E92_nhamilton_admin;Password=Jrjbcswh4;");
            this.itemID = Int32.Parse(Request.QueryString["ID"]);

            try
            {
                conn.Open();
                cmd = new SqlCommand("SELECT * FROM Item WHERE itemID = " + this.itemID);
                cmd.Connection = conn;
                itemReader = cmd.ExecuteReader();

                string itemImgTag;
                string itemNameTag;
                string itemDescTag;
                string itemPriceTag;

                while (itemReader.Read())
                {
                    this.itemName = itemReader["name"].ToString();
                    this.itemWeight = Int32.Parse(itemReader["weight"].ToString());
                    this.itemPrice = Double.Parse(itemReader["price"].ToString());

                    itemImgTag = "<img class=\"card-img-top img-fluid\" src=\"" + itemReader["card"].ToString() + "\" alt=\"\">";
                    itemNameTag = "<h2>" + this.itemName + "</h2>";
                    itemDescTag = itemReader["cardDescription"].ToString(); ;
                    itemPriceTag = "<h4> $" + this.itemPrice + ".00</h4>";

                    ItemImg.Controls.Add(new LiteralControl(itemImgTag));
                    ItemName.Controls.Add(new LiteralControl(itemNameTag));
                    ItemDesc.Controls.Add(new LiteralControl(itemDescTag));
                    ItemPrice.Controls.Add(new LiteralControl(itemPriceTag));
                }
            }
            catch(Exception err)
            {
                ErrorLabel.Text = err.Message;
            }
            finally
            {
                cmd = null;
                itemReader = null;
                conn.Close();
            }
        }

        public void AddToCart_Click(object sender, EventArgs e)
        {
            bool inCart = false;
            this.itemQuantity = Int32.Parse(Quantity.SelectedValue);
            
            foreach (CartItem item in this.cart)
            {
                if (item.GetID().Equals(this.itemID))
                {
                    inCart = true;
                    item.SetQuantity(this.itemQuantity);
                }
            }

            if (!inCart)
                this.cart.Add(new CartItem(this.itemID, this.itemName, this.itemQuantity, this.itemPrice, this.itemWeight));

            Session["Cart"] = this.cart;
            Response.Redirect("Cart.aspx");
        }
    }
}