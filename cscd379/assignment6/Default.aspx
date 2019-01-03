<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="Assignment6.Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE html>

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MVC In Web Development</title>

    <!-- Bootstrap core CSS -->
    <link href="template/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="template/css/blog-post.css" rel="stylesheet">

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Contact</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <!-- Post Content Column -->
        <div class="col-lg-8">

          <!-- Title -->
          <h1 class="mt-4">Model View Controller In ASP.NET</h1>

          <!-- Author -->
          <p class="lead">
            by
            <a href="#">Nathan Hamilton</a>
          </p>

          <hr>

          <!-- Date/Time -->
          <p>Posted on June 13, 2018 at 8:21 AM</p>

          <hr>

          <!-- Preview Image -->
          <img class="img-fluid rounded" src="./mvc.jpg" alt="">

          <hr>

          <!-- Post Content -->
          <p>In the world of software development, design patterns are the key to consistent and efficient programming. Of the dozens of widely used design patterns, Model View Controller (or MVC for short) is arguably the most important.</p>

          <p>MVC is made up of three main parts. That's right, you guessed it, they're model, view, and controller. Each individual part serves an important role, and together, they are used to create applications that we interact with every day.</p>

          <p>Let's take a look at Model. Model deals with the state of an application. Just about every application has some form of state management. For example, a flight simulator would use a complex algorithm to constantly save the coordinates, altitude, and movement of the controllable aircraft. Or we could look at a shopping website, which would use state management to preserve your shopping cart. All of these fit into the Model part of MVC.</p>

          <p>Next, we'll dive into View. View is perhaps the most familiar to most people. View deals with the user interface (or GUI) of an application. The View is a visual representation of the Model. Let's take a look at video games. The View would be the game world, or the heads up display. For a website, this would be the actual webpage itself.</p>

          <p>The final part of MVC is Controller. Controller is the interactive side of the View. Controller is what allows the user to use an application in the way it was intended. Generally, this interactivity corresponds directly with the changing of state, which correlates with the Model of the MVC. With a television, the remote control is the Controller (obviously). The pressing of buttons on the remote changes the state of the television, which changes what is displayed on the screen. For a website, Controller is simply clicking your mouse, or typing on the keyboard.</p>

          <p>The applicability of MVC in web development is nothing to scoff at. In fact, very few large scale websites are developed without some form of MVC under the hood (or as the hood, as far as View is concerned). Despite being created for desktop applications originally, .Net has adopted MVC as a template directly in the .Net framework. This allows for seemless integration of MVC into any web form or .aspx application.</p>

          <p>When a web form is created with the .Net framework, MVC is directly implemented. Web forms consist of a .aspx file, which uses HTML5 and CSS to produce a visual webpage for the web application (View). The .aspx page is also where buttons and input boxes are implemented. The data transmitted by these inputs is dealt with by the code in the code file of the .aspx file (Controller). This code file can be in many different popular coding languages, but it's typically C# or Visual Basic. The automatic MVC implementation of the web form allows for the HTML5 and CSS objects within the .aspx file to be directly accessed by the code file. This is how the state of the webpage is preserved and changed (Model).</p>

          <p>The coding involved with ASP.NET Core MVC is very simple. Going back to the earlier discussion of Model, a shopping website uses state management for the preservation and changing of the items you have stored in your cart. Check out this snippet of code...<br />public void SaveSessionState() {<br />&nbsp&nbsp&nbsp Session["Cart"] = this.Cart;<br />}<br />public void GetSessionState() {<br />&nbsp&nbsp&nbsp this.Cart = (List) Session["Cart"];<br />}<br />The first method takes a class attribute called Cart, which is a list of your cart items, and saves it to the Session State, into a key-valued pair, labeled "Cart". The second method simply retrieves that list from the Session State. Typically, the first method would be used on an item page, after clicking "add to cart". The second method would be used on the cart page itself so it can display all your saved items.</p>

          <p>Creating the View of a webpage is the best part. This is where web developers can really let their creativity show. If you right click anywhere on this webpage (or any of your favorite webpages), and click "View Page Source", you can take a look at all of the HTML5 used in the creation of the page. Pretty neat, huh?</p>

          <p>And finally, we have Control. The Control of a web application is what allows it to progress.<br />protected void Submit_Click(object Sender, EventArgs e) {<br />&nbsp&nbsp&nbsp Session["Age"] = InputBox.Value;<br />&nbsp&nbsp&nbsp Server.Transfer("NextPage.aspx");<br />}<br />Suppose we have a webpage that wants to know your age. You type in your age and hit the submit button, which then transfers you to the next page. When clicking the submit button, the above method is called. The input object is accessed, and the value you typed is stored to the Session State labeled "Age". The method then calls Server.Transfer which sends a signal to the server that you want to move on to .../NextPage.aspx. This is how Controller controls the flow and progression of a web application.</p>

          <p>Due to the fact that the user of a web application has little control over the Model and View, Controller tends to be where issues and bugs arise. To have a web application run smoothly and without fault, it requires the user to interact with it perfectly. This means all input must be useable. If a user were to enter "watermelon" into a text box asking for their age, the application could produce very unexpected results if not handled properly in the code. Many HTML5 controls allow for input to be specified as a certain type, like a number. This would solve the issue. Or, within the code itself, it could check if the input is a number, and if not, display a prompt telling the user of the error. This would allow the application to continue on without crashing.</p>

          <p>Now that we've taken an in-depth look at the Model View Controller design pattern and how it is implemented into just about every web application, it's time for you to take to the web, and create web applications of your own!</p>

          <p>Sources:<br />
              <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller">https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller<br /></a>
              <a href="http://www.tutorialsteacher.com/mvc/mvc-architecture">http://www.tutorialsteacher.com/mvc/mvc-architecture<br /></a>
              <a href="https://www.codeproject.com/Articles/54576/Understanding-ASP-NET-MVC-Model-View-Controller-Ar">https://www.codeproject.com/Articles/54576/Understanding-ASP-NET-MVC-Model-View-Controller-Ar<br /></a>
          </p>

          <hr>

        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

          <!-- Search Widget -->
          <div class="card my-4">
            <h5 class="card-header">Search</h5>
            <div class="card-body">
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                  <button class="btn btn-secondary" type="button">Go!</button>
                </span>
              </div>
            </div>
          </div>

          <!-- Categories Widget -->
          <div class="card my-4">
            <h5 class="card-header">Categories</h5>
            <div class="card-body">
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="#">Web Design</a>
                    </li>
                    <li>
                      <a href="#">HTML</a>
                    </li>
                    <li>
                      <a href="#">Freebies</a>
                    </li>
                  </ul>
                </div>
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="#">JavaScript</a>
                    </li>
                    <li>
                      <a href="#">CSS</a>
                    </li>
                    <li>
                      <a href="#">Tutorials</a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>

        </div>

      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Nathan E Hamilton, 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="template/vendor/jquery/jquery.min.js"></script>
    <script src="template/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
