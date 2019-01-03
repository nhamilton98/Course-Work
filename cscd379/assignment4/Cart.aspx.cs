using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment4
{
    public partial class Cart : System.Web.UI.Page
    {
        private List<CartItem> cart = new List<CartItem>();

        protected void Page_Load(object sender, EventArgs e)
        {
            TableHeaders();

            if (Session["Cart"] != null)
            {
                this.cart = (List<CartItem>)Session["Cart"];
                AddCartItem();
            }
            else
                FinalizeTable();
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
            TableCell cell;
            Button button;

            foreach(CartItem item in this.cart)
            {
                row = new TableRow();

                row.Cells.Add(AddCell(item.GetName()));
                row.Cells.Add(AddCell("$" + item.GetPrice().ToString("f")));
                row.Cells.Add(AddCell(item.GetQauntity().ToString()));
                row.Cells.Add(AddCell(item.GetWeight().ToString() + " lb"));
                row.Cells.Add(AddCell("$" + item.GetShipping().ToString("F")));
                row.Cells.Add(AddCell("$" + item.GetTotalPrice().ToString("F")));

                button = new Button();
                button.ID = "change " + item.GetID().ToString();
                button.Text = "Change";
                button.Click += new EventHandler(Change_Click);
                button.Attributes.Add("runat", "server");

                cell = new TableCell();
                cell.Controls.Add(button);
                row.Cells.Add(cell);

                button = new Button();
                button.ID = item.GetID().ToString();
                button.Text = "Remove";
                button.Click += new EventHandler(Remove_Click);
                button.Attributes.Add("runat", "server");

                cell = new TableCell();
                cell.Controls.Add(button);
                row.Cells.Add(cell);

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
            Session["Cart"] = this.cart;
            Response.Redirect("Default.aspx");
        }

        public void Checkout_Click(object sender, EventArgs e)
        {
            if (!(this.cart.Count() == 0))
            {
                Session["Cart"] = this.cart;
                Response.Redirect("Checkout.aspx");
            }
        }

        public void Remove_Click(object sender, EventArgs e)
        {
            Button button = (Button) sender;
            int ID = Int32.Parse(button.ID);

            for (int x = 0; x < this.cart.Count; x++)
                if (this.cart[x].GetID() == ID)
                    this.cart.RemoveAt(x);

            Session["Cart"] = this.cart;
            Response.Redirect("Cart.aspx");
        }

        public void Change_Click(object sender, EventArgs e)
        {
            Button button = (Button) sender;
            String ID = button.ID;
            String[] IDsplit = ID.Split(' ');
            Session["Cart"] = this.cart;
            Response.Redirect("Detail.aspx?ID=" + IDsplit[1]);
        }
    }
}