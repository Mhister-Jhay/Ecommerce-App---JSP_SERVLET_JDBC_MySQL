<!DOCTYPE html>
<html lang="en" >
<head>
	<meta charset="UTF-8">
	<title>Jhay Commerce</title>
	<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.2.0/css/all.css'>
	<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.2.0/css/fontawesome.css'>
	<link rel="stylesheet" href="css/registration.css">

</head>
<body>
<div class="col-lg-4 align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex" style= "width: 400px; margin: 50px auto;">
	<a href="" class="text-decoration-none">
		<span class="h1 text-uppercase text-primary bg-dark px-2">Jhay</span>
		<span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Commerce</span>
	</a>
</div>
	<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">

	<div class="main">
		<!-- Sign up form -->
		<section class="signup">
				<div class="screen">
					<div class="screen__content">

						<form method="post" action="register" class="register-form" id="register-form">
							<h2 class="form-title">Create New Account</h2>
							<div class="signup">
								<span class="signup__text">Already have an account?</span>
								<a href="login.jsp" class="button signup__button">
									<span class="button__text">Login</span>
									<i class="button__icon fas fa-chevron-right"></i>
								</a>
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-user"></i>
								<input type="text" class="login__input" name="first_name" id="first_name" placeholder="first name" />
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-user"></i>
								<input type="text" class="login__input" name="last_name" id="last_name" placeholder="last name" />
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-user"></i>
								<input type="text" class="login__input" name="phone_number" id="phone_number" placeholder="Phone Number" />
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-user"></i>
								<input type="email" class="login__input" name="email" id="email" placeholder="Email" />
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-lock"></i>
								<input type="password" class="login__input" name="password" id="password" placeholder="Password" />
							</div>
							<div class="form-group">
								<i class="login__icon fas fa-lock"></i>
								<input type="password" class="login__input" name="re_password" id="re_password" placeholder="Repeat password" />
							</div>
								<button class="button login__submit">
									<span class="button__text">Register</span>
									<i class="button__icon fas fa-chevron-right"></i>
								</button>
							</form>
					</div>
				</div>
		</section>

		<!-- JS -->
		<script src="js/jquery.min.js"></script>
		<script src="js/main.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
		var status = document.getElementById("status").value;
		if (status === "success") {
			Swal.fire(
					'Welcome Onboard',
					'Proceed to Login',
					'success'
			)
		}
	</script>

	</div>
</body>
</html>