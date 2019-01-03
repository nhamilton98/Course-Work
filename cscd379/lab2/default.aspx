<%@ Page Language="C#" AutoEventWireup="true" CodeFile="default.aspx.cs" Inherits="Lab2._default" %>

<!DOCTYPE html>
<style>
        body {
            background-color:darkgray
        }
    </style>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Age At Graduation</title>
    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
    <script>
        $(document).ready(getServerDate());
        function AgeButton_Click() {
            var bMonth = document.getElementById("bMonthInput").value;
            var gMonth = document.getElementById("gMonthInput").value;
            var bDay = document.getElementById("bDayInput").value;
            var gDay = document.getElementById("gDayInput").value;
            var bYear = document.getElementById("bYearInput").value;
            var gYear = document.getElementById("gYearInput").value;

            var bDate = new Date(parseInt(bYear), parseInt(bMonth), parseInt(bDay));
            var gDate = new Date(parseInt(gYear), parseInt(gMonth), parseInt(gDay));
            var age = (parseInt(gYear) - parseInt(bYear));

            document.getElementById("result").innerHTML = "You will be " + age.toString() + " years old at graduation.";
        }
        function getServerDate() {
            $.ajax({
                type: "POST",
                url: 'default.aspx/GetDate',
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (results) {
                    document.getElementById("curDate").innerHTML = results.d;
                },
                error: function (err) {
                    alert(err.status + " - " + err.statusText);
                }
            })
        }
    </script>
</head>
<body>
    <form id="form" runat="server">
        <img alt="banner" src="basicBanner.jpg" height="100" width="1080" />
        <p>
            <asp:Label ID="curDate" runat="server" Text=""></asp:Label>
            <br />
            <asp:Button ID="Update" runat="server" Text="Update" />
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
                <input id="submit" type="button" value="Submit" onclick="AgeButton_Click()"/>
            </p>
        </div>
        <asp:Label ID="result" runat="server" Text=""></asp:Label>
    </form>
</body>
</html>
