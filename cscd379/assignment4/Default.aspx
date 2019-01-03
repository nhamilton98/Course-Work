<%@ Page Title="Wholesale Supply Home" Language="C#" MasterPageFile="./Master.Master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="Assignment4.Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="Malibu" runat="server">
    <div class="col-lg-4 col-md-6 mb-4">
        <div class="card h-100">
            <asp:PlaceHolder ID="MalibuImg" runat="server"></asp:PlaceHolder>
            <div class="card-body">
                <h4 class="card-title">
                    <asp:PlaceHolder ID="MalibuName" runat="server"></asp:PlaceHolder>
                </h4>
                <h5>
                    <asp:PlaceHolder ID="MalibuPrice" runat="server"></asp:PlaceHolder>
                </h5>
                <asp:PlaceHolder ID="MalibuDesc" runat="server"></asp:PlaceHolder>
                <asp:Label ID="ErrorLabel" Text="" runat="server"></asp:Label>
            </div>
            <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
            </div>
        </div>
    </div>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="Cruze" runat="server">
    <div class="col-lg-4 col-md-6 mb-4">
        <div class="card h-100">
            <asp:PlaceHolder ID="CruzeImg" runat="server"></asp:PlaceHolder>
            <div class="card-body">
                <h4 class="card-title">
                    <asp:PlaceHolder ID="CruzeName" runat="server"></asp:PlaceHolder>
                </h4>
                <h5>
                    <asp:PlaceHolder ID="CruzePrice" runat="server"></asp:PlaceHolder>
                </h5>
                <asp:PlaceHolder ID="CruzeDesc" runat="server"></asp:PlaceHolder>
            </div>
            <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
            </div>
        </div>
    </div>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="Fusion" runat="server">
    <div class="col-lg-4 col-md-6 mb-4">
        <div class="card h-100">
            <asp:PlaceHolder ID="FusionImg" runat="server"></asp:PlaceHolder>
            <div class="card-body">
                <h4 class="card-title">
                    <asp:PlaceHolder ID="FusionName" runat="server"></asp:PlaceHolder>
                </h4>
                <h5>
                    <asp:PlaceHolder ID="FusionPrice" runat="server"></asp:PlaceHolder>
                </h5>
                <asp:PlaceHolder ID="FusionDesc" runat="server"></asp:PlaceHolder>
            </div>
            <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
            </div>
        </div>
    </div>
</asp:Content>

<asp:Content ID="Content4" ContentPlaceHolderID="Explorer" runat="server">
    <div class="col-lg-4 col-md-6 mb-4">
        <div class="card h-100">
            <asp:PlaceHolder ID="ExplorerImg" runat="server"></asp:PlaceHolder>
            <div class="card-body">
                <h4 class="card-title">
                    <asp:PlaceHolder ID="ExplorerName" runat="server"></asp:PlaceHolder>
                </h4>
                <h5>
                    <asp:PlaceHolder ID="ExplorerPrice" runat="server"></asp:PlaceHolder>
                </h5>
                <asp:PlaceHolder ID="ExplorerDesc" runat="server"></asp:PlaceHolder>
            </div>
            <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
            </div>
        </div>
    </div>
</asp:Content>

<asp:Content ID="Content5" ContentPlaceHolderID="Ram" runat="server">
    <div class="col-lg-4 col-md-6 mb-4">
        <div class="card h-100">
            <asp:PlaceHolder ID="RamImg" runat="server"></asp:PlaceHolder>
            <div class="card-body">
                <h4 class="card-title">
                    <asp:PlaceHolder ID="RamName" runat="server"></asp:PlaceHolder>
                </h4>
                <h5>
                    <asp:PlaceHolder ID="RamPrice" runat="server"></asp:PlaceHolder>
                </h5>
                <asp:PlaceHolder ID="RamDesc" runat="server"></asp:PlaceHolder>
            </div>
            <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
            </div>
        </div>
    </div>
</asp:Content>

<asp:Content ID="Content6" ContentPlaceHolderID="Challenger" runat="server">
    <div class="col-lg-4 col-md-6 mb-4">
        <div class="card h-100">
            <asp:PlaceHolder ID="ChallengerImg" runat="server"></asp:PlaceHolder>
            <div class="card-body">
                <h4 class="card-title">
                    <asp:PlaceHolder ID="ChallengerName" runat="server"></asp:PlaceHolder>
                </h4>
                <h5>
                    <asp:PlaceHolder ID="ChallengerPrice" runat="server"></asp:PlaceHolder>
                </h5>
                <asp:PlaceHolder ID="ChallengerDesc" runat="server"></asp:PlaceHolder>
            </div>
            <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
            </div>
        </div>
    </div>
</asp:Content>

