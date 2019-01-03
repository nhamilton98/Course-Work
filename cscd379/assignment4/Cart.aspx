<%@ Page Title="Your Cart" Language="C#" MasterPageFile="./MasterPage.Master" AutoEventWireup="true" CodeFile="Cart.aspx.cs" Inherits="Assignment4.Cart" %>
<asp:Content ID="Content1" ContentPlaceHolderID="Item" runat="server">
    <div class="card-BODY"" >
        <div class="card-body" style ="text-align:center">
            <div class="card-body" style ="text-align:center">
                <div class="card-body" style ="text-align:center">
                    <form runat="server">
                        <asp:Table ID="Table" runat="server" CellPadding="10" CellSpacing="1"></asp:Table>
                        <br />
                        <asp:Button ID="ContinueShopping" Text="Continue Shopping" runat="server" OnClick="ContinueShopping_Click" />
                        <asp:Button ID="Checkout" Text="Checkout" runat="server" OnClick="Checkout_Click" />
                    </form>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
