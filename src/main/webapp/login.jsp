<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>Jhay Commerce | login</title>

    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.2.0/css/all.css'>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.2.0/css/fontawesome.css'>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/style.css">

</head>
<body>

<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<!-- partial:index.partial.html -->
<div class="container">
    <div class="screen__content mb-4">
        <a href="index.jsp" class="text-decoration-none">
            <span class="h1 text-uppercase text-primary bg-dark px-2 font-weight-bold">Jhay</span>
            <span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1 font-weight-bold">Commerce</span>
        </a>
    </div>
    <div class="screen">
        <div class="screen__content">
            <form method="post" action="login" class="login"
                  id="login_form">
                <div class="login_start">
                    <h2>LOGIN</h2>
                    <p>Enter email & password</p>
                </div>
                <div class="signup">
                    <span class="signup__text">Don't have an account yet?</span>
                    <a href="registration.jsp" class="button signup__button">
                        <span class="button__text">Sign Up Now</span>
                        <i class="button__icon fas fa-chevron-right"></i>
                    </a>
                </div>
                <div class="login__field">
                    <i class="login__icon fas fa-user"></i>
                    <input type="email" name="email" class="login__input" placeholder="Email">
                </div>
                <div class="login__field">
                    <i class="login__icon fas fa-lock"></i>
                    <input type="password" name="password" class="login__input" placeholder="Password">
                </div>
                <button class="button login__submit">
                    <span class="button__text">Log In Now</span>
                    <i class="button__icon fas fa-chevron-right"></i>
                </button>
            </form>
        </div>
        <div class="screen__background">
            <span class="screen__background__shape screen__background__shape4"></span>
            <span class="screen__background__shape screen__background__shape3"></span>
            <span class="screen__background__shape screen__background__shape2"></span>
            <span class="screen__background__shape screen__background__shape1"></span>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    var status = document.getElementById("status").value;
    if (status === "failed") {
        Swal.fire(
            'Login Failed',
            'Wrong email or Password',
            'error'
        )
    }
</script>


</body>
</html>
