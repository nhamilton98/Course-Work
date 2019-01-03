<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
    <form method="post" action="./Default.aspx" id="form">
        <div class="aspNetHidden">
            <input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwULLTE2MTY2ODcyMjlkZIs2XiHQEv29lbo2mLzxmqkBYaTYelGG1wQ7ut4gjM7n" />
        </div>

        <div class="aspNetHidden">

            <input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="5484060C" />
        </div>
        <header>
            <h1>Simple Form</h1>
        </header>
        <div>
            <fieldset>
                <legend>Customer Info</legend>
                Name:
                <input type="text" name="name" placeholder="Enter your name" />
                <br />
                <br />
                Telephone:
                <input type="tel" name="number" placeholder="Ex: 1-234-567-8910" />
                <br />
                <br />
                Email:
                <input type="email" placeholder="Ex: example@domain.com" />
                <br />
                <br />
            </fieldset>
            <fieldset>
                <legend>Books</legend>
                <input list="books"></input>
                <datalist id="books">
                    <option value="Coding for Dummies"></option>
                    <option value="The Magical World of Parry Hotter"></option>
                    <option value="Do Not Read This Book"></option>
                </datalist>
                Quantity Number:
                <input type="number" min="1" max="5" />
            </fieldset>
            <input id="button" type="button" value="Submit" />
        </div>
    </form>
</body>
</html>
