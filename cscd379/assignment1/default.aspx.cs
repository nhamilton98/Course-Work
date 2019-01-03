using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment1
{
    public partial class _default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            DateTime now = DateTime.Now;
            this.curDate.Text = "It is currently " + now.ToString();
        }

        protected void submit_Click(object sender, EventArgs e)
        {
            int bMonth = Convert.ToInt32(bMonthInput.Text);
            int bDay = Convert.ToInt32(bDayInput.Text);
            int bYear = Convert.ToInt32(bYearInput.Text);
            int gMonth = Convert.ToInt32(gMonthInput.Text);
            int gDay = Convert.ToInt32(gDayInput.Text);
            int gYear = Convert.ToInt32(gYearInput.Text);
            int age;

            if (gMonth <= bMonth)
            {
                if (gMonth == bMonth && bDay < gDay)
                    age = gYear - bYear;
                else
                    age = gYear - bYear - 1;
            }
            else
                age = gYear - bYear;

            result.Text = "You will be " + age + " years old at graduation.";
        }
    }
}