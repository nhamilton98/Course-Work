<%@ Page Title="Wholesale Vehicle Supply" Language="C#" MasterPageFile="./MasterPage.Master" AutoEventWireup="true" CodeFile="Detail.aspx.cs" Inherits="Assignment4.Detail" %>

<asp:Content ID="Content2" ContentPlaceHolderID="Item" runat="server">
    <div class="card mt-4">
        <asp:PlaceHolder ID="ItemImg" runat="server"></asp:PlaceHolder>
    <div class="card-body" style ="text-align:center"?>
        <asp:PlaceHolder ID="ItemName" runat="server"></asp:PlaceHolder>
    </div>
    <div class="card-body">
        <asp:PlaceHolder ID="ItemDesc" runat="server"></asp:PlaceHolder>
            <div class="card-body" style ="text-align:center"?>
                <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
                <br />
                4.0 stars
            </div>
            <div class="" style ="text-align:center"?>
                <form runat="server">
                    <asp:PlaceHolder ID="ItemPrice" runat="server"></asp:PlaceHolder>
                    Quantity: &nbsp;
                    <asp:DropDownList ID="Quantity" runat="server">
                        <asp:ListItem Value="1"></asp:ListItem>
                        <asp:ListItem Value="2"></asp:ListItem>
                        <asp:ListItem Value="3"></asp:ListItem>
                        <asp:ListItem Value="4"></asp:ListItem>
                    </asp:DropDownList>
                    &nbsp;&nbsp;
                    <asp:Button ID="AddToCart" runat="server" Text="Add To Cart" OnClick="AddToCart_Click" />
                    <br /><br /><br /><br />
                    <asp:label id="ErrorLabel" text="" runat="server"></asp:label>
                </form>
            </div>
               
        </div>
    </div>
</asp:Content>
