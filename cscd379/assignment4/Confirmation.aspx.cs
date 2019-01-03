using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment4
{
    public partial class Confirmation : System.Web.UI.Page
    {
        private List<CartItem> cart = new List<CartItem>();
        private Customer customer;

        protected void Page_Load(object sender, EventArgs e)
        {
            TableHeaders();
            customer = (Customer) Session["Customer"];
            OrderID.Text = "Order ID: " + this.customer.GetOrderID().ToString();

            if (Session["Cart"] != null)
            {
                this.cart = (List<CartItem>)Session["Cart"];
                AddCartItem();
            }
            else
                FinalizeTable();

            SqlConnection conn;
            SqlCommand cmd;
            conn = new SqlConnection("Data Source=SQL7001.site4now.net;Initial Catalog=DB_A38E92_nhamilton;User Id=DB_A38E92_nhamilton_admin;Password=Jrjbcswh4;");

            try
            {
                int id = customer.GetOrderID() + 3;
                conn.Open();
                cmd = new SqlCommand("UPDATE OrderID SET orderID = " + id + " WHERE Company = 'Wholesale';", conn);
                cmd.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                err.ToString();
            }
            finally
            {
                conn = null;
                cmd = null;
            }

            Session["Cart"] = null;
        }

        private void TableHeaders()
        {
            TableRow row = new TableRow();

            row.Cells.Add(AddCell_Header("Name"));
            row.Cells.Add(AddCell_Header("Price"));
            row.Cells.Add(AddCell_Header("Quantity"));
            row.Cells.Add(AddCell_Header("Weight"));
            row.Cells.Add(AddCell_Header("Shipping"));
            row.Cells.Add(AddCell_Header("Total"));
            row.Cells.Add(AddCell_Header(""));

            row.Font.Bold = true;
            row.HorizontalAlign = HorizontalAlign.Left;
            row.VerticalAlign = VerticalAlign.Middle;

            Table.Rows.Add(row);
        }

        private void AddCartItem()
        {
            TableRow row;

            foreach (CartItem item in this.cart)
            {
                row = new TableRow();

                row.Cells.Add(AddCell(item.GetName()));
                row.Cells.Add(AddCell("$" + item.GetPrice().ToString("f")));
                row.Cells.Add(AddCell(item.GetQauntity().ToString()));
                row.Cells.Add(AddCell(item.GetWeight().ToString() + " lb"));
                row.Cells.Add(AddCell("$" + item.GetShipping().ToString("F")));
                row.Cells.Add(AddCell("$" + item.GetTotalPrice().ToString("F")));

                Table.Rows.Add(row);
            }

            FinalizeTable();
        }

        private void FinalizeTable()
        {
            double totalCost = 0.0;
            int totalQuantity = 0;
            int totalWeight = 0;
            double totalShipping = 0.0;

            foreach (CartItem item in this.cart)
            {
                totalQuantity += item.GetQauntity();
                totalWeight += item.GetWeight();
                totalShipping += item.GetShipping();
                totalCost += item.GetPrice() * totalQuantity;
            }
            totalCost += totalShipping;

            TableRow row = new TableRow();

            row.Cells.Add(AddCell(""));
            TableCell cell = AddCell("Total:");
            cell.Font.Bold = true;
            row.Cells.Add(cell);
            row.Cells.Add(AddCell(totalQuantity.ToString()));
            row.Cells.Add(AddCell(totalWeight.ToString() + " lb"));
            row.Cells.Add(AddCell("$" + totalShipping.ToString("F")));
            row.Cells.Add(AddCell("$" + totalCost.ToString("F")));

            Table.Rows.Add(row);
        }

        private TableCell AddCell(string text)
        {
            TableCell cell = new TableCell();

            cell.BorderStyle = BorderStyle.None;
            cell.HorizontalAlign = HorizontalAlign.Left;
            cell.Text = text;

            return cell;
        }

        private TableCell AddCell_Header(string text)
        {
            TableCell cell = new TableCell();
            cell.BorderStyle = BorderStyle.None;

            if (text == "Name" || text == "")
                cell.Wrap = true;
            else
            {
                cell.Wrap = false;
                cell.Width = 100;
            }

            cell.Font.Bold = true;
            cell.HorizontalAlign = HorizontalAlign.Left;
            cell.Text = text;

            return cell;
        }

        public void ContinueShopping_Click(object sender, EventArgs e)
        {
            Response.Redirect("Default.aspx");
        }
    }
}