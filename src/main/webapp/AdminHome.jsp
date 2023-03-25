<%@ page import="com.example.week6ecommerce.model.Product" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Jhay Commerce</title>
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
<!-- Topbar Start -->
<div class="container-fluid">
    <div class="row bg-secondary py-1 px-xl-5">
        <div class="col-lg-6 text-center text-lg-right">
            <div class="d-inline-flex align-items-right">
                <div class="btn-group">
                    <a href="Logout">
                        <button type="button" class="nav-link">Logout</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="row align-items-center justify-content-center bg-light py-3 px-xl-5 d-none d-lg-flex">
        <a href="AdminHome.jsp" class="text-decoration-none">
            <span class="h1 text-uppercase text-primary bg-dark px-2">Jhay</span>
            <span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Commerce</span>
            <span class="h1 text-uppercase text-primary bg-dark px-2">Admin</span>
        </a>
    </div>
</div>
<!-- Topbar End -->


<!-- Navbar Start -->
<div class="container-fluid bg-dark mb-30">
    <div class="row px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a class="btn d-flex align-items-center justify-content-between bg-primary w-100" data-toggle="collapse" style="height: 65px; padding: 0 30px;">
                <h5 class="text-dark m-0"><%= session.getAttribute("firstName")%></h5>
            </a>
        </div>
        <div class="col-lg-9">
            <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-0">
                <a href="AdminHome.jsp" class="text-decoration-none d-block d-lg-none">
                    <span class="h1 text-uppercase text-dark bg-light px-2">Jhay</span>
                    <span class="h1 text-uppercase text-light bg-primary px-2 ml-n1">Commerce</span>
                    <span class="h1 text-uppercase text-dark bg-light px-2">Admin</span>
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav mr-auto py-0">
                        <a href="index.jsp" class="nav-item nav-link">Home</a>
                        <a href="AdminHome.jsp" class="nav-item nav-link">Admin Home</a>
                        <form method="get" action="AdminProduct" class="form-inline">
                            <input type="hidden" name="prevPage" value="AdminProduct.jsp">
                            <button type="submit" class="btn px-0">
                                <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">Products</span>
                            </button>
                        </form>
                    </div>
                    <div class="navbar-nav ml-auto py-0 d-none d-lg-flex align-items-center">
                        <a href="addProduct.jsp" class="btn px-0">
                            <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">Add Product</span>
                        </a>
                        <form method="get" action="AdminProduct" class="form-inline">
                            <input type="hidden" name="prevPage" value="updateProduct.jsp">
                            <button type="submit" class="btn px-0 ml-3">
                                <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">Update Product</span>
                            </button>
                        </form>
                        <form method="get" action="AdminProduct" class="form-inline">
                            <input type="hidden" name="prevPage" value="deleteProduct.jsp">
                            <button type="submit" class="btn px-0 ml-3">
                                <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">Delete Product</span>
                            </button>
                        </form>
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
                <h5 class="font-weight-semi-bold m-0">Add Product</h5>
            </nav>
        </div>
    </div>
    <div class="row px-xl-5">
        <div class="col-12">
            <table class="table table-light table-borderless table-hover text-center mb-0">
                <thead class="thead-dark">
                <tr>
                    <th>Product Name</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Image</th>
                    <th>Add Product</th>
                </tr>
                </thead>
                <tbody>
                <form method="post" action="AddProduct">
                    <tr>
                        <td>
                            <input type="text" class="form-control form-control-sm bg-secondary2 border-0 text-center" name="name" placeholder="Product Name">
                        </td>
                        <td>
                            <input type="number" class="form-control form-control-sm bg-secondary2 border-0 text-center" name="category_id" placeholder="Category">
                        </td>
                        <td>
                            <input type="number" class="form-control form-control-sm bg-secondary2 border-0 text-center" name="price" placeholder="Price">
                        </td>
                        <td>
                            <input type="number" class="form-control form-control-sm bg-secondary2 border-0 text-center" name="quantity" placeholder="Quantity">
                        </td>
                        <td>
                            <input type="text" class="form-control form-control-sm bg-secondary2 border-0 text-center" name="image" placeholder="Image">
                            <input type="hidden" name="prevPage" value="AdminHome.jsp">
                        </td>
                        <td>
                            <button type="submit" class="btn btn-sm btn-primary">Add Product</button>
                        </td>
                    </tr>
                </form>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row px-xl-5">
        <div class="col-12">
            <nav class="breadcrumb align-items-center bg-light mb-30">
                <h5 class="font-weight-semi-bold m-0">Search Product</h5>
            </nav>
        </div>
    </div>
    <div class="row px-xl-5">
        <div class="col-12">
            <form method="get" action="SearchProduct" class="form-inline">
                <div class="input-group">
                    <input type="number" name="product_id" placeholder="Enter Product ID" class="form-control">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-primary">
                            <i class="fa fa-search"></i> Search
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->

<!-- Breadcrumb Start -->
<%List<Product> product = (List<Product>) request.getAttribute("product");%>
<%if(product !=  null){%>
<div class="container-fluid my-5">
    <div class="row px-xl-5">
        <div class="col-12">
            <table class="table table-light table-borderless table-hover text-center mb-0">
                <thead class="thead-dark">
                <tr>
                    <th>Product id</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Update Price</th>
                    <th>Quantity</th>
                    <th>Update Quantity</th>
                    <th>Delete Product</th>
                </tr>
                </thead>
                <tbody class="align-middle">
                <tr>
                    <td class="align-middle"><%=product.get(0).getId()%></td>
                    <td class="align-middle"><img src="img/<%=product.get(0).getImage()%>" alt="" style="width: 50px;"> <%=product.get(0).getName()%></td>
                    <td class="align-middle"><%=product.get(0).getPrice()%></td>
                    <form action="UpdateProduct" method="post">
                        <td class="align-middle">
                            <input type="hidden" name="product_id" value="<%=product.get(0).getId()%>">
                            <input type="hidden" name="quantity" value="0">
                            <input type="hidden" name="prevPage" value="AdminHome.jsp">
                            <input type="number" name="price" placeholder="Enter Price" class="form-control form-control-sm bg-secondary2 border-0 text-center">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-sm btn-primary">Update Price</button>
                            </div>
                        </td>
                    </form>
                    <td class="align-middle"><%=product.get(0).getQuantity()%></td>
                    <form action="UpdateProduct" method="post">
                        <td class="align-middle">
                            <input type="hidden" name="product_id" value="<%=product.get(0).getId()%>">
                            <input type="hidden" name="price" value="0.0">
                            <input type="hidden" name="prevPage" value="AdminHome.jsp">
                            <input type="number" name="quantity" placeholder="Enter Quantity" class="form-control form-control-sm bg-secondary2 border-0 text-center">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-sm btn-primary">Update Quantity</button>
                            </div>
                        </td>
                    </form>
                    <form action="DeleteProduct" method="post">
                        <td class="align-middle">
                            <input type="hidden" name="product_id" value="<%=product.get(0).getId()%>">
                            <input type="hidden" name="prevPage" value="AdminHome.jsp">
                            <button type="submit" class="btn btn-sm btn-danger">
                                <i class="fa fa-times"></i>
                            </button>
                        </td>
                    </form>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%}%>
<!-- Products End -->

<!-- Footer Start -->
<div class="container-fluid bg-dark text-secondary mt-5 pt-5">
    <div class="row px-xl-5 pt-5">
        <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
            <h5 class="text-secondary text-uppercase mb-4">Get In Touch</h5>
            <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Decagon Institute</p>
            <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>osazuwa.omosigho@decagon.dev</p>
            <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+234 809 572 7920</p>
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