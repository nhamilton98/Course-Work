using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment3
{
    public partial class SecondPage : System.Web.UI.Page
    {
        private Table table;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Page.PreviousPage != null)
            {
                lblInfo.Text = "You came from a page titled " + PreviousPage.Title + "<br />";
                Default prevPage = (Default) PreviousPage;
                lblInfo.Text += "Some text from the previous page: " + prevPage.PageName();

                List<InvoiceLineItem> items = prevPage.GetItems();
                this.table = new Table();
                table.BorderStyle = BorderStyle.Dotted;
                table.EnableViewState = true;
                TableRow row = new TableRow();

                row.Cells.Add(AddCell("Order ID"));
                row.Cells.Add(AddCell("Item"));
                row.Cells.Add(AddCell("Quantity"));
                row.Cells.Add(AddCell("Price Per Unit"));
                row.Cells.Add(AddCell("Shipping Price Per Unit"));
                this.table.Rows.Add(row);

                foreach (InvoiceLineItem item in items)
                {
                    row = new TableRow();
                    row.BorderStyle = BorderStyle.Solid;

                    row.Cells.Add(AddCell(item.OrderID.ToString()));
                    row.Cells.Add(AddCell(item.Description));
                    row.Cells.Add(AddCell(item.Quantity.ToString()));
                    row.Cells.Add(AddCell(item.Price.ToString()));
                    row.Cells.Add(AddCell(item.Shipping.ToString()));

                    this.table.Rows.Add(row);
                }

                Panel2.Controls.Add(table);

                Table prev = prevPage.getTable();
                Panel1.Controls.Add(prev);
            }
        }

        private TableCell AddCell(String text)
        {
            TableCell cell = new TableCell();
            cell.BorderStyle = BorderStyle.Solid;
            cell.BorderWidth = 1;
            cell.Text = text;

            return cell;
        }
    }
}