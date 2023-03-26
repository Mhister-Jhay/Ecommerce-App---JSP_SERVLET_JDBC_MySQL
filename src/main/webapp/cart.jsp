<%@ page import="com.example.week6ecommerce.model.Cart" %>
<%@ page import="java.util.List" %>
<%List<Cart> cartList = (List<Cart>) request.getAttribute("cartList");%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <title>Jhay Commerce | cart</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="Free HTML Templates" name="keywords">
  <meta content="Free HTML Templates" name="description">

  <!-- Favicon -->
  <link href="img/favicon.ico" rel="icon">

  <!-- Google Web Fonts -->
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

  <!-- Font Awesome -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

  <!-- Libraries Stylesheet -->
  <link href="lib/animate/animate.min.css" rel="stylesheet">
  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

  <!-- Customized Bootstrap Stylesheet -->
  <link href="css/style.css" rel="stylesheet">
</head>

<body>
<input type="hidden" id="statusCart" value="<%= request.getAttribute("statusCart")%>">
<!-- Topbar Start -->
<div class="container-fluid">
  <div class="row bg-secondary py-1 px-xl-5">
    <div class="col-lg-6 d-none d-lg-block">
      <div class="d-inline-flex align-items-center h-100">
        <a class="text-body mr-3" href="contact.jsp">Contact Us</a>
      </div>
    </div>
    <div class="col-lg-6 text-center text-lg-right">
      <div class="d-inline-flex align-items-center">
        <div class="btn-group">
          <% if (session.getAttribute("logged_in") != null) { %>
          <a href="Logout">
            <button type="button" class="nav-link">Logout</button>
          </a>
          <% } else { %>
          <button type="button" class="nav-link dropdown-toggle" data-toggle="dropdown">Login/Register</button>
          <div class="dropdown-menu position-absolute rounded-0 border-0 m-0">
            <a href="login.jsp"><button class="dropdown-item" type="button">Login</button></a>
            <a href="registration.jsp"><button class="dropdown-item" type="button">Register</button></a>

          </div>
          <% } %>

        </div>
      </div>
    </div>
  </div>
  <div class="row align-items-center justify-content-center bg-light py-3 px-xl-5 d-none d-lg-flex">
    <a href="" class="text-decoration-none">
      <span class="h1 text-uppercase text-primary bg-dark px-2">Jhay</span>
      <span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Commerce</span>
    </a>
  </div>
</div>
<!-- Topbar End -->


<!-- Navbar Start -->
<div class="container-fluid bg-dark mb-30">
  <div class="row px-xl-5">
    <div class="col-lg-3 d-none d-lg-block">
      <a class="btn d-flex align-items-center justify-content-between bg-primary w-100" data-toggle="collapse" style="height: 65px; padding: 0 30px;">
        <% if (session.getAttribute("logged_in") != null) { %>
        <h5 class="text-dark m-0">Hi, <%= session.getAttribute("firstName") %></h5>
        <% } else{%>
        <h5 class="text-dark m-0">Welcome</h5>
        <% } %>
      </a>
    </div>
    <div class="col-lg-9">
      <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-0">
        <a href="index.jsp" class="text-decoration-none d-block d-lg-none">
          <span class="h1 text-uppercase text-dark bg-light px-2">Jhay</span>
          <span class="h1 text-uppercase text-light bg-primary px-2 ml-n1">Commerce</span>
        </a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
          <div class="navbar-nav mr-auto py-0">
            <a href="index.jsp" class="nav-item nav-link active">Home</a>
            <a href="Product" class="nav-item nav-link">Products</a>
            <% if (session.getAttribute("firstName")!=null&&session.getAttribute("firstName").equals("Admin")) { %>
            <a href="AdminHome.jsp" class="nav-item nav-link">Admin Home</a>
            <%}%>
          </div>
          <div class="navbar-nav ml-auto py-0 d-none d-lg-flex align-items-center">
            <% if (session.getAttribute("logged_in") != null) { %>
            <form method="get" action="wishlist" class="form-inline">
              <input type="hidden" name="customer_id" value="<%=session.getAttribute("customer_id")%>" class="form-input">
              <button type="submit" class="btn px-0">
                <i class="fas fa-heart text-primary"></i>
                <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">Wishlist</span>
              </button>
            </form>
            <form method="get" action="cart" class="form-inline">
              <input type="hidden" name="customer_id" value="<%=session.getAttribute("customer_id")%>" class="form-input">
              <button type="submit" class="btn px-0 ml-3">
                <i class="fas fa-shopping-cart text-primary"></i>
                <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">Cart</span>
              </button>
            </form>
            <form method="get" action="orders" class="form-inline">
              <input type="hidden" name="customer_id" value="<%=session.getAttribute("customer_id")%>" class="form-input">
              <button type="submit" class="btn px-0 ml-3">
                <i class="fas fa-file-invoice text-primary"></i>
                <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">View All Orders</span>
              </button>
            </form>
            <% } else{%>
            <form method="get" action="login.jsp" class="form-inline">
              <input type="hidden" name="customer_id" value="<%=session.getAttribute("customer_id")%>" class="form-input">
              <button type="submit" class="btn px-0">
                <i class="fas fa-heart text-primary"></i>
                <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">Wishlist</span>
              </button>
            </form>
            <form method="get" action="login.jsp" class="form-inline">
              <input type="hidden" name="customer_id" value="<%=session.getAttribute("customer_id")%>" class="form-input">
              <button type="submit" class="btn px-0 ml-3">
                <i class="fas fa-shopping-cart text-primary"></i>
                <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">Cart</span>
              </button>
            </form>
            <form method="get" action="login.jsp" class="form-inline">
              <input type="hidden" name="customer_id" value="<%=session.getAttribute("customer_id")%>" class="form-input">
              <button type="submit" class="btn px-0 ml-3">
                <i class="fas fa-file-invoice text-primary"></i>
                <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">View All Orders</span>
              </button>
            </form>
            <% } %>
          </div>
        </div>
      </nav>
    </div>
  </div>
</div>
<!-- Navbar End -->


<!-- Breadcrumb Start -->
<div class="container-fluid">
  <div class="row px-xl-5">
    <div class="col-12">
      <nav class="breadcrumb align-items-center bg-light mb-30">
        <a href="viewCart"><h5 class="font-weight-semi-bold m-0">My Shopping Cart</h5></a>
      </nav>
    </div>
  </div>
</div>
<!-- Breadcrumb End -->


<!-- Cart Start -->
<div class="container-fluid">
  <div class="row px-xl-5">
    <div class="col-lg-8 table-responsive mb-5">
      <table class="table table-light table-borderless table-hover text-center mb-0">
        <thead class="thead-dark">
        <tr>
          <th>Product id</th>
          <th>Product Name</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Total</th>
          <th>Remove</th>
        </tr>
        </thead>
        <tbody class="align-middle">
        <% int counter = 0;double cartTotal = 0.0; double total = 0.0; double shipping = 0.0;
          for(Cart cart: cartList) {%>

        <tr>
          <td class="align-middle"><%=cart.getProduct_id()%></td>
          <td class="align-middle"><img src="img/<%=cart.getImage()%>" alt="" style="width: 50px;"> <%=cart.getName()%></td>
          <td class="align-middle"><%=cart.getPrice()%></td>
            <%cartTotal = cart.getPrice()*cart.getQuantity();total += cartTotal; shipping = total*0.01;%>
            <form id="myForm<%=counter%>" action="updateCartQuantity" method="post" class="form-inline input-group quantity mx-auto" style="width: 100px;">
              <td class="align-middle">
              <input type="hidden" name="customer_id" value="<%=session.getAttribute("customer_id")%>" class="form-input">
              <input type="hidden" name="product_id" value="<%=cart.getProduct_id()%>" class="form-input">
              <input type="hidden" name="submit_type" id="submit_type<%=counter%>" value="" class="form-input">
                <div class="input-group input-group-sm">
                  <div class="input-group-prepend">
                    <button type="button" class="btn btn-sm btn-primary btn-minus" onclick="setSubmitType('minus', '<%=counter%>'); submitForm('<%=counter%>');">
                      <i class="fa fa-minus"></i>
                    </button>
                  </div>
                  <span type="text" class="form-control form-control-sm bg-secondary2 border-0 text-center"><%=cart.getQuantity()%></span>
                  <div class="input-group-append">
                    <button type="button" class="btn btn-sm btn-primary btn-plus" onclick="setSubmitType('plus', '<%=counter%>'); submitForm('<%=counter%>');">
                      <i class="fa fa-plus"></i>
                    </button>
                  </div>
                </div>
              </td>
              <td class="align-middle"><%=cartTotal%></td>
              <td class="align-middle">
                <button class="btn btn-sm btn-danger" onclick="setSubmitType('delete', '<%=counter%>'); submitForm('<%=counter%>');" >
                  <i class="fa fa-times"></i>
                </button>
              </td>
            </form>

            <script>
              function setSubmitType(type, formId) {
                document.getElementById("submit_type"+formId).value = type;
              }

              function submitForm(formId) {
                var form = document.getElementById("myForm"+formId);
                form.method = "post";
                form.submit();
              }
            </script>
        </tr>
        <% counter++;
        } %>
        </tbody>
      </table>
    </div>
    <div class="col-lg-4">
      <h5 class="section-title position-relative text-uppercase mb-3" style="color: black"><span class="bg-secondary pr-3">Cart Summary</span></h5>
      <div class="bg-light p-30 mb-5">
        <div class="border-bottom pb-2">
          <div class="d-flex justify-content-between mb-3">
            <h6>Subtotal</h6>
            <h6><%=total%></h6>
          </div>
          <div class="d-flex justify-content-between">
            <h6 class="font-weight-medium">Shipping</h6>
            <h6 class="font-weight-medium"><%=shipping%></h6>
          </div>
        </div>
        <div class="pt-2">
          <div class="d-flex justify-content-between mt-2">
            <h5>Total</h5>
            <h5><%=total+shipping%></h5>
          </div>
          <form method="post" action="Checkout">
            <input type="hidden" name="customer_id" value="<%=session.getAttribute("customer_id")%>">
            <button type="submit" class="btn btn-block btn-primary font-weight-bold my-3 py-3">Proceed To Checkout</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Cart End -->


<!-- Footer Start -->
<div class="container-fluid bg-dark text-secondary mt-5 pt-5">
  <div class="row px-xl-5 pt-5">
    <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
      <h5 class="text-secondary text-uppercase mb-4">Get In Touch</h5>
      <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Decagon Institute</p>
      <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>osazuwa.omosigho@decagon.dev</p>
      <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+234 809 572 7920</p>
    </div>
    <div class="col-lg-8 col-md-12">
      <div class="row">
        <div class="col-md-4 mb-5">
          <h5 class="text-secondary text-uppercase mb-4">Quick Shop</h5>
          <div class="d-flex flex-column justify-content-start">
            <a class="text-secondary mb-2" href="index.jsp"><i class="fa fa-angle-right mr-2"></i>Home</a>
            <a class="text-secondary mb-2" href="Product"><i class="fa fa-angle-right mr-2"></i>Product</a>

          </div>
        </div>
        <div class="col-md-4 mb-5">
          <h5 class="text-secondary text-uppercase mb-4">My Account</h5>
          <div class="d-flex flex-column justify-content-start">
            <% if (session.getAttribute("logged_in") != null) { %>
            <a href="cart"  class="text-secondary mb-2">
              <i class="fa fa-angle-right mr-2">Cart</i>
            </a>
            <a href="wishlist" class="text-secondary mb-2">
              <i class="fa fa-angle-right mr-2">Wishlist</i>
            </a>
            <a href="orders" class="text-secondary mb-2">
              <i class="fa fa-angle-right mr-2">All Orders</i>
            </a>
            <% } else { %>
            <a href="login.jsp"  class="text-secondary mb-2">
              <i class="fa fa-angle-right mr-2">Cart</i>
            </a>
            <a href="login.jsp" class="text-secondary mb-2">
              <i class="fa fa-angle-right mr-2">Wishlist</i>
            </a>
            <a href="login.jsp" class="text-secondary mb-2">
              <i class="fa fa-angle-right mr-2">All Orders</i>
            </a>
            <% } %>
          </div>
        </div>
        <div class="col-md-4 mb-5">
          <h6 class="text-secondary text-uppercase mt-4 mb-3">Follow Us</h6>
          <div class="d-flex">
            <a class="btn btn-primary btn-square mr-2"><i class="fab fa-twitter"></i></a>
            <a class="btn btn-primary btn-square mr-2"><i class="fab fa-facebook-f"></i></a>
            <a class="btn btn-primary btn-square mr-2"><i class="fab fa-linkedin-in"></i></a>
            <a class="btn btn-primary btn-square"><i class="fab fa-instagram"></i></a>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="row border-top mx-xl-5 py-4" style="border-color: rgba(256, 256, 256, .1) !important;">
    <div class="col-md-6 px-xl-0">
      <p class="mb-md-0 text-center text-md-left text-secondary">
        &copy; <a class="text-primary" href="#">Domain</a>. All Rights Reserved. Designed
        by
        <a class="text-primary" href="">Osazuwa Omosigho</a>
      </p>
    </div>
    <div class="col-md-6 px-xl-0 text-center text-md-right">
      <img class="img-fluid" src="img/payments.png" alt="">
    </div>
  </div>
</div>
<!-- Footer End -->


<!-- Back to Top -->
<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Contact Javascript File -->
<script src="mail/jqBootstrapValidation.min.js"></script>
<script src="mail/contact.js"></script>
<%--<!-- Template Javascript -->--%>
<script src="js/main.js"></script>
</body>

</html>