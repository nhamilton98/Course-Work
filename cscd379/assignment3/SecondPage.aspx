<%@ Page Language="C#" AutoEventWireup="true" CodeFile="SecondPage.aspx.cs" Inherits="Assignment3.SecondPage" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Second Page</title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <p>
                <img alt="banner" src="basicBanner.jpg" height="100" width="1080" />
                <br />
            </p>
            <asp:Label ID="lblInfo" runat="server" Text=""></asp:Label>
            <br /><br />
            <asp:Panel ID="Panel1" runat="server" BorderWidth="1" BackColor="#FFFFCC">Original Table From First Page:</asp:Panel>
            <br /><br />
            <asp:Panel ID="Panel2" runat="server" BorderWidth="1" BackColor="#66CCFF">New Table Created From The ArrayList<></asp:Panel>
        </div>
    </form>
</body>
</html>
