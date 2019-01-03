using System;
using System.Collections.Generic;
using System.Data.OleDb;
using System.Linq;
using System.Web;
using System.Web.Configuration;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment3
{
    public partial class Default : System.Web.UI.Page
    {
        private List<InvoiceLineItem> items;
        private Table table;

        protected void Page_Load(object sender, EventArgs e)
        {
            OleDbConnection conn;
            OleDbCommand cmd;
            OleDbDataReader lineItem;

            if (!IsPostBack)
            {
                Label1.Text = "First Page";

                try
                {
                    this.items = new List<InvoiceLineItem>();

                    //String connString = WebConfigurationManager.ConnectionStrings["assignment3"].ToString();
                    String connString = @"Provider=Microsoft.ACE.OLEDB.12.0;Data Source=" + Server.MapPath("") + "\\assignment3.mdb;Persist Security Info=False;";
                    conn = new OleDbConnection();
                    conn.ConnectionString = connString;
                    conn.Open();
                    cmd = new OleDbCommand("SELECT * FROM InvoiceLineItem", conn);
                    lineItem = cmd.ExecuteReader();

                    while (lineItem.Read())
                    {
                        InvoiceLineItem item = new InvoiceLineItem(int.Parse(lineItem["CustomerID"].ToString()),
                                                                   int.Parse(lineItem["OrderID"].ToString()),
                                                                   lineItem["Description"].ToString(),
                                                                   int.Parse(lineItem["Quantity"].ToString()),
                                                                   double.Parse(lineItem["Price"].ToString()),
                                                                   double.Parse(lineItem["Weight"].ToString()));
                        this.items.Add(item);
                    }

                    lineItem.Close();
                    conn.Close();
                }
                catch (Exception err)
                {
                    lblStatus.Text = err.Message;
                    return;
                }
            }

            try
            {
                if (this.items == null)
                {
                    this.items = (List<InvoiceLineItem>)ViewState["items"];
                }

                this.table = new Table();
                this.table.BorderStyle = BorderStyle.None;

                TableRow row = new TableRow();
                row.BorderStyle = BorderStyle.Solid;
                row.Cells.Add(AddCell("Order ID"));
                row.Cells.Add(AddCell("Item"));
                row.Cells.Add(AddCell("Quantity"));
                row.Cells.Add(AddCell("Price Per Unit"));
                row.Cells.Add(AddCell("Shipping Price Per Unit"));
                this.table.Rows.Add(row);

                foreach (InvoiceLineItem item in this.items)
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

                Page.Controls.Add(this.table);
            }
            catch (Exception err)
            {
                lblStatus.Text = err.Message;
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

        public List<InvoiceLineItem> GetItems()
        {
            return items;
        }

        public Table getTable()
        {
            return this.table;
        }

        protected void Page_PreRender(object sender, EventArgs e)
        {
            try
            {
                ViewState["items"] = this.items;
            }
            catch (Exception err)
            {
                lblStatus.Text = err.Message;
            }
        }

        public string PageName()
        {
            return " -- this is coming from a code-behind method in the first page - Default.aspx";
        }

        protected void LinkButton1_Click(object sender, EventArgs e)
        {
            Server.Transfer("SecondPage.aspx", true);
        }
    }
}