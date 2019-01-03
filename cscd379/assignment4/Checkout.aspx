<%@ Page Title="Checkout" Language="C#" MasterPageFile="./MasterPage.Master" AutoEventWireup="true" CodeFile="Checkout.aspx.cs" Inherits="Assignment4.Checkout" %>
<asp:Content ID="Content1" ContentPlaceHolderID="Item" runat="server">
    <div class="card-BODY"" >
        <div class="card-body" style ="text-align:left">
            <div class="card-body" style ="text-align:left">
                <div class="card-body" style ="text-align:left">
                    <form runat="server">
                        <fieldset>
                            <legend>Customer Info</legend>
                            <br />
                            First Name:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                            <input type="text" runat="server" id="fname" required="required" placeholder="First Name" />
                            <br />
                            Last Name:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                            <input type="text" runat="server" id="lname" required="required" placeholder="Last Name" />
                            <br />
                            Street Address:&nbsp&nbsp
                            <input type="text" runat="server" id="address" required="required" placeholder="Street" />
                            <br />
                            City:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                            <input type="text" runat="server" id="city" required="required" placeholder="City" />
                            <br />
                            State:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                            <asp:DropDownList id="state" runat="server">
                                <asp:ListItem Value="AL"></asp:ListItem>
                                <asp:ListItem Value="AK"></asp:ListItem>
                                <asp:ListItem Value="AZ"></asp:ListItem>
                                <asp:ListItem Value="AR"></asp:ListItem>
                                <asp:ListItem Value="CA"></asp:ListItem>
                                <asp:ListItem Value="CO"></asp:ListItem>
                                <asp:ListItem Value="CT"></asp:ListItem>
                                <asp:ListItem Value="DE"></asp:ListItem>
                                <asp:ListItem Value="FL"></asp:ListItem>
                                <asp:ListItem Value="GA"></asp:ListItem>
                                <asp:ListItem Value="HI"></asp:ListItem>
                                <asp:ListItem Value="ID"></asp:ListItem>
                                <asp:ListItem Value="IL"></asp:ListItem>
                                <asp:ListItem Value="IN"></asp:ListItem>
                                <asp:ListItem Value="IA"></asp:ListItem>
                                <asp:ListItem Value="KS"></asp:ListItem>
                                <asp:ListItem Value="KY"></asp:ListItem>
                                <asp:ListItem Value="LA"></asp:ListItem>
                                <asp:ListItem Value="ME"></asp:ListItem>
                                <asp:ListItem Value="MD"></asp:ListItem>
                                <asp:ListItem Value="MA"></asp:ListItem>
                                <asp:ListItem Value="MI"></asp:ListItem>
                                <asp:ListItem Value="MN"></asp:ListItem>
                                <asp:ListItem Value="MS"></asp:ListItem>
                                <asp:ListItem Value="MO"></asp:ListItem>
                                <asp:ListItem Value="MT"></asp:ListItem>
                                <asp:ListItem Value="NE"></asp:ListItem>
                                <asp:ListItem Value="NV"></asp:ListItem>
                                <asp:ListItem Value="NH"></asp:ListItem>
                                <asp:ListItem Value="NJ"></asp:ListItem>
                                <asp:ListItem Value="NM"></asp:ListItem>
                                <asp:ListItem Value="NY"></asp:ListItem>
                                <asp:ListItem Value="NC"></asp:ListItem>
                                <asp:ListItem Value="ND"></asp:ListItem>
                                <asp:ListItem Value="OH"></asp:ListItem>
                                <asp:ListItem Value="OK"></asp:ListItem>
                                <asp:ListItem Value="OR"></asp:ListItem>
                                <asp:ListItem Value="PA"></asp:ListItem>
                                <asp:ListItem Value="RI"></asp:ListItem>
                                <asp:ListItem Value="SC"></asp:ListItem>
                                <asp:ListItem Value="SD"></asp:ListItem>
                                <asp:ListItem Value="TN"></asp:ListItem>
                                <asp:ListItem Value="TX"></asp:ListItem>
                                <asp:ListItem Value="UT"></asp:ListItem>
                                <asp:ListItem Value="VA"></asp:ListItem>
                                <asp:ListItem Value="WA"></asp:ListItem>
                                <asp:ListItem Value="WV"></asp:ListItem>
                                <asp:ListItem Value="WI"></asp:ListItem>
                                <asp:ListItem Value="WY"></asp:ListItem>
                            </asp:DropDownList>
                            <br />
                            ZIP Code:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                            <input type="text" runat="server" id="zip" required="required" placeholder="ZIP" />
                            <br />
                            Email:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                            <input type="text" runat="server" id="email" required="required" placeholder="example@domain.com" />
                            <br />
                        </fieldset>
                        <asp:Button ID="Continue" Text="Continue" runat="server" OnClick="Continue_Click" />
                    </form>
                </div>
            </div>
        </div>
    </div>
</asp:Content>

