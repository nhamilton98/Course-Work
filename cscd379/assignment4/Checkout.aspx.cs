using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Net.Mail;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment4
{
    public partial class Checkout : System.Web.UI.Page
    {
        int orderID;
        protected void Page_Load(object sender, EventArgs e)
        {
            email.Attributes["type"] = "email";
            SqlConnection conn;
            SqlCommand cmd;
            SqlDataReader IDReader;

            conn = new SqlConnection("Data Source=SQL7001.site4now.net;Initial Catalog=DB_A38E92_nhamilton;User Id=DB_A38E92_nhamilton_admin;Password=Jrjbcswh4;");

            try
            {
                conn.Open();
                cmd = new SqlCommand("SELECT * FROM OrderID WHERE company = 'Wholesale';", conn);
                IDReader = cmd.ExecuteReader();

                if (IDReader.Read())
                    orderID = Int32.Parse(IDReader["orderID"].ToString());
            }
            catch (Exception err)
            {
                err.ToString();
            }
            finally
            {
                conn.Close();
                IDReader = null;
                cmd = null;
            }
        }

        protected void Continue_Click(object sender, EventArgs e)
        {
            Customer cust = new Customer(orderID, fname.Value, lname.Value, address.Value, city.Value, state.SelectedValue, Int32.Parse(zip.Value), email.Value);

            Session["Customer"] = cust;
            SendEmail(cust);
            Response.Redirect("Confirmation.aspx");
        }

        public void SendEmail(Customer customer)
        {
            List<CartItem> cart = (List<CartItem>)Session["Cart"];
            double subTotal = 0.0, totalShipping = 0.0, total = 0.0;

            MailMessage mail = new MailMessage("postmaster@nhamiltonwebapp.com", customer.GetEmail());
            SmtpClient client = new SmtpClient();
            client.Port = 25;
            client.Host = "mail.nhamiltonwebapp.com";
            client.DeliveryMethod = SmtpDeliveryMethod.Network;
            client.Credentials = new System.Net.NetworkCredential("postmaster@nhamiltonwebapp.com", "Jrjbcswh4");
            client.EnableSsl = false;

            mail.Subject = "No Reply: Vehicle Order Confirmation";
            String body = "Thank you " + customer.GetFirstName() + " for your order! This is your sales receipt. Please keep and store with your records.\n\n" +
                "Order ID: " + this.orderID + "\n\n";

            foreach (CartItem item in cart)
            {
                subTotal += item.GetPrice();
                totalShipping += item.GetShipping();
                body += item.GetQauntity().ToString() + " " + item.GetName() + "\n";
            }
            total = subTotal + totalShipping;

            body += "\nSubtotal: $" + subTotal.ToString("F") + "\n" +
                "Shipping: $" + totalShipping.ToString("F") + "\n" +
                "\nTotal: $" + total.ToString("F");

            mail.Body = body;

            try
            {
                client.Send(mail);
            }
            catch (Exception err)
            {
                err.ToString();
            }
        }
    }
}