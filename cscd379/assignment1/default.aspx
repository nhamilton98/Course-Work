<%@ Page Language="C#" AutoEventWireup="true" CodeFile="default.aspx.cs" Inherits="Assignment1._default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Age At Graduation</title>
    <style type="text/css">
        #bDayInput {
            margin-left: 0px;
        }
        body {
            background-color:darkgray
        }
    </style>
</head>
<body>
    <form id="form" runat="server">
        <img alt="banner" src="basicBanner.jpg" height="100" width="1080" />
        <p>
            <asp:Label ID="curDate" runat="server" Text=""></asp:Label>
        </p>
        <asp:Label ID="instructions" runat="server" Text="Enter your birth date and graduation date."></asp:Label>
        <div>
            <p>
                <asp:Label ID="birth" runat="server" Text="Birth Date"></asp:Label>
                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:Label ID="grad" runat="server" Text="Graduation Date"></asp:Label>
                <br />
                <asp:Label ID="bMonthLabel" runat="server" Text="Month: "></asp:Label>
                <asp:TextBox ID="bMonthInput" runat="server"></asp:TextBox>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp
                <asp:Label ID="gMonthLabel" runat="server" Text="Month: "></asp:Label>
                <asp:TextBox ID="gMonthInput" runat="server"></asp:TextBox>
                <br />
                <asp:Label ID="bDayLabel" runat="server" Text="Day: "></asp:Label>
                &nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="bDayInput" runat="server"></asp:TextBox>
                &nbsp&nbsp&nbsp&nbsp&nbsp
                <asp:Label ID="gDayLabel" runat="server" Text="Day: "></asp:Label>
                &nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="gDayInput" runat="server"></asp:TextBox>
                <br />
                <asp:Label ID="bYearLabel" runat="server" Text="Year: "></asp:Label>
                &nbsp;&nbsp;
                <asp:TextBox ID="bYearInput" runat="server"></asp:TextBox>
                &nbsp&nbsp&nbsp&nbsp&nbsp;
                <asp:Label ID="gYearLabel" runat="server" Text="Year: "></asp:Label>
                &nbsp;&nbsp;
                <asp:TextBox ID="gYearInput" runat="server"></asp:TextBox>
                <br />
                <asp:Button ID="submit" runat="server" Text="Submit" OnClick="submit_Click" />
            </p>
        </div>
        <asp:Label ID="result" runat="server" Text=""></asp:Label>
    </form>
</body>
</html>
