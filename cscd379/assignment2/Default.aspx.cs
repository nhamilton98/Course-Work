using System;
using System.Collections.Generic;
using System.Data.OleDb;
using System.Drawing;
using System.Linq;
using System.Web;
using System.Web.Configuration;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment2
{
    public partial class Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            OleDbConnection conn;
            OleDbCommand cmd;
            OleDbDataReader invoice;
            OleDbDataReader customer;
            OleDbDataReader lineItem;
            OleDbDataReader inventory;

            string path;

            int totalQ = 0;
            double totalP = 0.0;

            if (IsPostBack == false)
            {
                try
                {
                    //path = WebConfigurationManager.ConnectionStrings["assignment2"].ConnectionString;
                    path = @"Provider=Microsoft.ACE.OLEDB.12.0;Data Source=" + Server.MapPath("") + "\\assignment2.accdb;Persist Security Info=False;";
                    conn = new OleDbConnection();
                    conn.ConnectionString = path;
                    conn.Open();

                    cmd = new OleDbCommand("SELECT * FROM Customer", conn);
                    customer = cmd.ExecuteReader();

                    Table table = new Table();

                    while (customer.Read())
                    {
                        table.Width = 1080;
                        table.BorderStyle = BorderStyle.Dashed;
                        TableRow row = new TableRow();
                        TableCell cell = new TableCell();

                        cmd = new OleDbCommand("SELECT * FROM Invoice WHERE CustomerNumber = " + customer["CustomerNumber"], conn);
                        invoice = cmd.ExecuteReader();

                        addCell(row, cell, "Customer ID: ");
                        addCell_Tab(row, cell, customer, "CustomerNumber");
                        addCell(row, cell, "Contact: ");
                        addCell_Tab(row, cell, customer, "Contact");
                        row.BackColor = Color.LightBlue;
                        table.Rows.Add(row);

                        row = new TableRow();

                        addCell(row, cell, "Billing Address: ");

                        cell = new TableCell();
                        cell.Controls.Add(new LiteralControl(customer["BillingStreet"] + ", " + customer["BillingCity"] + ", " + customer["BillingState"] + " " + customer["BillingZip"] + "\t"));
                        row.Cells.Add(cell);

                        addCell(row, cell, "Shipping Address: ");

                        cell = new TableCell();
                        cell.Controls.Add(new LiteralControl(customer["ShippingStreet"] + ", " + customer["ShippingCity"] + ", " + customer["ShippingState"] + " " + customer["ShippingZip"] + "\t"));
                        row.Cells.Add(cell);

                        addCell(row, cell, "Phone: ");
                        addCell_Tab(row, cell, customer, "Phone");

                        row.BackColor = Color.LightBlue;
                        table.Rows.Add(row);

                        while (invoice.Read())
                        {
                            if (invoice["Status"].ToString().Equals("Open"))
                            {
                                row = new TableRow();

                                addCell(row, cell, "Invoice Number: ");
                                addCell(row, cell, invoice, "InvoiceNumber");
                                addCell(row, cell, "Order Date: ");

                                cell = new TableCell();
                                cell.Controls.Add(new LiteralControl(invoice["OrderMonth"] + "/" + invoice["OrderDay"] + "/" + invoice["OrderYear"]));
                                row.Cells.Add(cell);

                                table.Controls.Add(row);

                                cmd = new OleDbCommand("SELECT * FROM LineItem WHERE InvoiceNumber = " + invoice["InvoiceNumber"], conn);
                                lineItem = cmd.ExecuteReader();

                                while (lineItem.Read())
                                {
                                    row = new TableRow();

                                    cmd = new OleDbCommand("SELECT * FROM Inventory WHERE SKU = " + lineItem["SKU"], conn);
                                    inventory = cmd.ExecuteReader();

                                    if (inventory.Read())
                                    {
                                        addCell(row, cell, "SKU: ");
                                        addCell(row, cell, inventory, "SKU");
                                        addCell(row, cell, "Description: ");
                                        addCell(row, cell, inventory, "Description");
                                        addCell(row, cell, "Quantity: ");

                                        cell = new TableCell();
                                        int quantity = Int32.Parse(lineItem["QuantityOrdered"] + "");
                                        totalQ += quantity;
                                        cell.Controls.Add(new LiteralControl(quantity + ""));
                                        row.Controls.Add(cell);

                                        addCell(row, cell, "Price: $");

                                        cell = new TableCell();
                                        double price = Double.Parse(inventory["UnitPrice"] + "") * quantity;
                                        totalP += price;
                                        cell.Controls.Add(new LiteralControl(price + ""));
                                        row.Controls.Add(cell);

                                        table.Controls.Add(row);
                                    }

                                    inventory.Close();
                                }
                                row = new TableRow();

                                addCell(row, cell, "Total Quantity: ");
                                addCell(row, cell, totalQ.ToString());
                                addCell(row, cell, "Total Price: $");
                                addCell(row, cell, totalP.ToString());

                                table.Controls.Add(row);

                                totalQ = 0;
                                totalP = 0.0;

                                lineItem.Close();
                            }
                        }
                        invoice.Close();

                        Controls.Add(table);
                        Controls.Add(new LiteralControl("<br />"));
                    }
                    customer.Close();
                    conn.Close();

                    this.Controls.Add(table);
                }
                catch (Exception err)
                {
                    ErrorLabel.Text += err.Message;
                }
            }
        }

        public void addCell(TableRow row, TableCell cell, OleDbDataReader data, string field)
        {
            cell = new TableCell();
            cell.Controls.Add(new LiteralControl(data[field] + ""));
            row.Cells.Add(cell);
        }

        public void addCell_Tab(TableRow row, TableCell cell, OleDbDataReader data, string field)
        {
            cell = new TableCell();
            cell.Controls.Add(new LiteralControl(data[field] + "\t"));
            row.Cells.Add(cell);
        }

        public void addCell(TableRow row, TableCell cell, string text)
        {
            cell = new TableCell();
            cell.Controls.Add(new LiteralControl(text));
            row.Cells.Add(cell);
        }
    }
}