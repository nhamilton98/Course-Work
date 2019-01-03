using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment4
{
    public partial class Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            SqlConnection conn;
            SqlCommand cmd;
            SqlDataReader itemReader;

            conn = new SqlConnection("Data Source=SQL7001.site4now.net;Initial Catalog=DB_A38E92_nhamilton;User Id=DB_A38E92_nhamilton_admin;Password=Jrjbcswh4;");

            try
            {
                conn.Open();
                cmd = new SqlCommand("SELECT * FROM Item");
                cmd.Connection = conn;
                itemReader = cmd.ExecuteReader();

                int itemCount = 0;

                int id;
                string nameTag;
                double price;
                string priceTag;
                string thumbnailTag;
                string descriptionTag;

                while (itemReader.Read())
                {
                    id = Int32.Parse(itemReader["itemID"].ToString());
                    nameTag = "<a href=\"Detail.aspx?ID=" + id + "\">" + itemReader["name"].ToString() + "</a>";
                    thumbnailTag = "<a href = \"Detail.aspx?ID=" + id + "\"><img class=\"card-img-top\" src=\"" + itemReader["thumbnail"].ToString() + "\" alt=\"\"></a>";

                    price = Double.Parse(itemReader["price"].ToString());
                    priceTag = "<h5> $" + price + ".00 </h5>";

                    descriptionTag = "<p class=\"card-text\">" + itemReader["description"] + "</p>";

                    if (itemCount == 0)
                        addMalibu(nameTag, thumbnailTag, priceTag, descriptionTag);
                    else if (itemCount == 1)
                        addCruze(nameTag, thumbnailTag, priceTag, descriptionTag);
                    else if (itemCount == 2)
                        addFusion(nameTag, thumbnailTag, priceTag, descriptionTag);
                    else if (itemCount == 3)
                        addExplorer(nameTag, thumbnailTag, priceTag, descriptionTag);
                    else if (itemCount == 4)
                        addRam(nameTag, thumbnailTag, priceTag, descriptionTag);
                    else if (itemCount == 5)
                        addChallenger(nameTag, thumbnailTag, priceTag, descriptionTag);

                    itemCount++;
                }

                HttpCookie cart = new HttpCookie("Cart Items");
            }
            catch (Exception err)
            {
                ErrorLabel.Text = err.Message;
            }
            finally
            {
                itemReader = null;

                if (conn != null)
                    conn.Close();
            }
        }

        private void addMalibu(string name, string pic, string price, string desc)
        {
            MalibuName.Controls.Add(new LiteralControl(name));
            MalibuImg.Controls.Add(new LiteralControl(pic));
            MalibuPrice.Controls.Add(new LiteralControl(price));
            MalibuDesc.Controls.Add(new LiteralControl(desc));
        }

        private void addCruze(string name, string pic, string price, string desc)
        {
            CruzeName.Controls.Add(new LiteralControl(name));
            CruzeImg.Controls.Add(new LiteralControl(pic));
            CruzePrice.Controls.Add(new LiteralControl(price));
            CruzeDesc.Controls.Add(new LiteralControl(desc));
        }

        private void addFusion(string name, string pic, string price, string desc)
        {
            FusionName.Controls.Add(new LiteralControl(name));
            FusionImg.Controls.Add(new LiteralControl(pic));
            FusionPrice.Controls.Add(new LiteralControl(price));
            FusionDesc.Controls.Add(new LiteralControl(desc));
        }

        private void addExplorer(string name, string pic, string price, string desc)
        {
            ExplorerName.Controls.Add(new LiteralControl(name));
            ExplorerImg.Controls.Add(new LiteralControl(pic));
            ExplorerPrice.Controls.Add(new LiteralControl(price));
            ExplorerDesc.Controls.Add(new LiteralControl(desc));
        }

        private void addRam(string name, string pic, string price, string desc)
        {
            RamName.Controls.Add(new LiteralControl(name));
            RamImg.Controls.Add(new LiteralControl(pic));
            RamPrice.Controls.Add(new LiteralControl(price));
            RamDesc.Controls.Add(new LiteralControl(desc));
        }

        private void addChallenger(string name, string pic, string price, string desc)
        {
            ChallengerName.Controls.Add(new LiteralControl(name));
            ChallengerImg.Controls.Add(new LiteralControl(pic));
            ChallengerPrice.Controls.Add(new LiteralControl(price));
            ChallengerDesc.Controls.Add(new LiteralControl(desc));
        }
    }
}