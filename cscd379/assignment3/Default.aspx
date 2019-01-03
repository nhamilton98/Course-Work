<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="Assignment3.Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>First Page</title>
</head>
<body>
    <form id="form1" runat="server" defaultbutton="LinkButton1">
        <p>
            <img alt="banner" src="basicBanner.jpg" height="100" width="1080" />
            <br />
        </p>
        <p>
            <asp:Button ID="btnLoad" runat="server" Text="Postback and Reload Orders from Viewstate" />
        </p>

        <p>
            <asp:LinkButton ID="LinkButton1" runat="server" OnClick="LinkButton1_Click">Second Page with Cross Page Post</asp:LinkButton>
        </p>
        <p>
            <a href="SecondPage.aspx">Second Page without Cross Page Post</a>
            <br />
        </p>
        <p>
            <asp:Label ID="Label1" runat="server" EnableViewState="False"></asp:Label>
            <asp:Label ID="lblStatus" runat="server" Text=""></asp:Label>
        </p>
    </form>
</body>
</html>
