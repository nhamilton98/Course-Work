<%@ Page Title="Confirmation" Language="C#" MasterPageFile="./MasterPage.Master" AutoEventWireup="true" CodeFile="Confirmation.aspx.cs" Inherits="Assignment4.Confirmation" %>
<asp:Content ID="Content1" ContentPlaceHolderID="Item" runat="server">
    <div class="card-BODY"" >
        <div class="card-body" style ="text-align:center">
            <div class="card-body" style ="text-align:center">
                <div class="card-body" style ="text-align:center">
                    <form runat="server">
                        <asp:Label ID="Heading" runat="server" Text="Order Confirmation" Font-Bold="true" Font-Size="Large"></asp:Label>
                        <br />
                        <asp:Label ID="OrderID" runat="server" Text="" Font-Underline="True" Font-Size="Medium"></asp:Label>
                        <asp:Table ID="Table" runat="server" CellPadding="10" CellSpacing="1"></asp:Table>
                        <br />
                        <asp:Button ID="ContinueShopping" Text="Continue Shopping" runat="server" OnClick="ContinueShopping_Click" />
                    </form>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
